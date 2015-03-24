package org.freamcoding.template.effect;

import java.util.ArrayList;
import java.util.Random;

import org.freamcoding.template.actor.Actor;
import org.newdawn.slick.opengl.Texture;

public class Banish extends Effect {

	public ArrayList<Texture> self = loadTextures("graphics/effectIcons/curse", "png", true);

	
	public Banish(int dmg) {
		effect = dmg;
		this.time = 0;
		canBeStacked = false;
		tooltip = "instantly kills the victim with 25 chance";
	}

	@Override
	public void effect(Actor actor) {
		Random rng = new Random();
		int chance = rng.nextInt(100);
		if(chance < 25){
			actor.health = -5;
		}
	}
	
	public void applyTimedEffects(Actor actor){
	}
	
	public void bindSelf(){
		//ethis.visionRange = 2;
		self.get(frame%(animationLength+1)).bind();
	}

}
