package com.ultimateCloud.App.cloudServices;

import com.ultimateCloud.App.cloudServices.Dropbox.listFileJson;
import com.ultimateCloud.App.interfaces.CloudServiceInterface;
import com.ultimateCloud.App.models.FileCloud;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thoma on 10/05/2016.
 */
public class GoogleDrive implements CloudServiceInterface {

    private final boolean DEBUG = true;
    private WebTarget webTarget;

    public GoogleDrive() {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        if (DEBUG)
            client.register(new LoggingFilter());
        webTarget = client.target(getBaseURI());
    }

    public String getBaseURI() {
        return "https://www.googleapis.com/drive/v2/files/root/";
    }

    public List<FileCloud> getFileList() {
        List<FileCloud> result = new ArrayList<FileCloud>();
        String response = webTarget.
                path("files/list_folder").
                request().
                header(HttpHeaders.AUTHORIZATION,"Bearer ya29.CjHlAnacF9iV7GyfQV8XV3bhww2GIUSs7RTQQGYfsmyFJUY_yXu68VdW-L6yZg8qst_W").
                header(HttpHeaders.CONTENT_TYPE,"application/json").
                accept(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(new listFileJson())).readEntity(String.class);
        if(DEBUG)
            System.out.println(response);
        return result;
    }

    public String getAuth(){
        //TODO
        return "";
    }
}
