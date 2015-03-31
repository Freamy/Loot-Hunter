package org.freamcoding.template.logic;

import java.awt.Point;
import java.util.ArrayList;

import org.freamcoding.template.actor.Actor;
import org.freamcoding.template.assets.Assets;
import org.freamcoding.template.assets.algorithms.aStar.AStar;
import org.freamcoding.template.assets.algorithms.aStar.Zone;
import org.freamcoding.template.assets.algorithms.fieldOfView.FoVAlgorithm;
import org.freamcoding.template.assets.algorithms.roomedDungeonGenerator.RoomedDungeonGenerator;
import org.freamcoding.template.assets.floatingText.FloatingText;
import org.freamcoding.template.data.Data;
import org.freamcoding.template.effect.Effect;
import org.freamcoding.template.io.MouseInput;
import org.freamcoding.template.item.Item;
import org.freamcoding.template.item.Weapon;
import org.freamcoding.template.lootBags.LootBag;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Logic {
	
	boolean keyNotDown;
	boolean computerTurn;
	boolean turnFinish;
	
	FoVAlgorithm fov;
	
	public Logic(){
		fov =  new FoVAlgorithm();
	}
	
	public void iterate(){
		initCycle();
		logicCycle();
		dropCycle();
	}
	
	public void logicCycle(){
		// Netural logic
		floatingTextLogic();
		fov.buildVision(Data.map, Data.player);
		// Player Begin
		if(Data.player.hitedAnimation < 0)
			Data.player.hited = false;
		Data.player.tick();
		playerControls();
		if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)){
			RoomedDungeonGenerator dungeonGenerator = new RoomedDungeonGenerator();
			Data.map = dungeonGenerator.makeMap(Data.map, 9, 5);
		}
		playerTooltips();
		// DEBUG
		/*
		int wheel = Mouse.getDWheel();
		if(wheel > 0 || Keyboard.isKeyDown(Keyboard.KEY_O)) Data.player.visionRange++;
		else if(wheel < 0 || Keyboard.isKeyDown(Keyboard.KEY_P)) Data.player.visionRange--;*/
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) Data.run = false;
		// DEBUG END
		// Player end
		// Enemy begin
		if(computerTurn)
			enemyLogic();
		// Enemy end
		if(turnFinish){
			applyTurnFinish();
			turnFinish = false;
		}
		
	}
	
	public void floatingTextLogic(){
		ArrayList<FloatingText> delete = new ArrayList<FloatingText>();
		for(FloatingText text: Data.floatingTexts){
			text.tick();
			if(text.time >= 60) delete.add(text);
		}
		Data.floatingTexts.removeAll(delete);
	}
	
	public void playerControls(){
		if(keyNotDown){
			keyboardControl();
			if(!keyNotDown)
				computerTurn = true;
		}else{
			keyNotDown = canPressKey();
		}
	}
	
	public boolean canPressKey(){
		for(int i = 0; i < Keyboard.getKeyCount(); i++)
			if(Keyboard.isKeyDown(i)) return false;
		return true;
	}
	
	public void keyboardControl(){
		movePlayer();
		lootPlayer();
		printLoot();
		//debugCommands();
	}
	
	// DEBUG
	public void debugCommands(){
		if(Keyboard.isKeyDown(Keyboard.KEY_N)){
			keyNotDown = false;
			boolean debug = Data.DEBUG_NOCLIP;
			Data.DEBUG_NOCLIP = !debug;
		}
	}
	
	public void movePlayer(){
		int x = Data.player.blockX;
		int y = Data.player.blockY;
		if(Keyboard.isKeyDown(Keyboard.KEY_W) && 
				(possibleToMoveTo(x, y-1) || Data.DEBUG_NOCLIP)){
			if(!attackMob(x,y-1)){
				Data.player.blockY--;
			}
			keyNotDown = false;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S) && 
				(possibleToMoveTo(x, y+1) || Data.DEBUG_NOCLIP)){ 
			if(!attackMob(x,y+1)){
				Data.player.blockY++;
			}
			keyNotDown = false;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A) && 
				(possibleToMoveTo(x-1, y)|| Data.DEBUG_NOCLIP)){
			if(!attackMob(x-1,y)){
				Data.player.blockX--;
			}
			keyNotDown = false;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D) && 
				(possibleToMoveTo(x+1, y) || Data.DEBUG_NOCLIP)){ 
			if(!attackMob(x+1,y)){
				Data.player.blockX++;
			}
			keyNotDown = false;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Q)){ 
			Data.audio = false;
			keyNotDown = false;
		}
	}
	
	public boolean possibleToMoveTo(int x, int y){
		if(Data.map.graph[x][y].getDifficulty() >= 10000) return false;
		return true;
	}
	
	public boolean attackMob(int x, int y){
		Actor target = isMonsterThere(x, y);
		if(target != null){
			Data.player.weapon.Inflict(Data.player, target);
			return true;
		}
		return false;
	}
	
	public Actor isMonsterThere(int x, int y){
		for(Actor monster: Data.enemies){
			if(monster.blockX == x && monster.blockY == y) return monster;
		}
		return null;
	}
	
	public void playerTooltips(){
		float startX = 1025;
		float startY = 335;
		float sizeX = 64;
		float sizeY = sizeX;
		
		int mx = MouseInput.mouseX;
		int my = MouseInput.mouseY;
		if(mx > startX && mx < startX+sizeX && my > startY && my < startY + sizeY) Data.tooltip = Data.player.weapon.getTooltip();
		startX = 1175;
		startY = 325;
		if(mx > startX && mx < startX+sizeX && my > startY && my < startY + sizeY) Data.tooltip = Data.player.ring.getTooltip();
		startX = 1175;
		startY = 365;
		if(mx > startX && mx < startX+sizeX && my > startY && my < startY + sizeY) Data.tooltip = Data.player.ring2.getTooltip();
		startX = 1015;
		startY = 330;
		sizeX = sizeY = 80;
		if(mx > startX && mx < startX+sizeX && my > startY && my < startY + sizeY) Data.tooltip = Data.player.armor.getTooltip();
	}
	
	public void lootPlayer(){
		LootBag delete = null;
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && keyNotDown){
			keyNotDown = false;
			for(LootBag loot: Data.loots){
				int x = Data.player.blockX;
				int y = Data.player.blockY;
				if(x == loot.blockX && y == loot.blockY){
					delete = loot;
					Data.player.pickUp(loot.loot);
				}
			}
		}
		Data.loots.remove(delete);
	}
	
	public void printLoot(){
		for(LootBag loot: Data.loots){
			int x = Data.player.blockX;
			int y = Data.player.blockY;
			if(x == loot.blockX && y == loot.blockY){
				Data.tooltip = loot.loot.getTooltip();
			}
		}
		
		for(Actor enemy: Data.enemies){
			int blockX = enemy.blockX;
			int blockY = enemy.blockY;
			int xx = (int) (blockX - Data.player.blockX + Data.visionX);
			int yy = (int) (blockY - Data.player.blockY + Data.visionY);
			if(xx == MouseInput.mouseBlockX && yy == MouseInput.mouseBlockY) Data.tooltip = enemy.toolTip;
		}
	}
	
	public void enemyLogic(){
		for(Actor enemy: Data.enemies){
			int blockX = enemy.blockX;
			int blockY = enemy.blockY;
			Actor player = Data.player;
			if(distance(player, enemy) > enemy.weapon.range){
				if(fov.isVisibleFrom(new Point(blockX, blockY), player, Data.map)){
					Zone target = Data.map.graph[player.blockX][player.blockY];
					followPlayer(enemy, target);
				}else if(enemy.aim != null){
					followPlayer(enemy, enemy.aim);
				}
			}else if(fov.isVisibleFrom(new Point(blockX, blockY),player, Data.map)){
				attackPlayer(enemy);
			}
		}
		computerTurn = false;
		turnFinish = true;
	}
	
	public double distance(Actor one, Actor two){
		return Math.sqrt(((one.blockX - two.blockX) * (one.blockX - two.blockX))+((one.blockY - two.blockY) * (one.blockY - two.blockY)));
	}
	
	public void followPlayer(Actor enemy, Zone target){
		AStar astar = new AStar();
		enemy.aim = target;
		int enemyX = enemy.blockX;
		int enemyY = enemy.blockY;
		int playerX = target.x;
		int playerY = target.y;
		for(Actor block: Data.enemies){
			if(!block.equals(enemy))
				Data.map.graph[block.blockX][block.blockY].bonus = 10000;
		}
		Zone start = Data.map.graph[enemyX][enemyY];
		Zone end = Data.map.graph[playerX][playerY];
		Zone[] path = astar.findPath(start, end, Data.map);
		if(path != null && distance(target, enemy) >= enemy.weapon.range){
			enemy.blockX = path[path.length-2].x;
			enemy.blockY = path[path.length-2].y;
		}
	}
	
	public double distance(Zone target, Actor actor){
		int x = target.x;
		int y = target.y;
		int ax = actor.blockX;
		int ay = actor.blockY;
		int dX = (x-ax)*(x-ax);
		int dY = (y-ay)*(y-ay);
		double distance = Math.sqrt(dX+dY);
		System.out.println(distance);
		return distance;
	}
	
	public void attackPlayer(Actor enemy){
		Actor player = Data.player;
		player.hited = true;
		player.hitedAnimation = 1;
		enemy.weapon.Inflict(enemy, player);
		if(Data.audio)
		if(!Data.audioPacks.audioPack.get(0).soundFile.isPlaying()){
			Data.audioPacks.audioPack.get(0).soundFile.playAsSoundEffect(1, 0.1f, false);
		}
	}
	
	public void applyTurnFinish(){
		ArrayList<Actor> deathActors = new ArrayList<Actor>();
		for(Actor actor: Data.enemies){
			for(Effect applied: actor.appliedEffects){
				applied.applyTimedEffects(actor);
			}
			if(actor.health <= 0){
				deathActors.add(actor);
				Data.player.gainExperience(actor);
				Item loot = actor.getDrop();
				if(loot != null)
					Data.loots.add(new LootBag(actor.blockX, actor.blockY, loot));
			}
		}
		
		for(Effect applied: Data.player.appliedEffects){
			applied.applyTimedEffects(Data.player);
		}
		
		removeEffects();
		Data.enemies.removeAll(deathActors);
		if(Data.player.health <= 0){
			Data.remakeGame();
		}
	}
	
	public void removeEffects(){
		for(Actor actor: Data.enemies){
			ArrayList<Effect> remove = new ArrayList<Effect>();
			for(Effect applied: actor.appliedEffects){
				if(applied.time <= 0) remove.add(applied);
			}
			actor.appliedEffects.removeAll(remove);
		}
		ArrayList<Effect> remove = new ArrayList<Effect>();
		for(Effect applied: Data.player.appliedEffects){
			if(applied.time <= 0) remove.add(applied);
		}
		Data.player.appliedEffects.removeAll(remove);
	}
	
	private void initCycle(){
		Data.tooltip = "";
		Assets.timer.tick();
		Data.map.darken();
		MouseInput.resetMouseCoordinates();
	}
	
	private void dropCycle(){
		Assets.timer.nullify();
	}
}
