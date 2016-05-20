package com.ultimateCloud.App.cloudServices.Dropbox;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by thoma on 10/05/2016.
 */
@XmlRootElement
public class listFileJson {
    public listFileJson(String path) {
        this.path = path;
    }

    public String path= "";
    public boolean recursive = false;
    public boolean include_media_info= false;
    public boolean include_deleted= false;
    public boolean include_has_explicit_shared_members= false;
}
