package org.freamcoding.template.actor;

import java.util.ArrayList;

import org.freamcoding.template.assets.algorithms.aStar.Zone;
import org.freamcoding.template.effect.Effect;
import org.freamcoding.template.item.Weapon;
import org.freamcoding.template.map.Block;
import org.freamcoding.template.map.Map;
import org.freamcoding.template.object.PhysicalObject;

public abstract class Actor extends PhysicalObject {
	
	public int visionRange;
	
	public boolean on;
	public boolean hited;
	
	public float hitedAnimation;
	
	public Zone aim;
	
	public Weapon weapon;
	
	public int health;
	public int maxHealth;
	
	public String toolTip;
	
	public ArrayList<Effect> appliedEffects;
	
	public Actor(int x, int y){
		super(x,y);
		hited = false;
		appliedEffects = new ArrayList<Effect>();
	}
	
	public void tick(){
	//	if(Assets.timer.tick)
			hitedAnimation -= 0.1;
	}
	
	public abstract ArrayList<Block> buildVisionBorder(Map map);

}
