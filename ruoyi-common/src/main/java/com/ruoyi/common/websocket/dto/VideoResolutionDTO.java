package com.ruoyi.common.websocket.dto;

import lombok.Data;

/**
 * websocket 巡检设备视频分辨率数据类型
 */
@Data
public class VideoResolutionDTO {
    private String deviceId;
    //视频分辨率宽度
    private int width;
    //视频分辨率高度
    private int height;
    //分辨率描述信息
    private String desc;
    //分辨率完整信息
    private String resolution;
    // 消息类型
    private String msgType;

}
