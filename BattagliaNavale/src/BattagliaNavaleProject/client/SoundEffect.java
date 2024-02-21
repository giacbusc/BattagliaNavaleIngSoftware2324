package BattagliaNavaleProject.client;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEffect 
{
	public void playMusic(String posizioneFile)
	{
		try {
			File pathMusica = new File(posizioneFile);
			
			if(pathMusica.exists())
			{
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(pathMusica);
				Clip clip = AudioSystem.getClip(); //ottiene la stringa audio
				clip.open(audioInput);
				clip.start();
			}
			else
			{
				System.out.println("File musica non trovato");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
