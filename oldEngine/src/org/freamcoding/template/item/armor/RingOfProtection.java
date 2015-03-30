package org.freamcoding.template.item.armor;

import org.freamcoding.template.effect.damage.magical.ice.Ice;

public class RingOfProtection extends Ring {
		
	public RingOfProtection(){
		super();
		effects.add(new Ice());
		modifiesEffect = -5;
	}
}
