package org.freamcoding.template.screen;

import org.freamcoding.template.Game;
import org.freamcoding.template.actor.Actor;
import org.freamcoding.template.assets.floatingText.FloatingText;
import org.freamcoding.template.data.Data;
import org.freamcoding.template.effect.Effect;
import org.freamcoding.template.font.Letter;
import org.freamcoding.template.font.StringDrawer;
import org.freamcoding.template.lootBags.LootBag;
import org.freamcoding.template.map.Map;
import org.freamcoding.template.map.tile.Tile;
import org.freamcoding.template.object.PhysicalObject;
import org.freamcoding.template.object.Widget;
import org.freamcoding.template.object.WidgetText;
import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.*;

public class Screen {
	
	public void render(){
		glClear(GL_COLOR_BUFFER_BIT);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
		paint();
		Display.update();
		Display.sync(60);
	}
	
	public void paint(){
		drawMap();
		drawMinimap();
		drawPlayer();
		drawEnemy();
		
		drawFloatingText();
		drawEffects();
		drawTooltips();
		drawLoots();
		//drawTestL1ines();
	}
	
/*	public void drawTestLines(){
		glDisable(GL_TEXTURE_2D);
		for(Actor actor: Data.player){
			int sight = actor.visionRange;
			Actor player = actor;
			for(int out = -sight; out <= sight; out++){
					int x = player.blockX*Data.blockSize+16;
					int y = player.blockY*Data.blockSize+16;
					int drawOut = out*Data.blockSize;
					int drawSight = sight*Data.blockSize;
					int xx = Data.enemies.get(0).blockX*Data.blockSize;
					int yy = Data.enemies.get(0).blockY*Data.blockSize;
					glBegin(GL_LINES);
						glVertex2d(x,y);
						glVertex2d(x-drawSight, y+drawOut);
						glVertex2d(x,y);
						glVertex2d(x+drawSight, y+drawOut);
						glVertex2d(x,y);
						glVertex2d(x+drawOut, y-drawSight);
						glVertex2d(x,y);
						glVertex2d(x+drawOut, y+drawSight);
						glVertex2d(x,y);
						glVertex2d(xx,yy);
					glEnd();
			}
		}
		glEnable(GL_TEXTURE_2D);
	}*/
	
	public void drawMap(){
		int playerX = Data.player.blockX;
		int playerY = Data.player.blockY;
		int visionX = (int)Data.visionX;
		int visionY = (int)Data.visionY;
		int drawX = 0;
		int drawY = 0;
		//System.out.println(vision);
		for(int i = playerX-visionX; i < playerX+visionX; i++){
			for(int j = playerY-visionY; j < playerY+visionY; j++){
				//int realLocationX = i+Data.camLocationX;
				//int realLocationY = j+Data.camLocationY;
				try{
				Tile.tiles[Data.map.map[i][j].getId()].bindSelf();
				}catch(Exception e){
					Tile.tiles[0].bindSelf();
				}
				try{
				if(Data.map.map[i][j].visible){
					float shadow = 1/calculateShadow(Data.player, i, j);
					shadow /= 3f;
					glColor4f(shadow, shadow, shadow, 1);
				}
				else if(Data.map.map[i][j].visited == false) glColor4f(0,0,0,1);
				else glColor4f(0.03f, 0.03f, 0.03f, 1f);
				}catch(Exception e){
					glColor4f(0,0,0,1);
				}
				drawQuad(drawY*Data.blockSize,drawX*Data.blockSize,Data.blockSize,Data.blockSize);
				drawX++;
			}
			//System.out.println("X-"+drawX);
			//System.out.println("Y-"+drawY);
			drawX = 0;
			drawY++;
		}
	}
	
	public float calculateShadow(Actor actor, int i, int j){
		float light = distance(actor, i, j);
		return light;
	}
	
	public float distance(Actor player, int i, int j){
		int playerX = player.blockX;
		int playerY = player.blockY;
		float iResize = i;
		float jResize = j;
		float distance = (float) Math.sqrt(((playerX - iResize) * (playerX - iResize))+((playerY - jResize) * (playerY - jResize)));
		//System.out.println(distance);
		return distance;
	}
	
	public void drawPlayer(){
		drawActor(Data.player);
		drawHealthBar(Data.player);
		drawPlayerWeapon(Data.player);
	}
	
	public void drawHealthBar(Actor actor){
		float width = Data.blockSize*Data.visionX*2;
		float fullLength = Data.width-width;
		float actualLenght = ((float)actor.health/actor.maxHealth)*fullLength;
		//float size = (Data.width-Data.blockSize*Data.visionX*2)/width*Data.blockSize;
		float height = 255;//(width - Data.map.height*size);
	//	System.out.println(height);
		glDisable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
			glColor3f(0.4f,0,0);
			glVertex2f(width, height);
			glVertex2f(width+actualLenght, height);
			glVertex2f(width+actualLenght, height+50);
			glVertex2f(width, height+50);
		glEnd();
		glEnable(GL_TEXTURE_2D);
	}
	
