package com.mashilin.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mashilin.dao.BookDAOImpl;
import com.mashilin.model.Book;
import com.mashilin.model.util.DBUtil;

/**
 * 新增書籍
 */
@WebServlet("/addBook")
public class AddBookServlet extends HttpServlet {
	private BookDAOImpl dao;

	public void init() throws ServletException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			dao = new BookDAOImpl(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String publishedDate = request.getParameter("publishedDate");
		Book book = new Book();
		book.setTitle(request.getParameter("title"));
		book.setAuthors(request.getParameter("authors"));
		book.setPublisher(request.getParameter("publisher"));
		if (!publishedDate.trim().isEmpty() || publishedDate == null) {
			book.setPublishedDate(LocalDate.parse(request.getParameter("publishedDate"))); // 若是 String
		}
		book.setDescription(request.getParameter("description"));
		book.setImageLinks(request.getParameter("imageLinks"));
		book.setPreviewLink(request.getParameter("previewLink"));
		book.setIsbn(request.getParameter("isbn"));
		dao.addBook(book);

		response.sendRedirect("bookList.jsp"); // 新增完導回書籍列表
	}

}
