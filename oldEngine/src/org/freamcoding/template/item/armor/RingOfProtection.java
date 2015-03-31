package org.freamcoding.template.item.armor;

import java.util.ArrayList;

import org.freamcoding.template.effect.damage.magical.ice.Ice;
import org.newdawn.slick.opengl.Texture;

public class RingOfProtection extends Ring {
	
	public ArrayList<Texture> self = loadTextures("graphics/armor/RingOfProtection", "png", true);
		
	public RingOfProtection(){
		super();
		effects.add(new Ice());
		modifiesEffect = 5;
	}
	
	public void bindSelf(){
		self.get(frame%(animationLength+1)).bind();
	}
}
