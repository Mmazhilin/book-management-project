CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '書籍 ID，自動編號',
    title VARCHAR(255) NOT NULL COMMENT '書名',
    authors VARCHAR(255) COMMENT '作者',
    publisher VARCHAR(255) COMMENT '出版社',
    published_date DATE COMMENT '出版日期',
    description TEXT COMMENT '書籍描述',
    image_links VARCHAR(500) COMMENT '封面圖片連結',
    preview_link VARCHAR(500) COMMENT '預覽連結',
    isbn VARCHAR(20) COMMENT '識別碼（ISBN）'
);
