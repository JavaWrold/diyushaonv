package cn.itcast.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import cn.itcast.utils.UItitl;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		UItitl.setFrameImage(this, "login.png");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		UItitl.setCenter(this);

		JLabel lblN = new JLabel("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF");
		lblN.setForeground(Color.ORANGE);
		lblN.setHorizontalAlignment(SwingConstants.CENTER);
		lblN.setFont(new Font("宋体", Font.BOLD, 44));
		lblN.setBounds(10, 0, 414, 44);
		contentPane.add(lblN);

		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(75, 73, 96, 31);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(181, 78, 168, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("\u5BC6  \u7801\uFF1A");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(75, 131, 96, 31);
		contentPane.add(lblNewLabel_1);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(181, 136, 168, 26);
		contentPane.add(passwordField_1);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("\u7BA1\u7406\u5458", true);
		rdbtnNewRadioButton.setFont(new Font("宋体", Font.PLAIN, 14));
		rdbtnNewRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton.setBounds(117, 169, 88, 23);
		contentPane.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("\u7528 \u6237");
		rdbtnNewRadioButton_1.setFont(new Font("宋体", Font.PLAIN, 14));
		rdbtnNewRadioButton_1.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton_1.setBounds(244, 169, 88, 23);
		contentPane.add(rdbtnNewRadioButton_1);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnNewRadioButton);
		buttonGroup.add(rdbtnNewRadioButton_1);

		JButton btnNewButton = new JButton("\u6CE8 \u518C");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterFrame rFrame = new RegisterFrame();
				rFrame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 14));
		btnNewButton.setBounds(165, 210, 93, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_2 = new JButton("\u767B \u5F55");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = (String) textField.getText().trim();
				String password = String.valueOf(passwordField_1.getPassword()).trim();

				String nameregex = ".{4,8}";
				String pdregex = "\\w{6,12}";

				if (!(name.matches(nameregex))) {
					JOptionPane.showMessageDialog(contentPane, "用户名不满足条件（4~8个字母组成）");
					textField.setText("");
					textField.requestFocus();
					return;
				}
				if (!(password.matches(pdregex))) {
					JOptionPane.showMessageDialog(contentPane, "密码不满足条件（6~12个任意字符）");
					passwordField_1.setText("");
					passwordField_1.requestFocus();
					return;
				}

				@SuppressWarnings("resource")
				ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
				ServiceDaoImpl sImpl = (ServiceDaoImpl) context.getBean("ServicesDao");
				if (rdbtnNewRadioButton.isSelected()) {
					String jurisdiction = rdbtnNewRadioButton.getText();
					boolean flag = sImpl.login(name, password, jurisdiction);
					if (flag) {
						JOptionPane.showMessageDialog(contentPane, "恭喜管理员:" +name+ "登录成功");
						AdminFrame aFrame = new AdminFrame(name);
						aFrame.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(contentPane, "用户名或密码错误，请重新输入");
						textField.setText("");
						passwordField_1.setText("");
						textField.requestFocus();
					}
				}

				if (rdbtnNewRadioButton_1.isSelected()) {
					String jurisdiction1 = rdbtnNewRadioButton_1.getText();
					boolean flag = sImpl.login(name, password, jurisdiction1);
					if (flag) {
						JOptionPane.showMessageDialog(contentPane, "恭喜用户:" + name + "登录成功");
						UserFrame uFrame = new UserFrame(name);
						uFrame.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(contentPane, "用户名或密码错误，请重新输入");
						textField.setText("");
						passwordField_1.setText("");
						textField.requestFocus();
					}
				}
			}
		});
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 14));
		btnNewButton_2.setBounds(268, 210, 93, 23);
		contentPane.add(btnNewButton_2);
		getRootPane().setDefaultButton(btnNewButton_2);

		JButton btnNewButton_1 = new JButton("\u53D6 \u6D88");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				passwordField_1.setText("");
				textField.requestFocus();

			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 14));
		btnNewButton_1.setBounds(62, 210, 93, 23);
		contentPane.add(btnNewButton_1);
	}
}
