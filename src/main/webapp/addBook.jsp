<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增書籍</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container mt-4">
		<h2 class="mb-4">新增書籍</h2>
		<form action="addBook" method="post">
			<div class="mb-3">
				<label class="form-label">書名</label>
				<input type="text" name="title" class="form-control" value="${param.title}" required>
			</div>
			<div class="mb-3">
				<label class="form-label">作者</label>
				<input type="text" name="authors" class="form-control" value="${param.authors}">
			</div>
			<div class="mb-3">
				<label class="form-label">出版社</label>
				<input type="text" name="publisher" class="form-control" value="${fn:escapeXml(book.publisher)}">
			</div>
			<div class="mb-3">
				<label class="form-label">出版社日期</label>
				<input type="text" name="publishedDate" class="form-control" value="${param.publishedDate}">
			</div>
			<div class="mb-3">
				<label class="form-label">描述</label>
				<textarea name="description" class="form-control">${param.description}</textarea>
			</div>
			<div class="mb-3">
				<label class="form-label">封面圖片連結</label>
				<input type="text" name="imageLinks" class="form-control" value="${param.imageLinks}">
			</div>
			<div class="mb-3">
				<label class="form-label">預覽連結</label>
				 <input type="text" name="previewLink" class="form-control" value="${param.previewLink}">
			</div>
			<div class="mb-3">
				<label class="form-label">ISBN</label>
				<input type="text" name="isbn" class="form-control" value="${param.isbn}">
			</div>
			<button type="submit" class="btn btn-primary">新增書籍</button>
		</form>
	</div>
</body>
</html>