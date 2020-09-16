package dev.salah.dao;

import dev.salah.DBInteraction;
import dev.salah.beans.Cart;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartsDAO {

    public static void create(Cart cart) {
        DBInteraction.connect();
        DBInteraction.update("INSERT INTO `carts` "
                + "(`user_id`, `book_id`)"
                + "VALUES "
                + "(" + cart.getUserId().getId() + ", " + cart.getBookId().getId() + ")");
        DBInteraction.close();
    }

    public static List<Cart> findAll() {
        try {
            DBInteraction.connect();
            ResultSet rs = DBInteraction.select("SELECT * FROM `carts`");
            List<Cart> carts = new ArrayList<>();
            while (rs.next()) {
                carts.add(getCartFromResultSet(rs));
            }
            DBInteraction.close();
            return carts;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Cart find(String id) {
        try {
            DBInteraction.connect();
            ResultSet rs = DBInteraction.select("SELECT * FROM `carts` c WHERE c.id = " + id);
            Cart cart = null;
            if (rs.next()) {
                cart = getCartFromResultSet(rs);
            }
            DBInteraction.close();
            return cart;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void edit(Cart cart) {
        DBInteraction.connect();
        DBInteraction.update("UPDATE `carts` SET user_id = " + cart.getUserId().getId() + ", book_id = " + cart.getBookId().getId() + " WHERE id = " + cart.getId());
        DBInteraction.close();
    }

    public static void remove(String id) {
        DBInteraction.connect();
        DBInteraction.update("DELETE FROM `carts` WHERE id = " + id);
        DBInteraction.close();
    }

    public static void removeByCart(Integer uid, Integer bid) {
        DBInteraction.connect();
        DBInteraction.update("DELETE FROM `carts` WHERE user_id = " + uid + " AND book_id = " + bid);
        DBInteraction.close();
    }

    public static void removeByUserID(Integer uid) {
        DBInteraction.connect();
        DBInteraction.update("DELETE FROM `carts` WHERE user_id = " + uid);
        DBInteraction.close();
    }

    public static Cart findByCart(Integer uid, Integer bid) {
        try {
            DBInteraction.connect();
            ResultSet rs = DBInteraction.select("SELECT * FROM `carts` WHERE user_id = " + uid + " AND book_id = " + bid);
            Cart cart = null;
            if (rs.next()) {
                cart = getCartFromResultSet(rs);
            }
            DBInteraction.close();
            return cart;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Cart> findByUserId(Integer uid) {
        try {
            DBInteraction.connect();
            ResultSet rs = DBInteraction.select("SELECT * FROM `carts` c WHERE c.user_id = " + uid);
            List<Cart> carts = new ArrayList<>();
            while (rs.next()) {
                carts.add(getCartFromResultSet(rs));
            }
            DBInteraction.close();
            return carts;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Cart getCartFromResultSet(ResultSet rs) {
        try {
            final Cart cart = new Cart();
            cart.setId(rs.getInt("id"));
            cart.setUserId(UsersDAO.find(String.valueOf(rs.getInt("user_id"))));
            cart.setBookId(BooksDAO.find(String.valueOf(rs.getInt("book_id"))));
            return cart;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
