package BattagliaNavaleProject.client;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

import java.io.IOException;


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
	
	public void musica() throws java.io.IOException {

		    AudioInputStream audio;
		    Clip clip;

		    try {
		        audio = AudioSystem.getAudioInputStream(new File("C:\\Users\\signo\\Documents\\GitHub\\BattagliaNavaleIngSoftware2324\\BattagliaNavale\\music\\sceltaMenu3.wav").getAbsoluteFile());
		        clip = AudioSystem.getClip();
		        clip.open(audio);
		        clip.start();
		    } catch (IOException ex) {
		        ex.printStackTrace();
		        System.out.println("IOException: Errore nella riproduzione, controllare il formauto audio o la presenza di esso");
		    } catch (LineUnavailableException ex) {
		        ex.printStackTrace();
		        System.out.println("LineUnavailableException: Errore nella riproduzione, controllare il formauto audio o la presenza di esso");
		    } catch (UnsupportedAudioFileException ex) {
		        ex.printStackTrace();
		        System.out.println("UnsupportedAudioFileException: Errore nella riproduzione, controllare il formauto audio o la presenza di esso");
		    } catch (Exception ex) {
		        ex.printStackTrace();
		        System.out.println("Errore sconosciuto nella riproduzione audio");
		    }
		}


}
