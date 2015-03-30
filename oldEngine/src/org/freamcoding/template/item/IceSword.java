package org.freamcoding.template.item;

import java.util.ArrayList;

import org.freamcoding.template.effect.Damage;
import org.freamcoding.template.effect.damage.magical.ice.Chill;
import org.newdawn.slick.opengl.Texture;

public class IceSword extends Weapon {
	
	public ArrayList<Texture> self = loadTextures("graphics/weapons/IceSword", "png", true);
	
	public IceSword(){
		super();
		negative.add(new Damage(0));
		negative.add(new Chill(5));
		toolTip = "Ice Sword ";
	}
	
	public void bindSelf(){
		//ethis.visionRange = 2;
		self.get(frame%(animationLength+1)).bind();
	}
}
