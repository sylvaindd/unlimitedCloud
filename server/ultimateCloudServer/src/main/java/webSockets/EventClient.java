package webSockets;

/**
 * Created by Sylvain on 13/05/2016.
 */

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.json.JSONObject;

import java.net.URI;
import java.util.concurrent.Future;

public class EventClient
{
    public static void main(String[] args)
    {
        URI uri = URI.create("ws://localhost:8080/lebonnuage/REST/");

        WebSocketClient client = new WebSocketClient();
        try
        {
            try
            {
                client.start();
                // The socket that receives events
                EventSocket socket = new EventSocket();
                // Attempt Connect
                Future<Session> fut = client.connect(socket,uri);
                // Wait for Connect
                Session session = fut.get();
                // Send a message
                JSONObject test = new JSONObject();
                test.put("function","getFilesFolders");
                test.put("token","tokentest");
                session.getRemote().sendString(test.toString());
                // Close session
                session.close();
            }
            finally
            {
                client.stop();
            }
        }
        catch (Throwable t)
        {
            t.printStackTrace(System.err);
        }
    }
}