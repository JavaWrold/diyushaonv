package cn.itcast.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class UItitl {

	public UItitl() {

	}

	public static void setFrameImage(JFrame jf, String imageName) {
		// 获取工具类对象
		Toolkit tk = Toolkit.getDefaultToolkit();
		// 根据路径获取图片
		Image i = tk.getImage("images//" + imageName);
		// 给窗体设置图片
		jf.setIconImage(i);
	}

	public static void setPictureground(JFrame jf, String imageName, JLabel jLabel) {
		// 设置背景图片
		ImageIcon background = new ImageIcon("images//" + imageName);
		// 把背景图片显示在一个标签里面
		jLabel = new JLabel();
		Image img = background.getImage();
		img = img.getScaledInstance(jf.getWidth(), jf.getHeight(), Image.SCALE_DEFAULT);
		background.setImage(img);
		jLabel.setIcon(background);
		// 把标签的大小位置设置为图片刚好填充整个面板
		jLabel.setBounds(0, 0, jf.getWidth(), jf.getHeight());
		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		JPanel imagePanel = (JPanel) jf.getContentPane();
		imagePanel.setOpaque(false);
		// 把背景图片添加到分层窗格的最底层作为背景
		jf.getLayeredPane().add(jLabel, new Integer(Integer.MIN_VALUE));
	}

	public static void setCenter(JFrame jFrame) {
		Dimension d1 = jFrame.getSize();
		Dimension dd = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (dd.width - d1.width) / 2;
		int y = (dd.height - d1.height) / 2;
		jFrame.setLocation(x, y);
	}

	public static void setTableRowColor(JTable Table, final Color Color1, final Color Color2, Color SelectedColor,
			Color Cellcolor, Color CellfontColor) {
		try {
			// 1.设置选中行的背景颜色
			Table.setSelectionBackground(SelectedColor);
			// 声明渲染器
			@SuppressWarnings("serial")
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
				@Override
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
						boolean hasFocus, int row, int column) {
					// 为当前Cell时 isSelected为true时行选中,hasFocus为true时table获得光标
					if (isSelected && hasFocus && row == table.getSelectedRow()
							&& column == table.getSelectedColumn()) {
						// 2.设置当前Cell的颜色
						Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
								column);
						c.setBackground(Cellcolor);// 设置背景色
						c.setForeground(CellfontColor);// 设置前景色
						return c;
					} else {
						// 3.设置单数行，偶数行的颜色
						if (row % 2 == 0) {// 偶数行时的颜色
							setBackground(Color2);
						} else if (row % 2 == 1) {// 设置单数行的颜色
							setBackground(Color1);
						}
						return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
					}
				}
			};
			// 4.设置每一列的渲染器关键的一步，设置了这一步才能生效
			for (int i = 0; i < Table.getColumnCount(); i++) {
				Table.getColumnModel().getColumn(i).setCellRenderer(tcr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
