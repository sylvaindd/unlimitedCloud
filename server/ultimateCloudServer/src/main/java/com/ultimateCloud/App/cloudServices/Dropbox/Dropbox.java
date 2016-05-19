package com.ultimateCloud.App.cloudServices.Dropbox;

import com.ultimateCloud.App.interfaces.CloudServiceInterface;
import com.ultimateCloud.App.models.FileCloud;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
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

    public String getAuth(){

        String response = "<!DOCTYPE html><html> <body><script>window.location = \"https://www.dropbox.com/1/oauth2/authorize?client_id=tmg1y9xyh1c3j05&response_type=code&state=notretoekn&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Flebonnuage%2Fcallbackdropboxauthorise\"; </script></body></html>";
        return response;
    }

    public JsonObject getToken(tokenJson tokenJson){
        webTargetMain = client.target("");

        String response = webTargetMain.
                path("https://api.dropboxapi.com/oauth2/token").
                request().
                header(HttpHeaders.CONTENT_TYPE,"application/json").
                accept(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(tokenJson)).readEntity(String.class);
        if(DEBUG)
            System.out.println(response);
        webTargetMain = client.target(baseUri);

        return null;
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

    public List<FileCloud> getFileList(listFileJson listFileJson){
        String response = webTargetMain.
                path("files/list_folder").
                request().
                header(HttpHeaders.AUTHORIZATION,"Bearer GBre0x3PlHAAAAAAAAAACWPyyJYxh2ljcf3dttoT5tUKfLwv8slxe0payqYYswjL").
                header(HttpHeaders.CONTENT_TYPE,"application/json").
                accept(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(listFileJson)).readEntity(String.class);
        if(DEBUG)
            System.out.println(response);
        return null;
    }

}
