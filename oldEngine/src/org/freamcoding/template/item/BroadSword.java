package org.freamcoding.template.item;

import java.util.ArrayList;

import org.freamcoding.template.effect.Damage;
import org.newdawn.slick.opengl.Texture;

public class BroadSword extends Weapon {
	
	public ArrayList<Texture> self = loadTextures("graphics/weapons/BroadSword", "png", true);
	
	public BroadSword(){
		super();
		negative.add(new Damage(7));
		toolTip = "Broad Sword ";
	}
	
	public void bindSelf(){
		//ethis.visionRange = 2;
		self.get(frame%(animationLength+1)).bind();
	}
}
