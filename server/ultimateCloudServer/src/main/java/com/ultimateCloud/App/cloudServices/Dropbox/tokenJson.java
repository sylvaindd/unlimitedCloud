package com.ultimateCloud.App.cloudServices.Dropbox;

/**
 * Created by thoma on 17/05/2016.
 */
public class tokenJson {
    public tokenJson(String code, String grant_type, String client_id, String client_secret, String redirect_uri) {
        this.code = code;
        this.grant_type = grant_type;
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.redirect_uri = redirect_uri;
    }

    public String code, grant_type, client_id , client_secret, redirect_uri ;
}
