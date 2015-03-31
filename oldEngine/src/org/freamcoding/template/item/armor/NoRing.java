package org.freamcoding.template.item.armor;

import java.util.ArrayList;

import org.freamcoding.template.effect.damage.magical.ice.Ice;
import org.newdawn.slick.opengl.Texture;

public class NoRing extends Ring {
	
	public ArrayList<Texture> self = loadTextures("graphics/armor/noRing", "png", true);
		
	public NoRing(){
		super();
	}
	
	public void bindSelf(){
		self.get(frame%(animationLength+1)).bind();
	}
}
