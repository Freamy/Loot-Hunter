package org.freamcoding.template.effect;

import org.freamcoding.template.actor.Actor;
import org.freamcoding.template.assets.floatingText.FloatingText;
import org.freamcoding.template.data.Data;
import org.freamcoding.template.object.PhysicalObject;

public abstract class Effect extends PhysicalObject{
	public int time;
	public int effect;
	public boolean canBeStacked;
	public abstract void effect(Actor actor);
	public abstract void applyTimedEffects(Actor actor);
	
	public String tooltip;
	
	public void showEffectText(Actor actor, int effect){
		int size = Data.blockSize;
		float drawX = actor.blockX - Data.player.blockX + Data.visionX;
		float drawY = actor.blockY - Data.player.blockY + Data.visionY;
		Data.floatingTexts.add(new FloatingText(drawX * size, drawY * size, Integer.toString(effect)));
	}
}
