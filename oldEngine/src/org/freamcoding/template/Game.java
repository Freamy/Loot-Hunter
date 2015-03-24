package org.freamcoding.template;

import org.freamcoding.template.data.Data;
import org.freamcoding.template.font.Fonts;
import org.freamcoding.template.logic.Logic;
import org.freamcoding.template.screen.Screen;
import org.lwjgl.opengl.Display;

public class Game {
	
	public Data data;
	
	public Logic logic;
	public Screen screen;	

	public static Fonts fonts = new Fonts();
	
	public Game(Data d){
		data = d;
		logic = new Logic();
		screen = new Screen();
	}
	
	public void run(){
		data.makeDummy();
		while(!Display.isCloseRequested() && Data.run){
			logic.iterate();
			screen.render();
		}
	}

}
