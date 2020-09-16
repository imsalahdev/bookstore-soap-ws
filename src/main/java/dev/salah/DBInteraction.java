package dev.salah;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInteraction {

    private static Connection conn;
    private static Statement st;
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore-ws", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet select(String query) {
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void update(String query) {
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static PreparedStatement updatePrepared(String query) {
        try {
            ps = conn.prepareStatement(query);
            return ps;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
