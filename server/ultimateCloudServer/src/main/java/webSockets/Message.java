package webSockets;

/**
 * Created by thoma on 12/05/2016.
 */
import org.json.JSONObject;

import java.io.StringWriter;
import javax.json.Json;
import javax.json.JsonObject;

public class Message {
    private JSONObject json;

    public Message(JSONObject json) {
        this.json = json;
    }

    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }

    @Override
    public String toString(){
       return json.toString();
    }

}
