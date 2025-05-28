/**
 * deleteBook刪除指定的收藏書籍方法
 * @param confirm 交談窗
 * @param uriComponent 轉換 bookId 讓其為值
 */
function deleteBook(bookId, button) {
	if (!confirm("確定要刪除這本書嗎？")) return;

	fetch('deleteBook', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded'
		},
		body: 'id=' + encodeURIComponent(bookId)
	})
		.then(response => response.json())
		.then(data => {
			if (data.success) {
				// 從畫面移除該筆書籍卡片
				const card = button.closest('.col'); // 假設是在 .col 裡
				if (card) card.remove();
			} else {
				alert("刪除失敗，請稍後再試");
			}
		})
		.catch(error => {
			console.error("錯誤：", error);
			alert("刪除失敗，請稍後再試");
		});
}

