package Date;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import utils.DBUtil;

/**
 * 该类是获取借书信息（以条形图查看）
 * 
 * @author 韩炳琪 邸腾 冯宇
 *
 */
public class BarChart {
	ChartPanel frame1;

	public BarChart() {
		CategoryDataset dataset = getDataSet();
		JFreeChart chart = ChartFactory.createBarChart3D("仓库内货物数量分析", // 图表标题
				"货物种类", // 目录轴的显示标签
				"货物数量", // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, // 是否显示图例(对于简单的柱状图必须是false)
				false, // 是否生成工具
				false // 是否生成URL链接
		);

		// 从这里开始
		CategoryPlot plot = chart.getCategoryPlot();// 获取图表区域对象
		CategoryAxis domainAxis = plot.getDomainAxis(); // 水平底部列表
		domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 水平底部标题
		domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 垂直标题
		ValueAxis rangeAxis = plot.getRangeAxis();// 获取柱状
		rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体

		frame1 = new ChartPanel(chart, true); // 这里也可以用chartFrame,可以直接生成一个独立的Frame

	}

	private static CategoryDataset getDataSet() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String sql1 = "select count(GoodsType = '数码产品' OR null) as '数码产品',count(GoodsType = '日用品' OR null) as '日用品' ,count(GoodsType = '洗护日化' OR null) as '洗护日化',count(GoodsType = '图书' OR null) as '图书',count(GoodsType = '食品' OR null) as '食品',count(GoodsType = '五金电料' OR null) as '五金电料',count(GoodsType = '厨具' OR null) as '厨具' from goods;";
		Statement stmt = DBUtil.getStatement();
		try {
			ResultSet rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				dataset.addValue(rs1.getInt(1), "数码产品", "数码产品");
				dataset.addValue(rs1.getInt(2), "日用品", "日用品");
				dataset.addValue(rs1.getInt(3), "洗护日化", "洗护日化");
				dataset.addValue(rs1.getInt(4), "图书", "图书");
				dataset.addValue(rs1.getInt(5), "食品", "食品");
				dataset.addValue(rs1.getInt(6), "五金电料", "五金电料");
				dataset.addValue(rs1.getInt(7), "厨具", "厨具");
				return dataset;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataset;
	}

	public ChartPanel getChartPanel() {
		return frame1;
	}
}
