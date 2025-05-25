package com.bookstore.servlet;



import com.bookstore.dao.BookDAO;
import com.bookstore.model.Book;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/registerBook")
public class BookRegistrationServlet extends HttpServlet {

    private BookDAO bookDAO;

    @Override
    public void init() throws ServletException {
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        bookDAO = (BookDAO) ctx.getBean("bookDAO");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        double price = Double.parseDouble(request.getParameter("price"));

        Book book = new Book(title, author, price);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            bookDAO.addBook(book);
            out.println("<h2>Book Registered Successfully</h2>");
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h2>Error Registering Book</h2>");
        }
    }
}
