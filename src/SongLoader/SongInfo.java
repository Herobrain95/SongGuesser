package SongLoader;

import java.awt.image.BufferedImage;

public class SongInfo {
	
	public BufferedImage image;
	String sTitle = "not set";
	String sArtist = "not set";
	
	public SongInfo(String songTitle, String artist){
		
	sTitle = songTitle;
	sArtist = artist;
	
	}
	
	public void showInfo() {
		
		System.out.println("Du hører " + sTitle + " lavet af " + sArtist );
	}
	
}
