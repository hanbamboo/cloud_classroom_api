package com.ruoyi.common.websocket.server;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.websocket.dto.WebSocketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * websocket 服务端
 */
@Component
@ServerEndpoint(value = "/webSocket/{identity}", subprotocols = {"protocol"})
public class WebSocketServer {


    @Autowired
    private RedisCache redisCache;
    /**
     * 存放所有在线的客户端
     */
    private static final Map<String, Session> onlineSessionClientMap = new ConcurrentHashMap<>();
    private static final Map<String, JSONObject> onlineCheckinMap = new ConcurrentHashMap<>();

    private Session session;
    private String identity;


    private static final String MSG_TYPE_HEARTBEAT_PING = "heartbeat_ping";
    private static final String MSG_TYPE_HEARTBEAT_PONG = "heartbeat_pong";
//    private static final String MSG_TYPE_CHECKIN_READY = "checkin_ready";
    private static final String MSG_TYPE_CHECKIN_BROADCAST = "checkin_broadcast";
    private static final String MSG_TYPE_SYSTEM = "system";

    /**
     * 连接建立成功调用的方法
     *
     * @param identity 身份id
     * @param session  连接会话
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("identity") String identity) {
        this.identity = identity;
        this.session = session;
        onlineSessionClientMap.put(identity, session);
        // 创建消息对象
        WebSocketDTO webSocketDTO = writeMessage(identity, MSG_TYPE_SYSTEM, "success");
        this.sendMessageTo(identity, webSocketDTO);
        System.out.println("WebSocket 建立连接：" + session);
    }

    /**
     * 连接关闭调用的方法
     *
     * @param identity 身份id
     * @param session  连接会话
     */
    @OnClose
    public void onClose(Session session, @PathParam("identity") String identity) {
        onlineSessionClientMap.remove(identity);
        System.out.println("WebSocket 关闭连接：" + session + " identity:" + identity);
    }


    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 消息
     */
    @OnMessage
    public void onMessage(String message) {
        System.out.println("WebSocket 收到消息：" + message);
        JSONObject jsonObject = JSON.parseObject(message);
        if (!ObjectUtils.isEmpty(jsonObject) && !ObjectUtils.isEmpty(jsonObject.getString("msgType"))) {
            switch (jsonObject.getString("msgType")) {
                case MSG_TYPE_HEARTBEAT_PING:
                case MSG_TYPE_HEARTBEAT_PONG:
                    return;
//                case MSG_TYPE_CHECKIN_READY:
//                    onlineCheckinMap.put(jsonObject.getString("checkId"), jsonObject.getJSONObject("data"));
//                    return;
                default:
            }
        }

        broadcastMessage(message);
    }

    /**
     * 发生错误调用的方法
     *
     * @param session 会话
     * @param error   错误信息
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }


    /**
     * 单独发送消息
     *
     * @param identity 身份id
     * @param message  JSON过后的信息内容
     */
    public void sendMessageTo(String identity, Object message) {
        String msg = JSON.toJSONString(message);
        // 通过uid查询map中是否存在
        Session toSession = onlineSessionClientMap.get(identity);
        if (toSession == null) {
            return;
        }
        synchronized(toSession){
            try {
                toSession.getBasicRemote().sendText(msg);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 广播消息
     *
     * @param message JSON过后的信息内容
     */
    public void broadcastMessage(String message) {
        onlineSessionClientMap.forEach((identity, session) -> {
            if (session != null && session.isOpen()&&!identity.equals(this.identity)) {
                try {
                    synchronized(session){
                        session.getBasicRemote().sendText(message);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
//                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * 生成消息
     *
     * @param msgType 消息类型
     * @param message 信息
     * @return webSocketDTO对象
     */
    public WebSocketDTO writeMessage(String identity, String msgType, String message) {
        WebSocketDTO webSocketDTO = new WebSocketDTO();
        webSocketDTO.setUserId(identity);
        if(this.session!=null){
            webSocketDTO.setSessionId(this.session.getId());
        }
        webSocketDTO.setMsgType(msgType);
        webSocketDTO.setMessage(message);
        return webSocketDTO;
    }

    public void sendHeartbeat() {
        onlineSessionClientMap.forEach((onlineUid, session) -> {
            if (session != null && session.isOpen()) {
                synchronized(session){
                    try {
                        session.getBasicRemote().sendText(JSON.toJSONString(writeMessage(onlineUid, MSG_TYPE_HEARTBEAT_PING, "keep living")));
                    } catch (IOException e) {
//                        throw new RuntimeException(e);
                        e.printStackTrace();
                    }
                }}
        });
    }


    /**
     * 通过Id判断客户端是否在线
     *
     * @param id
     * @return
     */
    public boolean isClientOnline(String id) {
        // 通过id查询map中是否存在
        return onlineSessionClientMap.containsKey(id);
    }


    /**
     * 获取在线用户
     *
     * @return Map<String, Session> onlineSessionClientMap
     */
    public Map<String, Session> getOnlineSessionClientMap() {
        return onlineSessionClientMap;
    }

    public void addOnlineCheckinMap(String checkinId, Long courseId,Date endTime) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", new ArrayList<>());
        jsonObject.put("courseId", courseId);
        jsonObject.put("endTime", endTime);
        jsonObject.put("msgType",MSG_TYPE_CHECKIN_BROADCAST);
        onlineCheckinMap.put(checkinId, jsonObject);
    }
    public JSONObject addOnlineCheckinMember(String checkinId, Long studentId,Date checkinTime,String userName,String avatar) {
        JSONObject jsonObject = new JSONObject();
        if(!onlineCheckinMap.containsKey(checkinId)){
            jsonObject.put("result", false);
            jsonObject.put("reason","无法签到！签到时间已过！");
        }
        JSONObject onlineCheckin = onlineCheckinMap.get(checkinId);
        JSONArray list = onlineCheckin.getJSONArray("list");
        if (list.stream().anyMatch(o -> ((JSONObject) o).getLong("studentId").equals(studentId))) {
            jsonObject.put("result", false);
            jsonObject.put("reason","签到失败，请勿重复签到！");
            return jsonObject;
        }
        JSONObject checkinResult = new JSONObject();
        if(checkinTime.after(onlineCheckin.getDate("endTime"))){
            checkinResult.put("result",1); // 迟到
        }else{
            checkinResult.put("result",0); // 正常
        }
        checkinResult.put("studentId", studentId);
        checkinResult.put("studentName", userName);
        checkinResult.put("studentAvatar", avatar);
        boolean result = onlineCheckinMap.get(checkinId).getJSONArray("list").add(checkinResult);
        jsonObject.put("result", result);
        if(!result){
            jsonObject.put("reason","签到失败，请勿重复签到！");
        }else{
            jsonObject.put("reason","签到成功！");
        }
        return jsonObject;
    }
    @Scheduled(fixedRate = 1500)
    private void checkinMemberListPush() {
        onlineCheckinMap.forEach((checkinId, checkinJSON) -> {
            broadcastMessage(checkinJSON.toJSONString());
            if (redisCache.getCacheObject("checkin:" + checkinJSON.getString("courseId") + ":info")==null) {
                onlineCheckinMap.remove(checkinId);
                //签到结束
            }

        });
    }
}