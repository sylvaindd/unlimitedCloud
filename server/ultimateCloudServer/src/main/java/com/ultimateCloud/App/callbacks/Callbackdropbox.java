package com.ultimateCloud.App.callbacks;

import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.ultimateCloud.App.cloudServices.Dropbox.Dropbox;
import org.eclipse.jetty.http.HttpStatus;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;

/**
 * Created by thoma on 19/05/2016.
 */
public class Callbackdropbox extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException

    {
        resp.setStatus(HttpStatus.OK_200);
        String ourToken = req.getParameter("state");
            String code = req.getParameter("code");
        if(code !=null && code!=""){
            //onrécupère le code
            //store code in BDD
            //TODO
            //and asktoken
            System.out.println("code"+code);
            Dropbox dropbox = new Dropbox();

            MultivaluedMap formData = new MultivaluedMapImpl();
            formData.add("code", code);
            formData.add("grant_type", "authorization_code");
            formData.add("client_id", dropbox.app_key);
            formData.add("client_secret",  dropbox.secret_key);
            formData.add("redirect_uri",  "http://localhost:8080/lebonnuage/callbackdropboxauthorise");

            JSONObject obj = new JSONObject(dropbox.getToken(formData));
            String token =   obj.getString("access_token");
            String user_dropbox_id =   obj.getString("uid");
           System.out.println("user_dropbox_id:"+user_dropbox_id+"token :"+token);
            //store token in bdd
        }

    }
}
