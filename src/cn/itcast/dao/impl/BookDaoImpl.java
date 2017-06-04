package cn.itcast.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import cn.itcast.dao.BookDao;
import cn.itcast.entity.BookInfo;

public class BookDaoImpl implements BookDao {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addBook(BookInfo bookInfo) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		bookInfo.setBook_id(bookInfo.getBook_id());
		bookInfo.setBook_ISBN(bookInfo.getBook_ISBN());
		bookInfo.setBook_title(bookInfo.getBook_title());
		bookInfo.setBook_author(bookInfo.getBook_author());
		bookInfo.setBook_publisher(bookInfo.getBook_publisher());
		bookInfo.setBook_pubdate(bookInfo.getBook_pubdate());
		bookInfo.setBook_price(bookInfo.getBook_price());
		bookInfo.setBook_count(bookInfo.getBook_count());

		session.save(bookInfo);
	}

	@Override
	public BookInfo selectBook(String book_ISBN, String bookname) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		String hql = "from BookInfo where book_ISBN=? or book_title=?";
		BookInfo bookInfo = new BookInfo();
		@SuppressWarnings("unchecked")
		Query<BookInfo> query = session.createQuery(hql);
		query.setParameter(0, book_ISBN);
		query.setParameter(1, bookname);
		List<BookInfo> bookinfos = query.list();
		for (BookInfo bookInfo2 : bookinfos) {
			bookInfo.setBook_id(bookInfo2.getBook_id());
			bookInfo.setBook_ISBN(bookInfo2.getBook_ISBN());
			bookInfo.setBook_title(bookInfo2.getBook_title());
			bookInfo.setBook_author(bookInfo2.getBook_author());
			bookInfo.setBook_publisher(bookInfo2.getBook_publisher());
			bookInfo.setBook_pubdate(bookInfo2.getBook_pubdate());
			bookInfo.setBook_price(bookInfo2.getBook_price());
			bookInfo.setBook_count(bookInfo2.getBook_count());
		}
		return bookInfo;
	}

	@Override
	public void deleteBook(int book_id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		BookInfo bookInfo = session.get(BookInfo.class, book_id);
		session.delete(bookInfo);
		tx.commit();
	}

	@Override
	public void updateBookCount(int book_id, int book_count) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String hql = "update BookInfo set book_count=? where book_id=?";
		@SuppressWarnings({ "unchecked" })
		Query<BookInfo> query = session.createQuery(hql);
		query.setParameter(0, book_count);
		query.setParameter(1, book_id);
		query.executeUpdate();
		tx.commit();
	}

}
