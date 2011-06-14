package cn.youmay;

import org.achartengine.GraphicalView;
import org.achartengine.chart.AbstractChart;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class ManiActivity extends Activity {

	private AbstractChart getMyTimeChart() {
		MyChart myChart = new MyChart();

		AbstractChart mChart = new MyTimeChart(
				myChart.getXYMultipleSeriesDataset(),
				myChart.getXYMultipleSeriesRenderer());

		return mChart;
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		AbstractChart mChart = getMyTimeChart();
		GraphicalView mView = new MyView(this, mChart);

		/*String title = savedInstanceState.getString(ChartFactory.TITLE);
		if (title == null) {
			requestWindowFeature(Window.FEATURE_NO_TITLE);
		} else if (title.length() > 0) {
			setTitle(title);
		}*/
		setContentView(mView);
	}

	public class MyView extends GraphicalView implements Runnable {

		private int x = 20, y = 20;

		public MyView(Context context, AbstractChart chart) {
			super(context, chart);
			// setFocusable(true);
			// new Thread(this).start();

		}

		@Override
		public void run() {
			RefreshHandler mRedrawhandler = new RefreshHandler();
			while (!Thread.currentThread().isInterrupted()) {
				Message m = new Message();
				m.what = 0x101;
				mRedrawhandler.sendMessage(m);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			Paint p = new Paint();
			p.setColor(Color.GREEN);
			canvas.drawText("Henley", x + 100, y + 100, p);
			canvas.drawCircle(x, y, 10, p);
		}

		class RefreshHandler extends Handler {

			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0x101) {
					MyView.this.update();
					MyView.this.invalidate();
				}
				super.handleMessage(msg);
			}
		}

		private void update() {
			int h = getHeight();
			y += 5;
			if (y >= h)
				y = 20;
		}

	}
}