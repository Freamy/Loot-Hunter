package org.freamcoding.template.data;


import java.util.ArrayList;
import java.util.Random;

import org.freamcoding.template.actor.*;
import org.freamcoding.template.assets.algorithms.roomedDungeonGenerator.RoomedDungeonGenerator;
import org.freamcoding.template.assets.floatingText.FloatingText;
import org.freamcoding.template.audio.AudioPack;
import org.freamcoding.template.lootBags.LootBag;
import org.freamcoding.template.map.Map;

/**
 * 
 * @author Freamcoding
 *
 *	Data class, mostly contains static variables and visible from most class.
 */

public class Data {
	
	public static int width;
	public static int height;
	
	public static int blockSize = 96;
	
	public static boolean run = true;
	
	public static boolean audio = true;
	
	public static AudioPack audioPacks = new AudioPack();
	
	public static Map map;
	public static String tooltip = "";
	
	//public static ArrayList<Actor> player = new ArrayList<Actor>();
	public static Actor player;
	public static ArrayList<Actor> enemies = new ArrayList<Actor>();
	public static ArrayList<LootBag> loots = new ArrayList<LootBag>();
	
	public static ArrayList<FloatingText> floatingTexts = new ArrayList<FloatingText>();
	
	public static int camLocationX;
	public static int camLocationY;
	
	public static float visionX = 5;
	public static float visionY = 5;
	//public static int fullVision =(int) vision*2;
	
	// DEBUG
	
	public static boolean DEBUG_NOCLIP = false;
	
	public void setResolution(int w, int h){
		width = w;
		height = h;
		map = new Map();
		RoomedDungeonGenerator dungeonGenerator = new RoomedDungeonGenerator();
		map = dungeonGenerator.makeMap(map, 50, 9);
		visionX = (width*0.8f)/blockSize/2;
		visionY = (height*0.95f)/blockSize/2;
		//fullVision = (int)vision*2;
	}
	
	public static void makeDummy(){
		enemies.clear();
		loots.clear();
		Random rgn = new Random();
		int mRoom = 9;
		player = new Dummy(map.rooms[0][0].centerX,map.rooms[0][0].centerY);
		player.visionRange = 4;
		enemies.add(new VampireCount(map.rooms[8][8].centerX, map.rooms[8][8].centerY));
		map.rooms[8][8].occupied = true;
		map.rooms[0][0].occupied = true;
		for(int i = 0; i < 15; i++){
			int x = rgn.nextInt(mRoom);
			int y = rgn.nextInt(mRoom);
			int placeX = map.rooms[x][y].centerX;
			int placeY = map.rooms[x][y].centerY;
			if(!map.rooms[x][y].occupied){
				enemies.add(new SmallerZombie(placeX,placeY));
				map.rooms[x][y].occupied = true;
			}
		}
		for(int i = 0; i < 10; i++){
			int x = rgn.nextInt(mRoom);
			int y = rgn.nextInt(mRoom);
			int placeX = map.rooms[x][y].centerX;
			int placeY = map.rooms[x][y].centerY;
			if(!map.rooms[x][y].occupied){
				enemies.add(new Zombie(placeX,placeY));
				map.rooms[x][y].occupied = true;
			}
		}
		for(int i = 0; i < 10; i++){
			int x = rgn.nextInt(mRoom);
			int y = rgn.nextInt(mRoom);
			int placeX = map.rooms[x][y].centerX;
			int placeY = map.rooms[x][y].centerY;
			if(!map.rooms[x][y].occupied){
				enemies.add(new Skeleton(placeX,placeY));
				map.rooms[x][y].occupied = true;
			}
		}
		for(int i = 0; i < 7; i++){
			int x = rgn.nextInt(mRoom);
			int y = rgn.nextInt(mRoom);
			int placeX = map.rooms[x][y].centerX;
			int placeY = map.rooms[x][y].centerY;
			if(!map.rooms[x][y].occupied){
				enemies.add(new SkeletonWarrior(placeX,placeY));
				map.rooms[x][y].occupied = true;
			}
		}
		for(int i = 0; i < 5; i++){
			int x = rgn.nextInt(mRoom);
			int y = rgn.nextInt(mRoom);
			int placeX = map.rooms[x][y].centerX;
			int placeY = map.rooms[x][y].centerY;
			if(!map.rooms[x][y].occupied){
				enemies.add(new Spectre(placeX,placeY));
				map.rooms[x][y].occupied = true;
			}
		}
		for(int i = 0; i < 3; i++){
			int x = rgn.nextInt(mRoom);
			int y = rgn.nextInt(mRoom);
			int placeX = map.rooms[x][y].centerX;
			int placeY = map.rooms[x][y].centerY;
			if(!map.rooms[x][y].occupied){
				enemies.add(new FireGiant(placeX,placeY));
				map.rooms[x][y].occupied = true;
			}
		}
	}
	
	public static void remakeGame(){
		map = new Map();
		RoomedDungeonGenerator dungeonGenerator = new RoomedDungeonGenerator();
		map = dungeonGenerator.makeMap(map, 50, 9);
		makeDummy();
	}

}
