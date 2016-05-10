package com.ultimateCloud.App.cloudServices.Dropbox;

import com.ultimateCloud.App.interfaces.CloudServiceInterface;

import javax.ws.rs.Path;

/**
 * Created by thoma on 10/05/2016.
 */
@Path("https://api.dropboxapi.com/2/")
public class Dropbox implements CloudServiceInterface {
    public static final String apiBaseUrl = "https://api.dropboxapi.com/2/files/list_folder";

    public void getFileList() {

    }
}
