package com.mazhilin.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mazhilin.model.Book;

/**
 * 書籍 DAO 實作
 */
public class BookDAOImpl implements BookDAO {
	Connection conn = null;

	public BookDAOImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO Books(title, authors, publisher, publishedDate, description, imageLinks, previewLink, buyLink, isbn) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		LocalDate publishedDate = book.getPublishedDate();
		try (PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthors());
			ps.setString(3, book.getPublisher());
			if (publishedDate != null || LocalDate.of(1970, 1, 1).equals(publishedDate)) {
				if (publishedDate != null) {
					ps.setDate(4, Date.valueOf(publishedDate));
				}
			} else {
				ps.setNull(4, java.sql.Types.DATE);
			}
			ps.setString(5, book.getDescription());
			ps.setString(6, book.getImageLinks());
			ps.setString(7, book.getPreviewLink());
			ps.setString(8, book.getBuyLink());
			ps.setString(9, book.getIsbn());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Book> getAllBook() {
		// TODO Auto-generated method stub
		List<Book> books = new ArrayList<>();
		String sql = "SELECT * FROM books";
		try (PreparedStatement ps = conn.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setTitle(rs.getString("title"));
				book.setAuthors(rs.getString("authors"));
				book.setPublisher(rs.getString("publisher"));
				String publishedDateStr = rs.getString("publishedDate");
				if (publishedDateStr == null || publishedDateStr.trim().isEmpty()) {
					book.setPublishedDate(null);
				} else {
					book.setPublishedDate(LocalDate.parse(publishedDateStr));
				}
				book.setDescription(rs.getString("description"));
				book.setImageLinks(rs.getString("imageLinks"));
				book.setPreviewLink(rs.getString("previewLink"));
				book.setBuyLink(rs.getString("buyLink"));
				book.setIsbn(rs.getString("isbn"));
				books.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public boolean deleteBook(int id) {
		// TODO Auto-generated method stub
		int delecheck = 0;
		String sql = "DELETE FROM books WHERE id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, id);
			delecheck = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return delecheck > 0;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		try {
			if (conn != null || !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
