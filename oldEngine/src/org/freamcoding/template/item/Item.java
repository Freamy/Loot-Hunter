package org.freamcoding.template.item;

import java.util.ArrayList;

import org.freamcoding.template.actor.Actor;
import org.freamcoding.template.effect.Effect;
import org.freamcoding.template.object.PhysicalObject;

public class Item extends PhysicalObject {
	
	ArrayList<Effect> positive;
	ArrayList<Effect> negative;
	
	public String toolTip; 
	
	public Item(){
		positive = new ArrayList<Effect>();
		negative = new ArrayList<Effect>();
	}
	
	public void Inflict(Actor user, Actor target){
		negativeEffect(target);
		positiveEffect(user);
	}
	
	public void negativeEffect(Actor actor){
		for(Effect effect: negative){
			effect.effect(actor);
		}
	}
	
	public void positiveEffect(Actor actor){
		for(Effect effect: positive){
			effect.effect(actor);
		}
	}
	
	public String getTooltip(){
		return toolTip+getToolTips(positive)+getToolTips(negative);
	}
	
	public String getToolTips(ArrayList<Effect> tipList){
		String tip = "";
		for(Effect effect: tipList){
			tip += effect.tooltip+" ";
		}
		return tip;
	}
}
