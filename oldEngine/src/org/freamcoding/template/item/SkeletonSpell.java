package org.freamcoding.template.item;

import java.util.ArrayList;

import org.freamcoding.template.effect.damage.magical.ice.Chill;
import org.newdawn.slick.opengl.Texture;

public class SkeletonSpell extends Weapon {
	
	public ArrayList<Texture> self = loadTextures("graphics/weapons/Mace", "png", true);
	
	public SkeletonSpell(){
		super();
		negative.add(new Chill(1));
		toolTip = "Skeleton spell ";
		range = 4;
	}
	
	public void bindSelf(){
		self.get(frame%(animationLength+1)).bind();
	}
}
