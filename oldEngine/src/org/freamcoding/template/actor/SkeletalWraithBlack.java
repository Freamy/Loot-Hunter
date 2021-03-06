package org.freamcoding.template.actor;

import java.util.ArrayList;

import org.freamcoding.template.drop.Drop;
import org.freamcoding.template.item.*;
import org.freamcoding.template.item.armor.RingOfProtection;
import org.freamcoding.template.item.armor.RingOfRegeneration;
import org.freamcoding.template.map.Block;
import org.freamcoding.template.map.Map;
import org.newdawn.slick.opengl.Texture;

public class SkeletalWraithBlack extends Actor {
	
	public ArrayList<Texture> self = loadTextures("graphics/actors/SkeletalWraithBlack", "png", true);

	public SkeletalWraithBlack(int x, int y) {
		super(x, y);
		this.weapon = new GhostHand(5);
		maxHealth = 20;
		health = 20;
		toolTip = "Weapon " + weapon.getTooltip();
		
		dropTable.add(new Drop(new RingOfProtection(), 10));
		dropTable.add(new Drop(new RingOfRegeneration(), 100));
	}

	@Override
	public ArrayList<Block> buildVisionBorder(Map map) {
		return null;
	}
	
	public void bindSelf(){
		self.get(frame%(animationLength+1)).bind();
	}

}
