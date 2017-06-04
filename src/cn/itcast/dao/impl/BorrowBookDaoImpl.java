package cn.itcast.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import cn.itcast.dao.BorrowBookDao;
import cn.itcast.entity.BookInfo;
import cn.itcast.entity.BorrowBook;
import cn.itcast.entity.User;

public class BorrowBookDaoImpl implements BorrowBookDao {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addBook(BorrowBook borrowBook) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			borrowBook.setBorrow_name(borrowBook.getBorrow_name());
			borrowBook.setBorrow_bookname(borrowBook.getBorrow_bookname());
			borrowBook.setBorrow_ISBN(borrowBook.getBorrow_ISBN());
			borrowBook.setBorrow_date(borrowBook.getBorrow_date());
			session.save(borrowBook);

			String hql = "update BookInfo set book_count=book_count-1 where book_id=?";
			BookInfo bookInfo = new BookInfo();
			@SuppressWarnings("unchecked")
			Query<User> query = session.createQuery(hql);
			query.setParameter(0, bookInfo.getBook_id());
			query.setParameter(1, bookInfo.getBook_count());
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
		} finally {

		}
	}

	@Override
	public List<BorrowBook> selectBook(String name) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		List<BorrowBook> borrowBooklist = new ArrayList<BorrowBook>();
		String hql = "from BorrowBook where borrow_name=?";
		@SuppressWarnings("unchecked")
		Query<BorrowBook> query = session.createQuery(hql);
		query.setParameter(0, name);
		List<BorrowBook> bookinfos = query.list();
		borrowBooklist.addAll(bookinfos);

		return borrowBooklist;
	}

	@Override
	public void delectBook(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		BorrowBook borrowBook = session.get(BorrowBook.class, id);
		session.delete(borrowBook);
		tx.commit();
	}

}
