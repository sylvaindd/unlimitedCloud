package com.ultimateCloud.App.cloudServices.Dropbox;

import com.ultimateCloud.App.interfaces.CloudServiceInterface;
import com.ultimateCloud.App.models.FileCloud;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

import javax.json.JsonObject;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

/**
 * Created by thoma on 10/05/2016.
 */
public class Dropbox extends CloudServiceInterface {
    private final static boolean DEBUG =true;
    private WebTarget webTarget;
    public Dropbox(){
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        if(DEBUG)
        client.register(new LoggingFilter());
        webTarget = client.target(baseUri);




    }
   private static final String baseUri= "https://api.dropboxapi.com/2/";

    public String getAuth(){

        String response = webTarget.
                path("https://www.dropbox.com/oauth2/authorize?").
                request().
                header(HttpHeaders.CONTENT_TYPE,"application/json").
                accept(MediaType.APPLICATION_JSON_TYPE).get().readEntity(String.class);
        return response;
    }

    public JsonObject getToken(tokenJson tokenJson){
        String response = webTarget.
                path("https://api.dropboxapi.com/oauth2/token").
                request().
                header(HttpHeaders.CONTENT_TYPE,"application/json").
                accept(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(tokenJson)).readEntity(String.class);
        if(DEBUG)
            System.out.println(response);
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
        String response = webTarget.
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
