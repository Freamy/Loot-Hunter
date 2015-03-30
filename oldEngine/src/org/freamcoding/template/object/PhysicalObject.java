package org.freamcoding.template.object;

import java.util.ArrayList;

import org.freamcoding.template.data.Data;
import org.freamcoding.template.io.MouseInput;
import org.newdawn.slick.opengl.Texture;

/**
 * 
 * @author Freamcoding
 *
 *	
 *	Represents an object that has a place in the game world. The block variables used if the game map works in
 * tiles, while the "cor" variables responsible for more free game types.
 * 
 * 	The offset variables used mostly for the slow movement in pixel precise games.
 */

public class PhysicalObject extends MyObject {
	
	public int blockX;
	public int blockY;
	
	public int corX;
	public int corY;
	
	public int offsetX;
	public int offsetY;
	
	public int sizeX;
	public int sizeY;
	
	public ArrayList<Texture> graphics;
	
	public PhysicalObject(){
	}
	
	public PhysicalObject(String path, String type, boolean precise){
		graphics = MyObject.loadTextures(path, type, precise);
	}
	
	public PhysicalObject(int x, int y){
		blockX = x;
		blockY = y;
		offsetX = 0;
		offsetY = 0;
		sizeX = 32;
		sizeY = 32;
	}
	
	public boolean collisionCheckingPixelPrecise(int camX, int camY){
		int mx = MouseInput.mouseX + camX;
		int my = MouseInput.mouseY + camY;
		
		if(mx > corX+offsetX && mx < corX+offsetX+Data.blockSize &&
				my > corY+offsetY && my < corY+offsetY+Data.blockSize){
			return true;
		}
		return false;
	}
	

}
