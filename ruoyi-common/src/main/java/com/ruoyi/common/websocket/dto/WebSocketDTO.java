package com.ruoyi.common.websocket.dto;

import lombok.Data;

/**
 * websocket 基本数据类型
 */
@Data
public class WebSocketDTO {
    private String  userId; // 用户id
    private String  sessionId; // session id
    private String  msgType; // 消息类型
    private String  deviceId; //设备id
    private String  message; //消息
    private Object  data; //数据对象


}
