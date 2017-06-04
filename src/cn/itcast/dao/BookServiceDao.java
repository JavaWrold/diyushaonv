package cn.itcast.dao;

import cn.itcast.entity.BookInfo;

public interface BookServiceDao {
	// 添加书籍
	public abstract void addBook(BookInfo bookInfo);

	// 图书查询
	public abstract BookInfo selectBook(String book_ISBN, String bookname);

	// 图书删除
	public abstract void deleteBook(int book_id);

	// 图书修改
	public abstract void updateBookCount(int book_id,int book_count);
}
