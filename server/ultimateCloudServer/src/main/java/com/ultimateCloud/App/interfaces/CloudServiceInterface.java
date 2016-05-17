package com.ultimateCloud.App.interfaces;

import com.ultimateCloud.App.models.FileCloud;

import java.net.URI;
import java.util.List;

/**
 * Created by thoma on 10/05/2016.
 */
public interface CloudServiceInterface {
    public String getBaseURI();
    public List<FileCloud> getFileList();
    public String getAuth();
}
