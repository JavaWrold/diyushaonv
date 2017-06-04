package cn.itcast.entity;

import java.util.Date;

public class BookInfo {
	// 图书ID
	private Integer book_id;
	// 图书ISBN
	private String book_ISBN;
	// 图书名
	private String book_title;
	// 作者
	private String book_author;
	// 出版社
	private String book_publisher;
	// 出版时间
	private Date book_pubdate;
	// 价格
	private Float book_price;
	// 图书数量
	private Integer book_count;

	public Integer getBook_id() {
		return book_id;
	}

	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}

	public String getBook_ISBN() {
		return book_ISBN;
	}

	public void setBook_ISBN(String book_ISBN) {
		this.book_ISBN = book_ISBN;
	}

	public String getBook_title() {
		return book_title;
	}

	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}

	public String getBook_author() {
		return book_author;
	}

	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}

	public String getBook_publisher() {
		return book_publisher;
	}

	public void setBook_publisher(String book_publisher) {
		this.book_publisher = book_publisher;
	}

	public Date getBook_pubdate() {
		return book_pubdate;
	}

	public void setBook_pubdate(Date book_pubdate) {
		this.book_pubdate = book_pubdate;
	}

	public Float getBook_price() {
		return book_price;
	}

	public void setBook_price(Float book_price) {
		this.book_price = book_price;
	}

	public Integer getBook_count() {
		return book_count;
	}

	public void setBook_count(Integer book_count) {
		this.book_count = book_count;
	}

	@Override
	public String toString() {
		return "BookInfo [book_id=" + book_id + ", book_ISBN=" + book_ISBN + ", book_title=" + book_title
				+ ", book_author=" + book_author + ", book_publisher=" + book_publisher + ", book_pubdate="
				+ book_pubdate + ", book_price=" + book_price + ", book_count=" + book_count + "]";
	}

}
