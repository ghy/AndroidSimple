package cn.youmay;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class NetworkChartActivity extends Activity {

	// 图片的URL http://61.144.123.214:2712/
	// private final String ImageUrl =
	// "http://192.168.1.103:2727/home/getchart";
	private final String ImageUrl = "http://61.144.123.214:2712/home/getchart";

	private ImageView imageView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.network);
		imageView = (ImageView) findViewById(R.id.imageView1);
		// imageView.setImageResource(R.drawable.chart1);

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new MyTask(this), 1, 1000);
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			try {
				URL url = new URL(ImageUrl);
				URLConnection conn = url.openConnection();
				InputStream in = conn.getInputStream();
				Bitmap bmp = BitmapFactory.decodeStream(in);
				imageView.setImageBitmap(bmp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	};

	private class MyTask extends TimerTask {

		private Context context;

		public MyTask(Context context) {
			this.context = context;
		}

		@Override
		public void run() {
			Message message = new Message();
			message.obj = context;
			mHandler.sendMessage(message);
		}
	}

}
