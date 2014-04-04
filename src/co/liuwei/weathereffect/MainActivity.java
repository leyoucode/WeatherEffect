package co.liuwei.weathereffect;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import co.liuwei.weathereffect.view.CloudyView;
import co.liuwei.weathereffect.view.LightningView;
import co.liuwei.weathereffect.view.RainView;
import co.liuwei.weathereffect.view.SnowSurfaceView;
import co.liuwei.weathereffect.view.SunSurfaceView;
import co.liuwei.weathereffect.view.WindmillSurfaceView;

public class MainActivity extends Activity {

	private FrameLayout linearWeatherBg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//背景容器
		linearWeatherBg = (FrameLayout)findViewById(R.id.linearWeatherBg);
		
	}

	public void doRain(View v){
//		linearWeatherBg.removeAllViews();
//		RainSurfaceView rainSurfaceView=new RainSurfaceView(this);
//		linearWeatherBg.addView(rainSurfaceView);
		
		linearWeatherBg.removeAllViews();
		RainView rainView = new RainView(this,null);
		rainView.LoadSnowImage();

		// 获取当前屏幕的高和宽
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		rainView.SetView(dm.heightPixels, dm.widthPixels);
		linearWeatherBg.addView(rainView);
		// 更新当前雪花
		rainView.update();
		
		
		LightningView lightningView = new LightningView(this, 80, 0, 80);
		linearWeatherBg.addView(lightningView);
		lightningView.move();
		
	}
	public void doSnow(View v){
		linearWeatherBg.removeAllViews();
		SnowSurfaceView snowSurfaceView=new SnowSurfaceView(this);
		linearWeatherBg.addView(snowSurfaceView);
	}
	public void doOvercast(View v){
		
	}
	public void doCloudy(View v){
		linearWeatherBg.removeAllViews();
		CloudyView view1=new CloudyView(this,
				 R.drawable.yjjc_h_a2, -150, 40,20);
				 CloudyView view4=new CloudyView(this,
				 R.drawable.yjjc_h_a5, 0, 60,30);
				 CloudyView view2=new CloudyView(this,
				 R.drawable.yjjc_h_a3, 280, 80,50);
				 CloudyView view3=new CloudyView(this,
				 R.drawable.yjjc_h_a4, 140, 130,40);
				 linearWeatherBg.addView(view1);
				 linearWeatherBg.addView(view2);
				 linearWeatherBg.addView(view3);
				 linearWeatherBg.addView(view4);
				 view1.move();
				 view2.move();
				 view3.move();
				 view4.move();
	}
	public void doSunny(View v){
		linearWeatherBg.removeAllViews();
		SunSurfaceView loadWeatherView = new SunSurfaceView(this);
		linearWeatherBg.addView(loadWeatherView);
	}
	public void doFog(View v){
		
	}
	public void doWind(View v){
		linearWeatherBg.removeAllViews();
		WindmillSurfaceView loadWeatherView = new WindmillSurfaceView(this);
		linearWeatherBg.addView(loadWeatherView);
	}

}
