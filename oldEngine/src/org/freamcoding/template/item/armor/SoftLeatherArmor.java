package org.freamcoding.template.item.armor;

import java.util.ArrayList;

import org.freamcoding.template.effect.damage.physical.Bludgeon;
import org.newdawn.slick.opengl.Texture;

public class SoftLeatherArmor extends Armor {
	
	public ArrayList<Texture> self = loadTextures("graphics/armor/SoftLeatherArmor", "png", true);
	
	public SoftLeatherArmor(){
		super();
		effects.add(new Bludgeon(0));
		modifiesEffect = 4;
	}
	
	public void bindSelf(){
		self.get(frame%(animationLength+1)).bind();
	}

}
