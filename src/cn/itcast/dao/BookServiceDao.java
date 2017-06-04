package cn.itcast.dao;

import cn.itcast.entity.BookInfo;

public interface BookServiceDao {
	// ����鼮
	public abstract void addBook(BookInfo bookInfo);

	// ͼ���ѯ
	public abstract BookInfo selectBook(String book_ISBN, String bookname);

	// ͼ��ɾ��
	public abstract void deleteBook(int book_id);

	// ͼ���޸�
	public abstract void updateBookCount(int book_id,int book_count);
}
