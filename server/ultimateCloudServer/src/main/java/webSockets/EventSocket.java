package webSockets;

/**
 * Created by Sylvain on 13/05/2016.
 */

import com.ultimateCloud.App.cloudServices.Dropbox.Dropbox;
import com.ultimateCloud.App.cloudServices.Dropbox.listFileJson;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.json.JSONObject;

public class EventSocket extends WebSocketAdapter {
    @Override
    public void onWebSocketConnect(Session sess) {
        super.onWebSocketConnect(sess);
        System.out.println("Socket Connected: " + sess);
    }

    @Override
    public void onWebSocketText(String message) {
        super.onWebSocketText(message);
        System.out.println("Received TEXT message: " + message);

    }

    @Override
    public void onWebSocketClose(int statusCode, String reason) {
        super.onWebSocketClose(statusCode, reason);
        System.out.println("Socket Closed: [" + statusCode + "] " + reason);
    }

    @Override
    public void onWebSocketError(Throwable cause) {
        super.onWebSocketError(cause);
        cause.printStackTrace(System.err);
    }
}