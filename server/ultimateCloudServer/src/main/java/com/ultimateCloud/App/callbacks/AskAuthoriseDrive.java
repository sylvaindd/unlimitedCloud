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
        String tokenUltimateCloud = req.getParameter("token_ultimate_cloud");
        GoogleDrive googledrive = new GoogleDrive();
        System.out.println("reponseaskauthorise"+googledrive.getAuth(tokenUltimateCloud));
        resp.getWriter().write(googledrive.getAuth(tokenUltimateCloud));
        resp.getWriter().flush();
        resp.getWriter().close();

    }
}
