package com.ultimateCloud.App.models;

/**
 * Created by thoma on 20/05/2016.
 */
public abstract class FileSystem {
    public FileSystem(String id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public String id;
    public String nom;
}
