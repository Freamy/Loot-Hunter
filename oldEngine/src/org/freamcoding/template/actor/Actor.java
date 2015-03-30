package org.freamcoding.template.actor;

import java.util.ArrayList;

import org.freamcoding.template.assets.algorithms.aStar.Zone;
import org.freamcoding.template.effect.Effect;
import org.freamcoding.template.item.Weapon;
import org.freamcoding.template.item.armor.Ring;
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
	public Ring ring;
	
	public int health;
	public int maxHealth;
	
	public int experience;
	public int expForLevel;

	public int level;
	
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
			if(experience >= expForLevel){
				levelUp();
			}
	}
	
	public void levelUp(){
		maxHealth += 10;
		health = maxHealth;
		experience -= expForLevel;
	}
	
	public void gainExperience(Actor source){
		experience += (int)(((float)source.level/this.level)*10);
	}
	
	public abstract ArrayList<Block> buildVisionBorder(Map map);

}