	public void drawPlayerWeapon(Actor player){
		float startX = Data.blockSize*Data.visionX*2;
		float startY = 305;
		float sizeX = Data.width*0.1f;
		float sizeY = sizeX;
		player.weapon.bindSelf();
		glColor4f(1,1,1,1);
		drawQuad(startX, startY, sizeX, sizeY);
	}
	
	public void drawEnemy(){
		glColor4f(1,1,1,1);
		for(Actor enemy: Data.enemies){
			drawActor(enemy);
			drawEnemyHealthBars(enemy);
		}
	}
	
	public void drawActor(Actor actor){
			int drawX = (int)(actor.blockX - Data.player.blockX + Data.visionX);
			int drawY = (int)(actor.blockY - Data.player.blockY + Data.visionY);
			//System.out.println(drawX+" "+drawY+" "+Data.vision);
			float hit = actor.hitedAnimation;
			if(actor.hited) glColor4f(1 ,1-hit, 1-hit, 1);
			else glColor4f(1,1,1,1);
			actor.bindSelf();
			drawQuad(drawX*Data.blockSize, drawY*Data.blockSize, Data.blockSize, Data.blockSize);
			glColor4f(1,1,1,1);
	}
	
	public void drawEnemyHealthBars(Actor actor){
		int size = Data.blockSize;
		int x = (int)(actor.blockX - Data.player.blockX + Data.visionX);
		int y = (int)(actor.blockY - Data.player.blockY + Data.visionY);
		x *= size;
		y *= size;
		int startY = y+Data.blockSize;
		float end = ((float)actor.health/actor.maxHealth)*Data.blockSize;
		//System.out.println(end);
		glColor4f(1,1,1,1);
		glDisable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
			glVertex2f(x,startY);
			glVertex2f(x+end,startY);
			glVertex2f(x+end,startY+10);
			glVertex2f(x,startY+10);
		glEnd();
		glEnable(GL_TEXTURE_2D);
	}
	
	public void drawMinimap(){
		Map map = Data.map;
		float width = map.width;
		float height = map.height;
		float size = (Data.width-Data.blockSize*Data.visionX*2)/width;
		float drawStart = Data.blockSize*Data.visionX*2;
		//size = 1;
		//System.out.println(width);
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				Tile.tiles[map.map[i][j].getId()].bindSelf();
				if(map.map[i][j].visible) glColor4f(1,1,1,1);
				else glColor4f(0.3f, 0.3f, 0.3f, 1);
				if(!map.map[i][j].visited) glColor4f(0,0,0,1);
				drawQuad(drawStart+i*size, j*size, size, size);
			}
		}
	}
	
	public void drawFloatingText(){
		for(FloatingText text: Data.floatingTexts){
			glColor4f(1,0,0,1);
			drawText(text.text, text.x, text.y, 32);
			glColor4f(1,0,0,1);
		}
	}
	
	public void drawEffects(){
		glColor4f(1,1,1,1);
		int size = Data.blockSize;
		int i = 0;
		for(Effect effect: Data.player.appliedEffects){
			effect.bindSelf();
			drawQuad(0+i*size, 0, size, size);
			i++;
		}
	}
	
	public void drawTooltips(){
		drawText(Data.tooltip, 20, 960, 16);
	}
	
	public void drawText(String text, float x, float y, int size){
		StringDrawer sd = new StringDrawer();
		sd.prepareSentence(text, (int)x, (int)y, size);
		for(Letter l: sd.letters){
			//System.out.println((char)(l.id));
			Game.fonts.fontPacks.get(0).bindTexture(l.id);
			drawQuad(l.x,l.y,l.sizeX,l.sizeY);
		}
	}
	
	public void drawLoots(){
		for(LootBag lootBag: Data.loots){
			lootBag.loot.bindSelf();
			int drawX = (int)(lootBag.blockX - Data.player.blockX + Data.visionX);
			int drawY = (int)(lootBag.blockY - Data.player.blockY + Data.visionY);
			drawX *= Data.blockSize;
			drawY *= Data.blockSize;
			drawQuad(drawX, drawY, Data.blockSize, Data.blockSize);
		}
	}
	
	public void drawWidget(Widget widget){
		for(WidgetText wt: widget.texts){
			drawText(wt.text, widget.corX + wt.corX, widget.corY + wt.corY, wt.textSize);
		}
		for(PhysicalObject icon: widget.icons){
			icon.bindSelf();
			drawQuad(widget.corX+icon.corX, widget.corY+icon.corY, icon.sizeX, icon.sizeY);
		}
	}
	
	public void drawQuad(float x, float y, float sx, float sy){
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0); glVertex2f(x,y);
			glTexCoord2f(1, 0); glVertex2f(x+sx,y);
			glTexCoord2f(1, 1); glVertex2f(x+sx,y+sy);
			glTexCoord2f(0, 1); glVertex2f(x,y+sy);
		glEnd();
	}
	
}
