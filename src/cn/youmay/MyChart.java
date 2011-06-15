package cn.youmay;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.graphics.Color;
import android.graphics.Paint.Align;

public class MyChart {
	private static final int HOURS = 24;

	public String getName() {
		return "My Chart";
	}

	public String getDesc() {
		return "My Chart";
	}
	
	DataProvider dataProvider;
	
	public MyChart(DataProvider dp){
		dataProvider=dp;
	}

	/**
	 * Builds an XY multiple time dataset using the provided values.
	 * 
	 * @param titles
	 *            the series titles
	 * @param xValues
	 *            the values for the X axis
	 * @param yValues
	 *            the values for the Y axis
	 * @return the XY multiple time dataset
	 */
	private XYMultipleSeriesDataset buildDateDataset(String[] titles,
			List<Date[]> xValues, List<double[]> yValues) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		int length = titles.length;
		for (int i = 0; i < length; i++) {
			TimeSeries series = new TimeSeries(titles[i]);
			Date[] xV = xValues.get(i);
			double[] yV = yValues.get(i);
			int seriesLength = xV.length;
			for (int k = 0; k < seriesLength; k++) {
				series.add(xV[k], yV[k]);
			}
			dataset.addSeries(series);
		}
		return dataset;
	}

	
	/**
	 * Builds an XY multiple series renderer.
	 * 
	 * @param colors
	 *            the series rendering colors
	 * @param styles
	 *            the series point styles
	 * @return the XY multiple series renderers
	 */
	private XYMultipleSeriesRenderer buildRenderer(int[] colors,
			PointStyle[] styles) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		renderer.setAxisTitleTextSize(10);
		renderer.setChartTitleTextSize(15);
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15);
		renderer.setPointSize(5f);
		renderer.setMargins(new int[] { 20, 40, 15, 0 }); // in this order: top,
															// left, bottom,
															// right
		int length = colors.length;
		for (int i = 0; i < length; i++) {
			XYSeriesRenderer r = new XYSeriesRenderer();
			r.setColor(colors[i]);
			r.setPointStyle(styles[i]);
			renderer.addSeriesRenderer(r);
		}
		return renderer;
	}

	/**
	 * Sets a few of the series renderer settings.
	 * 
	 * @param renderer
	 *            the renderer to set the properties to
	 * @param title
	 *            the chart title
	 * @param xTitle
	 *            the title for the X axis
	 * @param yTitle
	 *            the title for the Y axis
	 * @param xMin
	 *            the minimum value on the X axis
	 * @param xMax
	 *            the maximum value on the X axis
	 * @param yMin
	 *            the minimum value on the Y axis
	 * @param yMax
	 *            the maximum value on the Y axis
	 * @param axesColor
	 *            the axes color
	 * @param labelsColor
	 *            the labels color
	 */
	private void setChartSettings(XYMultipleSeriesRenderer renderer,
			String title, String xTitle, String yTitle, double xMin,
			double xMax, double yMin, double yMax, int axesColor,
			int labelsColor) {
		renderer.setChartTitle(title);
		renderer.setXTitle(xTitle);
		renderer.setYTitle(yTitle);
		renderer.setXAxisMin(xMin);
		renderer.setXAxisMax(xMax);
		renderer.setYAxisMin(yMin);
		renderer.setYAxisMax(yMax);
		renderer.setAxesColor(axesColor);
		renderer.setLabelsColor(labelsColor);
	}

	XYMultipleSeriesRenderer renderer;

	public XYMultipleSeriesRenderer getXYMultipleSeriesRenderer() {
		return renderer;
	}

	public XYMultipleSeriesDataset getXYMultipleSeriesDataset() {

		String[] titles = new String[] { "In", "Out", "Bandwidth" };
		// long now = Math.round(new Date().getTime() / DAY) * DAY;
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		List<Date[]> dateList = new ArrayList<Date[]>();
		for (int i = 0; i < titles.length; i++) {
			Date[] dates = new Date[HOURS];
			for (int j = 0; j < HOURS; j++) {
				dates[j] = new Date(curDate.getYear(), curDate.getMonth(),
						curDate.getDate(), j, 0, 0);
			}
			dateList.add(dates);
		}
		List<double[]> values = new ArrayList<double[]>();
		
		double[] ds1=new double[24];
		for(int i=0;i<ds1.length;i++){
			ds1[i]=Math.random()*3;
		}
		
		double[] ds2=new double[24];
		for(int i=0;i<ds2.length;i++){
			ds2[i]=Math.random()*3;
		}
		values.add(dataProvider.getInValues());
		values.add(dataProvider.getOutValues());
		values.add(new double[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
				2, 2, 2, 2, 2, 2, 2, 2, 2 });

		int[] colors = new int[] { Color.GREEN, Color.BLUE, Color.YELLOW };

		PointStyle[] styles = new PointStyle[] { PointStyle.POINT,
				PointStyle.POINT, PointStyle.POINT };

		renderer = buildRenderer(colors, styles);
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			XYSeriesRenderer seriesRenderer = (XYSeriesRenderer) renderer
					.getSeriesRendererAt(i);
			seriesRenderer.setFillPoints(true);
			seriesRenderer.setFillBelowLine(i == 0);
			seriesRenderer.setFillBelowLineColor(colors[i]);
			if (i == 2) {
				seriesRenderer.setLineWidth(5.0f);
			} else {
				seriesRenderer.setLineWidth(1.0f);
			}
		}

		setChartSettings(renderer, "Utilization and Status graph", "Hour",
				"bits pre second", dateList.get(0)[0].getTime(),
				dateList.get(0)[HOURS - 1].getTime(), 0, 3, Color.LTGRAY,
				Color.LTGRAY);
		renderer.setXLabels(12);
		renderer.setYLabels(10);
		renderer.setShowGrid(true);
		renderer.setXLabelsAlign(Align.CENTER);
		renderer.setYLabelsAlign(Align.RIGHT);
		renderer.setBackgroundColor(Color.GRAY);
		renderer.setApplyBackgroundColor(true);
		return buildDateDataset(titles, dateList, values);
	}

	

}
