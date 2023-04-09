package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.interfaces.RSAKey;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;

import com.mysql.cj.x.protobuf.MysqlxSql.StmtExecute;

import utils.DBUtil;
import view.Good_Update_Delete.MyModel;

/**
 * 用户出货的界面
 * 
 * @author 韩炳琪 邸腾 冯宇
 *
 */
public class UsergoodsOut extends JFrame {
	private JScrollPane jsp = new JScrollPane();
	private JTable table = new JTable();
	private JButton btn1 = new JButton("出货");
	private JButton btn2 = new JButton("退出");
	private int row;
	private MyModel model;

	private JLabel jLabel1 = new JLabel("货物编号：");
	private JLabel jLabel2 = new JLabel("货物名称：");
	private JLabel jLabel3 = new JLabel("出货日期：");
	private JLabel jLabel4 = new JLabel("数      量：");
	private JLabel jLabel5 = new JLabel("出  货  人：");
	private JLabel jLabel6 = new JLabel("出货人ID：");
	private JLabel jLabel7 = new JLabel("货  类  型：");
	JTextField jTextField1 = new JTextField();
	private JTextField jTextField2 = new JTextField();
	private JTextField jTextField3 = new JTextField();
	private JTextField jTextField4 = new JTextField();
	private JTextField jTextField5 = new JTextField();
	private JTextField jTextField6 = new JTextField();
	private JTextField jTextField7 = new JTextField();

	private Panel panBtn = new Panel();
	private Panel panLab = new Panel();

	public UsergoodsOut() {
		setTitle("出货窗口");
		setSize(600, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		// 设置可见视图的接口
		jsp.setViewportView(table);
		// 定义表格 宽600，高度200
		jsp.setPreferredSize(new Dimension(600, 200));
		// 设置横向和垂直滚动条可见
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		panBtn.add(btn1);
		panBtn.add(btn2);
		// 设置中间的panel布局为空
		panLab.setLayout(null);
		JButton jButton1 = new JButton("查找");
		JTextField jTextField = new JTextField();
		// 单选框
		JRadioButton JB1 = new JRadioButton("按货名查找");
		JRadioButton JB2 = new JRadioButton("按类型查找");
		JB1.setSelected(true); // 默认选择JB1
		// 加入组，避免出现可以两个都选择的情况
		ButtonGroup bg = new ButtonGroup();
		bg.add(JB1);
		bg.add(JB2);

		JB1.setSize(100, 30);
		JB1.setLocation(40, 30);

		JB2.setSize(100, 30);
		JB2.setLocation(140, 30);

		jTextField.setSize(160, 30);
		jTextField.setLocation(250, 30);

		jButton1.setSize(80, 30);
		jButton1.setLocation(450, 30);

		// 给标签和文本框设置位置
		jLabel1.setSize(100, 100);
		jLabel1.setLocation(10, 60);
		jLabel1.setHorizontalAlignment(JLabel.RIGHT);
		jTextField1.setSize(150, 30);
		jTextField1.setLocation(110, 95);

		jLabel2.setSize(100, 100);
		jLabel2.setLocation(280, 60);
		jLabel2.setHorizontalAlignment(JLabel.RIGHT);
		jTextField2.setSize(150, 30);
		jTextField2.setLocation(380, 95);

		jLabel3.setSize(100, 100);
		jLabel3.setLocation(10, 120);
		jLabel3.setHorizontalAlignment(JLabel.RIGHT);
		jTextField3.setSize(150, 30);
		jTextField3.setLocation(110, 155);

		// 自动获取借书时间(当前时间)
		Calendar c = Calendar.getInstance();
		// 获取年月日
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int date = c.get(Calendar.DATE);
		String time = year + "-" + month + "-" + date;
		jTextField3.setText(time);

		jLabel4.setSize(100, 100);
		jLabel4.setLocation(280, 120);
		jLabel4.setHorizontalAlignment(JLabel.RIGHT);
		jTextField4.setSize(150, 30);
		jTextField4.setLocation(380, 155);

		jLabel5.setSize(100, 100);
		jLabel5.setLocation(10, 180);
		jLabel5.setHorizontalAlignment(JLabel.RIGHT);
		jTextField5.setSize(150, 30);
		jTextField5.setLocation(110, 215);

		String username = SystemUserFrame.USER_NAME;
		jTextField5.setText(username);// 自动获取当前登录用户名

		jLabel6.setSize(100, 100);
		jLabel6.setLocation(280, 180);
		jLabel6.setHorizontalAlignment(JLabel.RIGHT);
		jTextField6.setSize(150, 30);
		jTextField6.setLocation(380, 215);

		jLabel7.setSize(100, 100);
		jLabel7.setLocation(10, 230);
		jLabel7.setHorizontalAlignment(JLabel.RIGHT);
		jTextField7.setSize(150, 30);
		jTextField7.setLocation(110, 265);

		String sql = "select userID from user where userName = '" + username + "';";
		Statement st = DBUtil.getStatement();
		try {
			ResultSet r = st.executeQuery(sql);
			while (r.next()) {
				String sId = r.getString(1);
				jTextField6.setText(sId);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		// 把标签和文本框加到panLab面板中
		// panLab.add(Label);
		// panLab.add(comboBox);
		panLab.add(JB1);
		panLab.add(JB2);
		panLab.add(jTextField);
		panLab.add(jButton1);

		panLab.add(jLabel1);
		panLab.add(jLabel2);
		panLab.add(jLabel3);
		panLab.add(jLabel4);
		panLab.add(jLabel5);
		panLab.add(jLabel6);
		panLab.add(jLabel7);
		panLab.add(jTextField1);
		panLab.add(jTextField2);
		panLab.add(jTextField3);
		panLab.add(jTextField4);
		panLab.add(jTextField5);
		panLab.add(jTextField6);
		panLab.add(jTextField7);

		this.add(jsp, BorderLayout.NORTH);
		this.add(panLab, BorderLayout.CENTER);
		this.add(panBtn, BorderLayout.SOUTH);

		jButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (JB1.isSelected()) {
					String s = jTextField.getText().trim();
					String sql = "select * from goods where GoodsName like '%" + s + "%';";
					model = new MyModel(sql);
					table.setModel(model);
					table.getColumnModel().getColumn(0).setHeaderValue("货号");
					table.getColumnModel().getColumn(1).setHeaderValue("货名");
					table.getColumnModel().getColumn(2).setHeaderValue("出货地");
					table.getColumnModel().getColumn(3).setHeaderValue("类型");
					table.getColumnModel().getColumn(4).setHeaderValue("价格");
					table.getColumnModel().getColumn(5).setHeaderValue("数量");

				} else {
					String s = jTextField.getText().trim();
					String sql = "select * from goods where GoodsType like '%" + s + "%';";
					model = new MyModel(sql);
					table.setModel(model);
					table.getColumnModel().getColumn(0).setHeaderValue("货号");
					table.getColumnModel().getColumn(1).setHeaderValue("货名");
					table.getColumnModel().getColumn(2).setHeaderValue("出货地");
					table.getColumnModel().getColumn(3).setHeaderValue("类型");
					table.getColumnModel().getColumn(4).setHeaderValue("价格");
					table.getColumnModel().getColumn(5).setHeaderValue("数量");
				}
			}
		});

		// 获取表里的值
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id, name, type;
				int selRow = table.getSelectedRow();
				id = table.getValueAt(selRow, 0).toString().trim();
				name = table.getValueAt(selRow, 1).toString().trim();
				type = table.getValueAt(selRow, 3).toString().trim();
				jTextField1.setText(id);
				jTextField2.setText(name);
				jTextField7.setText(type);
			}
		});

		// 使书号的文本框不可编辑
		jTextField1.setEditable(false);
		jTextField2.setEditable(false);
		jTextField3.setEditable(false);
