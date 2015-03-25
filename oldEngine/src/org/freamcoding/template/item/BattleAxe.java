package org.freamcoding.template.item;

import java.util.ArrayList;

import org.freamcoding.template.effect.Damage;
import org.newdawn.slick.opengl.Texture;

public class BattleAxe extends Weapon {
	
	public ArrayList<Texture> self = loadTextures("graphics/weapons/BattleAxe", "png", true);
	
	public BattleAxe(){
		super();
		negative.add(new Damage(5));
		toolTip = "Battle Axe ";
	}
	
	public void bindSelf(){
		//ethis.visionRange = 2;
		self.get(frame%(animationLength+1)).bind();
	}
}
