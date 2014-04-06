package co.liuwei.weathereffect.particle;

import java.util.ArrayList;
import co.liuwei.weathereffect.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

/*
 * 显示粒子的类
 * 通过android的绘制类将粒子绘制出
 * 通过这个类调用绘制粒子的线程和
 */
public class ParticleView extends SurfaceView implements Callback {

	public static int DIE_OUT_LINE;	//死亡线，意思就是屏幕底部的坐标
	
	private float screenWidth;//屏幕宽度
	private float screenHeiht;//屏幕高度
	
	DrawThread dt;
	ParticleSet ps;	
	ParticleThread pt;
	SurfaceHolder holder;

	private Bitmap bgBitmap;
	RectF rectF;
	public ParticleView(Context ctx) {
		super(ctx);
		getViewSize(ctx);;
		bgBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.shouye);
		rectF = new RectF(0, 0, screenWidth, screenHeiht);
		
		holder = this.getHolder();
		holder.addCallback(this);
		dt = new DrawThread(this, holder);
		ps = new ParticleSet();
		pt = new ParticleThread(this,ctx);
	
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
	
	@SuppressLint("DrawAllocation")
	public void onDraw(Canvas canvas){
		//刷新背景
		canvas.drawBitmap(bgBitmap, null, rectF , null);
		
		ArrayList<Particle> particleSet = ps.particleSet;	//获取particleSet中的粒子集合
		Paint paint = new Paint();
		for(int i = 0; i < particleSet.size(); i++){
			Particle p = particleSet.get(i);
//			paint.setColor(p.color);	//设置画笔颜色为粒子颜色
			int tempX = p.currentX;	
			int tempY = p.currentY;	//物理引擎线程里一直在更新粒子的X,Y坐标，
									//所以每画一次都是不一样的位置
//			int tempRadius = p.r;
//			RectF oval = new RectF(tempX, tempY, 
//					tempX + 2 * tempRadius, tempY + 2 * tempRadius);
//			canvas.drawOval(oval, paint);
			canvas.drawBitmap(p.bitmap, tempX, tempY, paint);
		}
//		paint.setColor(Color.WHITE);
//		paint.setTextSize(18);
//		paint.setAntiAlias(true);
//		canvas.drawText(fps, 15, 15, paint);
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		DIE_OUT_LINE = this.getHeight();
		if(!dt.isAlive()){	//如果dt这个线程没有启动，就启动它
			dt.start();
		}
		if(!pt.isAlive()){	//同上
			pt.start();		
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		dt.flag = false;
		dt = null;
		pt.flag = false;
		pt = null;
	}
}