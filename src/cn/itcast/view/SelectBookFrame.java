package cn.itcast.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.dao.impl.BookDaoImpl;
import cn.itcast.dao.impl.BookServiceDaoImpl;
import cn.itcast.entity.BookInfo;
import cn.itcast.utils.UItitl;

public class SelectBookFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// SelectBookFrame frame = new SelectBookFrame();
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
	public SelectBookFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		UItitl.setPictureground(this, "selectbook.jpg", new JLabel());

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u56FE\u4E66ISBN\u6216\u56FE\u4E66\u540D\uFF1A");
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setFont(new Font("����", Font.PLAIN, 14));
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(textField);
		textField.setColumns(20);

		MyTableModel tableModel = new MyTableModel();

		JButton btnNewButton = new JButton("\u67E5 \u8BE2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataList.removeAll(dataList);
				String bookname = textField.getText().trim();
				String book_ISBN = textField.getText().trim();
				MyTableModel tableModel = new MyTableModel(book_ISBN, bookname);
				table.setModel(tableModel);
				textField.setText("");
				textField.requestFocus();
			}
		});
		btnNewButton.setFont(new Font("����", Font.PLAIN, 14));
		panel.add(btnNewButton);

		getRootPane().setDefaultButton(btnNewButton);

		JButton button = new JButton("\u67E5\u8BE2\u6240\u6709");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataList.removeAll(dataList);
				table.setModel(new selectTableModel());
			}
		});
		button.setFont(new Font("����", Font.PLAIN, 14));
		panel.add(button);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable(tableModel);
		scrollPane.setViewportView(table);

		scrollPane.setOpaque(false);// ��JScrollPane����Ϊ͸��
		scrollPane.setViewportView(table);
		scrollPane.getViewport().setOpaque(false);// ���м��viewport����Ϊ͸��
		scrollPane.setColumnHeaderView(table.getTableHeader());// ����ͷ����HeaderView���֣�
		scrollPane.getColumnHeader().setOpaque(false);// ��ȡ��ͷ����������Ϊ͸��

		// ���ñ�ͷ�����϶�
		JTableHeader header = table.getTableHeader();
		header.setReorderingAllowed(false);
		// ���ò����϶���
		header.setResizingAllowed(false);
		// ���õ�Ԫ�����ݾ���
		DefaultTableCellRenderer cr = (DefaultTableCellRenderer) table.getDefaultRenderer(columnNames.getClass());
		cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

		// ���ñ�ͷ����
		TableRowSorter<TableModel> tableRowSorter = new TableRowSorter<TableModel>(tableModel);
		table.setRowSorter(tableRowSorter);

		// �����п�
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(40);
		table.getColumnModel().getColumn(6).setPreferredWidth(40);
		table.getColumnModel().getColumn(7).setPreferredWidth(20);

		// �����и�
		table.setRowHeight(25);
	}

	String[] columnNames = { "ͼ��ID", "ͼ��ISBN", "ͼ����", "����", "������", "����ʱ��", "�۸�", "ͼ������" };
	List<BookInfo> dataList = new ArrayList<BookInfo>();

	// ����ָ��ͼ��ISBN��ͼ������ѯ
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
				JOptionPane.showMessageDialog(contentPane, "��������ȷ��ͼ��ISBN��ͼ����", "����", JOptionPane.WARNING_MESSAGE);
			} else {
				dataList.add(bookInfo);
			}
		}

		/**
		 * �õ��������
		 */
		@Override
		public int getRowCount() {
			return dataList.size();
		}

		/**
		 * �õ��������
		 */
		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		/**
		 * �õ�����
		 */
		@Override
		public String getColumnName(int column) {
			return columnNames[column];
		}

		/**
		 * �õ���������Ӧ����
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
		 * ָ���������ݵ�Ԫ�Ƿ�ɱ༭.��������"����","ѧ��"���ɱ༭
		 */
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if (columnIndex < 7) {
				return false;
			} else {
				return true;
			}
		}

		/**
		 * ������ݵ�ԪΪ�ɱ༭���򽫱༭���ֵ�滻ԭ����ֵ
		 */
		// @Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			BookInfo bookInfo = dataList.get(rowIndex);
			if (columnIndex == 7) {
				bookInfo.setBook_count(Integer.parseInt((String) aValue));
			}
			int bookid = bookInfo.getBook_id();
			int bookcount = bookInfo.getBook_count();
			@SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-book.xml");
			BookServiceDaoImpl bImpl = (BookServiceDaoImpl) context.getBean("bookServiceDao");
			bImpl.updateBookCount(bookid, bookcount);
			JOptionPane.showMessageDialog(contentPane, "ͼ�������޸ĳɹ�");

			/* ֪ͨ���������ݵ�Ԫ�����Ѿ��ı� */
			fireTableCellUpdated(rowIndex, columnIndex);
		}
	}

	// ��ѯ����ͼ��
	private class selectTableModel extends MyTableModel {

		private static final long serialVersionUID = 1L;

		public selectTableModel() {
			@SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-book.xml");
			BookDaoImpl bImpl = (BookDaoImpl) context.getBean("bookDao");
			SessionFactory sessionFactory = bImpl.getSessionFactory();
			Session session = sessionFactory.openSession();
			@SuppressWarnings("unchecked")
			Query<BookInfo> query = session.createQuery("from BookInfo");
			List<BookInfo> books = query.list();
			for (BookInfo book : books) {
				dataList.add(book);
			}
		}
	}

}
