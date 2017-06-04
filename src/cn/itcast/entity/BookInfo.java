package cn.itcast.entity;

import java.util.Date;

public class BookInfo {
	// ͼ��ID
	private Integer book_id;
	// ͼ��ISBN
	private String book_ISBN;
	// ͼ����
	private String book_title;
	// ����
	private String book_author;
	// ������
	private String book_publisher;
	// ����ʱ��
	private Date book_pubdate;
	// �۸�
	private Float book_price;
	// ͼ������
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
