package co.liuwei.weathereffect.particle;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

/*
 * （1）这个类是一个绘制的线程类，线程不断执行，不断的绘制粒子
 * 通过surfaceHolder取得画布，让surfaceView类不断的改变
 * （2）另一个作用是绘制FPS用于程序的调试
 */
public class DrawThread extends Thread{
	ParticleView particleView;	//要把粒子绘制到这个类里显示，所以要声明他
	SurfaceHolder holder;	//需要调用surfaceHolder的画布，来更改particleView，所以需要声明它
	boolean flag = false;	//万年不变的线程执行标志位
	int sleepSpan = 30;	//万年不变的线程休眠时间
	long start = System.nanoTime();	//取得当前系统的时间，用于计算时间差
//	int count = 0;	//记录帧数，用于计算帧数率
	
	public DrawThread(ParticleView particleView, SurfaceHolder holder){
		this.particleView = particleView;
		this.holder = holder;
		this.flag = true;	//线程执行了，标志位改成true
	}
	
	@SuppressLint("WrongCall")
	public void run(){
		Canvas canvas = null;
		while(flag){
		try{
			canvas = holder.lockCanvas();	//给particleView锁定画布
			synchronized (holder) {	//对象锁
				particleView.onDraw(canvas);	//开始不断的绘制
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(canvas != null){
				holder.unlockCanvasAndPost(canvas);	//解锁并传回画布对象
			}
		}
//		this.count++;	//每执行一次加一帧
//		if(count == 20){
//			count = 0;	//首先清空计数器
//			long tempStamp = System.nanoTime();	//记录记满20帧的时间
//			long Span = tempStamp - start;	//获取时间间隔
//			start = tempStamp;	//要重新计数计算，所以在这里给start重新赋值
//			double fps = Math.round(100000000000.0 / Span * 20) / 100.0;	//计算fps的公式
//		}
		try{
			Thread.sleep(sleepSpan);	//线程休眠嘛~~~
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	}
}