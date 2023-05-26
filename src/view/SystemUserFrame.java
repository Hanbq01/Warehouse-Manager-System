package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 用户登录后的主界面
 */
public class SystemUserFrame extends JFrame {
	public static String USER_NAME;

	public SystemUserFrame() {
		this.setLayout(null);
		ImageIcon img = new ImageIcon(
				"image/780.jpg");
		// 要设置的背景图片
		JLabel imgLabel = new JLabel(img);
		// 将背景图放在标签里。
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		// 将背景标签添加到jfram的LayeredPane面板里。
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		// 设置背景标签的位置
		Container contain = this.getContentPane();
		((JPanel) contain).setOpaque(false);

		setSize(1000, 750);
		setTitle("仓库管理系统_用户");
		setResizable(false); // 不可改变窗口大小
		// 获取屏幕大小和当前frame的大小
		Dimension thisScreen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension thisFrame = this.getSize();
		// 使启动窗口位于屏幕的正中心
		setLocation((thisScreen.width - thisFrame.width) / 2,
				(thisScreen.height - thisFrame.height) / 2);
		// 设置单击窗口的【关闭】按钮时将发生相应的动作
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar(); // 创建菜单栏
		// 创建菜单
		JMenu j1 = new JMenu("货物管理");
		JMenuItem j1_1 = new JMenuItem("查找货物");
		j1_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new GoodSearch().setVisible(true);
			}
		});
		JMenuItem j1_2 = new JMenuItem("查看所有货物");
		j1_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AllGood().setVisible(true);
			}
		});
		JMenuItem j1_3 = new JMenuItem("出货");
		j1_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new UsergoodsOut().setVisible(true);
			}
		});

		JMenuItem j1_4 = new JMenuItem("进货");
		j1_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "普通用户无权进货，请联系仓库主管。");
			}
		});

		j1.add(j1_1);
		j1.add(j1_2);
		j1.add(j1_3);
		j1.add(j1_4);

		JMenu j2 = new JMenu("个人中心");
		JMenuItem j2_1 = new JMenuItem("修改密码");
		j2_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new UserChangePWD().setVisible(true);
			}
		});
		j2.add(j2_1);

		JMenu j3 = new JMenu("系统");
		JMenuItem j3_1 = new JMenuItem("注销登录");
		j3_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new Login().setVisible(true);
			}
		});
		j3.add(j3_1);

		menuBar.add(j1);
		menuBar.add(j2);
		menuBar.add(j3);

		this.setJMenuBar(menuBar);

	}
	
	
}
