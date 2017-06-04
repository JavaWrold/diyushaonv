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
		// ��ȡ���������
		Toolkit tk = Toolkit.getDefaultToolkit();
		// ����·����ȡͼƬ
		Image i = tk.getImage("images//" + imageName);
		// ����������ͼƬ
		jf.setIconImage(i);
	}

	public static void setPictureground(JFrame jf, String imageName, JLabel jLabel) {
		// ���ñ���ͼƬ
		ImageIcon background = new ImageIcon("images//" + imageName);
		// �ѱ���ͼƬ��ʾ��һ����ǩ����
		jLabel = new JLabel();
		Image img = background.getImage();
		img = img.getScaledInstance(jf.getWidth(), jf.getHeight(), Image.SCALE_DEFAULT);
		background.setImage(img);
		jLabel.setIcon(background);
		// �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������
		jLabel.setBounds(0, 0, jf.getWidth(), jf.getHeight());
		// �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
		JPanel imagePanel = (JPanel) jf.getContentPane();
		imagePanel.setOpaque(false);
		// �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����
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
			// 1.����ѡ���еı�����ɫ
			Table.setSelectionBackground(SelectedColor);
			// ������Ⱦ��
			@SuppressWarnings("serial")
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
				@Override
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
						boolean hasFocus, int row, int column) {
					// Ϊ��ǰCellʱ isSelectedΪtrueʱ��ѡ��,hasFocusΪtrueʱtable��ù��
					if (isSelected && hasFocus && row == table.getSelectedRow()
							&& column == table.getSelectedColumn()) {
						// 2.���õ�ǰCell����ɫ
						Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
								column);
						c.setBackground(Cellcolor);// ���ñ���ɫ
						c.setForeground(CellfontColor);// ����ǰ��ɫ
						return c;
					} else {
						// 3.���õ����У�ż���е���ɫ
						if (row % 2 == 0) {// ż����ʱ����ɫ
							setBackground(Color2);
						} else if (row % 2 == 1) {// ���õ����е���ɫ
							setBackground(Color1);
						}
						return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
					}
				}
			};
			// 4.����ÿһ�е���Ⱦ���ؼ���һ������������һ��������Ч
			for (int i = 0; i < Table.getColumnCount(); i++) {
				Table.getColumnModel().getColumn(i).setCellRenderer(tcr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
