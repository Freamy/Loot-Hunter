package org.freamcoding.template.drop;

import org.freamcoding.template.item.Item;

public class Drop {
	
	public Item item;
	public int droprate;
	
	public Drop(Item item, int dr){
		this.item = item;
		droprate = dr;
	}

}
