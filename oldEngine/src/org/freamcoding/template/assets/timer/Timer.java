package org.freamcoding.template.assets.timer;

/**
 * 
 * @author Freamcoding
 * 
 * 	Simple timer class.
 * 
 * 	The tick method needs to be called at the initialization part of every logic cycle.
 * 	The nullify method needs to be called at the end of every logic cycle.
 *
 */

public class Timer {
	
	private long second; public long getSecond(){return second;}
	private long lastSecond; public long getLastSecond(){return lastSecond;}
	private int fps; public int getFps(){return fps;} public void incFps(){fps++;}
		public void nullFps(){fps = 0;}
	public int realFps;
	private long nanotime; public long getNanoTime(){return nanotime;}
	public boolean tick;
	private int fakeTime;
	
	public Timer(){
		second = lastSecond = nanotime = 0l;
		fps = 0;
	}
	
	// - Sets the tick field true every 1/10 of a second. 
	
	public void tick(){
		lastSecond = second;
		lastSecond = second;
		if(System.nanoTime() >= nanotime+(100000000)){
			fakeTime++;
			if(fakeTime == 11){
				fakeTime = 0;
				second++;
			}
		}
		if(lastSecond != second && !tick) tick = true;
		if(System.nanoTime() >= nanotime+(1000000000)){
			nanotime = System.nanoTime();
			realFps = fps;
			fps = 0;
		}
	}
	
	public void nullify(){
		if(tick) tick = false;
	}

}
