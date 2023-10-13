package com.websocket.WebSocket.Playground.enums;

public enum TopicNames {
    SendMessageTopicName("send_message"),
    GetMessageTopicName("get_message");

    private final String type;

    TopicNames(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}
