package com.ultimateCloud.App.callbacks;

import com.ultimateCloud.App.cloudServices.Dropbox.Dropbox;
import com.ultimateCloud.App.cloudServices.Dropbox.tokenJson;
import org.eclipse.jetty.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by thoma on 19/05/2016.
 */
public class Callbackdropboxauthorise extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException

    {
        resp.setStatus(HttpStatus.OK_200);
        System.out.println("lol1");
        String ourToken = req.getParameter("state");
        String code = req.getParameter("code");
        //store code in BDD
        //TODO
        //and asktoken
        Dropbox dropbox = new Dropbox();
        tokenJson tokenJson = new tokenJson(code, "authorization_code", dropbox.app_key, dropbox.secret_key, "http://localhost:8080/lebonnuage/callbackdropboxtoken");
        dropbox.getToken(tokenJson);
    }
}
