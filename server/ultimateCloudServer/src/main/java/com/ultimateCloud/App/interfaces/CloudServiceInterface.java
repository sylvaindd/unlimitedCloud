package com.ultimateCloud.App.interfaces;

import com.ultimateCloud.App.models.FileCloud;

import javax.json.JsonObject;
import java.net.URI;
import java.util.List;

/**
 * Created by thoma on 10/05/2016.
 */
public interface CloudServiceInterface {
    public String getBaseURI();
    public List<FileCloud> getFileList();
    public JsonObject getFileInformations(String path);
    public JsonObject mkdir(String folder);
    public JsonObject rmdir(String folder);
    public JsonObject rm(String file);
    public JsonObject getAuth();
}
