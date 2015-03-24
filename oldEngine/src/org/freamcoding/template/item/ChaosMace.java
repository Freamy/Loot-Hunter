package org.freamcoding.template.item;

import java.util.ArrayList;

import org.freamcoding.template.effect.Banish;
import org.freamcoding.template.effect.Damage;
import org.newdawn.slick.opengl.Texture;

public class ChaosMace extends Weapon {
	
	public ArrayList<Texture> self = loadTextures("graphics/weapons/ChaosMace", "png", true);
	
	public ChaosMace(){
		super();
		negative.add(new Damage(14));
		negative.add(new Banish(0));
		toolTip = "Chaos Mace     ";
	}
	
	public void bindSelf(){
		//ethis.visionRange = 2;
		self.get(frame%(animationLength+1)).bind();
	}
}
