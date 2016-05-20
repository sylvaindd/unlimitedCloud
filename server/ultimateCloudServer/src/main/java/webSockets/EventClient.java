package webSockets;

/**
 * Created by Sylvain on 13/05/2016.
 */


import org.json.JSONObject;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.Future;
@ClientEndpoint
public class EventClient
{
    public static void main(String[] args)
    {
        URI uri = URI.create("ws://localhost:8080/lebonnuage/socket");

        EventClient client = new EventClient(uri);
        try
        {
                client.addMessageHandler(new EventClient.MessageHandler() {
                    public void handleMessage(String message) {
                        System.out.println(message);
                    }
                });
                // Send a message
                JSONObject auth = new JSONObject();
                auth.put("token","tokentest");
               client.sendMessage(auth.toString());
                JSONObject fct = new JSONObject();
                fct.put("function","getFilesFolders");
                fct.put("path","");
               client.sendMessage(fct.toString());
               /* fct.put("function","mkdir");
                fct.put("fileName","testtttt");
                client.sendMessage(fct.toString());*/
               /* fct.put("function","getFilesFolders");
                fct.put("path","");
                client.sendMessage(fct.toString());*/
              //  Thread.sleep(10000);
                while (true){
                    Thread.sleep(100);
                    Thread.yield();
                }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    Session userSession = null;
    private MessageHandler messageHandler;

    public EventClient(URI endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Callback hook for Connection open events.
     *
     * @param userSession the userSession which is opened.
     */
    @OnOpen
    public void onOpen(Session userSession) {
        System.out.println("opening websocket");
        this.userSession = userSession;
    }

    /**
     * Callback hook for Connection close events.
     *
     * @param userSession the userSession which is getting closed.
     * @param reason the reason for connection close
     */
    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        System.out.println("closing websocket");
        this.userSession = null;
    }

    /**
     * Callback hook for Message Events. This method will be invoked when a client send a message.
     *
     * @param message The text message
     */
    @OnMessage
    public void onMessage(String message) {
        if (this.messageHandler != null) {
            this.messageHandler.handleMessage(message);
        }
    }

    /**
     * register message handler
     *
     * @param msgHandler
     */
    public void addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
    }

    /**
     * Send a message.
     *
     * @param message
     */
    public void sendMessage(String message) {

            this.userSession.getAsyncRemote().sendText(message);

    }

    /**
     * Message handler.
     *
     * @author Jiji_Sasidharan
     */
    public static interface MessageHandler {

        public void handleMessage(String message);
    }
}