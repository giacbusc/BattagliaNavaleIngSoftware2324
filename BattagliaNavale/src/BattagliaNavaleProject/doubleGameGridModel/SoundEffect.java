package BattagliaNavaleProject.doubleGameGridModel;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundEffect {
    private Clip clip;

    public void playMusic(String posizioneFile, boolean play, boolean volumeBasso) {
        try {
            File pathMusica = new File(posizioneFile);

            if (play) {
                if (pathMusica.exists()) {
                    AudioInputStream audioInput = AudioSystem.getAudioInputStream(pathMusica);
                    
                    if (clip != null) {
                        clip.stop();
                        clip.close();
                    }

                    clip = AudioSystem.getClip();
                    clip.open(audioInput);

                    if (volumeBasso) { //se il volume basso è true
                        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                        gainControl.setValue(-30.0f); // Riduci il volume di 30 decibel.
                    }

                    clip.start();
                } else {
                    System.out.println("File musica non trovato");
                }
            } else {
                if (clip != null) {
                    clip.stop();
                    clip.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

