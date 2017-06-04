package cn.itcast.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.dao.impl.ServiceDaoImpl;
import cn.itcast.entity.User;
import cn.itcast.utils.UItitl;

public class AdminFrame extends JFrame {

	/**
	 * open with WindowBuilder Editor
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel UpdateJpane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPanel addPanel;
	private JTextField addtf_1;
	private JTextField addtf_2;
	private JLabel library;
	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// AdminFrame frame = new AdminFrame();
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
	public AdminFrame(String name) {
		init(name);
		UItitl.setFrameImage(this, "admin.jpg");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// 设置背景图片
		UItitl.setPictureground(this, "library.jpg", library);
		UItitl.setCenter(this);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 105, 21);
		contentPane.add(menuBar);

		JMenu mnNewMenu = new JMenu("\u7528\u6237\u7BA1\u7406");
		mnNewMenu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateJpane.setVisible(true);
				addPanel.setVisible(false);
				SelectFrame dFrame = new SelectFrame();
				dFrame.setVisible(false);
				dFrame.dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u67E5\u8BE2\u7528\u6237");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPanel.setVisible(false);
				UpdateJpane.setVisible(false);
				SelectFrame dFrame = new SelectFrame();
				dFrame.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("\u6DFB\u52A0\u7528\u6237\r\n");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPanel.setVisible(true);
				UpdateJpane.setVisible(false);
				SelectFrame dFrame = new SelectFrame();
				dFrame.setVisible(false);
				dFrame.dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);

		JMenu mnNewMenu_1 = new JMenu("\u56FE\u4E66\u7BA1\u7406");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("\u56FE\u4E66\u6982\u8FF0\r\n");
		mnNewMenu_1.add(mntmNewMenuItem_3);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("\u56FE\u4E66\u5220\u9664\r\n");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteBookFrame dFrame = new DeleteBookFrame();
				dFrame.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("\u56FE\u4E66\u5165\u5E93");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateJpane.setVisible(false);
				addPanel.setVisible(false);
				BookAddFrame bookAddFrame = new BookAddFrame();
				bookAddFrame.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_5);

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("\u56FE\u4E66\u67E5\u8BE2");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectBookFrame sFrame = new SelectBookFrame();
				sFrame.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_6);

		UpdateJpane = new JPanel();
		UpdateJpane.setBounds(119, 103, 310, 354);
		UpdateJpane.setLayout(null);
		contentPane.add(UpdateJpane);

		JLabel lblNewLabel = new JLabel("\u8BF7\u8BBE\u7F6E\u65B0\u5BC6\u7801\uFF1A");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel.setBounds(52, 28, 219, 34);
		UpdateJpane.add(lblNewLabel);

		textField_1 = new JTextField();
		textField_1.setBounds(52, 93, 219, 34);
		UpdateJpane.add(textField_1);
		textField_1.setColumns(10);

		JLabel label = new JLabel("\u518D\u6B21\u8F93\u5165\u65B0\u5BC6\u7801\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(52, 152, 219, 34);
		UpdateJpane.add(label);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(52, 217, 219, 34);
		UpdateJpane.add(textField_2);

		JButton btnNewButton = new JButton("\u786E  \u5B9A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String adminPwd = textField_1.getText().trim();
				String nextPwd = textField_2.getText().trim();

				String regidterpasswordregex = "\\w{6,12}";
				if (!adminPwd.matches(regidterpasswordregex)) {
					JOptionPane.showMessageDialog(contentPane, "密码不满足条件（6~12个任意字符）");
					textField_1.setText("");
					textField_1.requestFocus();
					return;
				}
				if (!adminPwd.equals(nextPwd)) {
					JOptionPane.showMessageDialog(contentPane, "两次输入的密码不一致，请重新输入");
					textField_1.setText("");
					textField_1.requestFocus();
					textField_2.setText("");
					return;
				}
				@SuppressWarnings("resource")
				ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
				ServiceDaoImpl sImpl = (ServiceDaoImpl) context.getBean("ServicesDao");
				sImpl.updateUser(name, adminPwd);

				JOptionPane.showMessageDialog(contentPane, "密码修改成功");
				LoginFrame lFrame = new LoginFrame();
				lFrame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton.setBounds(104, 286, 111, 39);
		UpdateJpane.add(btnNewButton);
		UpdateJpane.setVisible(false);

		getRootPane().setDefaultButton(btnNewButton);

		addPanel = new JPanel();
		addPanel.setBounds(119, 103, 369, 354);
		addPanel.setLayout(null);
		contentPane.add(addPanel);

		JLabel addlb_1 = new JLabel("\u7528\u6237\u540D\uFF1A\r\n");
		addlb_1.setFont(new Font("宋体", Font.PLAIN, 14));
		addlb_1.setHorizontalAlignment(SwingConstants.CENTER);
		addlb_1.setBounds(44, 54, 90, 28);
		addPanel.add(addlb_1);

		addtf_1 = new JTextField();
		addtf_1.setBounds(144, 55, 151, 28);
		addPanel.add(addtf_1);
		addtf_1.setColumns(10);

		JLabel addlb_2 = new JLabel("\u5BC6  \u7801\uFF1A");
		addlb_2.setFont(new Font("宋体", Font.PLAIN, 14));
		addlb_2.setHorizontalAlignment(SwingConstants.CENTER);
		addlb_2.setBounds(44, 115, 90, 28);
		addPanel.add(addlb_2); 

		addtf_2 = new JTextField();
		addtf_2.setBounds(144, 119, 151, 28);
		addPanel.add(addtf_2);
		addtf_2.setColumns(10);

		JRadioButton addrb_1 = new JRadioButton("\u7BA1\u7406\u5458", true);
		addrb_1.setHorizontalAlignment(SwingConstants.CENTER);
		addrb_1.setFont(new Font("宋体", Font.PLAIN, 14));
		addrb_1.setBounds(61, 192, 121, 23);
		addPanel.add(addrb_1);

		JRadioButton addrb_2 = new JRadioButton("\u7528 \u6237");
		addrb_2.setHorizontalAlignment(SwingConstants.CENTER);
		addrb_2.setFont(new Font("宋体", Font.PLAIN, 14));
		addrb_2.setBounds(184, 192, 121, 23);
		addPanel.add(addrb_2);
		ButtonGroup addGroup = new ButtonGroup();

		addGroup.add(addrb_1);
		addGroup.add(addrb_2);

		JButton addbt = new JButton("\u6DFB  \u52A0");
		addbt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String registername = addtf_1.getText().trim();
				String regidterpassword = addtf_2.getText().trim();

				String registernameregex = ".{4,8}";
				String regidterpasswordregex = "\\w{6,12}";

				if (!regidterpassword.matches(regidterpasswordregex)) {
					JOptionPane.showMessageDialog(contentPane, "密码不满足条件（6~12个任意字符）");
					addtf_2.setText("");
					addtf_2.requestFocus();
					return;
				}

				if (!registername.matches(registernameregex)) {
					JOptionPane.showMessageDialog(contentPane, "用户名不满足条件（4~8个字母组成）");
					addtf_1.setText("");
					addtf_1.requestFocus();
					return;
				}

				String jurisdiction = null;
				if (addrb_1.isSelected()) {
					jurisdiction = addrb_1.getText();
				}
				if (addrb_2.isSelected()) {
					jurisdiction = addrb_2.getText();
				}

				@SuppressWarnings("resource")
				ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
				ServiceDaoImpl sImpl = (ServiceDaoImpl) context.getBean("ServicesDao");
				User user = new User();
				user.setUsername(registername);
				user.setPassword(regidterpassword);
				user.setJurisdiction(jurisdiction);
				sImpl.regedist(user);

				JOptionPane.showMessageDialog(contentPane, "用户注册成功，返回登录界面");
				LoginFrame lFrame = new LoginFrame();
				lFrame.setVisible(true);
				dispose();
			}
		});
		addbt.setFont(new Font("宋体", Font.PLAIN, 14));
		addbt.setBounds(124, 242, 93, 23);
		addPanel.add(addbt);
		getRootPane().setDefaultButton(addbt);

		JLabel timerlb = new JLabel("");
		timerlb.setBackground(Color.WHITE);
		timerlb.setForeground(Color.RED);
		timerlb.setFont(new Font("宋体", Font.BOLD, 20));
		timerlb.setHorizontalAlignment(SwingConstants.CENTER);
		timerlb.setBounds(323, 522, 277, 39);
		contentPane.add(timerlb);
		addPanel.setVisible(false);

		// 设置时间
		Timer timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				long time = System.currentTimeMillis();
				String pattern = "yyyy-MM-dd HH:mm:ss";
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				String timerString = sdf.format(time);
				timerlb.setText(timerString);
			}
		});
		timer.start();

	}

	public void init(String name) {
		this.setTitle("欢迎您！管理员：" + name);
	}
}
