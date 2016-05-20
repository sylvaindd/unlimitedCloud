package com.ultimateCloud.App.jdbc;

/**
 * Created by thoma on 17/05/2016.
 */

import com.ultimateCloud.App.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCMysSQL {
    private static JDBCMysSQL jdbcMysSQL;

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    Connection conn;

    private JDBCMysSQL() {

    }

    public static JDBCMysSQL getInstance() {
        if (jdbcMysSQL == null || jdbcMysSQL.getConn() == null) {
            jdbcMysSQL = new JDBCMysSQL();
            jdbcMysSQL.setConn(null);

            try {
                jdbcMysSQL.setConn(DriverManager.getConnection("jdbc:mysql://lebonnuage.istic.univ-rennes1.fr/lebonnuage?" + "user=lebonnuage&password=lebonnuage123"
                        + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"));

                // Do something with the Connection

            } catch (Exception ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
            }

        }
        return jdbcMysSQL;

    }

    public List<String> getAllTokenCloudFromUser(int idType, String idUser) {
        Statement stmt = null;
        ResultSet rs = null;
        String req = "";
        List<String> listTokens = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            req = "SELECT tokenCloudService FROM accounts WHERE idUser='" + idUser + "' AND idType=" + idType + ";";
            System.out.println(req);
            if (stmt.execute(req)) {
                rs = stmt.getResultSet();
                while (rs.next()) {
                    listTokens.add(rs.getString("tokenCloudService"));
                }
            }
            return listTokens;
            // Now do something with the ResultSet ....
        } catch (Exception ex) {
            // handle any errors
            System.out.println(req);
            System.out.println("SQLException: " + ex.getMessage());
        } finally {
            endSQL(stmt, rs);
        }
        return listTokens;
    }


    public String getUserIdUltimateCloudFromToken(String tokenUltimateCloud) {
        Statement stmt = null;
        ResultSet rs = null;
        String req = "";
        String userIDUltimateCloud = "";
        try {
            stmt = conn.createStatement();
            req = "SELECT id FROM users WHERE tokenLeBonNuage='" + tokenUltimateCloud + "'";
            System.out.println(req);
            if (stmt.execute(req)) {
                rs = stmt.getResultSet();
                while (rs.next()) {
                    userIDUltimateCloud = rs.getString("id");
                    System.out.println(rs.getString("id"));
                    return userIDUltimateCloud;
                }
            }

            // Now do something with the ResultSet ....
        } catch (Exception ex) {
            // handle any errors
            System.out.println(req);
            System.out.println("SQLException: " + ex.getMessage());
        } finally {
            endSQL(stmt, rs);
        }
        return userIDUltimateCloud;
    }

    public void endSQL(Statement stmt, ResultSet rs) {
        // it is a good idea to release
        // resources in a finally{} block
        // in reverse-order of their creation
        // if they are no-longer needed

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException sqlEx) {
            } // ignore

            rs = null;
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException sqlEx) {
            } // ignore

            stmt = null;
        }
    }

    public void addDropBoxTokenToOurAccount(String token, String idDropBox, String tokenUltimateCloud) {
        Statement stmt = null;
        ResultSet rs = null;
        String idUserUltimateCloud = getUserIdUltimateCloudFromToken(tokenUltimateCloud);
        String req = "";

        try {
            stmt = conn.createStatement();
            req = "SELECT * FROM accounts WHERE idType=1 AND idCloudService=" + idDropBox + " AND idUser=" + idUserUltimateCloud + " AND tokenCloudService  IS NOT NULL AND idCloudService IS NOT NULL";
            if (stmt.execute(req)) {
                rs = stmt.getResultSet();
                int size = 0;
                if (rs != null) {
                    rs.beforeFirst();
                    rs.last();
                    size = rs.getRow();
                }

                if (size == 0) {
                    // insert
                    req = "INSERT INTO accounts (idType,idCloudService,idUser,tokenCloudService,codeCloudService) " + "    VALUES (1, '" + idDropBox + "', '" + idUserUltimateCloud + "','" + token
                            + "','-1');";
                    if (stmt.execute(req)) {
                        rs = stmt.getResultSet();
                    }
                } else {
                    // update
                    req = "UPDATE accounts set tokenCloudService='" + token + "' where idCloudService ='" + idDropBox + "' AND idType=1;";
                    if (stmt.execute(req)) {
                        rs = stmt.getResultSet();
                    }
                }
            }

            // Now do something with the ResultSet ....
        } catch (Exception ex) {
            // handle any errors
            System.out.println(req);
            System.out.println("SQLException: " + ex.getMessage());
        } finally {
            endSQL(stmt, rs);
        }
    }

    public void addDropBoxCodeToOurAccount(String code, String idDropBox, String tokenUltimateCloud) {
        // TODO
        Statement stmt = null;
        ResultSet rs = null;
        String req = "";
        String idUserUltimateCloud = getUserIdUltimateCloudFromToken(tokenUltimateCloud);
        try {
            stmt = conn.createStatement();
            req = "SELECT * FROM accounts WHERE idType=1 AND idCloudService=" + idDropBox + " AND idUser=" + idUserUltimateCloud + " AND codeCloudService  IS NOT NULL AND idCloudService IS NOT NULL";
            if (stmt.execute(req)) {
                rs = stmt.getResultSet();
                int size = 0;
                if (rs != null) {
                    rs.beforeFirst();
                    rs.last();
                    size = rs.getRow();
                }

                if (size == 0) {
                    // insert
                    req = "INSERT INTO accounts (idType,idCloudService,idUser,codeCloudService,tokenCloudService) " + "    VALUES (1, '" + idDropBox + "', '" + idUserUltimateCloud + "','" + code
                            + "','-1');";
                    if (stmt.execute(req)) {
                        rs = stmt.getResultSet();
                    }
                } else {
                    // update
                    req = "UPDATE accounts set codeCloudService='" + code + "' where idCloudService ='" + idDropBox + "' AND idType=1;";
                    if (stmt.execute(req)) {
                        rs = stmt.getResultSet();
                    }
                }
            }
            // Now do something with the ResultSet ....
        } catch (Exception ex) {
            // handle any errors
            System.out.println(req);
            System.out.println("SQLException: " + ex.getMessage() + ex.getLocalizedMessage());

        } finally {
            endSQL(stmt, rs);
        }
    }

    public void addGoogleDriveCodeToOurAccount(String code, String idGoogleDrive, String tokenUltimateCloud) {
        // TODO
        Statement stmt = null;
        ResultSet rs = null;
        String req = "";
        String idUserUltimateCloud = getUserIdUltimateCloudFromToken(tokenUltimateCloud);
        try {
            stmt = conn.createStatement();
            req = "SELECT * FROM accounts WHERE idType=2 AND idCloudService=" + idGoogleDrive + " AND idUser=" + idUserUltimateCloud
                    + " AND codeCloudService  IS NOT NULL AND idCloudService IS NOT NULL";
            if (stmt.execute(req)) {
                rs = stmt.getResultSet();
                int size = 0;
                if (rs != null) {
                    rs.beforeFirst();
                    rs.last();
                    size = rs.getRow();
                }

                if (size == 0) {
                    // insert
                    req = "INSERT INTO accounts (idType,idCloudService,idUser,codeCloudService,tokenCloudService) " + "    VALUES (2, '" + idGoogleDrive + "', '" + idUserUltimateCloud + "','" + code
                            + "','-1');";
                    if (stmt.execute(req)) {
                        rs = stmt.getResultSet();
                    }
                } else {
                    // update
                    req = "UPDATE accounts set codeCloudService='" + code + "' where idCloudService ='" + idGoogleDrive + "' AND idType=2;";
                    if (stmt.execute(req)) {
                        rs = stmt.getResultSet();
                    }
                }
            }
            // Now do something with the ResultSet ....
        } catch (Exception ex) {
            // handle any errors
            System.out.println(req);
            System.out.println("SQLException: " + ex.getMessage() + ex.getLocalizedMessage());

        } finally {
            endSQL(stmt, rs);
        }
    }

    public void addGoogleDriveTokenToOurAccount(String token, String idGoogleDrive, String tokenUltimateCloud) {
        Statement stmt = null;
        ResultSet rs = null;
        String idUserUltimateCloud = getUserIdUltimateCloudFromToken(tokenUltimateCloud);
        String req = "";

        try {
            stmt = conn.createStatement();
            req = "SELECT * FROM accounts WHERE idType=2 AND idCloudService=" + idGoogleDrive + " AND idUser=" + idUserUltimateCloud
                    + " AND tokenCloudService  IS NOT NULL AND idCloudService IS NOT NULL";
            if (stmt.execute(req)) {
                rs = stmt.getResultSet();
                int size = 0;
                if (rs != null) {
                    rs.beforeFirst();
                    rs.last();
                    size = rs.getRow();
                }

                if (size == 0) {
                    // insert
                    req = "INSERT INTO accounts (idType,idCloudService,idUser,tokenCloudService,codeCloudService) " + "    VALUES (2, '" + idGoogleDrive + "', '" + idUserUltimateCloud + "','" + token
                            + "','-1');";
                    if (stmt.execute(req)) {
                        rs = stmt.getResultSet();
                    }
                } else {
                    // update
                    req = "UPDATE accounts set tokenCloudService='" + token + "' where idCloudService ='" + idGoogleDrive + "' AND idType=2;";
                    if (stmt.execute(req)) {
                        rs = stmt.getResultSet();
                    }
                }
            }

            // Now do something with the ResultSet ....
        } catch (Exception ex) {
            // handle any errors
            System.out.println(req);
            System.out.println("SQLException: " + ex.getMessage());
        } finally {
            endSQL(stmt, rs);
        }
    }

    public User getUserFromToken(String token) {
        Statement stmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            stmt = conn.createStatement();
            String req = "SELECT * FROM users WHERE tokenLeBonNuage='" + token + "'";
            if (stmt.execute(req)) {
                rs = stmt.getResultSet();
                rs.next();
                user = new User(rs);
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            endSQL(stmt, rs);
        }
        return user;
    }

    public void getDropBoxAccountList() {
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT foo FROM bar");

            // or alternatively, if you don't know ahead of time that
            // the query will be a SELECT...

            if (stmt.execute("SELECT foo FROM bar")) {
                rs = stmt.getResultSet();
            }

            // Now do something with the ResultSet ....
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            endSQL(stmt, rs);
        }
    }
}
