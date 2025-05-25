package com.bookstore.dao;



import com.bookstore.model.Book;
import com.bookstore.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BookDAO {
    public void addBook(Book book) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO books (title, author, price) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setDouble(3, book.getPrice());
            ps.executeUpdate();
        } finally {
            DBUtil.close(conn, ps, null);
        }
    }
}
