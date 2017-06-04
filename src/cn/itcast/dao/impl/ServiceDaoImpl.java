package cn.itcast.dao.impl;

import cn.itcast.dao.ServiceDao;
import cn.itcast.dao.UserDao;
import cn.itcast.entity.User;

public class ServiceDaoImpl implements ServiceDao {

	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean login(String name, String password, String jurisdiction) {
		// TODO Auto-generated method stub
		return this.userDao.login(name, password, jurisdiction);
	}

	@Override
	public void regedist(User user) {
		// TODO Auto-generated method stub
		this.userDao.regedist(user);
	}

	@Override
	public boolean findUser(String name) {
		// TODO Auto-generated method stub
		return this.userDao.findUser(name);
	}

	@Override
	public void updateUser(String name,String password) {
		// TODO Auto-generated method stub
		this.userDao.updateUser(name,password);
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		this.userDao.deleteUser(id);
	}

}
