package com.ultimateCloud.App.models;

/**
 * Created by thoma on 10/05/2016.
 */
public class FileCloud extends FileSystem{

    public String dateDemodification;

    public FileCloud(String id, String nom, String type, String dateDemodification, String size) {
        super(id,nom, type);
        this.dateDemodification = dateDemodification;
        this.size = size;
    }

    public String size;
}
