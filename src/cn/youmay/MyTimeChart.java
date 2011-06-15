package cn.youmay;

import java.text.DecimalFormat;

import org.achartengine.chart.TimeChart;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.graphics.Canvas;
import android.graphics.Paint;

public class MyTimeChart extends TimeChart {
	
	private static final long serialVersionUID = 1L;
	
	private final DecimalFormat df = new DecimalFormat(".##");

	private DataTotal total;

	public MyTimeChart(XYMultipleSeriesDataset arg0,
			XYMultipleSeriesRenderer arg1, DataTotal total) {
		super(arg0, arg1);
		this.total = total;
	}

	@Override
	public void draw(Canvas canvas, int x, int y, int width, int height,
			Paint paint) {
		super.draw(canvas, x, y, width, height, paint);
		drawTotal(canvas, paint);
	}

	private void drawTotal(Canvas canvas, Paint paint) {
		canvas.drawText("In", 300, 360, paint);
		canvas.drawText("Avg:", 350, 360, paint);
		canvas.drawText(df.format(total.getInAvg()) + "MB/S", 390, 360, paint);
		canvas.drawText("Max:", 480, 360, paint);
		canvas.drawText(df.format(total.getInMax()) + "MB/S", 520, 360, paint);

		canvas.drawText("Out", 300, 380, paint);
		canvas.drawText("Avg:", 350, 380, paint);
		canvas.drawText(df.format(total.getOutAvg()) + "MB/S", 390, 380, paint);
		canvas.drawText("Max:", 480, 380, paint);
		canvas.drawText(df.format(total.getOutMax()) + "MB/S", 520, 380, paint);
	}

}