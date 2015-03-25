package org.freamcoding.template.actor;

import java.util.ArrayList;

import org.freamcoding.template.item.FlameSword;
import org.freamcoding.template.map.Block;
import org.freamcoding.template.map.Map;
import org.newdawn.slick.opengl.Texture;

public class FireGiant extends Actor {
	
	public ArrayList<Texture> self = loadTextures("graphics/actors/fireGiant", "png", true);

	public FireGiant(int x, int y) {
		super(x, y);
		this.weapon = new FlameSword();
		maxHealth = 45;
		health = 45;
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
