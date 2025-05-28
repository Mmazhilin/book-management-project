package com.mazhilin.dao;

import java.util.List;

import com.mazhilin.model.Book;

/*
 * 書籍 DAO 介面
 */
public interface BookDAO {
	// 新增
	void addBook(Book book);// 新增訊息內容
	// 查詢

	List<Book> getAllBook();

	// List<Book> getSearchBook(String keyword, int offset, int limit);
	// 修改
	// 刪除
	boolean deleteBook(int id);

	// 銷毀
	void destroy();
}
