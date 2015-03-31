package org.freamcoding.template.effect.damage.physical;

import org.freamcoding.template.actor.Actor;
import org.freamcoding.template.effect.Effect;

public class Heal extends Physical{
	
	public Heal(int dmg){
		this.effect = dmg;
		canBeStacked = false;
		this.time = 9999999;
	}

	@Override
	public void effect(Actor actor) {
		
	}

	@Override
	public void applyTimedEffects(Actor actor) {
		int realDamage = effect;
		for(Effect armor: actor.ring.effects){
			if(armor.getClass().equals(this.getClass())){
				realDamage += actor.ring.modifiesEffect;
			}
		}
		actor.health += realDamage;
		this.showEffectText(actor, realDamage);
		
	}

}
