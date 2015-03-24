package org.freamcoding.template.actor;

import java.util.ArrayList;

import org.freamcoding.template.item.*;
import org.freamcoding.template.map.Block;
import org.freamcoding.template.map.Map;
import org.newdawn.slick.opengl.Texture;

public class Dummy extends Actor {
	
	public ArrayList<Texture> self = loadTextures("graphics/actors/dummy", "png", true);

	public Dummy(int x, int y) {
		super(x, y);
		on = true;
		weapon = new ChaosMace();
		maxHealth = 100;
		health = 100;
	}

	public void bindSelf(){
		//ethis.visionRange = 2;
		self.get(frame%(animationLength+1)).bind();
	}

	@Override
	public ArrayList<Block> buildVisionBorder(Map map) {
		int sight = this.visionRange;
		ArrayList<Block> borderBlocks = new ArrayList<Block>();
		for(int out = -sight; out <= sight; out++){
				int x = this.blockX;
				int y = this.blockY;
				Block newBlock = map.fetchBlock(x-sight, y+out, map);
				if(newBlock != null)
					borderBlocks.add(newBlock);
				
				newBlock = map.fetchBlock(x+sight, y+out, map);
				if(newBlock != null)
					borderBlocks.add(newBlock);
				
				newBlock = map.fetchBlock(x+out, y-sight, map);
				if(newBlock != null)
					borderBlocks.add(newBlock);
				
				newBlock = map.fetchBlock(x+out, y+sight, map);
				if(newBlock != null)
					borderBlocks.add(newBlock);
		}
		return borderBlocks;
	}
}
