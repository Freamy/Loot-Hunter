package org.freamcoding.template.actor;

import java.util.ArrayList;

import org.freamcoding.template.item.BattleAxe;
import org.freamcoding.template.item.FlameSword;
import org.freamcoding.template.map.Block;
import org.freamcoding.template.map.Map;
import org.newdawn.slick.opengl.Texture;

public class Zombie extends Actor {
	
	public ArrayList<Texture> self = loadTextures("graphics/actors/zombie", "png", true);

	public Zombie(int x, int y) {
		super(x, y);
		this.weapon = new BattleAxe();
		maxHealth = 25;
		health = 25;
		toolTip = "Weapon " + weapon.getTooltip();
		level = 5;
	}

	@Override
	public ArrayList<Block> buildVisionBorder(Map map) {
		return null;
	}
	
	public void bindSelf(){
		self.get(frame%(animationLength+1)).bind();
	}

}
