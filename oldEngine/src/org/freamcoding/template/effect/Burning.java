package org.freamcoding.template.effect;

import java.util.ArrayList;

import org.freamcoding.template.actor.Actor;
import org.newdawn.slick.opengl.Texture;

public class Burning extends Effect {

	public ArrayList<Texture> self = loadTextures("graphics/effectIcons/curse", "png", true);

	
	public Burning(int dmg) {
		effect = dmg;
		this.time = 3;
		canBeStacked = true;
		tooltip = "deals "+effect+" damage for "+time+" turn";
	}

	@Override
	public void effect(Actor actor) {
		actor.health -= effect;
		this.showEffectText(actor);
		if(canBeStacked)
			actor.appliedEffects.add(new Burning(effect));
		else{
			for(Effect effect: actor.appliedEffects){
				if(effect.getClass().equals(this.getClass())){
					return;
				}
			}
			actor.appliedEffects.add(new Burning(effect));
		}
	}
	
	public void applyTimedEffects(Actor actor){
		actor.health -= effect;
		this.showEffectText(actor);
		time--;
	}
	
	public void bindSelf(){
		//ethis.visionRange = 2;
		self.get(frame%(animationLength+1)).bind();
	}

}
