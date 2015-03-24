package org.freamcoding.template.object;

import java.util.ArrayList;

/**
 * 
 * @author Freamcoding
 *
 *
 *	Stores an array of icons and text to be drawn at a specific location on the screen.
 * 
 * 	Both the icons and the text coordination should be added relativistically to the widgets location
 */

public class Widget extends PhysicalObject {
	
	public ArrayList<PhysicalObject> icons;
	public ArrayList<WidgetText> texts;
		
	public int width;
	public int height;
	
	public Widget(){
		icons = new ArrayList<PhysicalObject>();
		texts = new ArrayList<WidgetText>();
	}

}
