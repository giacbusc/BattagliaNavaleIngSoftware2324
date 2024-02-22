package BattagliaNavaleProject.doubleGameGridModel;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEffect 
{
	public void playMusic(String posizioneFile, boolean play)
	{
		try {
			File pathMusica = new File(posizioneFile);
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(pathMusica);
			Clip clip = AudioSystem.getClip(); //ottiene la stringa audio
			
			if(play==true)
			{
				if(pathMusica.exists())
				{
					
					clip.open(audioInput);
					clip.start();
				}
				else
				{
					System.out.println("File musica non trovato");
				
				}
			}
			else if(play==false)
			{
				clip.stop();
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

}
