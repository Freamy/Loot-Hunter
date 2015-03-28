package org.freamcoding.template.item;

import java.util.ArrayList;

import org.freamcoding.template.effect.Damage;
import org.newdawn.slick.opengl.Texture;

public class Mace extends Weapon {
	
	public ArrayList<Texture> self = loadTextures("graphics/weapons/Mace", "png", true);
	
	public Mace(){
		super();
		negative.add(new Damage(3));
		toolTip = "Mace ";
	}
	
	public void bindSelf(){
		//ethis.visionRange = 2;
		self.get(frame%(animationLength+1)).bind();
	}
}