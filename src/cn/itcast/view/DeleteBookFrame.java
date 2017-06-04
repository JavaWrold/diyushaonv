package cn.itcast.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
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

public class DeleteBookFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JPopupMenu popupMenu;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// DeleteBookFrame frame = new DeleteBookFrame();
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
	public DeleteBookFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		UItitl.setPictureground(this, "deletebook.jpg", new JLabel());

		JLabel lblNewLabel = new JLabel("\u5220 \u9664 \u56FE \u4E66");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("����", Font.BOLD, 40));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		MyTableModel tableModel = new MyTableModel();
		table = new JTable(tableModel);
		// �������͸��
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

		// ������ɫ
		// UItitl.setTableRowColor(table, Color.RED, Color.YELLOW, Color.BLUE,
		// Color.GREEN, Color.PINK);

		// ��������������Ч��
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);

		deletemenultem = new JMenuItem("\u5220\u9664");
		deletemenultem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookInfo bookInfo = dataList.get(table.getSelectedRow());
				int bookid = bookInfo.getBook_id();
				@SuppressWarnings("resource")
				ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-book.xml");
				BookServiceDaoImpl bImpl = (BookServiceDaoImpl) context.getBean("bookServiceDao");
				bImpl.deleteBook(bookid);
				JOptionPane.showMessageDialog(contentPane, "ͼ��ɾ���ɹ�");

				dataList.removeAll(dataList);
				table.setModel(new MyTableModel());
			}
		});
		popupMenu.add(deletemenultem);

		updatemenuItem = new JMenuItem("\u4FEE\u6539");
		updatemenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				table.editCellAt(row, col);
			}
		});
		popupMenu.add(updatemenuItem);
	}

	String[] columnNames = { "ͼ��ID", "ͼ��ISBN", "ͼ����", "����", "������", "����ʱ��", "�۸�", "ͼ������" };
	List<BookInfo> dataList = new ArrayList<BookInfo>();
	private JMenuItem deletemenultem;
	private JMenuItem updatemenuItem;

	private class MyTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;

		public MyTableModel() {
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
