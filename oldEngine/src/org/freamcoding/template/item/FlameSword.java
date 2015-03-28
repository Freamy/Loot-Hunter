package org.freamcoding.template.item;

import java.util.ArrayList;

import org.freamcoding.template.effect.Burning;
import org.freamcoding.template.effect.Damage;
import org.newdawn.slick.opengl.Texture;

public class FlameSword extends Weapon {
	
	public ArrayList<Texture> self = loadTextures("graphics/weapons/FlameSword", "png", true);
	
	public FlameSword(){
		super();
		negative.add(new Damage(5));
		negative.add(new Burning(4));
		toolTip = "Flame Sword ";
	}
	
	public void bindSelf(){
		//ethis.visionRange = 2;
		self.get(frame%(animationLength+1)).bind();
	}
}