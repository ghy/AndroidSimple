package cn.youmay;

import org.achartengine.GraphicalView;
import org.achartengine.chart.AbstractChart;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class MyGraphicalView extends GraphicalView {

	public MyGraphicalView(Context context, AbstractChart chart) {
		super(context, chart);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}

}
