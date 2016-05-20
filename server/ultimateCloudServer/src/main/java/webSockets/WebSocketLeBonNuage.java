package webSockets;

import com.ultimateCloud.App.jdbc.JDBCMysSQL;
import com.ultimateCloud.App.models.User;
import org.eclipse.jetty.websocket.WebSocket;
import org.json.JSONObject;
import utils.WebSocketUtils;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

/**
 * Created by thoma on 12/05/2016.
 */
@ServerEndpoint(value = "/socket", encoders = {MessageEncoder.class}, decoders = {MessageDecoder.class})
public class WebSocketLeBonNuage {

    public static final List<Session> sessions = Collections.synchronizedList(new ArrayList<Session>());
    public static final Map<String, User> mapSessions = Collections.synchronizedMap(new HashMap<String, User>());

    /**
     * @OnOpen allows us to intercept the creation of a new session. The session
     * class allows us to send data to the user. In the method onOpen,
     * we'll let the user know that the handshake was successful.
     */
    @OnOpen
    public void onOpen(Session session) {
        System.out.println(session.getId() + " has opened a connection");
        try {
            session.getBasicRemote().sendText("Connection Established");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        sessions.add(session);
    }

    /**
     * When a user sends a message to the server, this method will intercept the
     * message and allow us to react to it. For now the message is read as a
     * String.
     */
    @OnMessage
    public void onMessage(Message message, Session session) {
        System.out.println("Message from " + session.getId() + ": " + message);
        JSONObject jsonObject = new JSONObject(message);
        if (message.getJson().get("token") != null && message.getJson().get("token").toString().length() > 0) {
            String token = message.getJson().get("token").toString();
            User user = JDBCMysSQL.getInstance().getUserFromToken(token);
            if (user != null) {
                mapSessions.put(session.getId(), user);
                sendMessageToSession(session, "Authenticated succesfully");
            } else {
                try {
                    session.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sendMessageToSession(session, "Wrong or expired Token");
            }
        } else if (mapSessions.get(session.getId()) != null) {
            // process DATA
            try {


                User user = getUserFromSession(session);
                JSONObject json = message.getJson();
                System.out.println("Message : "+json.toString());
                switch (json.getString("function")) {
                    case "getFilesFolders":
                        WebSocketUtils.getFilesAndFolders(json.getString("path"), user);
                        break;
                    case "d":
                        break;
                    default:
                        sendMessageToSession(session, new JSONObject().put("error", "erreur function").toString());
                }
            } catch (Exception e) {
                sendMessageToSession(session, new JSONObject().put("error", "erreur " + e.getMessage()).toString());
            }
        }
    }

    public static User getUserFromSession(Session session) {

        for (Map.Entry<String, User> entry : mapSessions.entrySet()) {
            if (entry.getKey().equals(session.getId())) {


                return entry.getValue();


            }
        }
        return null;
    }


    public void sendMessageToSession(Session session, String message) {

        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The user closes the connection.
     * <p>
     * Note: you can't send messages to the client from this method
     */
    @OnClose
    public void onClose(Session session) {
        System.out.println("Session " + session.getId() + " has ended");
        sessions.remove(session);
    }

    public static Session findSessionAttachedToUser(User user) {
        Session session = null;
        for (Map.Entry<String, User> entry : mapSessions.entrySet()) {
            if (entry.getValue().getToken().equals(user.getToken())) {

                for (Session s : sessions) {
                    if (s.getId().equals(entry.getValue())) {
                        return session;
                    }
                }
            }
        }
        return null;
    }

}
