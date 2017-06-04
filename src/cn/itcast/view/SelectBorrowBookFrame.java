package cn.itcast.view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.dao.impl.BorrowBookServiceDaoImpl;
import cn.itcast.entity.BorrowBook;

public class SelectBorrowBookFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// SelectBorrowBookFrame frame = new SelectBorrowBookFrame();
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 */
	public SelectBorrowBookFrame(String username) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("�û�: " + username + " �ѽ�ͼ��");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);
		borrowTableModel bModel = new borrowTableModel(username);
		table.setModel(bModel);
	}

	String[] columnNames1 = { "����ͼ��ID", "����ͼ��ISBN", "����ͼ����", "��������" };
	List<BorrowBook> bList = new ArrayList<BorrowBook>();

	// ��ѯ�ѽ�ͼ��
	@SuppressWarnings("unused")
	private class borrowTableModel extends DefaultTableModel {
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
		 * �õ��������
		 */
		@Override
		public int getRowCount() {
			return bList.size();
		}

		/**
		 * �õ��������
		 */
		@Override
		public int getColumnCount() {
			return columnNames1.length;
		}

		/**
		 * �õ�����
		 */
		@Override
		public String getColumnName(int column) {
			return columnNames1[column];
		}

		/**
		 * �õ���������Ӧ����
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

}
