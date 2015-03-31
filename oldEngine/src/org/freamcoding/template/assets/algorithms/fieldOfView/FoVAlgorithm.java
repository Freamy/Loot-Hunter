package org.freamcoding.template.assets.algorithms.fieldOfView;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.freamcoding.template.actor.Actor;
import org.freamcoding.template.data.Data;
import org.freamcoding.template.map.Block;
import org.freamcoding.template.map.Map;
import org.freamcoding.template.map.tile.Tile;

public class FoVAlgorithm {
	
	/*
	 * This is the enterance function, it builds up the visible blocks on the map.
	 */
	
	public void buildVision(Map map, Actor player){
		ArrayList<Block> borderBlocks = buildBorderBlocks(map, player);
		if(borderBlocks != null){
			for(Block border: borderBlocks){
				setVisibility(map, player, border);
			}
		}
	}
	
	/*
	 * This function finds the borders of your vision, depending on how far the current character can see.
	 */
	
	public ArrayList<Block> buildBorderBlocks(Map map, Actor player){
		return player.buildVisionBorder(map);
	}
	
	/*
	 * Tries to get the border block from the map, if its not on the map returns a null value.
	 */
	

	
	/*
	 * This function picks every border block, and builds the collision line from them to the player. Then orders every blocks based on the distance of 
	 * the player, and then checks how long the player can see. If the vision collides with a wall piece the function stops and returns the point of calling.
	 */
	
	public void setVisibility(Map map, Actor player, Block border){
		int x = player.blockX;
		int y = player.blockY;
		int sight = player.visionRange;
		ArrayList<Block> collisionBlocks = new ArrayList<Block>();
		for(int i = x-sight; i <= x+sight; i++){
			for(int j = y-sight; j <= y+sight; j++){
				if(stillOnTheMap(i,j)){
					if(blockCollide(Data.map.map[i][j], border, player)) collisionBlocks.add(Data.map.map[i][j]);
				}
			}
		}
		collisionBlocks = orderBlocks(collisionBlocks, new Point(x,y));
		for(Block current: collisionBlocks){
			int blockX = current.x;
			int blockY = current.y;
			Data.map.map[blockX][blockY].visible = true;
			Data.map.map[blockX][blockY].visited = true;
			if(Tile.tiles[map.map[blockX][blockY].getId()].blocksVision) {
				return;
			}
		}
	}
	
	public boolean stillOnTheMap(int i, int j){
		return i >= 0 && i < Data.map.width && j >= 0 && j < Data.map.height;
	}
	
	/*
	 * The next two functions decide if the vision line intersect a block.
	 */
	
	public boolean blockCollide(Block current, Block border, Actor player){
		int startX = player.blockX*(Data.blockSize);
		int startY = player.blockY*(Data.blockSize);
		int endX = border.x*(Data.blockSize);
		int endY = border.y*(Data.blockSize);
		int centerX = current.x*(Data.blockSize);
		int centerY = current.y*(Data.blockSize);
		Point start = new Point(startX, startY);
		Point end = new Point(endX, endY);
		boolean firstSide = lineCollide(start, end, new Point(centerX - (Data.blockSize/2), centerY - (Data.blockSize/2)), new Point(centerX - (Data.blockSize/2), centerY + (Data.blockSize/2)));
		boolean secondSide = lineCollide(start, end, new Point(centerX - (Data.blockSize/2), centerY - (Data.blockSize/2)), new Point(centerX + (Data.blockSize/2), centerY - (Data.blockSize/2)));
		boolean thirdSide = lineCollide(start, end, new Point(centerX + (Data.blockSize/2), centerY + (Data.blockSize/2)), new Point(centerX - (Data.blockSize/2), centerY + (Data.blockSize/2)));
		boolean fourthSide = lineCollide(start, end, new Point(centerX + (Data.blockSize/2), centerY + (Data.blockSize/2)), new Point(centerX + (Data.blockSize/2), centerY - (Data.blockSize/2)));
		return firstSide || secondSide || thirdSide || fourthSide;
	}
	
	public boolean lineCollide(Point lineStart, Point lineEnd, Point zoneStart, Point zoneEnd){
		float s1_x = lineEnd.x - lineStart.x;
		float s1_y = lineEnd.y - lineStart.y;
		float s2_x = zoneEnd.x - zoneStart.x;
		float s2_y = zoneEnd.y - zoneStart.y;
		float s = (-s1_y * (lineStart.x - zoneStart.x) + s1_x * (lineStart.y - zoneStart.y)) / (-s2_x * s1_y + s1_x * s2_y);
		float t = ( s2_x * (lineStart.y - zoneStart.y) - s2_y * (lineStart.x - zoneStart.x)) / (-s2_x * s1_y + s1_x * s2_y);
		if(s >= 0 && s <= 1 && t >= 0 && t <= 1){
			return true;
		}
		return false;
	}
	
	/*
	 * Orders the blocks that collided with the actor's vision line.
	 * @param - center: the location of the actor you need to order from.
	 */
	
	public ArrayList<Block> orderBlocks(ArrayList<Block> unordered, final Point center){
		Collections.sort(unordered, new Comparator<Block>(){

			@Override
			public int compare(Block first, Block second) {
				double distanceFirstCenter = calculateDistance(first, center);
				double distanceSecondCenter = calculateDistance(second, center);
				if(distanceFirstCenter > distanceSecondCenter) return 1;
				else if(distanceFirstCenter == distanceSecondCenter) return 0;
				else return -1;
			}
			
			public double calculateDistance(Block block, Point center){
				int blockX = block.x;
				int blockY = block.y;
				return Math.sqrt(((blockX - center.x) * (blockX - center.x))+((blockY - center.y) * (blockY - center.y)));
			}
			
		});
		return unordered;
	}
	
	public boolean zoneVisible(Block zone, Actor target){
		return true;
	}
	
	public boolean isVisibleFrom(Point from, Actor to, Map map){
		int width = map.width;
		int height = map.height;
		ArrayList<Block> collision = new ArrayList<Block>();
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				if(blockCollide(map.map[i][j], map.map[from.x][from.y], to)) collision.add(Data.map.map[i][j]);
			}
		}
		collision = orderBlocks(collision, from);
		for(Block block: collision){
			int blockX = block.x;
			int blockY = block.y;
			if(Tile.tiles[map.map[blockX][blockY].getId()].blocksVision) return false;
		}
		return true;
	}
}

