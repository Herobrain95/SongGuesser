package main;

import java.util.Random;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.concurrent.TimeUnit;

import SongLoader.SongPlayer;

public class PlayPanel extends JPanel implements Runnable {

	JPanel guessInput = new JPanel();

	JButton testButton = new JButton("Test");
	JButton newSong = new JButton("Svar");
	JButton nextAttempt = new JButton("Flere Sek");

	JTextArea ablePointsInfo = new JTextArea();
	JTextArea playerInfo = new JTextArea();
	JTextField songGuess = new JTextField();

	public int screenSizeX;
	public int screenSizeY;
	int FPS = 60;

	Random rand = new Random();

	public int songPick;
	public int lastSong;
	public int currentScore = 0;
	public int score = 0;

	public int imageX;
	public int imageY;
	public int imageSize = 300;

	static JProgressBar timer;
	public int attempt = 0;
	public int seconds = 3;
	public int ablePoints;

	public int round = 0;
	public String playedSong[];

	SongPlayer song = new SongPlayer(this);
	Thread playThread;

	public void setPanel() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		screenSize.getHeight();
		screenSize.getWidth();

		screenSizeX = screenSize.width;
		screenSizeY = screenSize.height;

		imageX = screenSizeX / 2 - imageSize / 2;
		imageY = screenSizeY / 5;

	}

	public void setButtons() {

		playerInfo.setBounds(imageX - 300, imageY + 100, 250, 100);
		playerInfo.setText("Score: " + "\r\n" + currentScore);
		playerInfo.setBackground(Color.darkGray);
		playerInfo.setForeground(Color.ORANGE);
		playerInfo.setFont(ablePointsInfo.getFont().deriveFont(35f));
		this.add(playerInfo);

		songGuess.setBounds(400, 700, 400, 50);
		this.add(songGuess);

		ablePointsInfo.setBounds(imageX - 300, imageY, 250, 100);
		ablePointsInfo.setBackground(Color.darkGray);
		ablePointsInfo.setForeground(Color.ORANGE);
		ablePointsInfo.setFont(ablePointsInfo.getFont().deriveFont(35f));
		ablePointsInfo.setText("Able points:" + "\r\n" + ablePoints);
		this.add(ablePointsInfo);

		nextAttempt.setBounds(1000, 700, 100, 50);
		nextAttempt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeAttempt_actionPerformed(e);
			}
		});
		this.add(nextAttempt);

		newSong.setBounds(850, 700, 100, 50);
		newSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runSongCheck_actionPerformed(e);
			}
		});
		this.add(newSong);

		testButton.setBounds(400, 350, 100, 50);
		testButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				test_actionPerformed(e);
			}
		});
