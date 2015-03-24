package org.freamcoding.template.audio;

import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 * 
 * @author Freamcoding
 *
 *	Standard storage class for single audio files.
 */

public class AudioStore {
	
	public String name;
	public Audio soundFile;
	
	public AudioStore(String n, String path){
		name = n;
		try {
			soundFile = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
