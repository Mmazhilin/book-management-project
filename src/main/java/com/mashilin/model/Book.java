package com.mashilin.model;

import java.time.LocalDate;

/**
 * Book JavaBean
 */
public class Book {
	private int id;// 編號
	private String title;// 書名
	private String authors;// 作者
	private String publisher;// 出版社
	private LocalDate publishedDate;// 出版日期
	private String description;// 書籍描述
	private String imageLinks;// 封面圖片連結
	private String previewLink;// 預覽連結
	private String buyLink;// 購買連結
	private String isbn;// 識別碼

	public Book() {

	}

	public Book(String title, String authors, String publisher, LocalDate publishedDate, String description,
			String imageLinks, String previewLink, String buyLink, String ISBN) {
		super();
		this.title = title;
		this.authors = authors;
		this.publisher = publisher;
		this.publishedDate = publishedDate;
		this.description = description;
		this.imageLinks = imageLinks;
		this.previewLink = previewLink;
		this.buyLink = buyLink;
		this.isbn = ISBN;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public LocalDate getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageLinks() {
		return imageLinks;
	}

	public void setImageLinks(String imageLinks) {
		this.imageLinks = imageLinks;
	}

	public String getPreviewLink() {
		return previewLink;
	}

	public void setPreviewLink(String previewLink) {
		this.previewLink = previewLink;
	}

	public String getBuyLink() {
		return buyLink;
	}

	public void setBuyLink(String buyLink) {
		this.buyLink = buyLink;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

}
