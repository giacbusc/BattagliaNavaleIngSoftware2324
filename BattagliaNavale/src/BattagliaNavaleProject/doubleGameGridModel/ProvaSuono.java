package BattagliaNavaleProject.doubleGameGridModel;

public class ProvaSuono {
	
	public static void main(String[] args) throws InterruptedException {
	String filepath = "./music/Background_game_music.wav";
	SoundEffect se = new SoundEffect();
	se.playMusic(filepath, true,false);
	Thread.sleep(5000);
	//se.playMusic(filepath, false,false);
	se.playMusic(filepath, true,true);
	Thread.sleep(5000);
	se.playMusic(filepath, false,false);
	
	}
}
