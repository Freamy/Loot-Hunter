package org.freamcoding.template.lootBags;

import java.util.ArrayList;

import org.freamcoding.template.item.Item;
import org.freamcoding.template.object.PhysicalObject;
import org.newdawn.slick.opengl.Texture;

public class LootBag extends PhysicalObject{
	
//	public ArrayList<Texture> self = loadTextures("graphics/actors/lootbag", "png", true);
	public Item loot;
	
	public LootBag(int x, int y, Item loot){
		this.blockX = x;
		blockY = y;
		this.loot = loot;
	}
	
	//public void bindSelf(){
		//self.get(frame%(animationLength+1)).bind();
	//}


}
