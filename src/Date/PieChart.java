package Date;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import utils.DBUtil;

/**
 * 该类是获取借书信息（以饼形图查看）
 * 
 * @author 韩炳琪 邸腾 冯宇
 *   
 */
public class PieChart {
	ChartPanel frame1;

	public PieChart() {
		DefaultPieDataset data = getDataSet();
		JFreeChart chart = ChartFactory.createPieChart3D("仓库内货物数量分析", data, true,
				false, false);
		// 设置百分比
		PiePlot pieplot = (PiePlot) chart.getPlot();
		DecimalFormat df = new DecimalFormat("0.00%");// 获得一个DecimalFormat对象，主要是设置小数问题
		NumberFormat nf = NumberFormat.getNumberInstance();// 获得一个NumberFormat对象
		StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator(
				"{0}  {2}", nf, df);// 获得StandardPieSectionLabelGenerator对象
		pieplot.setLabelGenerator(sp1);// 设置饼图显示百分比

		// 没有数据的时候显示的内容
		pieplot.setNoDataMessage("无数据显示");
		pieplot.setCircular(false);
		pieplot.setLabelGap(0.02D);

		pieplot.setIgnoreNullValues(true);// 设置不显示空值
		pieplot.setIgnoreZeroValues(true);// 设置不显示负值
		frame1 = new ChartPanel(chart, true);
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体
		PiePlot piePlot = (PiePlot) chart.getPlot();// 获取图表区域对象
		piePlot.setLabelFont(new Font("宋体", Font.BOLD, 10));// 解决乱码
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 10));
	}

	private static DefaultPieDataset getDataSet() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		String sql1 = "select count(GoodsType = '数码产品' OR null) as '数码产品',count(GoodsType = '日用品' OR null) as '日用品' ,count(GoodsType = '洗护日化' OR null) as '洗护日化',count(GoodsType = '图书' OR null) as '图书',count(GoodsType = '食品' OR null) as '食品',count(GoodsType = '五金电料' OR null) as '五金电料',count(GoodsType = '厨具' OR null) as '厨具' from goods;";
		Statement stmt = DBUtil.getStatement();
		try {
			ResultSet rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				dataset.setValue("数码产品", rs1.getInt(1));
				dataset.setValue("日用品", rs1.getInt(2));
				dataset.setValue("洗护日化", rs1.getInt(3));
				dataset.setValue("图书", rs1.getInt(4));
				dataset.setValue("食品", rs1.getInt(5));
				dataset.setValue("五金电料", rs1.getInt(6));
				dataset.setValue("厨具", rs1.getInt(7));
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
