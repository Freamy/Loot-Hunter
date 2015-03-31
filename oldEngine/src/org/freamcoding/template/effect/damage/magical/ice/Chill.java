package org.freamcoding.template.effect.damage.magical.ice;

import java.util.ArrayList;

import org.freamcoding.template.actor.Actor;
import org.freamcoding.template.effect.Effect;
import org.newdawn.slick.opengl.Texture;

public class Chill extends Ice {

	public ArrayList<Texture> self = loadTextures("graphics/effectIcons/chill", "png", true);

	
	public Chill(int dmg) {
		effect = dmg;
		this.time = 3;
		canBeStacked = true;
		tooltip = "deals "+effect+" damage for "+time+" turn";
	}

	@Override
	public void effect(Actor actor) {
		if(canBeStacked)
			actor.appliedEffects.add(new Chill(effect));
		else{
			for(Effect effect: actor.appliedEffects){
				if(effect.getClass().equals(this.getClass())){
					return;
				}
			}
			actor.appliedEffects.add(new Chill(effect));
		}
	}
	
	public void applyTimedEffects(Actor actor){
		int realDamage = this.effect;
		for(Effect effect: actor.ring.effects){
			if(this.getClass().getSuperclass().equals(effect.getClass())){
				realDamage -= actor.ring.modifiesEffect;
			}
		}
		if(realDamage > 0){
			actor.health -= realDamage;
			this.showEffectText(actor, realDamage);
		}
		time--;
	}
	
	public void bindSelf(){
		//ethis.visionRange = 2;
		self.get(frame%(animationLength+1)).bind();
	}

}
