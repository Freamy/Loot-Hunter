package org.freamcoding.template.object.camera;

import org.freamcoding.template.data.Data;
import org.freamcoding.template.object.PhysicalObject;

/**
 * 
 * @author Freamcoding
 *
 *
 *	Simple camera class for games that requires scrolling.
 */

public class Camera extends PhysicalObject {
	
	public static int cameraWidth;
	public static int cameraHeight;
	
	public Camera(){
		blockX = 0;
		blockY = 0;
		offsetX = 0;
		offsetY = 0;
		cameraWidth = Data.width/Data.blockSize;
		cameraHeight = Data.height/Data.blockSize;
	}
	
	public void resetOffset(){
		if(offsetX > Data.blockSize){
			offsetX = 0;
			blockX--;
		}else if(offsetX < -Data.blockSize){
			offsetX = 0;
			blockX++;
		}
		if(offsetY > Data.blockSize){
			offsetY = 0;
			blockY--;
		}else if(offsetY < -Data.blockSize){
			offsetY = 0;
			blockY++;
		}
	}
}
