package main;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Component;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame window = new JFrame();
		
		PlayPanel PlayPanel = new PlayPanel();
		window.add(PlayPanel);

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("SongGuesser");

		window.pack();

		window.setLocationRelativeTo(null);
		window.setLayout(null);
		window.setVisible(true);
		
		PlayPanel.setup();
		PlayPanel.startPlayThread();


		
	}

}
