package org.freamcoding.template.map.tile;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

public class Void extends Tile {
	
	public ArrayList<Texture> self = loadTextures("graphics/tiles/void", "png", true);
	
	public Void(int id) {
		super(id);
		blocksVision = false;
	}
	
	public void bindSelf(){
		blocksVision = true;
		self.get(frame%(animationLength+1)).bind();
	}
}
