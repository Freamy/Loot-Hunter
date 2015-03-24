package org.freamcoding.template.object;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.freamcoding.template.assets.Assets;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import static org.lwjgl.opengl.GL11.*;

/**
 * 
 * @author Freamcoding
 *
 *
 *	Parent class for every object in the game. Represents an object that has graphics but no set location
 * in the game world. For example icons.
 * 
 * 	The update methods needs to be called at every logic cycle, it makes sure of the animation flow, every class
 * specific behavior needs to be included in the corresponding class.
 */

public class MyObject {
	
	public int frame;
	public int animationLength;
	
	/* 
	 * @param pixelPrecise - Decides if anti aliasing is applied to the sprite. 
	 */

	public static ArrayList<Texture> loadTextures(String path, String type, boolean pixelPrecise){
		ArrayList<Texture> txp = new ArrayList<Texture>();
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		for(File file: listOfFiles){
			String address = file.getName();
			Texture self;
			try{
				self = TextureLoader.getTexture(type, new FileInputStream(new File(path+"/"+address)));
				if(pixelPrecise){
					self.setTextureFilter(GL_NEAREST);
				}
				txp.add(self);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return txp;
	}
	
	public void update(){
		if(Assets.timer.tick){
			frame = (frame+1)%animationLength;
		}
	}
	
	public void bindSelf(){
	}
}
