package com.ly.fleet_management.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class VehicleStatusHandler extends TextWebSocketHandler {


    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        session.sendMessage(new TextMessage("Hello, this is a response from the server!"));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);

    }

    public void sendVehicleStatus(String statusMessage) {
        for (WebSocketSession session : sessions) {
            try {
                session.sendMessage(new TextMessage(statusMessage));
            } catch (IOException e) {
            }
        }
    }
}
