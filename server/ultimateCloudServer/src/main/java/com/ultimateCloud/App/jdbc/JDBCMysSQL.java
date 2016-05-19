package com.ultimateCloud.App.jdbc;

/**
 * Created by thoma on 17/05/2016.
 */

import java.sql.*;

public class JDBCMysSQL {
    private static JDBCMysSQL jdbcMysSQL;

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    Connection conn;
    private JDBCMysSQL (){

    }
    public static JDBCMysSQL getInstance() {
        if(jdbcMysSQL==null || jdbcMysSQL.getConn()==null) {
            jdbcMysSQL = new JDBCMysSQL();
            jdbcMysSQL.setConn(null);

            try {
                jdbcMysSQL.setConn(DriverManager.getConnection("jdbc:mysql://lebonnuage.istic.univ-rennes1.fr/lebonnuage?" + "user=lebonnuage&password=lebonnuage123"+"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"));


                // Do something with the Connection


            } catch (Exception ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
            }

        }
            return jdbcMysSQL;

    }

    public void addDropBoxTokenToOurAccount(String token, String idDropBox, String tokenUltimateCloud) {
        //TODO
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            String req = "SELECT * FROM accounts WHERE idType=1 AND idUser=idUserUltimateCloud";
            if (stmt.execute(req)) {
                rs = stmt.getResultSet();
            }
             req = "INSERT INTO accounts (idType,idCloudService,idUser,tokenCloudService) " +
                    "    VALUES (1, '" + idDropBox + "', 'u.id','" + token + "') LEFT JOIN User AS u ON u.tokenLeBonNuage='" + tokenUltimateCloud + "'" +
                    "ON DUPLICATE KEY UPDATE (tokenCloudService='" + token + "');";
            System.out.println(req);
            if (stmt.execute(req)) {
                rs = stmt.getResultSet();
            }

            // Now do something with the ResultSet ....
        } catch (Exception ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
        } finally {
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
    }

    public void addDropBoxCodeToOurAccount(String code, String idDropBox, String tokenUltimateCloud) {
        //TODO
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            System.out.println(conn);
            String req = "INSERT INTO Account (idType,idCloudService,idUser,codeCloudService) " +
                    "    VALUES (1, '" + idDropBox + "', 'u.id','" + code + "') LEFT JOIN User AS u ON u.tokenLeBonNuage='" + tokenUltimateCloud + "'" +
                    "ON DUPLICATE KEY UPDATE (codeCloudService='" + code + "');";
            System.out.println(req);
            if (stmt.execute(req)) {
                rs = stmt.getResultSet();
            }

            // Now do something with the ResultSet ....
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
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
    }
}
