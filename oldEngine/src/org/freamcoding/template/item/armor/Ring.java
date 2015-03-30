package org.freamcoding.template.item.armor;

import java.util.ArrayList;

import org.freamcoding.template.effect.Effect;
import org.freamcoding.template.item.Item;

public class Ring extends Item {
	public ArrayList<Effect> effects;
	public int modifiesEffect;

	public Ring(){
		effects = new ArrayList<Effect>();
	}
}
