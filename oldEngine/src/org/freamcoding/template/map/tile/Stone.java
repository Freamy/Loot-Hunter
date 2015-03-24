package org.freamcoding.template.map.tile;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

public class Stone extends Tile {
	
	public ArrayList<Texture> self = loadTextures("graphics/tiles/stone", "png", true);
	
	public Stone(int id) {
		super(id);
		blocksVision = false;
	}
	
	public void bindSelf(){
		self.get(frame%(animationLength+1)).bind();
	}
}
