package org.freamcoding.template.actor;

import java.util.ArrayList;

import org.freamcoding.template.item.IceSword;
import org.freamcoding.template.map.Block;
import org.freamcoding.template.map.Map;
import org.newdawn.slick.opengl.Texture;

public class VampireCount extends Actor {
	
	public ArrayList<Texture> self = loadTextures("graphics/actors/vampireCount", "png", true);

	public VampireCount(int x, int y) {
		super(x, y);
		this.weapon = new IceSword();
		maxHealth = 25;
		health = 25;
		toolTip = "Weapon " + weapon.getTooltip();
	}

	@Override
	public ArrayList<Block> buildVisionBorder(Map map) {
		return null;
	}
	
	public void bindSelf(){
		self.get(frame%(animationLength+1)).bind();
	}

}
