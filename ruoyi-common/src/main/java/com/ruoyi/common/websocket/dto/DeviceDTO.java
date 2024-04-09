package com.ruoyi.common.websocket.dto;

import com.alibaba.fastjson2.JSON;
import lombok.Data;

/**
 * websocket 巡检设备基本数据类型
 */
@Data
public class DeviceDTO {
    private String  userId; // 用户id
    private String  sessionId; // session id
    private String  deviceId; //设备id
    private VideoResolutionDTO  videoResolution; //设备巡检视频分辨率数据
    private String  message; //消息
    private JSON data; //数据对象


}
