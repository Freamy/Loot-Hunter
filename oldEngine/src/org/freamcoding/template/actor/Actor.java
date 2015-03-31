package org.freamcoding.template.actor;

import java.util.ArrayList;
import java.util.Random;

import org.freamcoding.template.assets.algorithms.aStar.Zone;
import org.freamcoding.template.drop.Drop;
import org.freamcoding.template.effect.Effect;
import org.freamcoding.template.item.Item;
import org.freamcoding.template.item.Weapon;
import org.freamcoding.template.item.armor.Armor;
import org.freamcoding.template.item.armor.NoRing;
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
	public Ring ring2;
	public Armor armor;
	
	public int health;
	public int maxHealth;
	
	public int experience;
	public int expForLevel;

	public int level;
	
	public String toolTip;
	
	public ArrayList<Effect> appliedEffects;
	public ArrayList<Drop> dropTable;
	
	public Actor(int x, int y){
		super(x,y);
		hited = false;
		appliedEffects = new ArrayList<Effect>();
		dropTable = new ArrayList<Drop>();
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

	public Item getDrop() {
		Random rgn = new Random();
		int rate = rgn.nextInt(100);
		for(Drop drop: dropTable){
			if(drop.droprate > rate) return drop.item;
		}
		return null;
	}

	public void pickUp(Item loot) {
		loot.imLooted(this);
	}

	public void armorLooted(Armor inArmor) {
		armor = inArmor;
	}

	public void ringLooted(Ring inRing) {
		if(ring.getClass().equals(NoRing.class)) ring = inRing;
		else if(ring2.getClass().equals(NoRing.class)) ring2 = inRing;
		else{
			ring2 = ring;
			ring = inRing;
		}
	}

	public void weaponLooted(Weapon inWeapon) {
		this.weapon = inWeapon;
	}

}
