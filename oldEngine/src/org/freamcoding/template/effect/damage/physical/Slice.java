package org.freamcoding.template.effect.damage.physical;

import org.freamcoding.template.actor.Actor;
import org.freamcoding.template.effect.Effect;

public class Slice extends Physical{
	
	public Slice(int dmg){
		this.effect = dmg;
		canBeStacked = false;
		this.time = 0;
	}

	@Override
	public void effect(Actor actor) {
		int realDamage = effect;
		for(Effect armor: actor.armor.effects){
			if(armor.getClass().equals(this.getClass())){
				realDamage += actor.armor.modifiesEffect;
			}
		}
		actor.health -= realDamage;
		this.showEffectText(actor, realDamage);
	}

	@Override
	public void applyTimedEffects(Actor actor) {
		// TODO Auto-generated method stub
		
	}

}
