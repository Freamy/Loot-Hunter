package org.freamcoding.template.item.armor;

import java.util.ArrayList;

import org.freamcoding.template.actor.Actor;
import org.freamcoding.template.effect.Effect;
import org.freamcoding.template.item.Item;

public class Armor extends Item {
	
	public ArrayList<Effect> effects;
	public int modifiesEffect;
	
	public Armor(){
		effects = new ArrayList<Effect>();
	}

	@Override
	public void imLooted(Actor actor) {
		actor.armorLooted(this);
	}

}
