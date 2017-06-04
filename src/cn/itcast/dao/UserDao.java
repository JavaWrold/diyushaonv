package cn.itcast.dao;

import cn.itcast.entity.User;

public interface UserDao {
	// 登录方法
	public abstract boolean login(String name, String password, String jurisdiction);

	// 注册方法
	public abstract void regedist(User user);

	// 查看是否注册
	public abstract boolean findUser(String name);

	// 修改密码
	public abstract void updateUser(String name, String password);

	// 删除用户
	public abstract void deleteUser(int id);

}
