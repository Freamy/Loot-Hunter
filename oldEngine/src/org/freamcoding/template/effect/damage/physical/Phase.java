package org.freamcoding.template.effect.damage.physical;

import org.freamcoding.template.actor.Actor;
import org.freamcoding.template.effect.Effect;

public class Phase extends Physical{
	
	public Phase(int dmg){
		this.effect = dmg;
		canBeStacked = false;
		this.time = 0;
	}

	@Override
	public void effect(Actor actor) {
		int realDamage = calculateInfluences(actor);
		actor.health -= realDamage;
		this.showEffectText(actor, realDamage);
	}

	@Override
	public void applyTimedEffects(Actor actor) {
		// TODO Auto-generated method stub
		
	}

}
