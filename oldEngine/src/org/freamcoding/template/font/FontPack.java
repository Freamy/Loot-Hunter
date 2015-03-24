package org.freamcoding.template.font;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

public class FontPack {
	private ArrayList<Texture> texture; public ArrayList<Texture> getTexture(){return texture;}
	private String name; public String getName(){return name;}
	
	public FontPack(String n){
		texture = new ArrayList<Texture>();
		name = n;
	}
	
	public void bindTexture(int id){
		texture.get(id).bind();
	}
}
