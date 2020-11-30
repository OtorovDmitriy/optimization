package ru.optimization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Message {
    int senderId;
    int recipientId;
    String textMessage;
    boolean isReceived;
}
