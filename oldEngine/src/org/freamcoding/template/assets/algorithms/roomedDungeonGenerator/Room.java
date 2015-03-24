package org.freamcoding.template.assets.algorithms.roomedDungeonGenerator;

public class Room {
	
	public int centerX;
	public int centerY;
	public int width;
	public int height;
	
	public boolean isRoom;
	public boolean isConnected;
	
	public boolean occupied;
	
	public Room(){
		isRoom = false;
		isConnected = false;
		occupied = false;
	}

}
