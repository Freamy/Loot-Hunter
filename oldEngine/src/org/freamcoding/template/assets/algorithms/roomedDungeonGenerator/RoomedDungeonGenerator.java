package org.freamcoding.template.assets.algorithms.roomedDungeonGenerator;

import java.util.Random;

import org.freamcoding.template.assets.algorithms.aStar.Zone;
import org.freamcoding.template.map.Block;
import org.freamcoding.template.map.Map;

public class RoomedDungeonGenerator {
	
	public int numberOfRooms;
	public int maxRooms;
	public Random rng;
	
	public int width;
	public int height;
	
	public int roomsDone;
	
	public Map map;
	
	public Map makeMap(Map map, int numberOfRooms, int maxRooms){
		width = map.width;
		height = map.height;
		this.numberOfRooms = numberOfRooms;
		this.maxRooms = maxRooms;
		this.map = map;
		roomsDone = 0;
		map.graph = new Zone[width][height];
		map.map = new Block[width][height];
		rng = new Random();
		initMap();
		makeRooms();
		return this.map;
	}
	
	public void initMap(){
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				map.graph[i][j] = new Zone(i,j,10000);
				map.map[i][j] = new Block(1,i,j);
			}
		}
	}
	
	public void makeRooms(){
		map.rooms = new Room[maxRooms][maxRooms];
		makeCenters();
		pickRooms();
		finishEmptyAreas();
		buildDungeon();
	}
	
	public void makeCenters(){
		for(int i = 0; i < maxRooms; i++){
			for(int j = 0; j < maxRooms; j++){
				map.rooms[i][j] = new Room();
				int sizeX = width/maxRooms;
				int sizeY = height/maxRooms;
				map.rooms[i][j].centerX = i*sizeX + width/(maxRooms*2);
				map.rooms[i][j].centerY = j*sizeY + height/(maxRooms*2);
			}
		}
	}
	
	public void pickRooms(){
		while(roomsDone < numberOfRooms){
			int x = rng.nextInt(maxRooms);
			int y = rng.nextInt(maxRooms);
			int rW = rng.nextInt(3)+2;
			int rH = rng.nextInt(3)+2;
			if(mineRoom(true, rW, rH, x, y)) roomsDone++;
		}
	}
	
	public void finishEmptyAreas(){
		for(int i = 0; i < maxRooms; i++){
			for(int j = 0; j < maxRooms; j++){
				mineRoom(true,2,2,i,j);
			}
		}
	}
	
	public boolean mineRoom(boolean isRoom, int w, int h, int x, int y){
		if(isRoom != map.rooms[x][y].isRoom){
			map.rooms[x][y].isRoom = isRoom;
			map.rooms[x][y].height = h;
			map.rooms[x][y].width = w;
			for(int i = 0; i < width; i++){
				for(int j = 0; j < height; j++){
					if(i > (map.rooms[x][y].centerX - map.rooms[x][y].width) && i < (map.rooms[x][y].centerX+map.rooms[x][y].width) &&
							j > (map.rooms[x][y].centerY-map.rooms[x][y].height) && j < (map.rooms[x][y].centerY + map.rooms[x][y].height)){
						map.map[i][j].setId(0);
						map.graph[i][j].setDiff(0);
					}

				}
			}
			return true;
		}
		return false;
	}
	
	public void buildDungeon(){
		int activeX = rng.nextInt(maxRooms);
		int activeY = rng.nextInt(maxRooms);
		map.rooms[activeX][activeY].isConnected = true;
		while(testConnections()){
			int direction = rng.nextInt(4); // 0 up, 1 down, 2 right, 3 left
			if(!checkNeighbor(activeX, activeY)){
				for(int i = 0; i < maxRooms; i++){
					for(int j = 0; j < maxRooms; j++){
						if(checkNeighbor(activeX, activeY) && map.rooms[i][j].isConnected){
							activeX = i;
							activeY = j;
						}
					}
				}
			}
			if(direction == 0 && activeY != 0){
				buildCorridor(direction, activeX, activeY, 0, -1, false);
				activeY--;
			}
			if(direction == 1 && activeY != maxRooms-1){
				buildCorridor(direction, activeX, activeY, 0, 1, false);
				activeY++;
			}
			if(direction == 2 && activeX != maxRooms-1){
				buildCorridor(direction, activeX, activeY, 1, 0, false);
				activeX++;
			}
			if(direction == 3 && activeX != 0){
				buildCorridor(direction, activeX, activeY, -1, 0, false);
				activeX--;
			}
		}
	}
	
	public void buildCorridor(int direction, int activeX, int activeY, int stepX, int stepY, boolean oneEnterance){

		int xLine = map.rooms[activeX][activeY].centerX;
		int yLine = map.rooms[activeX][activeY].centerY;
		if(direction == 0 && activeY != 0){
			if(map.rooms[activeX+stepX][activeY+stepY].isConnected == oneEnterance){
				for(int i = yLine; i > map.rooms[activeX+stepX][activeY+stepY].centerY; i--){
					cutCorridor(xLine,i);
				}
				map.rooms[activeX+stepX][activeY+stepY].isConnected = true;;
			}
		}
		
		if(direction == 1 && activeY != (maxRooms-1)){
			if(map.rooms[activeX+stepX][activeY+stepY].isConnected == oneEnterance){
				for(int i = yLine; i < map.rooms[activeX+stepX][activeY+stepY].centerY; i++){
					cutCorridor(xLine,i);
				}
				map.rooms[activeX+stepX][activeY+stepY].isConnected = true;
			}
		}
		
		if(direction == 2 && activeX != (maxRooms-1)){
			if(map.rooms[activeX+stepX][activeY+stepY].isConnected == oneEnterance){
				for(int i = xLine; i < map.rooms[activeX+stepX][activeY+stepY].centerX; i++){
					cutCorridor(i,yLine);
				}
				map.rooms[activeX+stepX][activeY+stepY].isConnected = true;
			}
		}
		
		if(direction == 3 && activeX != 0){
			if(map.rooms[activeX+stepX][activeY+stepY].isConnected == oneEnterance){
				for(int i = xLine; i > map.rooms[activeX+stepX][activeY+stepY].centerX; i--){
					cutCorridor(i,yLine);
				}
				map.rooms[activeX+stepX][activeY+stepY].isConnected = true;
			}
		}

	}
	
	public void cutCorridor(int x, int y){
		map.map[x][y].setId(0);
		map.graph[x][y].setDiff(0);
	}
	
	private boolean checkNeighbor(int activeX, int activeY){
		if(activeX != (maxRooms-1) && !map.rooms[activeX+1][activeY].isConnected)
			return true;
		if(activeX != 0 && !map.rooms[activeX-1][activeY].isConnected)
			return true;
		if(activeY != (maxRooms-1) && !map.rooms[activeX][activeY+1].isConnected)
			return true;
		if(activeY != 0 && !map.rooms[activeX][activeY-1].isConnected)
			return true;
		return false;
	}
	
	private boolean testConnections(){
		for(int i = 0; i < maxRooms; i++){
			for(int j = 0; j < maxRooms; j++){
				if(!map.rooms[i][j].isConnected) return true;
			}
		}
		return false;
	}
}
