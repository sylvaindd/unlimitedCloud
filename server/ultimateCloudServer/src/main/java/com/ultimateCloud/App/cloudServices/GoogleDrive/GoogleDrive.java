package com.ultimateCloud.App.cloudServices.GoogleDrive;

import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.ultimateCloud.App.cloudServices.Dropbox.listFileJson;
import com.ultimateCloud.App.interfaces.CloudServiceInterface;
import com.ultimateCloud.App.jdbc.JDBCMysSQL;
import com.ultimateCloud.App.jsonParser.FileSystemParser;
import com.ultimateCloud.App.models.FileCloud;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.json.JSONObject;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thoma on 10/05/2016.
 */
public class GoogleDrive extends CloudServiceInterface {

    private final boolean DEBUG = true;
    private static final String baseUri= "https://www.googleapis.com/drive/v2/";
    public static final String CLIENT_ID = "758933709678-agp5qsi2lougn8vbe1to772cifpd50g6.apps.googleusercontent.com";
    public static final String CLIENT_SECRET = "HNIY1vOkmFjlXQsV3XrKyphc";
    public static final String REDIRECT = "/lebonnuage/askDriveauthorise";
    public static final String REDIRECT_TOKEN = "/lebonnuage/callbackDriveauthorise";
    private WebTarget webTargetMain;
    private WebTarget webTargetoauth2;
    private Client client;

    public GoogleDrive() {
        ClientConfig config = new ClientConfig();
        client = ClientBuilder.newClient(config);
        if(DEBUG)
            client.register(new LoggingFilter());
        webTargetMain = client.target(baseUri);
        webTargetoauth2 = client.target(baseUri);
    }

    public String getAuth(String token) {
        String url = new StringBuilder(
                "<!DOCTYPE html><html> <body><script>window.location = \"https://accounts.google.com/o/oauth2/v2/auth?response_type=code")
                .append("&state=").append(token)
                .append("&client_id=").append(CLIENT_ID)
                .append("&redirect_uri=").append(REDIRECT)
                .append("\"; </script></body></html>")
                .toString();
        return url;
    }

    public String getToken(String code,String tokenUltimateCloud){
        webTargetMain = client.target("");
        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("code", code);
        formData.add("grant_type", "authorization_code");
        formData.add("client_id", CLIENT_ID);
        formData.add("client_secret",  CLIENT_SECRET);
        formData.add("redirect_uri",  REDIRECT_TOKEN);

        String response = webTargetMain.
                path("https://www.googleapis.com/oauth2/v4/token").
                request().
                accept(MediaType.APPLICATION_JSON_TYPE).post(Entity.form(formData)).readEntity(String.class);
        if(DEBUG)
            System.out.println(response);
        webTargetMain = client.target(baseUri);

        JSONObject obj = new JSONObject(response);
        String token = obj.getString("access_token");
//        String user_googledrive_id =   obj.getString("uid");
//        System.out.println("user_googledrive_id:"+user_googledrive_id+"token :"+token);
        //store token in bdd
//        JDBCMysSQL.getInstance().addGoogleDriveTokenToOurAccount(token,user_googledrive_id,tokenUltimateCloud);
        return response ;
    }

    public List<FileCloud> getFileList(listFileJson listFileJson, String token){
        String response = webTargetMain.
                path("files/list_folder").
                request().
                header(HttpHeaders.AUTHORIZATION,"Bearer "+token).
                header(HttpHeaders.CONTENT_TYPE,"application/json").
                accept(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(listFileJson)).readEntity(String.class);
        if(DEBUG)
            System.out.println(response);
        FileSystemParser.parse(response);
        return null; //TODO
    }

    public JsonObject getFileInformations(String path) {
        //TODO
        return null;
    }

    public JsonObject mkdir(String folder) {
        //TODO
        return null;
    }

    public JsonObject rmdir(String folder) {
        //TODO
        return null;
    }

    public JsonObject rm(String file) {
        //TODO
        return null;
    }
}
