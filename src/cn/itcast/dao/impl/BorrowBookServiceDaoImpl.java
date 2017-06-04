package cn.itcast.dao.impl;

import java.util.List;

import cn.itcast.dao.BorrowBookDao;
import cn.itcast.dao.BorrowBookServiceDao;
import cn.itcast.entity.BorrowBook;

public class BorrowBookServiceDaoImpl implements BorrowBookServiceDao {
	private BorrowBookDao borrowBookDao;

	public BorrowBookDao getBorrowBookDao() {
		return borrowBookDao;
	}

	public void setBorrowBookDao(BorrowBookDao borrowBookDao) {
		this.borrowBookDao = borrowBookDao;
	}

	@Override
	public void addBook(BorrowBook borrowBook) {
		// TODO Auto-generated method stub
		this.borrowBookDao.addBook(borrowBook);
	}

	@Override
	public List<BorrowBook> selectBook(String name) {
		// TODO Auto-generated method stub
		return this.borrowBookDao.selectBook(name);
	}

	@Override
	public void delectBook(int id) {
		// TODO Auto-generated method stub
		this.borrowBookDao.delectBook(id);
	}

}
