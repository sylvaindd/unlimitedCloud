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
String tokenUltimateCloud = req.getParameter("token_ultimate_cloud");
        Dropbox dropbox = new Dropbox();
        System.out.println("reponseaskauthorise"+dropbox.getAuth(tokenUltimateCloud));
        resp.getWriter().write(dropbox.getAuth(tokenUltimateCloud));
        resp.getWriter().flush();
        resp.getWriter().close();

    }
}
