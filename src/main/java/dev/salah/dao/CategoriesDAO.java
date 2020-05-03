package dev.salah.dao;

import dev.salah.DBInteraction;
import dev.salah.beans.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDAO {

    public static void create(Category category) {
        DBInteraction.connect();
        DBInteraction.update("INSERT INTO `categories` "
                + "(`name`)"
                + "VALUES "
                + "('" + category.getName() + "')");
        DBInteraction.close();
    }

    public static List<Category> findAll() {
        try {
            DBInteraction.connect();
            ResultSet rs = DBInteraction.select("SELECT * FROM `categories`");
            List<Category> categories = new ArrayList<Category>();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
            DBInteraction.close();
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Category find(String id) {
        try {
            DBInteraction.connect();
            ResultSet rs = DBInteraction.select("SELECT * FROM `categories` c WHERE c.id = " + id);
            Category category = new Category();
            if (rs.next()) {
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
            }
            DBInteraction.close();
            return category;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void edit(Category category) {
        DBInteraction.connect();
        DBInteraction.update("UPDATE `categories` SET name = '" + category.getName() + "' WHERE id = " + category.getId());
        DBInteraction.close();
    }

    public static void remove(String id) {
        DBInteraction.connect();
        DBInteraction.update("DELETE FROM `categories` WHERE id = " + id);
        DBInteraction.close();
    }
}
