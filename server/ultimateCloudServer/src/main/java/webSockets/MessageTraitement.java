package webSockets;

import org.json.JSONObject;

import javax.json.Json;
import javax.json.JsonObject;

/**
 * Created by thoma on 12/05/2016.
 */
public class MessageTraitement {

    public static void messageTraitement(Message message){
        JSONObject messageJson = message.getJson();
    }
}