//		jTextField4.setEditable(false);
		jTextField5.setEditable(false);
		jTextField6.setEditable(false);
		jTextField7.setEditable(false);

		// 限定只能输入数字
		jTextField4.setDocument(new NumberTextField());

		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String id = jTextField1.getText().trim();
				int i = Integer.parseInt(id);
				String bookname = jTextField2.getText().trim();
				String otime = jTextField3.getText().trim();
				String goodsnum = jTextField4.getText().trim();
				int num = Integer.parseInt(goodsnum);
				String username = jTextField5.getText().trim();
				String userid2 = jTextField6.getText().trim();
				int userid = Integer.parseInt(userid2);

				String type = jTextField7.getText().trim();
				Statement stmt = DBUtil.getStatement();
				String sql1 = "select GoodsNum from Goods where GoodsId = " + i + ";";
				String sql2 = "update Goods set GoodsNum = GoodsNum-" + goodsnum + " where GoodsId = " + i + ";";
				String sql3 = "insert into  goodsout(GoodsId,GoodsName,GoodsType,userid,username,OutTime,OutNum) values(" + i
						+ ",'" + bookname + "','" + type + "'," + userid + ",'" + username + "','" + otime + "',"+goodsnum+");";
				String sql4 = "delete from Goods where GoodsId=" + i + ";";
				try {

					ResultSet rs = stmt.executeQuery(sql1);
					rs.next();
					int s = rs.getInt(1);
					if (s > 1) {
						stmt.executeUpdate(sql2);
						JOptionPane.showMessageDialog(null, "出货成功。");
						stmt.executeUpdate(sql3);

						dispose();

					} else if (s == 1) {
						stmt.executeUpdate(sql4);
						JOptionPane.showMessageDialog(null, "出货成功。");
						stmt.executeUpdate(sql3);

						dispose();
						
					

					} else {
						JOptionPane.showMessageDialog(null, "未查找到该货物");
						dispose();
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});

		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}

	class MyModel extends AbstractTableModel {
		private int row;
		private int column;
		private ResultSet rs;
		private Statement stmt;

		public MyModel(String sql) {
			stmt = DBUtil.getStatement();
			try {
				rs = stmt.executeQuery(sql);
				rs.last();// 将光标移到最后一行
				row = rs.getRow();// 获取行号(最大行索引)
				ResultSetMetaData rsmd = rs.getMetaData();// 通过结果集对象来获取
				column = rsmd.getColumnCount();// 获取列数
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public int getColumnCount() {
			return column;
		}

		@Override
		public int getRowCount() {
			return row;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Object value = null;
			try {
				rs.absolute(rowIndex + 1);
				value = rs.getString(columnIndex + 1);// 获取表里的数据
			} catch (Exception e) {
				e.printStackTrace();
			}
			return value;
		}
	}

	
	
	/*
	 * 限定只能输入数字的方法 来自https://blog.csdn.net/mmmmmk_/article/details/78608917
	 * 本来可以键盘监听 但是过于僵硬
	 */
	class NumberTextField extends PlainDocument {
		public NumberTextField() {
			super();
		}

		public void insertString(int offset, String str, AttributeSet attr)
				throws javax.swing.text.BadLocationException {

			if (str == null) {
				return;
			}

			char[] s = str.toCharArray();
			int length = 0;
			// 过滤非数字

			for (int i = 0; i < s.length; i++) {
				if ((s[i] >= '0') && (s[i] <= '9')) {
					s[length++] = s[i];
				}
				// 插入内容
				super.insertString(offset, new String(s, 0, length), attr);
			}
		}
	}

}
