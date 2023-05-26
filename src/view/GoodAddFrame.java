package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.DBUtil;

/**
 * 管理员添加货物的类
 */
public class GoodAddFrame extends JFrame {
	private JPanel panBtn = new JPanel();
	private JPanel panLab = new JPanel();
	private JLabel jLabel2 = new JLabel("货物名称：");
	private JLabel jLabel4 = new JLabel("货物所在仓：");
	private JLabel jLabel3 = new JLabel("货物类型：");
	private JLabel jLabel5 = new JLabel("货物价格：");
	private JLabel jLabel6 = new JLabel("货物数量：");
	private JTextField jTextField1 = new JTextField();
	private JTextField jTextField2 = new JTextField();
	private JTextField jTextField3 = new JTextField();
	private JTextField jTextField4 = new JTextField();
	private JTextField jTextField5 = new JTextField();
	private JTextField jTextField6 = new JTextField();
	private JButton jButton1 = new JButton("添加");
	private JButton jButton2 = new JButton("重置");
	private JLabel jLabel7 = new JLabel();

	public GoodAddFrame() {
		setTitle("添加货物");
		setSize(400, 400);
		setResizable(false); // 不可改变窗口大小
		// 获取屏幕大小和当前frame的大小
		Dimension thisScreen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension thisFrame = this.getSize();
		// 使启动窗口位于屏幕的正中心
		setLocation((thisScreen.width - thisFrame.width) / 2, (thisScreen.height - thisFrame.height) / 2);
		// 设置单击窗口的【关闭】按钮时将发生相应的动作
		setDefaultCloseOperation(HIDE_ON_CLOSE);

		jLabel7.setFont(new Font("宋体", 0, 24));
		jLabel7.setHorizontalAlignment(JLabel.CENTER);
		jLabel7.setForeground(new Color(255, 51, 51));
		jLabel7.setText("添  加  货  物");

		jLabel2.setSize(100, 80);
		jLabel2.setLocation(50, 5);
		jLabel2.setHorizontalAlignment(JLabel.RIGHT);

		jLabel3.setSize(100, 80);
		jLabel3.setLocation(50, 40);
		jLabel3.setHorizontalAlignment(JLabel.RIGHT);

		jLabel4.setSize(100, 80);
		jLabel4.setLocation(50, 80);
		jLabel4.setHorizontalAlignment(JLabel.RIGHT);

		jLabel5.setSize(100, 80);
		jLabel5.setLocation(50, 120);
		jLabel5.setHorizontalAlignment(JLabel.RIGHT);

		jLabel6.setSize(100, 80);
		jLabel6.setLocation(50, 160);
		jLabel6.setHorizontalAlignment(JLabel.RIGHT);


		jTextField2.setSize(150, 25);
		jTextField2.setLocation(160, 33);
		

		JComboBox comboBox2 = new JComboBox();
		comboBox2.addItem("北京仓");
		comboBox2.addItem("上海仓");
		comboBox2.addItem("济南仓");
		comboBox2.addItem("广州仓");
		comboBox2.addItem("武汉仓");
		comboBox2.addItem("杭州仓");
		comboBox2.addItem("德州仓");
		comboBox2.addItem("临沂仓");
		comboBox2.addItem("宁波仓");
		comboBox2.addItem("西安仓");
		comboBox2.addItem("哈尔滨仓");
		comboBox2.setSize(150, 25);
		comboBox2.setLocation(160, 110);
		
		JComboBox comboBox1 = new JComboBox();
		comboBox1.addItem("数码产品");
		comboBox1.addItem("日用品");
		comboBox1.addItem("食品");
		comboBox1.addItem("家用电器");
		comboBox1.addItem("五金电料");
		comboBox1.addItem("厨具");
		comboBox1.addItem("洗护日化");
		comboBox1.addItem("图书");
		comboBox1.setSize(150, 25);
		comboBox1.setLocation(160, 70);

		jTextField5.setSize(150, 25);
		jTextField5.setLocation(160, 150);

		jTextField6.setSize(150, 25);
		jTextField6.setLocation(160, 190);

		panBtn.add(jButton1);
		panBtn.add(jButton2);
		panLab.setLayout(null);

		panLab.add(jLabel2);
		panLab.add(jLabel3);
		panLab.add(jLabel4);
		panLab.add(jLabel5);
		panLab.add(jLabel6);

		panLab.add(jTextField1);
		panLab.add(jTextField2);
		panLab.add(comboBox1);
		panLab.add(comboBox2);
		panLab.add(jTextField5);
		panLab.add(jTextField6);

		add(jLabel7, BorderLayout.NORTH);
		add(panBtn, BorderLayout.SOUTH);
		add(panLab, BorderLayout.CENTER);

		jButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String goodsname = jTextField2.getText().trim();
				String goodsplace = comboBox2.getSelectedItem().toString();
				String goodstype = comboBox1.getSelectedItem().toString();
				String bookprice = jTextField5.getText().trim();
				String goodsnum = jTextField6.getText().trim();

				double p = Double.parseDouble(bookprice);

				Statement stmt = DBUtil.getStatement();
				String sql = "select * from Goods where GoodsName='" + goodsname + "';";
				String sql2 = "insert into goods(GoodsName,GoodsPlace,GoodsType,Price,GoodsNum) value('" + goodsname
						+ "','" + goodsplace + "','" + goodstype + "'," + p + ",'" + goodsnum + "');";
				try {
					ResultSet rs = stmt.executeQuery(sql);
					if (!rs.next()) {
						stmt.executeUpdate(sql2);
						JOptionPane.showMessageDialog(null, "添加成功。");
					} else {
						// 改成增加数量
						stmt.executeUpdate("update Goods set GoodsNum=GoodsNum+'" + goodsnum + "';");
						JOptionPane.showMessageDialog(null, "添加成功。");
						jTextField2.setText("");
						jTextField3.setText("");
						jTextField4.setText("");
						jTextField5.setText("");
						jTextField6.setText("");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		jButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				jTextField2.setText("");
				jTextField3.setText("");
				jTextField4.setText("");
				jTextField5.setText("");
				jTextField6.setText("");
			}
		});
	}
}
