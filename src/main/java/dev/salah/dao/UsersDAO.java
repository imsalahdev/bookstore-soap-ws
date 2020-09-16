package dev.salah.dao;

import dev.salah.DBInteraction;
import dev.salah.beans.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {

    public static void create(User user) {
        try {
            DBInteraction.connect();
            PreparedStatement ps = DBInteraction.updatePrepared("INSERT INTO `users` (`username`, `email`, `password`, `role`, `photo`) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            ps.setBytes(5, user.getPhoto());
            ps.executeUpdate();
            DBInteraction.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<User> findAll() {
        try {
            DBInteraction.connect();
            ResultSet rs = DBInteraction.select("SELECT * FROM `users`");
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(getUserFromResultSet(rs));
            }
            DBInteraction.close();
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static User find(String id) {
        try {
            DBInteraction.connect();
            ResultSet rs = DBInteraction.select("SELECT * FROM `users` u WHERE u.id = " + id);
            User user = null;
            if (rs.next()) {
                user = getUserFromResultSet(rs);
            }
            DBInteraction.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static User findByEmail(String email) {
        try {
            DBInteraction.connect();
            ResultSet rs = DBInteraction.select("SELECT * FROM `users` u WHERE u.email = '" + email + "'");
            User user = null;
            if (rs.next()) {
                user = getUserFromResultSet(rs);
            }
            DBInteraction.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void edit(User user) {
        try {
            DBInteraction.connect();
            PreparedStatement ps = DBInteraction.updatePrepared("UPDATE `users` SET username = ?, email = ?, password = ?, role = ?, photo = ? WHERE id = ?");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            ps.setBytes(5, user.getPhoto());
            ps.setInt(6, user.getId());
            ps.executeUpdate();
            DBInteraction.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void remove(String id) {
        DBInteraction.connect();
        DBInteraction.update("DELETE FROM `users` WHERE id = " + id);
        DBInteraction.close();
    }

    public static User getUserFromResultSet(ResultSet rs) {
        try {
            final User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            user.setPhoto(rs.getBytes("photo"));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
