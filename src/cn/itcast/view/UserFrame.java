package cn.itcast.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.dao.impl.BookDaoImpl;
import cn.itcast.dao.impl.BookServiceDaoImpl;
import cn.itcast.dao.impl.BorrowBookDaoImpl;
import cn.itcast.dao.impl.BorrowBookServiceDaoImpl;
import cn.itcast.dao.impl.ServiceDaoImpl;
import cn.itcast.entity.BookInfo;
import cn.itcast.entity.BorrowBook;
import cn.itcast.utils.UItitl;

public class UserFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPopupMenu popupMenu;
	private JButton button_1;

	/**
	 * Create the frame.
	 */
	public UserFrame(String name) {
		init(name);
		UItitl.setFrameImage(this, "user.png");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 549, 414);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		UItitl.setPictureground(this, "08.jpg", new JLabel());

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.setOpaque(false);

		JPanel selectBookPanel = new JPanel();
		selectBookPanel.setLayout(new BorderLayout(0, 0));
		tabbedPane.addTab("\u56FE\u4E66\u67E5\u8BE2", null, selectBookPanel, null);
		selectBookPanel.setOpaque(false);

		JPanel panel = new JPanel();
		selectBookPanel.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.setOpaque(false);

		JLabel lblNewLabel_1 = new JLabel(
				"\u8BF7\u8F93\u5165\u8981\u67E5\u8BE2\u7684\u56FE\u4E66ISBN\u6216\u56FE\u4E66\u540D\uFF1A");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		panel.add(lblNewLabel_1);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("宋体", Font.PLAIN, 14));
		panel.add(textField_2);
		textField_2.setColumns(20);

		// 查询按钮
		JButton btnNewButton_1 = new JButton("\u67E5 \u8BE2");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataList.removeAll(dataList);
				String bookISBN = textField_2.getText().trim();
				String bookName = textField_2.getText().trim();

				MyTableModel tableModel = new MyTableModel(bookISBN, bookName);
				table.setModel(tableModel);
				textField_2.setText("");
				textField_2.requestFocus();
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 14));
		panel.add(btnNewButton_1);

		JScrollPane scrollPane = new JScrollPane();
		selectBookPanel.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setOpaque(false);

		MyTableModel tableModel = new MyTableModel();
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		scrollPane.getViewport().setOpaque(false);// 将中间的viewport设置为透明

		// 设置表头不可拖动
		JTableHeader header = table.getTableHeader();
		header.setReorderingAllowed(false);
		// 设置不可拖动列
		header.setResizingAllowed(false);
		// 设置单元格内容居中
		DefaultTableCellRenderer cr = (DefaultTableCellRenderer) table.getDefaultRenderer(columnNames.getClass());
		cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

		// 设置表头排序
		TableRowSorter<TableModel> tableRowSorter = new TableRowSorter<TableModel>(tableModel);
		table.setRowSorter(tableRowSorter);

		// 设置列宽
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(40);
		table.getColumnModel().getColumn(6).setPreferredWidth(40);
		table.getColumnModel().getColumn(7).setPreferredWidth(20);

		// 设置行高
		table.setRowHeight(25);

		popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);

		JMenuItem menuItem = new JMenuItem("\u501F\u9605");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookInfo bookInfo = dataList.get(table.getSelectedRow());
				BorrowBook borrowBook = new BorrowBook();
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
				String date1 = sdf.format(date);

				if (bookInfo.getBook_count() <= 0) {
					JOptionPane.showMessageDialog(contentPane, "sorry,图书已经被借完");
					return;
				}

				Transaction tx = null;
				try {
					@SuppressWarnings("resource")
					ApplicationContext context1 = new ClassPathXmlApplicationContext("applicationContext-book.xml");
					BookDaoImpl bImpl1 = (BookDaoImpl) context1.getBean("bookDao");
					SessionFactory sessionFactory2 = bImpl1.getSessionFactory();
					Session session2 = sessionFactory2.openSession();
					tx = session2.beginTransaction();
					String hql = "update BookInfo set book_count=book_count-1 where book_ISBN=?";
					@SuppressWarnings("unchecked")
					Query<BookInfo> query1 = session2.createQuery(hql);
					query1.setParameter(0, bookInfo.getBook_ISBN());
					query1.executeUpdate();

					// int i = 10 / 0;
					// System.out.println(i);
					// 没有进行回滚

					@SuppressWarnings("resource")
					ApplicationContext context = new ClassPathXmlApplicationContext(
							"applicationContext-borrowbook.xml");
					BorrowBookDaoImpl bImpl = (BorrowBookDaoImpl) context.getBean("borrowBookDao");
					SessionFactory sessionFactory = bImpl.getSessionFactory();
					Session session = sessionFactory.openSession();
					// tx = session.beginTransaction();
					borrowBook.setBorrow_name(name);
					borrowBook.setBorrow_bookname(bookInfo.getBook_title());
					borrowBook.setBorrow_ISBN(bookInfo.getBook_ISBN());
					borrowBook.setBorrow_date(date1);
					session.save(borrowBook);
					JOptionPane.showMessageDialog(contentPane, "借阅成功");

					tx.commit();
					dataList.removeAll(dataList);
					table.setModel(new MyTableModel());
				} catch (Exception e1) {
					tx.rollback();
					e1.printStackTrace();
				} finally {

				}
			}
		});
		popupMenu.add(menuItem);

		JPanel selectBorrowBookPanel = new JPanel();
		selectBorrowBookPanel.setLayout(new BorderLayout(0, 0));
		tabbedPane.addTab("\u5DF2\u501F\u56FE\u4E66", null, selectBorrowBookPanel, null);
		selectBorrowBookPanel.setOpaque(false);

		JScrollPane scrollPane_1 = new JScrollPane();
		selectBorrowBookPanel.add(scrollPane_1, BorderLayout.CENTER);
		scrollPane_1.setOpaque(false);

		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		scrollPane_1.getViewport().setOpaque(false);// 将中间的viewport设置为透明

		borrowTableModel borrowtableModel = new borrowTableModel();
		table_1.setModel(borrowtableModel);

		// 设置表头不可拖动
		JTableHeader header1 = table_1.getTableHeader();
		header1.setReorderingAllowed(false);
		// 设置不可拖动列
		header1.setResizingAllowed(false);
		// 设置单元格内容居中
		DefaultTableCellRenderer cr1 = (DefaultTableCellRenderer) table_1.getDefaultRenderer(columnNames1.getClass());
		cr1.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

		// 设置表头排序
		TableRowSorter<TableModel> tableRowSorter1 = new TableRowSorter<TableModel>(borrowtableModel);
		table_1.setRowSorter(tableRowSorter1);

		// 设置列宽
		table_1.getColumnModel().getColumn(0).setPreferredWidth(20);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(80);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(120);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(40);

		// 设置行高
		table_1.setRowHeight(25);

		JPanel panel_1 = new JPanel();
		selectBorrowBookPanel.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// 查询
		JButton button = new JButton("\u67E5 \u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bList.removeAll(bList);
				borrowTableModel tModel = new borrowTableModel(name);
				if (bList.size() != 0) {
					button_1.setEnabled(true);
					table_1.setModel(tModel);
				} else {
					JOptionPane.showMessageDialog(rootPane, "对不起，你还没有借书");
				}

			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_1.add(button);

		// 归还按钮
		button_1 = new JButton("\u5F52 \u8FD8");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookInfo bookInfo = new BookInfo();
				BorrowBook book = bList.get(table_1.getSelectedRow());
				bookInfo.setBook_ISBN(book.getBorrow_ISBN());
				Transaction tx = null;
				try {
					@SuppressWarnings("resource")
					ApplicationContext context1 = new ClassPathXmlApplicationContext("applicationContext-book.xml");
					BookDaoImpl bImpl1 = (BookDaoImpl) context1.getBean("bookDao");
					SessionFactory sessionFactory2 = bImpl1.getSessionFactory();
					Session session2 = sessionFactory2.openSession();
					tx = session2.beginTransaction();
					String hql = "update BookInfo set book_count=book_count+1 where book_ISBN=?";
					@SuppressWarnings("unchecked")
					Query<BookInfo> query1 = session2.createQuery(hql);
					query1.setParameter(0, bookInfo.getBook_ISBN());
					query1.executeUpdate();

					// int i = 10 / 0;
					// System.out.println(i);
					// 没有进行回滚

					int id = book.getBorrow_id();
					@SuppressWarnings("resource")
					ApplicationContext context = new ClassPathXmlApplicationContext(
							"applicationContext-borrowbook.xml");
					BorrowBookServiceDaoImpl bDaoImpl = (BorrowBookServiceDaoImpl) context
							.getBean("borrowBookServiceDao");
					bDaoImpl.delectBook(id);
					JOptionPane.showMessageDialog(contentPane, "图书还书操作成功");

					tx.commit();
					bList.removeAll(bList);
					borrowTableModel tModel = new borrowTableModel(name);
					table_1.setModel(tModel);
				} catch (Exception e1) {
					tx.rollback();
					e1.printStackTrace();
				} finally {

				}
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 14));
		button_1.setEnabled(false);
		panel_1.add(button_1);

		JPanel updatUserPanel = new JPanel();
		updatUserPanel.setLayout(null);
		tabbedPane.addTab("\u4FEE\u6539\u5BC6\u7801\r\n", null, updatUserPanel, null);
		updatUserPanel.setOpaque(false);

		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u65B0\u5BC6\u7801");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(133, 38, 238, 26);
		updatUserPanel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(133, 90, 238, 32);
		updatUserPanel.add(textField);
		textField.setColumns(10);

		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u65B0\u5BC6\u7801");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(133, 146, 238, 26);
		updatUserPanel.add(label);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(133, 198, 238, 32);
		updatUserPanel.add(textField_1);

		// 确定按钮
		JButton btnNewButton = new JButton("\u786E\u5B9A\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newpassword = textField.getText().trim();
				String againpassword = textField_1.getText().trim();

				String regidterpasswordregex = "\\w{6,12}";
				if (!newpassword.matches(regidterpasswordregex)) {
					JOptionPane.showMessageDialog(contentPane, "密码不满足条件（6~12个任意字符）");
					textField.setText("");
					textField.requestFocus();
					return;
				}
				if (!newpassword.equals(againpassword)) {
					JOptionPane.showMessageDialog(contentPane, "两次输入的密码不一致，请重新输入");
					textField.setText("");
					textField.requestFocus();
					textField_1.setText("");
					return;
				}

				@SuppressWarnings("resource")
				ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
				ServiceDaoImpl sImpl = (ServiceDaoImpl) context.getBean("ServicesDao");
				sImpl.updateUser(name, newpassword);

				JOptionPane.showMessageDialog(contentPane, "密码修改成功");
				LoginFrame lFrame = new LoginFrame();
				lFrame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton.setBounds(199, 258, 100, 32);
		updatUserPanel.add(btnNewButton);
		getRootPane().setDefaultButton(btnNewButton);
	}

	public void init(String name) {
		this.setTitle("欢迎您！用户：" + name);
	}

	String[] columnNames = { "图书ID", "图书ISBN", "图书名", "作者", "出版社", "出版时间", "价格", "图书数量" };
	List<BookInfo> dataList = new ArrayList<BookInfo>();
	private JTextField textField_2;
	private JTable table;

	// 根据图书ISBN和图书名查询
	private class MyTableModel extends DefaultTableModel {
		private static final long serialVersionUID = 1L;

		public MyTableModel() {

		}

		public MyTableModel(String book_ISBN, String bookname) {
			@SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-book.xml");
			BookServiceDaoImpl bImpl = (BookServiceDaoImpl) context.getBean("bookServiceDao");
			BookInfo bookInfo = bImpl.selectBook(book_ISBN, bookname);
			if (!(book_ISBN.equals(bookInfo.getBook_ISBN()) || bookname.equals(bookInfo.getBook_title()))) {
				JOptionPane.showMessageDialog(contentPane, "请输入正确的图书ISBN或图书名", "警告", JOptionPane.WARNING_MESSAGE);
				return;
			}
			dataList.add(bookInfo);
		}

		/**
		 * 得到表格行数
		 */
		@Override
		public int getRowCount() {
			return dataList.size();
		}

		/**
		 * 得到表格列数
		 */
		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		/**
		 * 得到列名
		 */
		@Override
		public String getColumnName(int column) {
			return columnNames[column];
		}

		/**
		 * 得到数据所对应对象
		 */
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			BookInfo bookInfo = dataList.get(rowIndex);
			if (columnIndex == 0)
				return bookInfo.getBook_id();
			else if (columnIndex == 1)
				return bookInfo.getBook_ISBN();
			else if (columnIndex == 2) {
				return bookInfo.getBook_title();
			} else if (columnIndex == 3) {
				return bookInfo.getBook_author();
			} else if (columnIndex == 4) {
				return bookInfo.getBook_publisher();
			} else if (columnIndex == 5) {
				return bookInfo.getBook_pubdate();
			} else if (columnIndex == 6) {
				return bookInfo.getBook_price();
			} else {
				return bookInfo.getBook_count();
			}
		}

		/**
		 * 指定设置数据单元是否可编辑.这里设置"姓名","学号"不可编辑
		 */
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

	}

	String[] columnNames1 = { "借阅图书ID", "借阅图书ISBN", "借阅图书名", "借阅日期" };
	List<BorrowBook> bList = new ArrayList<BorrowBook>();
	private JTable table_1;

	// 查询已借图书
	private class borrowTableModel extends MyTableModel {
		private static final long serialVersionUID = 1L;

		public borrowTableModel() {

		}

		public borrowTableModel(String name) {
			@SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-borrowbook.xml");
			BorrowBookServiceDaoImpl bDaoImpl = (BorrowBookServiceDaoImpl) context.getBean("borrowBookServiceDao");
			List<BorrowBook> borrowlist = bDaoImpl.selectBook(name);
			bList.addAll(borrowlist);
		}

		/**
		 * 得到表格行数
		 */
		@Override
		public int getRowCount() {
			return bList.size();
		}

		/**
		 * 得到表格列数
		 */
		@Override
		public int getColumnCount() {
			return columnNames1.length;
		}

		/**
		 * 得到列名
		 */
		@Override
		public String getColumnName(int column) {
			return columnNames1[column];
		}

		/**
		 * 得到数据所对应对象
		 */
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			BorrowBook borrowBook = bList.get(rowIndex);
			if (columnIndex == 0)
				return borrowBook.getBorrow_id();
			else if (columnIndex == 1)
				return borrowBook.getBorrow_ISBN();
			else if (columnIndex == 2) {
				return borrowBook.getBorrow_bookname();
			} else {
				return borrowBook.getBorrow_date();
			}
		}

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
