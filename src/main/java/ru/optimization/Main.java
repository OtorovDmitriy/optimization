package ru.optimization;

import com.thoughtworks.xstream.XStream;

public class Main {
    public static void main(String[] args) {
        Message messageObj = new Message(1, 3, "Hello world", true);
        XStream xStream = new XStream();
        String xml = xStream.toXML(messageObj);

        Message newMessageXStream = (Message) xStream.fromXML(xml);
    }
}
