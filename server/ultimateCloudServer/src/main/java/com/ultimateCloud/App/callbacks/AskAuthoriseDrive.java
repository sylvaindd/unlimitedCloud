package com.ultimateCloud.App.callbacks;

import com.ultimateCloud.App.cloudServices.Dropbox.Dropbox;
import com.ultimateCloud.App.cloudServices.GoogleDrive.GoogleDrive;
import org.eclipse.jetty.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by steven on 19/05/2016.
 */
public class AskAuthoriseDrive extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException

    {
        resp.setStatus(HttpStatus.OK_200);

        GoogleDrive drive = new GoogleDrive();
        System.out.println("reponseaskauthorise"+drive.getAuth());
        String rst = drive.getAuth();
        rst.replace("<form action=\"/","<form action=\"https://www.dropbox.com/oauth2/authorize/");
        resp.getWriter().write(drive.getAuth());
        resp.getWriter().flush();
        resp.getWriter().close();

    }
}
