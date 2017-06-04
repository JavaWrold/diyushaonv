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
		// JFrame.EXIT_ON_CLOES �������������˳�
		// JFrame.DISPOSE_ON_CLOSE ֻ�õ�ǰ�Ĵ��ڹرն���
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
		// �������͸��
		scrollPane.setOpaque(false);// ��JScrollPane����Ϊ͸��
		scrollPane.setViewportView(table);
		scrollPane.getViewport().setOpaque(false);// ���м��viewport����Ϊ͸��
		scrollPane.setColumnHeaderView(table.getTableHeader());// ����ͷ����HeaderView���֣�
		scrollPane.getColumnHeader().setOpaque(false);// ��ȡ��ͷ����������Ϊ͸��

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = table.getSelectedRow() + 1;
				int y = table.getSelectedColumn() + 1;
				lblNewLabel.setText("��ѡ�е��ǣ���" + x + "��" + y + "��");
			}
		});
		// ���ñ�ͷ�����϶�
		JTableHeader header = table.getTableHeader();
		header.setReorderingAllowed(false);
		// ���ñ��������
		table.setRowSorter(new TableRowSorter<MyTableModel>(tableModel));
		// ���õ�Ԫ�����ݾ���
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
				if (user.getJurisdiction().equals("����Ա")) {
					JOptionPane.showMessageDialog(contentPane, "��û��Ȩ��ɾ������Ա");
				} else {
					@SuppressWarnings("resource")
					ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
					ServiceDaoImpl sImpl = (ServiceDaoImpl) context.getBean("ServicesDao");
					sImpl.deleteUser(id);
					JOptionPane.showMessageDialog(contentPane, "ɾ���ɹ�");

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
				if (user.getJurisdiction().equals("�� ��")) {
					String username = user.getUsername();
					SelectBorrowBookFrame sbbf = new SelectBorrowBookFrame(username);
					sbbf.setVisible(true);
				}
				if (user.getJurisdiction().equals("����Ա")) {
					JOptionPane.showMessageDialog(rootPane, "�������ǹ���Ա");
				}
			}
		});
		popupMenu.add(selectbookmenuItem);
	}

	String[] columnNames = { "ID", "�û���", "����", "Ȩ��" };
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
		 * ָ���������ݵ�Ԫ�Ƿ�ɱ༭.��������"����","ѧ��"���ɱ༭
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
		 * ������ݵ�ԪΪ�ɱ༭���򽫱༭���ֵ�滻ԭ����ֵ
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
			JOptionPane.showMessageDialog(contentPane, "�����޸ĳɹ�");

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
