package com.ultimateCloud.App.cloudServices;

import com.ultimateCloud.App.cloudServices.Dropbox.listFileJson;
import com.ultimateCloud.App.interfaces.CloudServiceInterface;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.json.simple.JSONObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thoma on 10/05/2016.
 */
public class GoogleDrive extends CloudServiceInterface {

    private static GoogleDrive instance = null;
    private final boolean DEBUG = true;
    private final String CLIENT_ID = "758933709678-agp5qsi2lougn8vbe1to772cifpd50g6.apps.googleusercontent.com";
    private final String CLIENT_SECRET = "HNIY1vOkmFjlXQsV3XrKyphc";
    private final String REDIRECT = "http://localhost:8080/lebonnuage/drive";
    private String token;
    private WebTarget webTarget;


    private GoogleDrive() {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        if (DEBUG)
            client.register(new LoggingFilter());
        webTarget = client.target(getBaseURI());
    }

    public synchronized static GoogleDrive getInstance() {
        if (instance == null) instance = new GoogleDrive();
        return instance;
    }

    public String getBaseURI() {
        return "https://www.googleapis.com/drive/v2/";
    }

    public JSONObject getAuth() {
        String url = new StringBuilder(
                "https://accounts.google.com/o/oauth2/v2/auth?response_type=code&scope=https://www.googleapis.com/auth/drive&")
                .append("client_id=").append(CLIENT_ID)
                .append("&redirect_uri=").append(REDIRECT)
                .toString();
        Map content = new HashMap<String, String>();
        content.put("url", url);
        return new JSONObject(content);
    }

    public JSONObject getFileInformations(String path) {
        //TODO
        return null;
    }

    public JSONObject mkdir(String folder) {
        //TODO
        return null;
    }

    public JSONObject rmdir(String folder) {
        //TODO
        return null;
    }

    public JSONObject rm(String file) {
        //TODO
        return null;
    }

    public String getFileList() {
        //TODO
        String response = webTarget.
                path("files/").
                request().
                header(HttpHeaders.AUTHORIZATION, "Bearer ya29.CjHlAnacF9iV7GyfQV8XV3bhww2GIUSs7RTQQGYfsmyFJUY_yXu68VdW-L6yZg8qst_W").
                header(HttpHeaders.CONTENT_TYPE, "application/json").
                accept(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(new listFileJson())).readEntity(String.class);
        if (DEBUG)
            System.out.println(response);
        return response;
    }
}
