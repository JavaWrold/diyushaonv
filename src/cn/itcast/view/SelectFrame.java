package cn.itcast.view;

import java.awt.Component;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.dao.impl.ServiceDaoImpl;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.entity.User;
import cn.itcast.utils.UItitl;

public class SelectFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JPanel Deletepanel;
	private JPopupMenu popupMenu;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// DeleteFrame frame = new DeleteFrame();
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printSt ackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 */
	public SelectFrame() {
		// JFrame.EXIT_ON_CLOES 会让整个程序都退出
		// JFrame.DISPOSE_ON_CLOSE 只让当前的窗口关闭而已
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(170, 203, 508, 343);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		UItitl.setFrameImage(this, "delete.jpg");
		UItitl.setPictureground(this, "09.jpg", new JLabel());

		Deletepanel = new JPanel();
		Deletepanel.setBounds(10, 10, 472, 284);
		Deletepanel.setLayout(null);
		Deletepanel.setOpaque(false);
		contentPane.add(Deletepanel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 462, 255);
		Deletepanel.add(scrollPane);

		MyTableModel tableModel = new MyTableModel();
		table = new JTable(tableModel);
		// 设置面板透明
		scrollPane.setOpaque(false);// 将JScrollPane设置为透明
		scrollPane.setViewportView(table);
		scrollPane.getViewport().setOpaque(false);// 将中间的viewport设置为透明
		scrollPane.setColumnHeaderView(table.getTableHeader());// 设置头部（HeaderView部分）
		scrollPane.getColumnHeader().setOpaque(false);// 再取出头部，并设置为透明

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = table.getSelectedRow() + 1;
				int y = table.getSelectedColumn() + 1;
				lblNewLabel.setText("你选中的是，第" + x + "行" + y + "列");
			}
		});
		// 设置表头不可拖动
		JTableHeader header = table.getTableHeader();
		header.setReorderingAllowed(false);
		// 设置表格排序器
		table.setRowSorter(new TableRowSorter<MyTableModel>(tableModel));
		// 设置单元格内容居中
		DefaultTableCellRenderer cr = (DefaultTableCellRenderer) table.getDefaultRenderer(columnNames.getClass());
		cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(302, 253, 160, 31);
		Deletepanel.add(lblNewLabel);

		popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);

		deleteBt = new JMenuItem("\u5220 \u9664");
		deleteBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				User user = dataList.get(index);
				int id = user.getUid();
				if (user.getJurisdiction().equals("管理员")) {
					JOptionPane.showMessageDialog(contentPane, "你没有权限删除管理员");
				} else {
					@SuppressWarnings("resource")
					ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
					ServiceDaoImpl sImpl = (ServiceDaoImpl) context.getBean("ServicesDao");
					sImpl.deleteUser(id);
					JOptionPane.showMessageDialog(contentPane, "删除成功");

					dataList.removeAll(dataList);
					table.setModel(new MyTableModel());
				}
			}
		});
		popupMenu.add(deleteBt);

		updateBt = new JMenuItem("\u4FEE \u6539");
		updateBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				table.editCellAt(row, col);
			}
		});
		popupMenu.add(updateBt);

		selectbookmenuItem = new JMenuItem("\u67E5\u8BE2\u7528\u6237\u5DF2\u501F\u56FE\u4E66");
		selectbookmenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				User user = dataList.get(index);
				if (user.getJurisdiction().equals("用 户")) {
					String username = user.getUsername();
					SelectBorrowBookFrame sbbf = new SelectBorrowBookFrame(username);
					sbbf.setVisible(true);
				}
				if (user.getJurisdiction().equals("管理员")) {
					JOptionPane.showMessageDialog(rootPane, "你点击的是管理员");
				}
			}
		});
		popupMenu.add(selectbookmenuItem);
	}

	String[] columnNames = { "ID", "用户名", "密码", "权限" };
	List<User> dataList = new ArrayList<User>();
	private JMenuItem deleteBt;
	private JMenuItem updateBt;
	private JMenuItem selectbookmenuItem;

	private class MyTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;

		public MyTableModel() {
			@SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			UserDaoImpl uImpl = (UserDaoImpl) context.getBean("userDao");
			SessionFactory sessionFactory = uImpl.getSessionFactory();
			Session session = sessionFactory.openSession();
			@SuppressWarnings("unchecked")
			Query<User> query = session.createQuery("from User");
			List<User> users = query.list();
			for (User user : users) {
				dataList.add(user);
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
			User user = dataList.get(rowIndex);
			if (columnIndex == 0)
				return user.getUid();
			else if (columnIndex == 1)
				return user.getUsername();
			else if (columnIndex == 2) {
				return user.getPassword();
			} else {
				return user.getJurisdiction();
			}
		}

		/**
		 * 指定设置数据单元是否可编辑.这里设置"姓名","学号"不可编辑
		 */
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if (columnIndex < 2) {
				return false;
			} else if (columnIndex > 2) {
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
			User user = dataList.get(rowIndex);
			if (columnIndex == 2) {
				user.setPassword((String) aValue);
			}
			String password = user.getPassword();
			String name = user.getUsername();
			@SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			ServiceDaoImpl sImpl = (ServiceDaoImpl) context.getBean("ServicesDao");
			sImpl.updateUser(name, password);
			JOptionPane.showMessageDialog(contentPane, "密码修改成功");

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
