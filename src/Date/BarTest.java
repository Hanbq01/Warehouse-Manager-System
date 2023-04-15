package Date;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * 查看条形图的界面
 * 
 * @author HBQ
 *
 */
public class BarTest extends JFrame {
	public BarTest() {
		setTitle("各类货物数量比例");
		setBounds(50, 50, 800, 600);
		setResizable(false); // 不可改变窗口大小
		// 获取屏幕大小和当前frame的大小
		Dimension thisScreen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension thisFrame = this.getSize();
		// 使启动窗口位于屏幕的正中心
		this.setLocation((thisScreen.width - thisFrame.width) / 2,
				(thisScreen.height - thisFrame.height) / 2);
		this.setLocationRelativeTo(null);
		// 设置单击窗口的【关闭】按钮时将发生相应的动作
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		// 添加条形图
		this.add(new BarChart().getChartPanel());
	}
}
