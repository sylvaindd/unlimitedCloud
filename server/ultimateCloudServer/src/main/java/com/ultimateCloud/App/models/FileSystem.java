package com.ultimateCloud.App.models;

/**
 * Created by thoma on 20/05/2016.
 */
public abstract class FileSystem {
    public FileSystem(String id, String nom,String type) {
        this.id = id;
        this.nom = nom;
        this.type = type;
    }

    public String id;
    public String nom;
    public String type;


}
