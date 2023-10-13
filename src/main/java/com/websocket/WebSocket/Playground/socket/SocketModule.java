package com.websocket.WebSocket.Playground.socket;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.websocket.WebSocket.Playground.builders.TopicBuilderImpl;
import com.websocket.WebSocket.Playground.enums.TopicNames;
import com.websocket.WebSocket.Playground.models.Message;
import com.websocket.WebSocket.Playground.topics.MessageTopic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class SocketModule {

    Map<String, UUID> participants = new HashMap<>();

    public SocketModule(SocketIOServer socketIOServer, TopicBuilderImpl topicBuilder){
        var message = topicBuilder.getMessageTopic();
        socketIOServer.addConnectListener(onConnected());
        socketIOServer.addDisconnectListener(onDisconnected());
        socketIOServer.addEventListener(message.getSendMessageTopicName().getType(),
                Message.class,
                message.onMessageReceived(participants));
    }

    private DisconnectListener onDisconnected() {
        return client -> {
            var clientId = client.getHandshakeData().getHttpHeaders().get("client_id");
            if (participants.get(clientId) != null){
                participants.remove(clientId);
            }
            log.info(String.format("SessionID : %s connected", client.getSessionId()));
        };
    }

    private ConnectListener onConnected() {
        return client -> {
            var clientId = client.getHandshakeData().getHttpHeaders().get("client_id");
            var sessionId = client.getSessionId();
            participants.put(clientId, sessionId);
            log.info(String.format("SessionID : %s disconnected", sessionId));
        };
    }
}
