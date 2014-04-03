package co.liuwei.weathereffect.view;


public class Rain {
	Coordinate coordinate;
	int speed;
	
	public Rain(int x, int y, int speed){
		coordinate = new Coordinate(x, y);
		System.out.println("Speed:"+speed);
		this.speed = speed;
		if(this.speed == 0) {
			this.speed =1;
		}
	}
	
}
