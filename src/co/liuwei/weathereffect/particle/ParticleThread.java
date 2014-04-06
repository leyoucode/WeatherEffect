package co.liuwei.weathereffect.particle;

import java.util.ArrayList;

import android.content.Context;

/*
 * 这个类是整个demo里最重要的类，一个物理引擎类，有一个作用
 * （1）改变每个粒子的物理轨迹
 * 这是最重要的作用
 */
public class ParticleThread extends Thread {
	
	boolean flag;	//按照国际惯例，这是线程执行的标志位
	ParticleView particleView;	//声明一个particelView类
	int sleepSpan = 30;	//按照国际惯例，这是线程休眠时间
	double time = 0;	//物理引擎的时间轴
	double span = 0.15;	//每次计算粒子的位移时采用的时间间隔
	Context context;
	
	public ParticleThread(ParticleView particleView,Context ctx){
		this.particleView = particleView;
		this.flag = true;	
		this.context = ctx;
	}
	
	//线程中最重要的方法
	public void run(){
		while(flag){
			particleView.ps.add(context,5, time);	//每次添加5个粒子
			ArrayList<Particle> tempSet = particleView.ps.particleSet;
			int count = tempSet.size();	//取得上面这个集合的大小
			for(int i = 0; i < count; i++)
			 {	//遍历整个粒子集合，修改每一个的轨迹
				Particle particle = tempSet.get(i);		//取得要修改的粒子对象（依次取）
				
				int tempx =particle.currentX;
				//particle.startY+random.nextInt(100);
				int tempy = (int) (particle.currentY+Math.round(Math.random() * 40));
				if(tempy > ParticleView.DIE_OUT_LINE-140){	//如果粒子超过屏幕下沿
					tempSet.remove(particle);	//就从集合里移除该particle对象
					count = tempSet.size();	//?
					System.out.println("====>");
				}
				particle.currentX = tempx;	
				particle.currentY = tempy;	//修改粒子的坐标
			}
			time += span;	
			try{
				Thread.sleep(sleepSpan);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
