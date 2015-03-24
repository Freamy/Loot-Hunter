package org.freamcoding.template.font;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Fonts {
	
	public ArrayList<FontPack> fontPacks;
	
	public Fonts(){
		fontPacks = new ArrayList<FontPack>();
		fontPacks.add(new FontPack("font1"));
		loadTextures("font1");
	}
	
	public FontPack getFontPack(String in){
		String inLower = in.toLowerCase();
		for(FontPack fp: fontPacks){
			if(fp.getName().toLowerCase().equals(inLower)) return fp;
		}
		return null;
	}
	
	public void loadTextures(String in){
		FontPack current = getFontPack(in);
		loadTextureList(current.getTexture(), "resource/textures/texts", true);
	}
	

	public void loadTextureList(ArrayList<Texture> txp, String path, boolean pixelPrecision){
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		
		for(File file: listOfFiles){
			String fileAddress = file.getName();
			txp.add(loadTexture(path+"/"+fileAddress, pixelPrecision));
		}
	}
	
	public Texture loadTexture(String path, boolean pixelPrecision){
		Texture self;
		try{
			self = TextureLoader.getTexture("png", new FileInputStream(new File(path)));
			if(pixelPrecision)
				self.setTextureFilter(GL11.GL_NEAREST);
			return self;
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
}
