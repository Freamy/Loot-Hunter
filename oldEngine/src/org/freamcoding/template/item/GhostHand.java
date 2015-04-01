package org.freamcoding.template.item;

import java.util.ArrayList;

import org.freamcoding.template.effect.damage.magical.ice.ColdTouch;
import org.freamcoding.template.effect.damage.physical.Phase;
import org.newdawn.slick.opengl.Texture;

public class GhostHand extends Weapon{
	public ArrayList<Texture> self = loadTextures("graphics/weapons/Mace", "png", true);
	
	public GhostHand(int dmg){
		super();
		negative.add(new ColdTouch(3));
		negative.add(new Phase(dmg));
		toolTip = "Cold embrace of a ghost Deals 3 ice and "+dmg+" phase damage";
		range = 1;
	}
}
