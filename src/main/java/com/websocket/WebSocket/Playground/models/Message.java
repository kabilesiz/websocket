package com.websocket.WebSocket.Playground.models;

import lombok.Data;

@Data
public class Message {
    private String from;
    private String  to;
    private String content;
}
