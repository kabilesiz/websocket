package com.websocket.WebSocket.Playground.topics;

import com.corundumstudio.socketio.listener.DataListener;
import com.websocket.WebSocket.Playground.enums.TopicNames;
import com.websocket.WebSocket.Playground.models.Message;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.UUID;

@Data
@Slf4j
public class MessageTopic {
    private final TopicNames sendMessageTopicName;
    private final TopicNames getMessageTopicName;
    private final Class<Message> topicData;

    private MessageTopic(Builder builder){
        this.sendMessageTopicName = builder.sendMessageTopicName;
        this.getMessageTopicName = builder.getMessageTopicName;
        this.topicData = builder.topicData;
    }

    public DataListener<Message> onMessageReceived(Map<String, UUID> participants){
        return (senderClient, data, ackRequest) -> {
            var to = participants.get(data.getTo());
            var from = participants.get(data.getFrom());
            if (to != null){
                senderClient.getNamespace().getClient(to).sendEvent(getMessageTopicName.getType(), data.getContent());
            }
            else {
                senderClient.getNamespace().getClient(from).sendEvent(getMessageTopicName.getType(),
                        "The user you want to chat is not active right now");
            }
            log.info(String.format("%s -> %s", senderClient.getSessionId(), data.getContent()));
        };
    }

    public static Builder create(TopicNames sendMessageTopicName,TopicNames getMessageTopicName, Class<Message> topicData){
        return new Builder(sendMessageTopicName, getMessageTopicName, topicData);
    }


    public static class Builder{
        private final TopicNames sendMessageTopicName;
        private final TopicNames getMessageTopicName;
        private final Class<Message> topicData;


        public Builder(TopicNames sendMessageTopicName, TopicNames getMessageTopicName, Class<Message> topicData) {
            this.sendMessageTopicName = sendMessageTopicName;
            this.getMessageTopicName = getMessageTopicName;
            this.topicData = topicData;
        }

        public MessageTopic build(){
            return new MessageTopic(this);
        }
    }
}
