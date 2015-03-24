package org.freamcoding.template.audio;

import java.util.ArrayList;

/**
 * 
 * @author Freamcoding
 *
 *
 *	Template for an audio pack class, might be in the need of changing.
 */

public class AudioPack {
	
	public ArrayList<AudioStore> audioPack;
	
	public AudioPack(){
		audioPack = new ArrayList<AudioStore>();
		audioPack.add(new AudioStore("Terraria", "resource/Audio.wav"));
		//audioPack.add(new AudioStore("Fire", "resource/Audio2.wav"));
		//audioPack.add(new AudioStore("Fire2", "resource/Audio3.wav"));
	}
	
	public AudioStore getAudioStore(String name){
		for(AudioStore as: audioPack){
			if(as.name.toLowerCase().equals(name.toLowerCase())) return as;
		}
		return null;
	}
}
