package org.freamcoding.template.item.armor;

import java.util.ArrayList;

import org.freamcoding.template.effect.damage.physical.Slice;
import org.newdawn.slick.opengl.Texture;

public class SoftLeatherArmor extends Armor {
	
	public ArrayList<Texture> self = loadTextures("graphics/armor/SoftLeatherArmor", "png", true);
	
	public SoftLeatherArmor(){
		super();
		effects.add(new Slice(10));
		modifiesEffect = 4;
	}
	
	public void bindSelf(){
		//ethis.visionRange = 2;
		self.get(frame%(animationLength+1)).bind();
	}

}
