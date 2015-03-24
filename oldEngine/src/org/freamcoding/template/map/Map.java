package org.freamcoding.template.map;

import org.freamcoding.template.assets.algorithms.aStar.Zone;
import org.freamcoding.template.assets.algorithms.roomedDungeonGenerator.Room;
import org.freamcoding.template.data.Data;

public class Map {	
	public Block[][] map;
	public Zone[][] graph;
	public Room[][] rooms;
	public int width;
	public int height;
	
	public Map(){
		int size = 90;
		width = size;
		height = size;
		createMap(size, size);
	}
	
	public void createMap(int w, int h){
		/*
		map = new Block[w][h];
		graph = new Zone[w][h];
		width = w;
		height = h;
		for(int i = 0; i < w; i++){
			for(int j = 0; j < h; j++){
				map[i][j] = new Block(0,i,j);
				graph[i][j] = new Zone(i,j,0);
			}
		}
		for(int i = 0; i < 250; i++){
			Random rgn = new Random();
			int x = rgn.nextInt(25);
			int y = rgn.nextInt(25);
			map[x][y].setId(1);
			graph[x][y].setDiff(10000);
		}*/
	}
	
	public void darken(){
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				map[i][j].setVisible(false);
			}
		}
	}
	
	public Block fetchBlock(int x, int y, Map map){
		try{
			if(x < 0) x = 0;
			else if(x > width-1) x = width-1;
			if(y < 0) y = 0;
			else if (y > height-1) y = height-1;
 			return map.map[x][y];
		}catch(Exception e){
			return null;
		}
	}

	public void clear() {
		for(int i = 0; i < width; i++)
			for(int j = 0; j < height; j++){
				graph[i][j].partOfDone = false;
				graph[i][j].partOfFrontier = false;
				graph[i][j].clear_Zones();
			}
	}

	public Zone[] getAdjecent(Zone current) {
		Zone[] near = new Zone[4];
		if(current.x > 0){
			near[0] = graph[current.getX()-1][current.getY()];
			//if(Data.map.map[near[0].x][near[0].y].visited == false) near[0].bonus = 10000;
		}
		if(current.x < width-1){
			near[1] = graph[current.getX()+1][current.getY()];
			//if(Data.map.map[near[1].x][near[1].y].visited == false) near[1].bonus = 10000;
		}
		if(current.y > 0){
			near[2] = graph[current.getX()][current.getY()-1];
			//if(Data.map.map[near[2].x][near[2].y].visited == false) near[2].bonus = 10000;
		}
		if(current.y < height-1){
			near[3] = graph[current.getX()][current.getY()+1];
			//if(Data.map.map[near[3].x][near[3].y].visited == false) near[3].bonus = 10000;
		}
		return near;
	}
}
