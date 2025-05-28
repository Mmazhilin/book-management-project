package com.mazhilin.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mazhilin.api.GoogleBookApiOkHttp;
import com.mazhilin.dao.BookDAOImpl;
import com.mazhilin.model.Book;
import com.mazhilin.model.util.DBUtil;

/**
 * 查詢 / 路由主控 Servlet
 */
@WebServlet("/searchBooks")
public class BookServlet extends HttpServlet {
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
		// TODO Auto-generated method stub

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String publishedDate = request.getParameter("publishedDate");
		if ("showAddForm".equals(action)) {
			Book book = new Book();
			book.setTitle(request.getParameter("title"));
			book.setAuthors(request.getParameter("authors"));
			book.setPublisher(request.getParameter("publisher"));
			if (!publishedDate.trim().isEmpty() || publishedDate == null) {
				book.setPublishedDate(LocalDate.parse(request.getParameter("publishedDate")));
			}
			book.setDescription(request.getParameter("description"));
			book.setImageLinks(request.getParameter("imageLinks"));
			book.setPreviewLink(request.getParameter("previewLink"));
			book.setIsbn(request.getParameter("isbn"));
			request.setAttribute("book", book);
			request.getRequestDispatcher("/addBook.jsp").forward(request, response);
		} else if ("search".equals(action)) {
			// 處理 Google API 查詢
			String query = request.getParameter("query");// 書名關鍵字
			if (query != null && !query.trim().isEmpty()) {
				List<Book> bookList = GoogleBookApiOkHttp.searchBooks(query);
				request.setAttribute("bookList", bookList);
			}
			request.getRequestDispatcher("/bookList.jsp").forward(request, response);
		} else if ("local".equals(action)) {
			List<Book> books = dao.getAllBook();
			request.setAttribute("books", books);
			request.getRequestDispatcher("/bookList.jsp").forward(request, response);
		} 
	}

	@Override
	public void destroy() {
		dao.destroy();
	}
}
