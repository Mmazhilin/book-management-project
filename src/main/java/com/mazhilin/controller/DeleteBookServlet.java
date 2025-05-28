package com.mazhilin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mazhilin.dao.BookDAOImpl;
import com.mazhilin.model.util.DBUtil;

/**
 * 刪除書籍
 */
@WebServlet("/deleteBook")
public class DeleteBookServlet extends HttpServlet {
	private BookDAOImpl dao;

	@Override
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
		// TODO Auto-generated method stub

		String idStr = request.getParameter("id");
		if (!idStr.trim().isEmpty() || idStr != null) {
			boolean success = dao.deleteBook(Integer.parseInt(idStr));
			System.out.println("刪除" + success);
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print("{\"success\": " + success + "}");
			out.flush();
			return;
		}

	}

}
