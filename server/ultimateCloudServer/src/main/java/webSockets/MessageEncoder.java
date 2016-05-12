package webSockets;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Created by thoma on 12/05/2016.
 */

public class MessageEncoder implements Encoder.Text<Message> {


    public String encode(Message message) throws EncodeException {
        return message.getJson().toString();
    }
    public void init(EndpointConfig config) {
        System.out.println("Init");
    }

    public void destroy() {
        System.out.println("destroy");
    }

}