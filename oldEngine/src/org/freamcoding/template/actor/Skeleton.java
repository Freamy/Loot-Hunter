package org.freamcoding.template.actor;

import java.util.ArrayList;

import org.freamcoding.template.item.FlameSword;
import org.freamcoding.template.item.Mace;
import org.freamcoding.template.map.Block;
import org.freamcoding.template.map.Map;
import org.newdawn.slick.opengl.Texture;

public class Skeleton extends Actor {
	
	public ArrayList<Texture> self = loadTextures("graphics/actors/skeleton", "png", true);

	public Skeleton(int x, int y) {
		super(x, y);
		this.weapon = new Mace();
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
