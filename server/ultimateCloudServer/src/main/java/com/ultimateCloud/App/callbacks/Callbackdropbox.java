package com.ultimateCloud.App.callbacks;

import com.ultimateCloud.App.cloudServices.Dropbox.Dropbox;
import com.ultimateCloud.App.jdbc.JDBCMysSQL;
import org.eclipse.jetty.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            String user_id = req.getParameter("user_id");
            if(code !=null && code!=""){
            //onrécupère le code
            //store code in BDD
            JDBCMysSQL.getInstance().addDropBoxCodeToOurAccount(code,user_id,ourToken);
            //and asktoken
            System.out.println("code"+code);
            Dropbox dropbox = new Dropbox();
            dropbox.getToken(code,ourToken);

        }

    }
}
