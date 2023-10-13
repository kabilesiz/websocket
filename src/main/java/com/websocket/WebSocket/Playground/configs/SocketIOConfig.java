package com.websocket.WebSocket.Playground.configs;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketIOConfig {
    @Value("${socket-server.port}")
    private int port;

    @Value("${socket-server.host}")
    private String host;

    @Bean
    public SocketIOServer socketIOServer(){
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setPort(port);
        config.setHostname(host);
        config.setOrigin("*");
        return new SocketIOServer(config);
    }
}
