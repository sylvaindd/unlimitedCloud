package com.ultimateCloud.App.callbacks;

import com.ultimateCloud.App.jdbc.JDBCMysSQL;
import org.eclipse.jetty.http.HttpStatus;
import org.json.HTTP;
import org.json.JSONObject;
import utils.Security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Sylvain on 17/05/2016.
 */
public class MonNuage extends HttpServlet {

    public static final String REGISTER = "/lebonnuage/register";
    public static final String AUTHENT = "/lebonnuage/authent";


    public static JDBCMysSQL jdbcMysSQL = JDBCMysSQL.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpStatus.OK_200);

        System.out.println(req.getRequestURI());

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
            case AUTHENT:
                authent(jb, resp);
                break;
            default:
                resp.setStatus(HttpStatus.NOT_FOUND_404);
                resp.addHeader("Access-Control-Allow-Origin", "*");
        }
        resp.addHeader("Content-Type", "application/json; charset=UTF-8");

    }

    private void authent(StringBuffer jb, HttpServletResponse resp) {
        JSONObject postData = null;
        try {
            postData = new JSONObject(jb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println(postData);

        String mailOrUsername = postData.getString("mailOrUsername");
        String password = postData.getString("password");
        Statement statement = null;
        String req = "SELECT * FROM users WHERE username = '" + mailOrUsername + "' OR mail = '" + mailOrUsername + "'";
        boolean erreur = false;
        String message = "";
        String token = "";
        try {
            statement = jdbcMysSQL.getConn().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            int nbRows = 0;
            if (statement.execute(req)) {
                ResultSet resultSet = statement.getResultSet();
                resultSet.last();
                nbRows = resultSet.getRow();
                resultSet.beforeFirst();
                if (nbRows > 0) {
                    resultSet.next();
                    if (resultSet.getString("password").equals(password)) {
                        erreur = false;
                        token = Security.generateToken();

                        String req2 = "UPDATE users SET tokenLeBonNuage = '"+token+"' WHERE id = '"+resultSet.getInt("id")+"';";
                        if(statement.executeUpdate(req2) > 0){
                        }else{
                            token = resultSet.getString("tokenLeBonNuage");
                        }
                    } else {
                        erreur = true;
                        message = "Mot de passe incorrect";
                    }
                }else{
                    erreur = true;
                    message = "Utilisateur ou mail incorrect";
                }
            } else {
                erreur = true;
                message = "Utilisateur ou mail incorrect";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JSONObject jsonObjectRet = new JSONObject();
        jsonObjectRet.put("succes", !erreur).put("message", message).put("token", token);

        resp.setStatus(HttpStatus.OK_200);
        resp.addHeader("Access-Control-Allow-Origin", "*");

        try {
            jsonObjectRet.write(resp.getWriter());
        } catch (IOException e) {
            e.printStackTrace();
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
        String phone = postData.getString("phone");

        JSONObject jsonObjectRet = new JSONObject();
        Statement statement = null;
        String req = "SELECT * FROM users WHERE username = '" + username + "' OR mail = '" + mail + "'";
        try {
            statement = jdbcMysSQL.getConn().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            int nbRows = 0;
            boolean erreur = false;
            String message = "";
            String token = null;
            if (statement.execute(req)) {
                ResultSet resultSet = statement.getResultSet();
                resultSet.last();
                nbRows = resultSet.getRow();
                resultSet.beforeFirst();
                boolean usernameUsed = false, mailUsed = false;
                if (nbRows > 0) {
                    while (resultSet.next()) {
                        if (resultSet.getString("username").equals(username))
                            usernameUsed = true;
                        if (resultSet.getString("mail").equals(mail))
                            mailUsed = true;
                    }
                    if (usernameUsed && mailUsed)
                        message = "Nom d'utilisateur et adresse mail déjà utilisés.";
                    else if (usernameUsed)
                        message = "Nom d'utilisateur déjà utilisé.";
                    else
                        message = "Adresse mail déjà utilisée.";
                    if (usernameUsed || mailUsed)
                        erreur = true;
                } else {
                    token = Security.generateToken();
                    String req2 = "INSERT INTO users VALUES (NULL, '" + username + "', '" + password + "', '" + mail + "', '" + phone + "', '" + 1 + "', '" + token + "');";

                    System.out.println(req2);
                    if (statement.executeUpdate(req2) > 0) {
                        ResultSet resultSet2 = statement.getResultSet();
                        System.out.println(resultSet2);
                        message = "Compte créé.";
                    }
                }
            }
            jsonObjectRet.put("succes", !erreur).put("message", message).put("token", token);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.setStatus(HttpStatus.OK_200);
        resp.addHeader("Access-Control-Allow-Origin", "*");

        try {
            jsonObjectRet.write(resp.getWriter());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
