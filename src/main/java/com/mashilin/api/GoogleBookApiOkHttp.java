package com.mashilin.api;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mashilin.model.Book;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Google Book API 處理工具
 */
public class GoogleBookApiOkHttp {
	private static final OkHttpClient client = new OkHttpClient();

	public static List<Book> searchBooks(String query) {
		List<Book> books = new ArrayList<>();
		String apiUrl = "https://www.googleapis.com/books/v1/volumes?q=" + query;

		Request request = new Request.Builder().url(apiUrl).build();

		try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful()) {//API請求失敗：
				System.err.println("API request failed: " + response.code());
				return books;
			}

			String responseBody = response.body().string();
			JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
			JsonArray items = json.getAsJsonArray("items");

			if (items == null)
				return books;

			for (JsonElement itemElem : items) {
				JsonObject volumeInfo = itemElem.getAsJsonObject().getAsJsonObject("volumeInfo");
				JsonObject saleInfo = itemElem.getAsJsonObject().getAsJsonObject("saleInfo");

				Book book = new Book();
				book.setTitle(getString(volumeInfo, "title"));
				book.setAuthors(joinArray(volumeInfo.getAsJsonArray("authors")));
				book.setPublisher(getString(volumeInfo, "publisher"));
				book.setPublishedDate(parsePublishedDate(getString(volumeInfo, "publishedDate")));
				book.setDescription(getString(volumeInfo, "description"));
				book.setPreviewLink(getString(volumeInfo, "previewLink"));

				JsonObject imageLinks = volumeInfo.getAsJsonObject("imageLinks");
				if (imageLinks != null) {
					book.setImageLinks(getString(imageLinks, "thumbnail"));
				}

				JsonArray identifiers = volumeInfo.getAsJsonArray("industryIdentifiers");
				if (identifiers != null && identifiers.size() > 0) {
					book.setIsbn(getString(identifiers.get(0).getAsJsonObject(), "identifier"));
				}

				if (saleInfo != null) {
					book.setBuyLink(getString(saleInfo, "buyLink"));
				}

				books.add(book);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return books;
	}

	//判斷obj中是否包含指定的key，若有則取得其值並轉換為字串，否則回傳null
	private static String getString(JsonObject obj, String key) {
		return (obj != null && obj.has(key)) ? obj.get(key).getAsString() : null;
	}

	// 用來解析 JSON 陣列，當中包含多個值
	private static String joinArray(JsonArray array) {
		if (array == null)
			return "";
		List<String> list = new ArrayList<>();
		for (JsonElement el : array)
			list.add(el.getAsString());
		return String.join(", ", list);
	}

	// 將日期字串轉換為 LocalDate，例如: "2024-07-22"
	// 若格式為 "2024" 或 "2024-07"，會自動補齊成完整日期
	private static LocalDate parsePublishedDate(String dateStr) {
		try {
			return LocalDate.parse(
					dateStr.length() == 4 ? dateStr + "-01-01" : dateStr.length() == 7 ? dateStr + "-01" : dateStr);
		} catch (Exception e) {
			return null;
		}
	}

}
