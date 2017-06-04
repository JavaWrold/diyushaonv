package cn.itcast.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import cn.itcast.dao.UserDao;
import cn.itcast.entity.User;

public class UserDaoImpl implements UserDao {

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public boolean login(String name, String password, String jurisdiction) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Session session = sessionFactory.openSession();
		String hql = "from User where username=? and password=? and jurisdiction=?";
		Query<User> query = session.createQuery(hql);
		query.setParameter(0, name);
		query.setParameter(1, password);
		query.setParameter(2, jurisdiction);
		List<User> users = query.list();
		for (User user2 : users) {
			if (user2.getUsername().equals(name) && user2.getPassword().equals(password)
					&& user2.getJurisdiction().equals(jurisdiction)) {
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public void regedist(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		user.setUsername(user.getUsername());
		user.setPassword(user.getPassword());
		user.setJurisdiction(user.getJurisdiction());
		session.save(user);
	}

	@Override
	public boolean findUser(String name) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Session session = sessionFactory.openSession();
		String hql = "from User where username=?";
		@SuppressWarnings("unchecked")
		Query<User> query = session.createQuery(hql);
		query.setParameter(0, name);
		List<User> users = query.list();
		for (User user2 : users) {
			if (user2.getUsername().equals(name)) {
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public void updateUser(String name, String password) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		// String hql = "update User user set user.password=? where
		// user.username=?";
		String hql1 = "update User set password=? where username=?";
		@SuppressWarnings({ "unchecked" })
		Query<User> query = session.createQuery(hql1);
		query.setParameter(0, password);
		query.setParameter(1, name);
		query.executeUpdate();
		tx.commit();
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		User user = session.get(User.class, id);
		session.delete(user);
		tx.commit();
	}

}
