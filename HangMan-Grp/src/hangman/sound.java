/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Hassan Nadeem (17885) and Furqan Saeed (17893)
 */

import javax.swing.JOptionPane;

public class sound {
	
	private Clip clip;
	
	// Change file name to match yours, of course
        public static sound sound1 = new sound("hangman\\resourses\\starwars.wav");
	        
	public sound (String fileName) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource(fileName));
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sound File not found!");
        }
	}

	public void play() {
		try {
			if (clip != null) {
				new Thread() {
                                        @Override
					public void run() {
						synchronized (clip) {
							clip.stop();
							clip.setFramePosition(0);
							clip.start();
						}
					}
				}.start();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error!");
		}
	}
	
	public void stop(){
		if(clip == null) return;
		clip.stop();
	}
	
	public void loop() {
		try {
			if (clip != null) {
				new Thread() {
					public void run() {
						synchronized (clip) {
							clip.stop();
							clip.setFramePosition(0);
							clip.loop(Clip.LOOP_CONTINUOUSLY);
						}
					}
				}.start();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error!");
		}
	}
	
	public boolean isActive(){
		return clip.isActive();
	}
}