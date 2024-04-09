package com.ruoyi.common.websocket.config;

import com.ruoyi.common.websocket.server.WebSocketServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.annotation.Resource;

/**
 * 开启WebSocket支持
 */
@Configuration
@EnableScheduling
public class WebSocketConfig {
    @Resource
    private WebSocketServer webSocketServer;

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    /**
     * 定时发送心跳
     */
    @Scheduled(fixedRate = 10 * 1000)
    private void configureTasks()  {
        webSocketServer.sendHeartbeat();
    }
}
