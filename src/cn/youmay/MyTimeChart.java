package cn.youmay;

import org.achartengine.chart.TimeChart;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.graphics.Canvas;
import android.graphics.Paint;

public class MyTimeChart extends TimeChart {

	public MyTimeChart(XYMultipleSeriesDataset arg0,
			XYMultipleSeriesRenderer arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public void draw(Canvas canvas, int x, int y, int width, int height,
			Paint paint) {
		super.draw(canvas, x, y, width, height, paint);
		drawTotal(canvas,paint);

	}

	private void drawTotal(Canvas canvas, Paint paint) {
		/*
		 * canvas.drawLine(300, 350, 600, 350, paint); canvas.drawLine(300, 365,
		 * 600, 365, paint); canvas.drawLine(300, 380, 600, 380, paint);
		 * canvas.drawLine(300, 395, 600, 395, paint);
		 */

		canvas.drawText("In", 300, 360, paint);
		canvas.drawText("Avg:", 350, 360, paint);
		canvas.drawText("1.8MB/S", 390, 360, paint);
		canvas.drawText("Max:", 480, 360, paint);
		canvas.drawText("2.9MB/S", 520, 360, paint);

		canvas.drawText("Out", 300, 380, paint);
		canvas.drawText("Avg:", 350, 380, paint);
		canvas.drawText("1.5MB/S", 390, 380, paint);
		canvas.drawText("Max:", 480, 380, paint);
		canvas.drawText("2.9MB/S", 520, 380, paint);
	}

}