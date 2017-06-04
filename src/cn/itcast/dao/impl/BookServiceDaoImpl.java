package cn.itcast.dao.impl;

import cn.itcast.dao.BookDao;
import cn.itcast.dao.BookServiceDao;
import cn.itcast.entity.BookInfo;

public class BookServiceDaoImpl implements BookServiceDao {
	private BookDao bookDao;

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public void addBook(BookInfo bookInfo) {
		// TODO Auto-generated method stub
		this.bookDao.addBook(bookInfo);
	}

	@Override
	public BookInfo selectBook(String book_ISBN, String bookname) {
		// TODO Auto-generated method stub
		return this.bookDao.selectBook(book_ISBN, bookname);
	}

	@Override
	public void deleteBook(int book_id) {
		// TODO Auto-generated method stub
		this.bookDao.deleteBook(book_id);
	}

	@Override
	public void updateBookCount(int book_id, int book_count) {
		// TODO Auto-generated method stub
		this.bookDao.updateBookCount(book_id, book_count);
	}

}
