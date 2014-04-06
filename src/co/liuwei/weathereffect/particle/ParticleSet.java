package co.liuwei.weathereffect.particle;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import co.liuwei.weathereffect.R;

/*
 * 这个类的作用有两个
 * （1）给存粒子的容器放粒子，并以一定范围的随机速度和一定范围内的随机坐标给他初始化                                      
 * （2）以一定的规律给粒子赋予不同的颜色
 */
public class ParticleSet {

	ArrayList<Particle> particleSet;	//声明一个存放particle的容器
	
	public ParticleSet(){
		particleSet = new ArrayList<Particle>();
	}
	
	//向存粒子的容器particleSet添加指定个数的粒子并给每个粒子传入一个起始时间
	//添加进容器的粒子 已经给予完盐水，半径和他们的速度 以及产生的初始坐标了
	public void add(Context context,int count, double startTime){
		
		DisplayMetrics metrics = new DisplayMetrics();
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(metrics);
		
		Bitmap bitmap = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.raindrop_l))
				.getBitmap();
		Random random = new Random();
		
		for(int i = 0; i < count; i++){	
			int tempX =  random.nextInt(metrics.widthPixels); //粒子的X坐标;
			int tempY = 0;//粒子的Y坐标
			Particle particle = new Particle(bitmap,tempX, tempY);	//创建粒子对象
			particleSet.add(particle);
		}
	}
	
	//这个方法是根据i得到一种颜色，i循环增加
	//具体很简单，如果不懂switch~case~的话，估计你也看不到这篇博客
	public int getColor(int i){
		int color = Color.RED;
		switch(i % 4){	//对任何一个数取4的余数的话，只能得到0,1,2,3四个数
			case 0:
				color = Color.RED;
				break;
			case 1:
				color = Color.BLUE;
				break;
			case 2:
				color = Color.GREEN;
				break;
			case 3:
				color = Color.YELLOW;
		}
		return color;	
	}
}