//		this.add(testButton);
	}

	public PlayPanel() {

		setPanel();

		setButtons();

		this.setPreferredSize(new Dimension(screenSizeX, screenSizeY));
		this.setBackground(Color.darkGray);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.setLayout(null);

		System.out.println("Skærm X: " + screenSizeX + " Skærm Y: " + screenSizeY);
		System.out.println(attempt);

	}

	public void setup() {

		randSong();

		lastSong = songPick;

		playMusic(songPick);

	}

	public void startPlayThread() {

		playThread = new Thread(this);
		playThread.start();

	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;

		while (playThread != null) {

			currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);

			lastTime = currentTime;

			if (delta >= 1) {

				update();

				repaint();

				delta--;
				drawCount++;

			}
		}
	}

	public void update() {

	}

	public void playMusic(int songNumber) {

		song.setFile(songNumber);

		song.play();

		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			System.out.println("Fejl");
			e.printStackTrace();
		}

		song.stop();

	}

	public void stopMusic() {

		song.stop();
	}

	public void randSong() {

		songPick = rand.nextInt(song.songNumber);

		while (songPick == lastSong) {
			songPick = rand.nextInt(song.songNumber);
		}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		switch (attempt) {

		case 0:

			seconds = 3;
			ablePoints = 100;

			g2d.setColor(Color.GRAY);
			g2d.fillRect(imageX + imageSize + 50, imageY, 20, imageSize);

			g2d.setColor(Color.ORANGE);
			g2d.fillRect(imageX + imageSize + 50, imageY + (imageSize / 4) * 3, 20, imageSize / 4);
			g2d.setFont(g2d.getFont().deriveFont(20f));
			g2d.drawString("3 sek.", imageX + imageSize + 80, imageY + (imageSize / 4) * 3);

			g2d.drawImage(song.songInfo[songPick].image, imageX, imageY, imageSize, imageSize, null);

			ablePointsInfo.setText("Able points:" + "\r\n" + ablePoints);

			break;

		case 1:

			seconds = 6;
			ablePoints = 75;

			g2d.setColor(Color.GRAY);
			g2d.fillRect(imageX + imageSize + 50, imageY, 20, imageSize);

			g2d.setColor(Color.ORANGE);

			g2d.fillRect(imageX + imageSize + 50, imageY + (imageSize / 4) * 3, 20, imageSize / 4);
			g2d.setFont(g2d.getFont().deriveFont(20f));
			g2d.drawString("3 sek.", imageX + imageSize + 80, imageY + (imageSize / 4) * 3);

			g2d.fillRect(imageX + imageSize + 50, imageY + (imageSize / 4) * 2, 20, imageSize / 4);
			g2d.setFont(g2d.getFont().deriveFont(20f));
			g2d.drawString("6 sek.", imageX + imageSize + 80, imageY + (imageSize / 4) * 2);

			g2d.drawImage(song.songInfo[songPick].image, imageX, imageY, imageSize, imageSize, null);

			ablePointsInfo.setText("Able points:" + "\r\n" + ablePoints);

			break;

		case 2:

			seconds = 9;
			ablePoints = 50;

			g2d.setColor(Color.GRAY);
			g2d.fillRect(imageX + imageSize + 50, imageY, 20, imageSize);

			g2d.setColor(Color.ORANGE);

			g2d.fillRect(imageX + imageSize + 50, imageY + (imageSize / 4) * 3, 20, imageSize / 4);
			g2d.setFont(g2d.getFont().deriveFont(20f));
			g2d.drawString("3 sek.", imageX + imageSize + 80, imageY + (imageSize / 4) * 3);

			g2d.fillRect(imageX + imageSize + 50, imageY + (imageSize / 4) * 2, 20, imageSize / 4);
			g2d.setFont(g2d.getFont().deriveFont(20f));
			g2d.drawString("6 sek.", imageX + imageSize + 80, imageY + (imageSize / 4) * 2);

			g2d.fillRect(imageX + imageSize + 50, imageY + imageSize / 4, 20, imageSize / 4);
			g2d.setFont(g2d.getFont().deriveFont(20f));
			g2d.drawString("9 sek.", imageX + imageSize + 80, imageY + imageSize / 4);

			g2d.drawImage(song.songInfo[songPick].image, imageX, imageY, imageSize, imageSize, null);

			ablePointsInfo.setText("Able points:" + "\r\n" + ablePoints);

			break;

		case 3:

			seconds = 12;
			ablePoints = 25;

			g2d.setColor(Color.GRAY);
			g2d.fillRect(imageX + imageSize + 50, imageY, 20, imageSize);

			g2d.setColor(Color.ORANGE);

			g2d.fillRect(imageX + imageSize + 50, imageY + (imageSize / 4) * 3, 20, imageSize / 4);
			g2d.setFont(g2d.getFont().deriveFont(20f));
			g2d.drawString("3 sek.", imageX + imageSize + 80, imageY + (imageSize / 4) * 3);

			g2d.fillRect(imageX + imageSize + 50, imageY + (imageSize / 4) * 2, 20, imageSize / 4);
			g2d.setFont(g2d.getFont().deriveFont(20f));
			g2d.drawString("6 sek.", imageX + imageSize + 80, imageY + (imageSize / 4) * 2);

			g2d.fillRect(imageX + imageSize + 50, imageY + imageSize / 4, 20, imageSize / 4);
			g2d.setFont(g2d.getFont().deriveFont(20f));
			g2d.drawString("9 sek.", imageX + imageSize + 80, imageY + imageSize / 4);

			g2d.fillRect(imageX + imageSize + 50, imageY, 20, imageSize / 4);
			g2d.setFont(g2d.getFont().deriveFont(20f));
			g2d.drawString("12 sek.", imageX + imageSize + 80, imageY);

			g2d.drawImage(song.songInfo[songPick].image, imageX, imageY, imageSize, imageSize, null);

			ablePointsInfo.setText("Able points:" + "\r\n" + ablePoints);

			break;

		case 4:

			break;
		}

		switch (round) {
		
		case 0:
			
			break;

		case 1:
			
			g2d.drawString(playedSong[0], 10, 10);
			break;

		case 2:

			break;

		case 3:

			break;

		case 4:

			break;

		case 5:

			break;

		case 6:

			break;
		}
	}

	public void runSongCheck_actionPerformed(ActionEvent e) {

		currentScore = song.songChecker(songPick, currentScore, ablePoints, songGuess, playerInfo);

		playerInfo.setText("Score: " + "\r\n" + currentScore);

	}

	public void changeAttempt_actionPerformed(ActionEvent e) {

		attempt = attempt + 1;

		playMusic(songPick);

		System.out.println("Attempt: " + attempt);
		System.out.println("Tid til at gætte " + seconds + " sek");
		System.out.println(ablePoints);
	}

	public void test_actionPerformed(ActionEvent e) {

		currentScore = score + ablePoints;

		score = currentScore;

		playerInfo.setText("Score: " + currentScore);
	}

}
