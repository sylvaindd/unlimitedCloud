package com.ultimateCloud.App.callbacks;

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
public class CallbackDriveauthorise extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException

    {
        resp.setStatus(HttpStatus.OK_200);
        System.out.println("callback google");
        String ourToken = req.getParameter("state");
        String code = req.getParameter("code");
        //store code in BDD
        //TODO
        //and asktoken
        GoogleDrive drive = new GoogleDrive();
    }
}
