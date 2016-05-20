package com.ultimateCloud.App.cloudServices.Dropbox;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by thoma on 10/05/2016.
 */
@XmlRootElement
public class listFileJson {
    public String path= "";
    public boolean recursive = true;
    public boolean include_media_info= false;
    public boolean include_deleted= false;
    public boolean include_has_explicit_shared_members= false;
}
