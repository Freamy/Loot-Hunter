package org.freamcoding.template.map;

public class Block {
	private int id; public void setId(int id){this.id = id;} public int getId(){return id;}
	public boolean visible; public void setVisible(boolean visible){this.visible = visible;}
	public boolean visited;
	public float shadow;
	public int x;
	public int y;
	
	
	public Block(int id, int x, int y){
		this.id = id;
		this.x = x;
		this.y = y;
		visited = false;
		visible = false;
	}
}
