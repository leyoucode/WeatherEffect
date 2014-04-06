package co.liuwei.weathereffect.particle;

import android.graphics.Bitmap;

/*
 * 这个类的作用就是定义粒子，并写构造函数把粒子初始化
 */
public class Particle {
//	int color;	//设置粒子的颜色
//	int r;	//粒子半径
//	double vertical_v;	//粒子的竖向速度
//	double horizontal_v;	//水平速度
//	int startX;	//初始X位置
//	int startY;	//初始Y位置
	int currentX;	//实时X位置
	int currentY;	//实时Y位置
//	double startTime;	//起始时间
	Bitmap bitmap;
	
	public Particle(Bitmap bitmap, int currentX, int currentY){
		this.bitmap = bitmap;
//		this.color = color;
//		this.r = r;
//		this.vertical_v = vertical_v;
//		this.horizontal_v = horizontal_v;
//		this.startX = currentX;
//		this.startY = currentY;
		this.currentX = currentX;
		this.currentY = currentY;
//		this.startTime = startTime;
	}
//	public Particle(Bitmap bitmap,int color, int r, double vertical_v, double horizontal_v,
//			int currentX, int currentY, double startTime){
//		this.bitmap = bitmap;
//		this.color = color;
//		this.r = r;
//		this.vertical_v = vertical_v;
//		this.horizontal_v = horizontal_v;
//		this.startX = currentX;
//		this.startY = currentY;
//		this.currentX = currentX;
//		this.currentY = currentY;
//		this.startTime = startTime;
//	}
}
