package org.freamcoding.template.item.armor;

import java.util.ArrayList;

import org.freamcoding.template.effect.damage.magical.ice.Ice;
import org.freamcoding.template.effect.damage.physical.Heal;
import org.newdawn.slick.opengl.Texture;

public class RingOfRegeneration extends Ring {
	
	public ArrayList<Texture> self = loadTextures("graphics/armor/RingOfRegeneration", "png", true);
		
	public RingOfRegeneration(){
		super();
		effects.add(new Heal(0));
		modifiesEffect = 1;
	}
	
	public void bindSelf(){
		self.get(frame%(animationLength+1)).bind();
	}
}
