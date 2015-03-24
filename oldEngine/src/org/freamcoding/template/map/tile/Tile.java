package org.freamcoding.template.map.tile;

import org.freamcoding.template.object.MyObject;

public class Tile extends MyObject {
	
	public int id;
	
	public boolean blocksVision;
	
	
	public static Tile[] tiles = new Tile[256];
	
	public static Stone stone = new Stone(0);
	public static Void space = new Void(1);
	
	public Tile(int id){
		this.id = id;
		tiles[id] = this;
	}
}
