package co.liuwei.weathereffect;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import co.liuwei.weathereffect.view.DynamicWeatherCloudyView;
import co.liuwei.weathereffect.view.LoadWeatherView;
import co.liuwei.weathereffect.view.SnowSurfaceView;

public class MainActivity extends Activity {

	private FrameLayout linearWeatherBg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//±³¾°ÈÝÆ÷
		linearWeatherBg = (FrameLayout)findViewById(R.id.linearWeatherBg);
		
		
		
		
		
	}

	public void doRain(View v){
		
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
		DynamicWeatherCloudyView view1=new DynamicWeatherCloudyView(this,
				 R.drawable.yjjc_h_a2, -150, 40,20);
				 DynamicWeatherCloudyView view4=new DynamicWeatherCloudyView(this,
				 R.drawable.yjjc_h_a5, 0, 60,30);
				 DynamicWeatherCloudyView view2=new DynamicWeatherCloudyView(this,
				 R.drawable.yjjc_h_a3, 280, 80,50);
				 DynamicWeatherCloudyView view3=new DynamicWeatherCloudyView(this,
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
		
	}
	public void doFog(View v){
		
	}
	public void doWind(View v){
		linearWeatherBg.removeAllViews();
		LoadWeatherView loadWeatherView = new LoadWeatherView(this);
		linearWeatherBg.addView(loadWeatherView);
	}

}
