package org.freamcoding.template.assets.floatingText;

public class FloatingText {

	public float x;
	public float y;
	
	public int floatSpeedX;
	public int floatSpeedY;
	
	public String text;
	
	public int time;
	
	public FloatingText(float x, float y, String text){
		this.x = x;
		this.y = y;
		this.text = text;
		floatSpeedY = 5;
		time = 0;
	}
	public void tick(){
		//if(Assets.timer.tick){
			x+=floatSpeedX;
			y-=floatSpeedY;
			time++;
		//}
	}
}
