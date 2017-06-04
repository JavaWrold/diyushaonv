package cn.itcast.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.dao.impl.ServiceDaoImpl;
import cn.itcast.entity.User;
import cn.itcast.utils.UItitl;

public class RegisterFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private ButtonGroup bGroup;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// RegisterFrame frame = new RegisterFrame();
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
	public RegisterFrame() {
		UItitl.setFrameImage(this, "register.png");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 439, 302);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		UItitl.setCenter(this);

		JLabel lblNewLabel = new JLabel("\u6CE8\u518C\u9875\u9762");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 44));
		lblNewLabel.setBounds(10, 10, 403, 56);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(90, 83, 93, 35);
		contentPane.add(lblNewLabel_1);

		// 注册用户失去焦点
		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String registername = textField.getText().trim();
				String registernameregex = ".{4,8}";

				if (!registername.matches(registernameregex)) {
					JOptionPane.showMessageDialog(contentPane, "用户名不满足条件（4~8个字母组成）");
					// lblNewLabel_3.setText("用户名不满足条件（4~8个字母组成）");
					textField.setText("");
					textField.requestFocus();
					return;
				}

				@SuppressWarnings("resource")
				ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
				ServiceDaoImpl sImpl = (ServiceDaoImpl) context.getBean("ServicesDao");
				boolean flag = sImpl.findUser(registername);
				if (flag) {
					JOptionPane.showMessageDialog(contentPane, "用户名已经被注册");
					textField.setText("");
					textField.requestFocus();
					return;
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				textField.getText();
			}
		});
		textField.setBounds(193, 90, 163, 28);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("\u5BC6  \u7801\uFF1A");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(90, 128, 93, 35);
		contentPane.add(lblNewLabel_2);

		// 密码框失去焦点
		passwordField = new JPasswordField();
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				passwordField.getPassword();
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				String regidterpassword = String.valueOf(passwordField.getPassword()).trim();
				String regidterpasswordregex = "\\w{6,12}";
				if (!regidterpassword.matches(regidterpasswordregex)) {
					JOptionPane.showMessageDialog(contentPane, "密码不满足条件（6~12个任意字符）");
					// lblNewLabel_4.setText("密码不满足条件（6~12个任意字符）");
					passwordField.setText("");
					passwordField.requestFocus();
					return;
				}
			}

		});
		passwordField.setBounds(193, 135, 163, 28);
		contentPane.add(passwordField);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("\u7BA1\u7406\u5458", true);
		rdbtnNewRadioButton.setFont(new Font("宋体", Font.PLAIN, 14));
		rdbtnNewRadioButton.setBounds(129, 179, 121, 23);
		contentPane.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("\u7528 \u6237");
		rdbtnNewRadioButton_1.setFont(new Font("宋体", Font.PLAIN, 14));
		rdbtnNewRadioButton_1.setBounds(252, 179, 93, 23);
		contentPane.add(rdbtnNewRadioButton_1);

		JButton btnNewButton = new JButton("\u53D6 \u6D88");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame lFrame = new LoginFrame();
				lFrame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 14));
		btnNewButton.setBounds(112, 217, 93, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\u6CE8 \u518C");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String registername = textField.getText().trim();
				String regidterpassword = String.valueOf(passwordField.getPassword()).trim();

				String registernameregex = ".{4,8}";
				String regidterpasswordregex = "\\w{6,12}";

				if (!regidterpassword.matches(regidterpasswordregex)) {
					JOptionPane.showMessageDialog(contentPane, "密码不满足条件（6~12个任意字符）");
					passwordField.setText("");
					passwordField.requestFocus();
					return;
				}

				if (!registername.matches(registernameregex)) {
					JOptionPane.showMessageDialog(contentPane, "用户名不满足条件（4~8个字母组成）");
					textField.setText("");
					textField.requestFocus();
					return;
				}

				String jurisdiction = null;
				if (rdbtnNewRadioButton.isSelected()) {
					jurisdiction = rdbtnNewRadioButton.getText();
				}
				if (rdbtnNewRadioButton_1.isSelected()) {
					jurisdiction = rdbtnNewRadioButton_1.getText();
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
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 14));
		btnNewButton_1.setBounds(248, 217, 93, 23);
		contentPane.add(btnNewButton_1);
		getRootPane().setDefaultButton(btnNewButton_1);

		bGroup = new ButtonGroup();
		bGroup.add(rdbtnNewRadioButton);
		bGroup.add(rdbtnNewRadioButton_1);
	}
}
