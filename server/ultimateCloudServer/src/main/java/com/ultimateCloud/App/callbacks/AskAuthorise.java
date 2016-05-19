package com.ultimateCloud.App.callbacks;

import com.ultimateCloud.App.cloudServices.Dropbox.Dropbox;
import org.eclipse.jetty.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by thoma on 19/05/2016.
 */
public class AskAuthorise extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException

    {
        resp.setStatus(HttpStatus.OK_200);

        Dropbox dropbox = new Dropbox();
        System.out.println("reponseaskauthorise"+dropbox.getAuth());
        String rst = dropbox.getAuth();
        rst.replace("<form action=\"/","<form action=\"https://www.dropbox.com/oauth2/authorize/");
        resp.getWriter().write(dropbox.getAuth());
        resp.getWriter().flush();
        resp.getWriter().close();

    }
}
