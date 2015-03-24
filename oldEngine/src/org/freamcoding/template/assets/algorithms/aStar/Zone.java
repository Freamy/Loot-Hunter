package org.freamcoding.template.assets.algorithms.aStar;

public class Zone {
	private Zone parent;
	private int difficulty; // Cost Step On
	private int cost; // G
	private int totalCost; // F
	
	public int bonus;
	
	public boolean partOfFrontier;
	public boolean partOfDone;
	
	public int x;
	public int y;
	
	public Zone(int x, int y, int difficulty){
		this.difficulty = difficulty;
		this.totalCost = Integer.MAX_VALUE;
		partOfFrontier = false;
		partOfDone = false;
		this.x = x;
		this.y = y;
	}
	
	public boolean calculateCost(int parentCost, Zone parent, Zone end){
		if(parentCost + getHeuristic(end) < totalCost){
			cost = parentCost+difficulty;
			totalCost = parentCost + getHeuristic(end)+difficulty+bonus;
			this.parent = parent;
			return true;
		}
		return false;
	}
	
	public void setCost(int cost){
		totalCost = cost;
	}
	
	public void setDiff(int diff){
		difficulty = diff;
	}
	
	public void setParent(Zone z){
		parent = z;
	}
	
	public Zone getParent(){
		return parent;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	public int getStepCost(){
		return cost;
	}
	
	public int getCost(){
		return totalCost;
	}
	
	public int getDifficulty(){
		return difficulty;
	}
	
	public int getHeuristic(Zone end){
		int xx = end.getX();
		int yy = end.getY();
		return (int)Math.sqrt(Math.pow(xx-x, 2)+Math.pow(yy-y, 2));
	}
	
	public void clear_Zones(){
		partOfFrontier = false;
		partOfDone = false;
		totalCost = 0;
		cost = 0;
		bonus = 0;
	}

}

