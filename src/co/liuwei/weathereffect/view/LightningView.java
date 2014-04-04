package co.liuwei.weathereffect.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import co.liuwei.weathereffect.R;

public class LightningView extends View implements Runnable{


	private int screenWidth;
	private int screenHeiht;
	
	private Bitmap lightningBitmap1;
	private Bitmap lightningBitmap2;

	private Bitmap currentLightBitmap;
	
	private int left;
	private int top;

	private int flashCount = 0;
	/**
	 * 图片移动频率
	 */
	private int dx = 2;
	private int dy = 1;

	private int sleepTime;

	/**
	 * 图片是否在移动
	 */
	private static boolean IsRunning = true;

	private Handler handler;

	public LightningView(Context context, int left,
			int top, int sleepTime) {
		super(context);
		getViewSize(context);
		this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		lightningBitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.light1);
		lightningBitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.light2);
		
		currentLightBitmap =  lightningBitmap1;
		
		this.left = left;
		this.top = top;
		this.sleepTime = sleepTime;

		handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				Log.i("icer", "handleMessage()");
				LightningView.this.invalidate();
			};
		};

	}

	public void move() {
		new Thread(this).start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		canvas.drawBitmap(currentLightBitmap, left, top, null);
		//canvas.drawBitmap(currentLightBitmap, null, new Rect(left, top, left+100, top+100), null);
		flashCount++;
		
		if(flashCount<=6){
			if(currentLightBitmap==lightningBitmap1){
				currentLightBitmap = lightningBitmap2;
			}else{
				currentLightBitmap = lightningBitmap1;
			}
		}
	}

	// 获取屏幕的分辨率
	private void getViewSize(Context context) {
		DisplayMetrics metrics = new DisplayMetrics();
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(metrics);
		this.screenHeiht = metrics.heightPixels;
		this.screenWidth = metrics.widthPixels;
	}
	
	@Override
	public void run() {

		while (LightningView.IsRunning) {
				if ((currentLightBitmap != null) && (left > (getWidth()))) {
					left = (int)(screenWidth*0.4);//-currentLightBitmap.getWidth()+150;//-currentLightBitmap.getWidth();
					flashCount = 0;
				}
			left = left + dx;
			Log.i("闪电位置:", left + "");
			handler.sendMessage(handler.obtainMessage());
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
