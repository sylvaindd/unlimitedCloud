package webSockets;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by thoma on 12/05/2016.
 */
@ServerEndpoint(value="/lebonnuage" , encoders = {MessageEncoder.class}, decoders = {MessageDecoder.class})
public class WebSocketLeBonNuage {

    private static final Set sessions = Collections.synchronizedSet(new HashSet());

        /**
         * @OnOpen allows us to intercept the creation of a new session.
         * The session class allows us to send data to the user.
         * In the method onOpen, we'll let the user know that the handshake was
         * successful.
         */
        @OnOpen
        public void onOpen(Session session){
            System.out.println(session.getId() + " has opened a connection");
            try {
                session.getBasicRemote().sendText("Connection Established");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            sessions.add(session);
        }

        /**
         * When a user sends a message to the server, this method will intercept the message
         * and allow us to react to it. For now the message is read as a String.
         */
        @OnMessage
        public void onMessage(Message message, Session session){
            System.out.println("Message from " + session.getId() + ": " + message);

        }

        /**
         * The user closes the connection.
         *
         * Note: you can't send messages to the client from this method
         */
        @OnClose
        public void onClose(Session session){
            System.out.println("Session " +session.getId()+" has ended");
            sessions.remove(session);
        }

}
