package org.freamcoding.template.item;

import org.freamcoding.template.actor.Actor;

public class Weapon extends Item{
	
	public int range;
	
	public Weapon(){
		super();
	}

	@Override
	public void imLooted(Actor actor) {
		actor.weaponLooted(this);
	}
}
