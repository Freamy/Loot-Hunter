package org.freamcoding.template.effect.damage.magical.ice;

import java.util.ArrayList;

import org.freamcoding.template.actor.Actor;
import org.freamcoding.template.effect.Effect;
import org.newdawn.slick.opengl.Texture;

public class ColdTouch extends Ice {

	public ArrayList<Texture> self = loadTextures("graphics/effectIcons/chill", "png", true);

	
	public ColdTouch(int dmg) {
		effect = dmg;
		this.time = 0;
		canBeStacked = false;
		tooltip = "deals "+effect+" damage for "+time+" turn";
		visible = false;
	}

	@Override
	public void effect(Actor actor) {
		int realDamage = calculateInfluences(actor);
		if(realDamage > 0){
			actor.health -= realDamage;
			this.showEffectText(actor, realDamage);
		}
	}
	
	public void applyTimedEffects(Actor actor){

	}
	
	public void bindSelf(){
		//ethis.visionRange = 2;
		self.get(frame%(animationLength+1)).bind();
	}

}
