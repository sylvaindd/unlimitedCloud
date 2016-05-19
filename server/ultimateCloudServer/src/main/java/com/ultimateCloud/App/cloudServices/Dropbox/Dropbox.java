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
public class Dropbox implements CloudServiceInterface {
    private final boolean DEBUG =true;
    private WebTarget webTarget;
    public Dropbox(){
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        if(DEBUG)
        client.register(new LoggingFilter());
        webTarget = client.target(getBaseURI());




    }
    public String getBaseURI() {
        return "https://api.dropboxapi.com/2/";
    }

    public JsonObject getAuth(){
        //TODO
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
