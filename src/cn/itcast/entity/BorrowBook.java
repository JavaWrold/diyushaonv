package cn.itcast.entity;

public class BorrowBook {
	private Integer borrow_id;
	private String borrow_ISBN;
	private String borrow_name;
	private String borrow_bookname;
	private String borrow_date;

	public Integer getBorrow_id() {
		return borrow_id;
	}

	public void setBorrow_id(Integer borrow_id) {
		this.borrow_id = borrow_id;
	}

	public String getBorrow_ISBN() {
		return borrow_ISBN;
	}

	public void setBorrow_ISBN(String borrow_ISBN) {
		this.borrow_ISBN = borrow_ISBN;
	}

	public String getBorrow_name() {
		return borrow_name;
	}

	public void setBorrow_name(String borrow_name) {
		this.borrow_name = borrow_name;
	}

	public String getBorrow_bookname() {
		return borrow_bookname;
	}

	public void setBorrow_bookname(String borrow_bookname) {
		this.borrow_bookname = borrow_bookname;
	}

	public String getBorrow_date() {
		return borrow_date;
	}

	public void setBorrow_date(String borrow_date) {
		this.borrow_date = borrow_date;
	}

}
