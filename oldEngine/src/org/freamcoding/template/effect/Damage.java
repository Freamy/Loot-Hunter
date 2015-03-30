package org.freamcoding.template.effect;

import java.util.ArrayList;

import org.freamcoding.template.actor.Actor;
import org.newdawn.slick.opengl.Texture;

public class Damage extends Effect {

	public ArrayList<Texture> self = loadTextures("graphics/effectIcons/curse", "png", true);

	
	public Damage(int dmg) {
		effect = dmg;
		tooltip = "deals "+effect+" damage";
	}

	@Override
	public void effect(Actor actor) {
		actor.health -= effect;
		this.showEffectText(actor,5);
	}
	
	public void bindSelf(){
		self.get(frame%(animationLength+1)).bind();
	}

	@Override
	public void applyTimedEffects(Actor actor) {
	}

}
