package BattagliaNavaleProject.doubleGameGridModel;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

public class SoundEffect 
{
	Clip clip;
	
	public void playMusic(String posizioneFile, boolean play)
	{
		try {
			File pathMusica = new File(posizioneFile);
			 //ottiene la stringa audio
			
			if(play==true)
			{
				if(pathMusica.exists())
				{
					AudioInputStream audioInput = AudioSystem.getAudioInputStream(pathMusica);
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
				clip = AudioSystem.getClip();
				clip.stop();
				clip.close();
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void riduciVolume()
	{
		Clip clip;
		try {
			clip = AudioSystem.getClip();
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
