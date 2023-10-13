package com.websocket.WebSocket.Playground.builders;

import com.websocket.WebSocket.Playground.enums.TopicNames;
import com.websocket.WebSocket.Playground.models.Message;
import com.websocket.WebSocket.Playground.topics.MessageTopic;

public class TopicBuilderImpl implements TopicBuilder{
    private MessageTopic messageTopic = null;
    @Override
    public MessageTopic getMessageTopic() {
        if (messageTopic == null){
            messageTopic = MessageTopic
                    .create(TopicNames.SendMessageTopicName,
                            TopicNames.GetMessageTopicName,
                            Message.class)
                    .build();
        }
        return messageTopic;
    }
}
