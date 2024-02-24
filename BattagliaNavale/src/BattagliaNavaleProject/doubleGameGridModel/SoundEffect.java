package BattagliaNavaleProject.doubleGameGridModel;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundEffect {
	private Clip clip;

	public void playMusic(String posizioneFile) {
		try {
			File pathMusica = new File(posizioneFile);

			if (pathMusica.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(pathMusica);
				clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void playMusic2(String posizioneFile, boolean play) {
		try {
			File pathMusica = new File(posizioneFile);

			if (play) {
				if (pathMusica.exists()) {
					AudioInputStream audioInput = AudioSystem.getAudioInputStream(pathMusica);
					clip = AudioSystem.getClip();
					clip.open(audioInput);
					FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(-30.0f); // Riduci il volume di 30 decibel.
					clip.start();

				}

			} else {
				clip = AudioSystem.getClip();
				clip.stop();
				clip.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
