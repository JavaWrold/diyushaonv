package cn.itcast.dao;

import java.util.List;

import cn.itcast.entity.BorrowBook;

public interface BorrowBookServiceDao {
	public abstract void addBook(BorrowBook borrowBook);

	public abstract List<BorrowBook> selectBook(String name);

	public abstract void delectBook(int id);
}
