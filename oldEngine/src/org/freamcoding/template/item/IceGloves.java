package org.freamcoding.template.item;

import java.util.ArrayList;

import org.freamcoding.template.effect.Chill;
import org.freamcoding.template.effect.Damage;
import org.newdawn.slick.opengl.Texture;

public class IceGloves extends Weapon {
	
	public ArrayList<Texture> self = loadTextures("graphics/weapons/IceGloves", "png", true);
	
	public IceGloves(){
		super();
		negative.add(new Damage(14));
		negative.add(new Chill(3));
		toolTip = "Ice Sword - ";
	}
	
	public void bindSelf(){
		//ethis.visionRange = 2;
		self.get(frame%(animationLength+1)).bind();
	}
}
