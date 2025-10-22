package it.unibo.cargogui.ws;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class StivaWS extends TextWebSocketHandler {
    private final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

    private String statoStivaCorrente = "{}";
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
        try {
        	session.sendMessage(new TextMessage(statoStivaCorrente));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
    }

    public void broadcast(String message) {
        synchronized (sessions) {
        	statoStivaCorrente = message;
            sessions.forEach(s -> {
                try {
                    s.sendMessage(new TextMessage(message));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
