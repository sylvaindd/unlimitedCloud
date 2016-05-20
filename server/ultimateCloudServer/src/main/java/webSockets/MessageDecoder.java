package webSockets;

/**
 * Created by thoma on 12/05/2016.
 */

import org.json.JSONObject;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * Decodes a client-sent string into a Message class
 */
public class MessageDecoder implements Decoder.Text<Message>{
    /**
     * Transform the input string into a Message
     */

    public Message decode(String string) throws DecodeException {
        JSONObject json = new JSONObject(string);
        return new Message(json);
    }

    /**
     * Checks whether the input can be turned into a valid Message object
     * in this case, if we can read it as a Json object, we can.
     */

    public boolean willDecode(String string) {
        try{
            Json.createReader(new StringReader(string)).read();
            return true;
        }catch (JsonException ex){
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * The following two methods are placeholders as we don't need to do anything
     * special for init or destroy.
     */

    public void init(EndpointConfig config) {
        System.out.println("init");
    }

    public void destroy() {
        System.out.println("destroy");
    }

}