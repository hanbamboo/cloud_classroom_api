package com.ruoyi.common.websocket.server;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.websocket.dto.VideoResolutionDTO;
import com.ruoyi.common.websocket.dto.WebSocketDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * websocket 服务端
 */
@Component
@ServerEndpoint(value = "/webSocket/{identity}", subprotocols = {"protocol"})
public class WebSocketServer {


    /**
     * 存放所有在线的客户端
     */
    private static final Map<String, Session> onlineSessionClientMap = new ConcurrentHashMap<>();


    private Session session;
    private String identity;
    private String uid;



    private static final String MSG_TYPE_HEARTBEAT_PING = "heartbeat_ping";
    private static final String MSG_TYPE_HEARTBEAT_PONG = "heartbeat_pong";
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
        // 创建消息对象
        WebSocketDTO webSocketDTO = writeMessage(MSG_TYPE_SYSTEM, "success", null);
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

    }


    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 消息
     */
    @OnMessage
    public void onMessage(String message) {
        System.out.println("WebSocket 收到消息："+ message);
        JSONObject jsonObject = JSON.parseObject(message);
        if (!ObjectUtils.isEmpty(jsonObject) && !ObjectUtils.isEmpty(jsonObject.getString("msgType"))) {
            switch (jsonObject.getString("msgType")) {
                case MSG_TYPE_HEARTBEAT_PING:
                    return;
                case MSG_TYPE_HEARTBEAT_PONG:
                    return;
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


    }


    /**
     * 广播消息
     *
     * @param message JSON过后的信息内容
     */
    public void broadcastMessage(String message) {
        if(this.identity == null){
            this.identity = JSON.parseObject(message).getString("message");
            if(this.identity == null){
                return;
            }
        }
        onlineSessionClientMap.forEach((identity, session) -> {
            // 排除掉自己
            if (session != null && session.isOpen() && !this.identity.equalsIgnoreCase(identity)) {
                session.getAsyncRemote().sendText(message);
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
    public WebSocketDTO writeMessage(String msgType, String message, Session session) {
        WebSocketDTO webSocketDTO = new WebSocketDTO();
        if (session == null) {
            webSocketDTO.setUserId(this.uid);
            webSocketDTO.setSessionId(this.session.getId());
        } else {
            webSocketDTO.setUserId("sysAdmin");
            webSocketDTO.setSessionId(session.getId());
        }
        webSocketDTO.setMsgType(msgType);
        webSocketDTO.setMessage(message);
        return webSocketDTO;
    }

    public void sendHeartbeat() {
        onlineSessionClientMap.forEach((onlineUid, session) -> {
            if (session != null && session.isOpen()) {
                session.getAsyncRemote().sendText(JSON.toJSONString(writeMessage(MSG_TYPE_HEARTBEAT_PING, "keep living", session)));
            }
        });
    }

    /**
     * 发送消息给指定客户端
     * 消息示例：
     * {
     * 	"msgType": "send_to_client",
     * 	"targetClientId": "1@9d29819d424c11ee92410242ac140002@1",
     * 	"message": {
     * 		"message": "tilted",
     * 		"msgType": "deviceStatus"
     *        }
     * }
     * @param message
     */
    private void sendMsgToClient(JSONObject message) {
        try {
             String targetClientId = message.get("targetClientId") == null ? null : message.getString("targetClientId");
            if (StringUtils.isEmpty(targetClientId)) {
                return;
            }
            Object msg = message.get("message");
            if (msg == null) {
                return;
            }
            sendMessageTo(targetClientId, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过Id判断客户端是否在线
     * @param id
     * @return
     */
    public boolean isClientOnline(String id) {
        // 通过id查询map中是否存在
        return onlineSessionClientMap.containsKey(id);
    }


    /**
     * 获取在线用户
     * @return Map<String, Session> onlineSessionClientMap
     */
    public Map<String, Session> getOnlineSessionClientMap() {
        return onlineSessionClientMap;
    }
}