package BattagliaNavaleProject.client;

import java.io.File;

import javax.sound.sampled.AudioSystem;

public class SoundEffect 
{
	void playMusic(String posizioneFile)
	{
		try {
			File pathMusica = new File(posizioneFile);
			
			if(pathMusica.exists())
			{
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(null);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
