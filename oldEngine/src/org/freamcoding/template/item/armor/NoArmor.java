package org.freamcoding.template.item.armor;

import java.util.ArrayList;

import org.freamcoding.template.effect.damage.magical.ice.Ice;
import org.newdawn.slick.opengl.Texture;

public class NoArmor extends Ring {
	
	public ArrayList<Texture> self = loadTextures("graphics/armor/noArmor", "png", true);
		
	public NoArmor(){
		super();
	}
	
	public void bindSelf(){
		self.get(frame%(animationLength+1)).bind();
	}
}
