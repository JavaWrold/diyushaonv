package cn.itcast.dao;

import cn.itcast.entity.User;

public interface UserDao {
	// ��¼����
	public abstract boolean login(String name, String password, String jurisdiction);

	// ע�᷽��
	public abstract void regedist(User user);

	// �鿴�Ƿ�ע��
	public abstract boolean findUser(String name);

	// �޸�����
	public abstract void updateUser(String name, String password);

	// ɾ���û�
	public abstract void deleteUser(int id);

}
