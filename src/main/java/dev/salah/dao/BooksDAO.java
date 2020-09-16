package dev.salah.dao;

import dev.salah.DBInteraction;
import dev.salah.beans.Book;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BooksDAO {

    public static void create(Book book) {
        try {
            DBInteraction.connect();
            PreparedStatement ps = DBInteraction.updatePrepared("INSERT INTO `books` (`title`, `author`, `price`, `count`, `thumbnail`, `categoryID`, `information`) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setDouble(3, book.getPrice());
            ps.setInt(4, book.getCount());
            ps.setBytes(5, book.getThumbnail());
            ps.setInt(6, book.getCategoryID().getId());
            ps.setString(7, book.getInformation());
            ps.executeUpdate();
            DBInteraction.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Book> findAll() {
        try {
            DBInteraction.connect();
            ResultSet rs = DBInteraction.select("SELECT * FROM books");
            List<Book> books = new ArrayList<>();
            while (rs.next()) {
                books.add(getBookFromResultSet(rs));
            }
            DBInteraction.close();
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Book find(String id) {
        try {
            DBInteraction.connect();
            ResultSet rs = DBInteraction.select("SELECT * FROM `books` b WHERE b.id = " + id);
            Book book = null;
            if (rs.next()) {
                book = getBookFromResultSet(rs);
            }
            DBInteraction.close();
            return book;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Book> findByCategoryID(Integer id) {
        try {
            DBInteraction.connect();
            ResultSet rs = DBInteraction.select("SELECT * FROM `books` b WHERE b.categoryID = " + id);
            List<Book> books = new ArrayList<>();
            while (rs.next()) {
                books.add(getBookFromResultSet(rs));
            }
            DBInteraction.close();
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void edit(Book book) {
        try {
            DBInteraction.connect();
            PreparedStatement ps = DBInteraction.updatePrepared("UPDATE `books` SET title = ?, author = ?, price = ?, count = ?, thumbnail = ?, categoryID = ?, information = ? WHERE id = ?");
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setDouble(3, book.getPrice());
            ps.setInt(4, book.getCount());
            ps.setBytes(5, book.getThumbnail());
            ps.setInt(6, book.getCategoryID().getId());
            ps.setString(7, book.getInformation());
            ps.setInt(8, book.getId());
            ps.executeUpdate();
            DBInteraction.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Integer countSOAP() {
        DBInteraction.connect();
        ResultSet rs = DBInteraction.select("SELECT * FROM `books`");
        Integer count = 0;
        try {
            while (rs.next()) {
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBInteraction.close();
        return count;
    }

    public static void remove(String id) {
        DBInteraction.connect();
        DBInteraction.update("DELETE FROM `books` WHERE id = " + id);
        DBInteraction.close();
    }

    public static Book getBookFromResultSet(ResultSet rs) {
        try {
            final Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setPrice(rs.getDouble("price"));
            book.setCount(rs.getInt("count"));
            book.setThumbnail(rs.getBytes("thumbnail"));
            book.setCategoryID(CategoriesDAO.find(String.valueOf(rs.getInt("categoryID"))));
            book.setInformation(rs.getString("information"));
            return book;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
