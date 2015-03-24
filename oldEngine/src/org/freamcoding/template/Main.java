package org.freamcoding.template;

import org.freamcoding.template.data.Data;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

/**
 * 
 * @author Freamcoding
 * 
 * 	Template engine program for games.
 * 
 */

public class Main {
	
	public Game game;
	public Data data;
	
	public Main(){
		data = new Data();
	}
	
	public void run(){
		displayInit();
		glInit();
		game = new Game(data);
		game.run();
	}
	
	public void displayInit(){
		try{
			boolean fullScreen = false;
			DisplayMode dpm = Display.getDesktopDisplayMode();
			Display.setFullscreen(fullScreen);
			if(!fullScreen){
				data.setResolution(1280,1024);
				//data.setResolution(600,400);
				dpm = new DisplayMode(Data.width,Data.height);
			}else{
				int width = dpm.getWidth();
				int height = dpm.getHeight();
				data.setResolution(width , height);
			}			
			Display.setDisplayMode(dpm);
			Display.setInitialBackground(0.5f,0.5f,0.5f);
			Display.create();
			Display.setVSyncEnabled(true);
			Display.setTitle("Loot Hunter - v0.11");
			
		}catch(LWJGLException e){
			e.printStackTrace();
		}
	}
	
	private void glInit(){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Data.width, Data.height, 0, 100, -100);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
	}
	
	
	public static void main(String[] args){
		Main main = new Main();
		main.run();
	}	
}
