package org.freamcoding.template.font;

import java.util.ArrayList;

public class StringDrawer {
	
	public ArrayList<Letter> letters;
	
	public void prepareSentence(String sentence, int locX, int locY, int size){
		letters = new ArrayList<Letter>();
		sentence = sentence.toUpperCase();
		for(int i = 0; i < sentence.length(); i++){
			char current = sentence.charAt(i);
			int id = convertCharToTextureIndex(current);
			int padding = 5;
			float x = locX + size*i - i*padding;
			float y = locY;
			float sizeF = size;
			letters.add(new Letter(x,y,sizeF,size*0.8f,id));
		}
	}
	
	public int convertCharToTextureIndex(char c){
		if(c == ' ') return c-6;
		if(c > 57) return c-65;
		else return c-48;
	}
}
