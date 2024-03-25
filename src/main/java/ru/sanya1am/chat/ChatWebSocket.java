package ru.sanya1am.chat;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;

@WebSocket
public class ChatWebSocket {
    private Session session;

    @OnWebSocketConnect
    public void onConnect(Session session) {
        this.session = session;
    }

    @OnWebSocketMessage
    public void onMessage(String message) {
        try {
            session.getRemote().sendString(message); // Отправка сообщения
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}