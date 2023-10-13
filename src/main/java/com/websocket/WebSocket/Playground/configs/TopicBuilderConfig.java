package com.websocket.WebSocket.Playground.configs;

import com.websocket.WebSocket.Playground.builders.TopicBuilderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicBuilderConfig {
    @Bean
    public TopicBuilderImpl topicBuilder(){
        return new TopicBuilderImpl();
    }
}
