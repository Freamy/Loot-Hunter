package org.freamcoding.template.assets.algorithms.aStar;

import java.util.ArrayList;

import org.freamcoding.template.map.Map;

//import java.util.ArrayList;

public class AStar {
	ArrayList<Zone> frontier;
	ArrayList<Zone> done;
	
	public AStar(){
		frontier = new ArrayList<Zone>();
		done = new ArrayList<Zone>();
	}
	
	public Zone[] findPath(Zone start, Zone end, Map map){
		
		Zone finals = end;
		frontier = new ArrayList<Zone>();
		done = new ArrayList<Zone>();
		boolean found = false;
		start.setParent(start);
		Zone current = start;
		current.setCost(0);
		if(start.equals(end)){
			return null; // No movement
		}
		
		frontier.add(current);
		while(frontier.size() > 0 && !found){
			done.add(current);
			frontier.remove(current);
			frontier.trimToSize();
			current.partOfFrontier = false;
			current.partOfDone = true;
			Zone[] adjacent = map.getAdjecent(current);
			for(int i = 0; i < 4; i++){
				if(adjacent[i] != null
						&& !done.contains(adjacent[i]) 
						&& adjacent[i].getDifficulty()+adjacent[i].bonus < 10000
						){
					if(!frontier.contains(adjacent[i])){
						adjacent[i].calculateCost(current.getCost(), current, end);
						adjacent[i].setParent(current);
						adjacent[i].partOfFrontier = true;
						frontier.add(adjacent[i]);
					}else if(frontier.contains(adjacent[i])){
						boolean different = adjacent[i].calculateCost(current.getCost(), current, end);
						if(different){
							adjacent[i].setParent(current);
						}
					}
				} // End of triple condition
			} // End of for cycle
			Zone temp = current;
			current =  getSmallestOnFrontier();
			if(temp.equals(end)){
				found = true;
				current.setParent(temp);
			}
		}
		
		current = end;
		if(found){
			found = false;
			ArrayList<Zone> pathList = new ArrayList<Zone>();
			while(!found){
				pathList.add(current.getParent());
				current = current.getParent();
				if(current.equals(start))
					found = true;
			}
			Zone[] path = new Zone[pathList.size()+1];
			int i = 0;
			path[0] = finals;
			i++;
		
			for(Zone at: pathList){
				path[i] = at;
				i++;
			}
			
			//map.clearPath();
			map.clear();
			frontier.clear();
			done.clear();
			return path;
		}
		return null;
	}

	private Zone getSmallestOnFrontier(){
		Zone zone = new Zone(Integer.MAX_VALUE,0,0);
		for(Zone at: frontier){
			if(at.getCost() < zone.getCost())
				zone = at;
		}
		return zone;
	}

}


