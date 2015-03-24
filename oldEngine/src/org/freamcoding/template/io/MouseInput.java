package org.freamcoding.template.io;

import org.freamcoding.template.data.Data;
import org.lwjgl.input.Mouse;

/**
 * 
 * @author Freamcoding
 *
 *	Basic mouse class, used to determinate the position of the mouse. The function resetMouseCoordinates have to be called at the begining of every
 * logic cycle.
 */

public class MouseInput {
	
	public static int mouseX;
	public static int mouseY;
	
	public static int mouseBlockX;
	public static int mouseBlockY;
	
	public static void resetMouseCoordinates(){
		mouseX = Mouse.getX();
		mouseY = Data.height - Mouse.getY();
		
		mouseBlockX = mouseX/Data.blockSize;
		mouseBlockY = mouseY/Data.blockSize;
	}
}
