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
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 40));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		MyTableModel tableModel = new MyTableModel();
		table = new JTable(tableModel);
		// 设置面板透明
		scrollPane.setOpaque(false);// 将JScrollPane设置为透明
		scrollPane.setViewportView(table);
		scrollPane.getViewport().setOpaque(false);// 将中间的viewport设置为透明
		scrollPane.setColumnHeaderView(table.getTableHeader());// 设置头部（HeaderView部分）
		scrollPane.getColumnHeader().setOpaque(false);// 再取出头部，并设置为透明

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

		// 设置颜色
		// UItitl.setTableRowColor(table, Color.RED, Color.YELLOW, Color.BLUE,
		// Color.GREEN, Color.PINK);

		// 设置面板滚动条的效果
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
				JOptionPane.showMessageDialog(contentPane, "图书删除成功");

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

	String[] columnNames = { "图书ID", "图书ISBN", "图书名", "作者", "出版社", "出版时间", "价格", "图书数量" };
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
			if (columnIndex < 7) {
				return false;
			} else {
				return true;
			}
		}

		/**
		 * 如果数据单元为可编辑，则将编辑后的值替换原来的值
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
			JOptionPane.showMessageDialog(contentPane, "图书数量修改成功");

			/* 通知监听器数据单元数据已经改变 */
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
