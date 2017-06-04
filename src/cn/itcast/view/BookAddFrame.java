package cn.itcast.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.dao.impl.BookServiceDaoImpl;
import cn.itcast.entity.BookInfo;
import cn.itcast.utils.UItitl;

public class BookAddFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField bookISBNtf;
	private JTextField booknametf;
	private JTextField bookauthortf;
	private JTextField publishertf;
	private JTextField pubdatatf;
	private JTextField pircetf;
	private JLabel library;
	private JTextField bookcounttf;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// BookAddFrame frame = new BookAddFrame();
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
	public BookAddFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(170, 203, 482, 555);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		UItitl.setFrameImage(this, "bookadd1.jpg");

		// 设置背景图片
		UItitl.setPictureground(this, "bookadd.jpg", library);

		JLabel lblNewLabel = new JLabel("\u6DFB\u52A0\u4E66\u7C4D");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 44));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 446, 75);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u56FE\u4E66ISBN\uFF1A\r\n");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(69, 109, 89, 24);
		contentPane.add(lblNewLabel_1);

		JLabel label = new JLabel("\u56FE\u4E66\u540D\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(69, 154, 89, 24);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u56FE\u4E66\u4F5C\u8005\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(69, 198, 89, 24);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("\u51FA\u7248\u793E\uFF1A");
		label_2.setFont(new Font("宋体", Font.PLAIN, 16));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(69, 245, 89, 24);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("\u51FA\u7248\u65F6\u95F4\uFF1A");
		label_3.setFont(new Font("宋体", Font.PLAIN, 16));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(69, 287, 89, 24);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("\u56FE\u4E66\u4EF7\u683C\uFF1A");
		label_4.setFont(new Font("宋体", Font.PLAIN, 16));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(69, 333, 89, 24);
		contentPane.add(label_4);

		bookISBNtf = new JTextField();
		bookISBNtf.setBounds(216, 108, 179, 29);
		contentPane.add(bookISBNtf);
		bookISBNtf.setColumns(10);

		booknametf = new JTextField();
		booknametf.setColumns(10);
		booknametf.setBounds(216, 157, 179, 29);
		contentPane.add(booknametf);

		bookauthortf = new JTextField();
		bookauthortf.setColumns(10);
		bookauthortf.setBounds(216, 201, 179, 29);
		contentPane.add(bookauthortf);

		publishertf = new JTextField();
		publishertf.setColumns(10);
		publishertf.setBounds(216, 248, 179, 29);
		contentPane.add(publishertf);

		pubdatatf = new JTextField();
		pubdatatf.setColumns(10);
		pubdatatf.setBounds(216, 290, 179, 29);
		contentPane.add(pubdatatf);

		pircetf = new JTextField();
		pircetf.setColumns(10);
		pircetf.setBounds(216, 336, 179, 29);
		contentPane.add(pircetf);

		JButton bookaddbtn = new JButton("\u6DFB   \u52A0");
		bookaddbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bookISBN = bookISBNtf.getText().trim();
				String booktitle = booknametf.getText().trim();
				String bookauthor = bookauthortf.getText().trim();
				String publisher = publishertf.getText().trim();
				String date1 = pubdatatf.getText().trim();
				Float price = Float.parseFloat(pircetf.getText().trim());
				Integer bookcount = Integer.parseInt(bookcounttf.getText().trim());

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
				Date date = null;
				try {
					date = sdf.parse(date1);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				@SuppressWarnings("resource")
				ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-book.xml");
				BookServiceDaoImpl bookServiceDaoImpl = (BookServiceDaoImpl) context.getBean("bookServiceDao");
				BookInfo bookInfo = new BookInfo();
				bookInfo.setBook_ISBN(bookISBN);
				bookInfo.setBook_title(booktitle);
				bookInfo.setBook_author(bookauthor);
				bookInfo.setBook_publisher(publisher);
				bookInfo.setBook_pubdate(date);
				bookInfo.setBook_price(price);
				bookInfo.setBook_count(bookcount);

				bookServiceDaoImpl.addBook(bookInfo);

				JOptionPane.showMessageDialog(contentPane, "添加成功");
				int value = JOptionPane.showConfirmDialog(contentPane, "是否继续输入", "提示", JOptionPane.YES_NO_OPTION);
				if (value == JOptionPane.YES_OPTION) {
					bookISBNtf.setText("");
					booknametf.setText("");
					bookauthortf.setText("");
					publishertf.setText("");
					pubdatatf.setText("");
					pircetf.setText("");
					bookcounttf.setText("");
					bookISBNtf.requestFocus();
				} else {
					BookAddFrame bFrame = new BookAddFrame();
					bFrame.setVisible(false);
					dispose();
				}
			}
		});
		bookaddbtn.setFont(new Font("宋体", Font.BOLD, 20));
		bookaddbtn.setBounds(143, 458, 179, 48);
		contentPane.add(bookaddbtn);
		// 设置默认按钮
		getRootPane().setDefaultButton(bookaddbtn);

		JLabel lblNewLabel_2 = new JLabel("\u56FE\u4E66\u6570\u91CF\uFF1A");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(69, 387, 89, 29);
		contentPane.add(lblNewLabel_2);

		bookcounttf = new JTextField();
		bookcounttf.setColumns(10);
		bookcounttf.setBounds(216, 391, 179, 29);
		contentPane.add(bookcounttf);
	}
}
