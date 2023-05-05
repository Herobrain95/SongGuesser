package SongLoader;

import java.io.IOException;
import java.net.URL;

import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.PlayPanel;

public class SongPlayer {
	
	PlayPanel pP;
	
	Scanner consoleScan = new Scanner(System.in);
	
	public int score = 0;
	
	public int songNumber = 10;
	
	Clip clip;
	
	URL songURL[] = new URL[songNumber];
	public SongInfo[] songInfo = new SongInfo[songNumber];
	
	public SongPlayer(PlayPanel pP) {
		
		this.pP = pP;
		
		setSongInfo();
		
	}
	
	public void setSongInfo () {
		
		try {
		
		songURL[0] = getClass().getResource("/eSongFile/1. Avicii-TheNights.wav");
		songInfo[0] = new SongInfo ("The Nights", "Avicii");
		songInfo[0].image = ImageIO.read(getClass().getResourceAsStream("/albumCover/theNightsCover.png"));
		
		songURL[1] = getClass().getResource("/eSongFile/2. Macklemore-ThriftShop.wav");
		songInfo[1] = new SongInfo ("Thrift Shop", "Macklemore");
		songInfo[1].image = ImageIO.read(getClass().getResourceAsStream("/albumCover/thriftShopCover.png"));
		
		songURL[2] = getClass().getResource("/eSongFile/3. NF-HOPE.wav");
		songInfo[2] = new SongInfo ("HOPE", "NF");
		songInfo[2].image = ImageIO.read(getClass().getResourceAsStream("/albumCover/hopeCover.png"));

		songURL[3] = getClass().getResource("/eSongFile/4. NF-MOTTO.wav");
		songInfo[3] = new SongInfo ("MOTTO", "NF");
		songInfo[3].image = ImageIO.read(getClass().getResourceAsStream("/albumCover/mottoCover.png"));

		songURL[4] = getClass().getResource("/eSongFile/5. SamSmith-StayWithMe.wav");
		songInfo[4] = new SongInfo ("Stay With Me", "Sam Smith");
		songInfo[4].image = ImageIO.read(getClass().getResourceAsStream("/albumCover/stayWithMeCover.png"));
		
		songURL[5] = getClass().getResource("/eSongFile/6. Shakira-WakaWaka.wav");
		songInfo[5] = new SongInfo ("Waka Waka", "Shakira");
		songInfo[5].image = ImageIO.read(getClass().getResourceAsStream("/albumCover/wakaWakaCover.jfif"));
		
		songURL[6] = getClass().getResource("/eSongFile/7. TonesAndI-DanceMonkey.wav");
		songInfo[6] = new SongInfo ("Dance Monkey", "Tones And I");
		songInfo[6].image = ImageIO.read(getClass().getResourceAsStream("/albumCover/wakaWakaCover.jfif"));
		
		songURL[7] = getClass().getResource("/eSongFile/8. Macklemore-Can'tHoldUs.wav");
		songInfo[7] = new SongInfo ("Can't Hold Us", "Macklemore");
		songInfo[7].image = ImageIO.read(getClass().getResourceAsStream("/albumCover/wakaWakaCover.jfif"));
		
		songURL[8] = getClass().getResource("/eSongFile/9. MileyCyrus-Flowers.wav");
		songInfo[8] = new SongInfo ("Flowers", "Miley Cyrus");
		songInfo[8].image = ImageIO.read(getClass().getResourceAsStream("/albumCover/wakaWakaCover.jfif"));
		
		songURL[9] = getClass().getResource("/eSongFile/10. TobiasRahim-Stormand.wav");
		songInfo[9] = new SongInfo ("Stormand", "Tobias Rahim");
		songInfo[9].image = ImageIO.read(getClass().getResourceAsStream("/albumCover/wakaWakaCover.jfif"));
		
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setFile(int songNumber) {
		
		try {
			
			AudioInputStream songFinder = AudioSystem.getAudioInputStream(songURL[songNumber]);
			clip = AudioSystem.getClip();
			clip.open(songFinder);
			
		}catch(Exception e){
			System.out.println("Error");
			
		}	
	}
	
	
	public void play() {
		
		clip.start();
		
	}
	public void loop() {
			
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		
		clip.stop();
	}

	
	public int songChecker(int songPick, int currentScore, int ablePoints, JTextField songGuess, JTextArea playerInfo) {
		
		String answer = songGuess.getText();
		
		if(answer.equals(songInfo[songPick].sTitle) || answer.equals(songInfo[songPick].sArtist)) {
			
			System.out.println("Korrekt");
			songGuess.setText("");
			
			clip.stop();
			
			pP.setup();
			
			currentScore = score + ablePoints;
			
			score = currentScore;
			
			pP.attempt = 0;
			
			pP.playedSong[pP.round] = songInfo[songPick].sTitle;
			
			pP.round =+ 1;
			
			
		} else{
			
			System.out.println("Forkert");
			
		}
	return currentScore;
	}
	
}
