<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>書籍列表</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="d-flex flex-column min-vh-100">
	<div class="container mt-4">

		<!--  搜尋表單 -->
		<h2 class="mb-3">Google Books 搜尋</h2>
		<form action="searchBooks" method="post" class="row g-3 mb-4">
			<div class="col-md-8">
				<input type="text" name="query" class="form-control" placeholder="輸入書名關鍵字" required value="${param.query}">
			</div>
			<div class="col-md-2">
				<button type="submit" name="action" value="search" class="btn btn-primary">搜尋</button>
			</div>
			<div class="col-md-2 text-end">
				<a href="#" onclick="submitLocalBooksForm()" class="btn btn-warning w-100">查詢收藏書籍</a>
			</div>
		</form>
		<form id="localBooksForm" action="searchBooks" method="post" style="display: none;">
			<input type="hidden" name="action" value="local">
		</form>

		<!-- 已儲存書籍 -->
		<c:if test="${not empty books}">
			<h4>我的書櫃</h4>
			<div class="row row-cols-1 row-cols-md-3 g-4">
				<c:forEach var="book" items="${books}">
					<!-- 書籍卡片 -->
					<div class="col">
						<div class="card h-100">
							<img src="${book.imageLinks}" class="card-img-top" alt="封面圖片">
							<div class="card-body">
								<h5 class="card-title">${book.title}</h5>
								<p class="card-text">
									<strong>作者：</strong>
									${book.authors}
								</p>
								<p class="card-text">
									<strong>出版社：</strong>
									${book.publisher}
								</p>
								<p class="card-text">
									<strong>出版日：</strong>
									${book.publishedDate}
								</p>
								<p class="card-text">${book.description}</p>
							</div>
							<div class="card-footer d-flex justify-content-between">
								<a href="${book.previewLink}" target="_blank" class="btn btn-primary btn-sm">預覽</a>
								<button class="btn btn-danger btn-sm" onclick="deleteBook(${book.id}, this)">刪除</button>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>
		<!--   Google Books 查詢結果 -->
		<c:if test="${not empty bookList}">
			<h4 class="mb-3">搜尋結果：</h4>
			<div class="row row-cols-1 row-cols-md-3 g-4">
				<c:forEach var="book" items="${bookList}">
					<!-- 書籍卡片 -->
					<div class="col">
						<div class="card h-100">
							<img src="${book.imageLinks}" class="card-img-top" alt="封面圖片">
							<div class="card-body">
								<h5 class="card-title">${book.title}</h5>
								<p class="card-text">
									<strong>作者：</strong>
									${book.authors}
								</p>
								<p class="card-text">
									<strong>出版社：</strong>
									${book.publisher}
								</p>
								<p class="card-text">
									<strong>出版日：</strong>
									${book.publishedDate}
								</p>
								<p class="card-text">${book.description}</p>
							</div>
							<div class="card-footer d-flex justify-content-between">
								<a href="${book.previewLink}" target="_blank" class="btn btn-primary btn-sm">預覽</a>
								<!-- 隱藏的表單，用於提交書籍資料 -->
								<form action="searchBooks" method="post">
									<input type="hidden" name="action" value="showAddForm">
									<input type="hidden" name="title" value="${book.title}">
									<input type="hidden" name="authors" value="${book.authors}">
									<input type="hidden" name="publisher" value="${fn:escapeXml(book.publisher)}">
									<input type="hidden" name="publishedDate" value="${book.publishedDate}" />
									<input type="hidden" name="description" value="${book.description}" />
									<input type="hidden" name="imageLinks" value="${book.imageLinks}" />
									<input type="hidden" name="previewLink" value="${book.previewLink}" />
									<input type="hidden" name="isbn" value="${book.isbn}" />
									<!-- 其他欄位照樣放隱藏欄位 -->
									<button type="submit" class="btn btn-warning btn-sm">收藏書籍</button>
								</form>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>

		<c:if test="${empty bookList && param.query != null}">
			<div class="alert alert-warning">查無結果。</div>
		</c:if>
		<c:if test="${empty books && books != null}">
			<div class="alert alert-warning">書籍收藏查無結果。</div>
		</c:if>
	</div>
	<!-- container 結束 -->
	<!-- 資料來源標示（合法用法） -->
	<!-- Footer 永遠出現在底部 -->
	<footer class="text-center mt-auto py-3" style="background-color: #f8f9fa;">
		<p style="font-size: 0.9em; color: gray;">
			本網站部分書籍資料由
			<a href="https://developers.google.com/books" target="_blank">Google Books API</a>
			提供，僅供學習展示用途。
		</p>
	</footer>
	<script src="js/deleteBook.js"></script>
	<script>
		function submitLocalBooksForm() {
			document.getElementById("localBooksForm").submit();
		}
	</script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>