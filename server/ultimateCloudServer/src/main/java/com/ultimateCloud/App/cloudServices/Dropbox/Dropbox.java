package com.ultimateCloud.App.cloudServices.Dropbox;

import com.sun.jersey.core.util.MultivaluedMapImpl;
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
import java.util.List;

/**
 * Created by thoma on 10/05/2016.
 */
public class Dropbox extends CloudServiceInterface {
    private final static boolean DEBUG =true;
    private WebTarget webTargetMain;
    private WebTarget webTargetoauth2;
    public final String app_key= "tmg1y9xyh1c3j05";
    public final String secret_key= "hh1uvee79hsie49";
    private   Client client;
    public Dropbox(){
        ClientConfig config = new ClientConfig();
         client = ClientBuilder.newClient(config);
        if(DEBUG)
        client.register(new LoggingFilter());
        webTargetMain = client.target(baseUri);
        webTargetoauth2 = client.target(baseUri);

    }
   private static final String baseUri= "https://api.dropboxapi.com/2/";

    public String getAuth(String tokenUltimateCloud){

        String response = "<!DOCTYPE html><html> <body><script>window.location = \"https://www.dropbox.com/1/oauth2/authorize?client_id=tmg1y9xyh1c3j05&response_type=code&state="+tokenUltimateCloud+"&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Flebonnuage%2Fcallbackdropboxauthorise\"; </script></body></html>";
        return response;
    }

    public String getToken(String code,String tokenUltimateCloud){
        webTargetMain = client.target("");
        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("code", code);
        formData.add("grant_type", "authorization_code");
        formData.add("client_id", app_key);
        formData.add("client_secret",  secret_key);
        formData.add("redirect_uri",  "http://localhost:8080/lebonnuage/callbackdropboxauthorise");

        String response = webTargetMain.
                path("https://api.dropboxapi.com/oauth2/token").
                request().
                accept(MediaType.APPLICATION_JSON_TYPE).post(Entity.form(formData)).readEntity(String.class);
        if(DEBUG)
            System.out.println(response);
        webTargetMain = client.target(baseUri);

        JSONObject obj = new JSONObject(response);
        String token = obj.getString("access_token");
        String user_dropbox_id =   obj.getString("uid");
        System.out.println("user_dropbox_id:"+user_dropbox_id+"token :"+token);
        //store token in bdd
        JDBCMysSQL.getInstance().addDropBoxTokenToOurAccount(token,user_dropbox_id,tokenUltimateCloud);
        getFileList(new listFileJson(),token);
        return response ;
    }

    public JsonObject getFileInformations(String path){
        //TODO
        return null;
    }

    public JsonObject mkdir(String folder){
        //TODO
        return null;
    }
    public JsonObject rmdir(String folder){
        //TODO
        return null;
    }

    public JsonObject rm(String file){
        //TODO
        return null;
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
        return null;
    }

}
