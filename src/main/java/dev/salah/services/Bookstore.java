package dev.salah.services;

import dev.salah.beans.Book;
import dev.salah.beans.Cart;
import dev.salah.beans.Category;
import dev.salah.beans.User;
import dev.salah.dao.BooksDAO;
import dev.salah.dao.CartsDAO;
import dev.salah.dao.CategoriesDAO;
import dev.salah.dao.UsersDAO;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "Bookstore")
public class Bookstore {

    // User Routes
    @WebMethod(operationName = "createUser")
    public void createUser(@WebParam(name = "user") User user) {
        UsersDAO.create(user);
    }

    @WebMethod(operationName = "findAllUsers")
    public List<User> findAllUsers() {
        return UsersDAO.findAll();
    }

    @WebMethod(operationName = "findUser")
    public User findUser(@WebParam(name = "id") String id) {
        return UsersDAO.find(id);
    }

    @WebMethod(operationName = "findByEmail")
    public User findByEmail(@WebParam(name = "email") String email) {
        return UsersDAO.findByEmail(email);
    }

    @WebMethod(operationName = "editUser")
    public void editUser(@WebParam(name = "user") User user) {
        UsersDAO.edit(user);
    }

    @WebMethod(operationName = "removeUser")
    public void removeUser(@WebParam(name = "id") String id) {
        UsersDAO.remove(id);
    }

    // Books Routes
    @WebMethod(operationName = "createBook")
    public void createBook(@WebParam(name = "book") Book book) {
        BooksDAO.create(book);
    }

    @WebMethod(operationName = "findAllBooks")
    public List<Book> findAllBooks() {
        return BooksDAO.findAll();
    }

    @WebMethod(operationName = "findBook")
    public Book findBook(@WebParam(name = "id") String id) {
        return BooksDAO.find(id);
    }
    
    @WebMethod(operationName = "findByCategoryID")
    public List<Book> findByCategoryID(Integer id) {
        return BooksDAO.findByCategoryID(id);
    }

    @WebMethod(operationName = "editBook")
    public void editBook(@WebParam(name = "book") Book book) {
        BooksDAO.edit(book);
    }

    @WebMethod(operationName = "removeBook")
    public void removeBook(@WebParam(name = "id") String id) {
        BooksDAO.remove(id);
    }

    // Categories Routes
    @WebMethod(operationName = "createCategory")
    public void createCategory(@WebParam(name = "category") Category category) {
        CategoriesDAO.create(category);
    }

    @WebMethod(operationName = "findAllCategories")
    public List<Category> findAllCategories() {
        return CategoriesDAO.findAll();
    }

    @WebMethod(operationName = "findCategory")
    public Category findCategory(@WebParam(name = "id") String id) {
        return CategoriesDAO.find(id);
    }

    @WebMethod(operationName = "editCategory")
    public void editCategory(@WebParam(name = "category") Category category) {
        CategoriesDAO.edit(category);
    }

    @WebMethod(operationName = "removeCategory")
    public void removeCategory(@WebParam(name = "id") String id) {
        CategoriesDAO.remove(id);
    }

    // Carts Routes
    @WebMethod(operationName = "createCart")
    public void createCart(@WebParam(name = "category") Cart category) {
        CartsDAO.create(category);
    }

    @WebMethod(operationName = "findAllCarts")
    public List<Cart> findAllCarts() {
        return CartsDAO.findAll();
    }

    @WebMethod(operationName = "findCart")
    public Cart findCart(@WebParam(name = "id") String id) {
        return CartsDAO.find(id);
    }

    @WebMethod(operationName = "editCart")
    public void editCart(@WebParam(name = "category") Cart category) {
        CartsDAO.edit(category);
    }

    @WebMethod(operationName = "removeCart")
    public void removeCart(@WebParam(name = "id") String id) {
        CartsDAO.remove(id);
    }

    @WebMethod(operationName = "removeByCart")
    public void removeByCart(@WebParam(name = "uid") Integer uid, @WebParam(name = "bid") Integer bid) {
        CartsDAO.removeByCart(uid, bid);
    }
    
    @WebMethod(operationName = "removeByUserID")
    public void removeByUserID(@WebParam(name = "uid") Integer uid) {
        CartsDAO.removeByUserID(uid);
    }

    @WebMethod(operationName = "findByCart")
    public Cart findByCart(@WebParam(name = "uid") Integer uid, @WebParam(name = "bid") Integer bid) {
        return CartsDAO.findByCart(uid, bid);
    }

    @WebMethod(operationName = "findByUserId")
    public List<Cart> findByUserId(@WebParam(name = "uid") Integer uid) {
        return CartsDAO.findByUserId(uid);
    }
}
