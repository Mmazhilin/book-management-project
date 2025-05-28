# 書籍查詢小專案（Book Search Mini Project）

本專案是一個使用 Java、JSP、Servlet 與 JDBC 技術製作的書籍查詢平台，整合 Google Books API，實現書籍搜尋、資訊顯示、封面展示、預覽連結與價格引用等功能。此專案僅作為學習用途使用。

##🔧使用技術
- Java
- JSP / Servlet
- JDBC + MySQL
- Google Books API
- Bootstrap + JSTL + AJAX(刪除功能)

## 📸 功能畫面介紹
- Google 書籍查詢與顯示
- 點選加入書籍 → 預填表單新增
- 顯示收藏書籍（資料庫）
- 使用 AJAX 無刷新刪除書籍

## 🗂️ 資料表設計
見 `/sql/create_books.sql`

## 📝 使用方式
1. 將專案匯入 Eclipse 或 IntelliJ
2. 建立資料庫並匯入 `/sql/create_books.sql`
3. 設定 `/DBUtil.java` 的連線帳密
4. 執行伺服器（如 Tomcat）

## 🖼️ Demo 截圖
### 首頁畫面
(docs/首頁.jpg)
### google book api搜尋書籍
(docs/image/搜尋書籍.jpg)
### 收藏書籍
(docs/image/收藏.jpg)
### 查詢剛剛收藏的書籍
(docs/image/查詢收藏書籍.jpg)
### 刪除指定的收藏書籍
(docs/image/刪除書籍.jpg)

## 📁 專案結構
```
BookManagementProject/
├── src/                            # Java 程式碼（Servlet、DAO、Bean）
│   └── com/example/
│       ├── controller/             # Servlet 控制器（BookServlet、AddBookServlet、DeleteBookServlet）
│       ├── model/
│       │   ├── Book.java           # 書籍資料模型（JavaBean）
│       │   └── dao/
│       │       ├── BookDAO.java    # DAO 介面
│       │       ├── BookDAOImpl.java# DAO 實作
│       │       └── DBUtil.java     # 資料庫連線工具
│       └── util/
│           └── GoogleBookApiOkHttp.java  # API 串接工具類
│
├── webapp/                         # 前端資源（JSP、CSS、JavaScript）
│   ├── bookList.jsp                # 查詢與收藏書籍畫面
│   ├── addBook.jsp                 # 新增書籍畫面
│   ├── css/                        # CSS 樣式（可選）
│   └── js/  
│	 └── deleteBook.js           # JavaScript（AJAX 刪除等）
│	
├── sql/                            # 資料庫建表語法
│   └── create_books.sql
│
├── docs/                           # 文件區（流程說明、資料表、畫面圖）
│   └── 工作流程.docx
│
└── README.md                       # GitHub 專案說明文件
```
## 🔗 資料來源
本專案使用 Google Books API 顯示書籍資訊（如書名、作者、封面、價格等），所有資料皆來源自 Google，僅供學習與非商業用途之展示使用。

請勿轉載或利用本專案內容作為任何商業用途，如有違反者請自行負責，本人不負任何法律責任。

> This project uses the Google Books API to display book information (such as title, author, cover, price, etc.). All data is provided by Google and intended for educational and non-commercial use only.  
>  
> Commercial use or redistribution of this project or its contents is strictly prohibited. Any such actions are the sole responsibility of the individual.

## 📝 授權 License

本專案採用 MIT 授權條款。

```text
MIT License

Copyright (c) 2025 馬志霖

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights  
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, subject to the following conditions:  

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.  

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,  
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER  
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,  
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  
SOFTWARE.
