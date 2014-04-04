package co.liuwei.weathereffect.view;

import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import co.liuwei.weathereffect.R;

public class RainView extends View{
	
	int MAX_SNOW_COUNT = 40;
	// 雪花图片
	Bitmap bitmap_snows = null;
	// 画笔
	private final Paint mPaint = new Paint();
	// 随即生成器
	private static final Random random = new Random();
	// 花的位置
	private Rain[] rains = new Rain[MAX_SNOW_COUNT];
	// 屏幕的高度和宽度
	int view_height = 0;
	int view_width = 0;
	int MAX_SPEED = 55;

//	Bitmap bitmap_bg;
	
	/**
	 * 构造器
	 * 
	 * 
	 */
	public RainView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public RainView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	/**
	 * 加载天女散花的花图片到内存中
	 * 
	 */
	public void LoadSnowImage() {
		Resources r = this.getContext().getResources();
		bitmap_snows = ((BitmapDrawable) r.getDrawable(R.drawable.raindrop_l))
				.getBitmap();
		
//		bitmap_bg = BitmapFactory.decodeResource(getResources(),
//				R.drawable.shouye);
	}

	/**
	 * 设置当前窗体的实际高度和宽度
	 * 
	 */
	public void SetView(int height, int width) {
		view_height = height ;//- 100;
		view_width = width ;//- 50;
	}

	/**
	 * 随机的生成花朵的位置
	 * 
	 */
	public void addRandomSnow() {
		for(int i =0; i< MAX_SNOW_COUNT;i++){
			rains[i] = new Rain(random.nextInt(view_width), 0,random.nextInt(MAX_SPEED));
		}
	}


	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
//		canvas.drawBitmap(bitmap_bg, null, new Rect(0, 0, view_width, view_height), mPaint);
		
		for (int i = 0; i < MAX_SNOW_COUNT; i += 1) {
			if (rains[i].coordinate.x >= view_width || rains[i].coordinate.y >= view_height) {
				rains[i].coordinate.y = 0;
				rains[i].coordinate.x = random.nextInt(view_width);
			}
			// 雪花下落的速度
			rains[i].coordinate.y += rains[i].speed + 15;
			//雪花飘动的效果

//			// 随机产生一个数字，让雪花有水平移动的效果
//			int tmp = MAX_SPEED/2 - random.nextInt(MAX_SPEED);
//			//为了动画的自然性，如果水平的速度大于雪花的下落速度，那么水平的速度我们取下落的速度。
//			snows[i].coordinate.x += snows[i].speed < tmp ? snows[i].speed : tmp;
			canvas.drawBitmap(bitmap_snows, rains[i].coordinate.x,//((float) snows[i].coordinate.x)
					((float) rains[i].coordinate.y) - 140, mPaint);
		}

	}
	
	/*
	 * 负责做界面更新工作 ，实现下雪
	 */
	private RefreshHandler mRedrawHandler = new RefreshHandler();

	class RefreshHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			//snow.addRandomSnow();
			RainView.this.invalidate();
			sleep(100);
		}
		public void sleep(long delayMillis) {
			this.removeMessages(0);
			sendMessageDelayed(obtainMessage(0), delayMillis);
		}
	};

	/**
	 * Handles the basic update loop, checking to see if we are in the running
	 * state, determining if a move should be made, updating the snake's
	 * location.
	 */
	public void update() {
		this.addRandomSnow();
		mRedrawHandler.sleep(600);
	}

}
