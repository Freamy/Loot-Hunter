package org.freamcoding.template.effect;

import org.freamcoding.template.actor.Actor;
import org.freamcoding.template.assets.floatingText.FloatingText;
import org.freamcoding.template.data.Data;
import org.freamcoding.template.effect.damage.Damage;
import org.freamcoding.template.item.armor.Armor;
import org.freamcoding.template.object.PhysicalObject;

public class Effect extends PhysicalObject{
	public int time;
	public int effect;
	public boolean canBeStacked;
	public boolean infinite;
	public boolean visible;
	
	public  void effect(Actor actor){
		
	}
	public  void applyTimedEffects(Actor actor){
		
	}
	
	public int calculateInfluences(Actor actor){
		int realEffect = this.effect;
		if(actor.armor != null){
			for(Effect itemModifier: actor.armor.effects){
				Class currentEffect = this.getClass();
				if(currentEffect.equals(itemModifier.getClass())){
					realEffect -= actor.armor.modifiesEffect;
				}
				while(!currentEffect.equals(Damage.class)){
					currentEffect = currentEffect.getSuperclass();
					if(currentEffect.equals(itemModifier.getClass())){
						realEffect -= actor.armor.modifiesEffect;
					}
				}
			}
		}
		if(actor.ring != null){
			for(Effect itemModifier: actor.ring.effects){
				Class currentEffect = this.getClass();
				if(currentEffect.equals(itemModifier.getClass())){
					realEffect -= actor.ring.modifiesEffect;
				}
				while(!currentEffect.equals(Damage.class)){
					currentEffect = currentEffect.getSuperclass();
					if(currentEffect.equals(itemModifier.getClass())){
						realEffect -= actor.ring.modifiesEffect;
					}
				}
			}
		}
		if(actor.ring2 != null){
			for(Effect itemModifier: actor.ring2.effects){
				Class currentEffect = this.getClass();
				if(currentEffect.equals(itemModifier.getClass())){
					realEffect -= actor.ring2.modifiesEffect;
				}
				while(!currentEffect.equals(Damage.class)){
					currentEffect = currentEffect.getSuperclass();
					if(currentEffect.equals(itemModifier.getClass())){
						realEffect -= actor.ring2.modifiesEffect;
					}
				}
			}
		}
		return realEffect;
	}
	
	public String tooltip;
	
	public void showEffectText(Actor actor, int effect){
		int size = Data.blockSize;
		float drawX = actor.blockX - Data.player.blockX + Data.visionX;
		float drawY = actor.blockY - Data.player.blockY + Data.visionY;
		Data.floatingTexts.add(new FloatingText(drawX * size, drawY * size, Integer.toString(effect)));
	}
}
