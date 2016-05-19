package com.ultimateCloud.App.callbacks;

import org.eclipse.jetty.http.HttpStatus;
import org.json.HTTP;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Sylvain on 17/05/2016.
 */
public class MonNuage extends HttpServlet {

    public static final String REGISTER = "/lebonnuage/register";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpStatus.OK_200);

        System.out.println(req.getRequestURI());
        String code = req.getParameter("username");
        String user_id = req.getParameter("password");

        JSONObject jsonObject = new JSONObject();
        jsonObject.append("succes", "true");
        try {
            jsonObject.write(resp.getWriter());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
            e.printStackTrace();
        }

        switch (req.getRequestURI()) {
            case REGISTER:
                register(jb, resp);
                break;
            default:
                resp.setStatus(HttpStatus.NOT_FOUND_404);
                resp.addHeader("Access-Control-Allow-Origin", "*");
        }

    }

    private void register(StringBuffer jb, HttpServletResponse resp) {


        JSONObject jsonObject = null;
        try {
            jsonObject = HTTP.toJSONObject(jb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }



        JSONObject postData = new JSONObject(jsonObject.getString("Method"));

        System.out.println(postData);

        String username = postData.getString("username");
        String mail = postData.getString("mail");
        String password = postData.getString("password");

        JSONObject jsonObjectRet = new JSONObject();
        jsonObjectRet.append("succes", "true");

        resp.setStatus(HttpStatus.OK_200);
        resp.addHeader("Access-Control-Allow-Origin", "*");

        try {
            jsonObjectRet.write(resp.getWriter());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
