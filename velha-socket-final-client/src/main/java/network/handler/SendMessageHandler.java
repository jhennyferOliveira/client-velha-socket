package network.handler;

import model.Message;
import network.configuration.Client;

import static model.enumeration.MessageCode.Code.*;

public class SendMessageHandler {
    private static SendMessageHandler sendMessageHandler;

    public static synchronized SendMessageHandler getInstance() {
        if (sendMessageHandler == null) {
            sendMessageHandler = new SendMessageHandler();
        }
        return sendMessageHandler;
    }

    public void setMessageToSend(Message message) {
        switch (message.getCode()) {

            case GIVE_UP -> {
                try {
                    Client.remoteObject.sendMessage(GIVE_UP.code + message.getTextMessage());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            case PASS_TURN -> {
                try {
                    Client.remoteObject.sendMessage(PASS_TURN.code + message.getTextMessage());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            case CLOSE_WINDOW -> {
                try {
                    Client.remoteObject.sendMessage(CLOSE_WINDOW.code);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            case INIT_NUMBER -> {
                try {
                    Client.remoteObject.sendMessage(INIT_NUMBER.code + message.getTextMessage());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            case CHAT -> {
                try {
                    Client.remoteObject.sendMessage(CHAT.code + message.getTextMessage());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            case EMPTY -> {
                try {
                    Client.remoteObject.sendMessage(EMPTY.code + message.getTextMessage());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
