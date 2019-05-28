package ru.sibnsk.bubleshooter2d;

import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
//import java.awt.BasicStroke;

public class Menue {

	// FIELDS
	private int buttonWidth;
	private int buttonHeigth;
	private Color color1;
	public static String play;
	private String exit;
	private String saveResult;
	private String showResult;
	private int transparencyPlay = 0;
	private int transparencyExit = 0;
	private int transparencySaveResult = 0;
	private int transparencyShowResult = 0;	
	private int transparencyNewGame = 0;

	public static boolean newGame = false;

	// Constructor
	public Menue() {
		buttonWidth = 140;
		buttonHeigth = 60;

		color1 = Color.WHITE;
		play = "Play";
		exit = "Exit";
		saveResult = "Save result";
		showResult = "Show rezults";
	}

	// Functions
	private void UpdateTransparency(String transparency) {
		if(transparency.equals("transparencyPlay")) {
		transparencyExit = 0;
		transparencySaveResult = 0;
		transparencyShowResult = 0;	
		transparencyNewGame = 0;
		}
		else if(transparency.equals("transparencyExit")) {
			transparencyPlay = 0;
			transparencySaveResult = 0;
			transparencyShowResult = 0;	
			transparencyNewGame = 0;
			}
		else if(transparency.equals("transparencySaveResult")) {
			transparencyPlay = 0;
			transparencyExit = 0;
			transparencyShowResult = 0;	
			transparencyNewGame = 0;
			}	
		else if(transparency.equals("transparencyShowResult")) {
			transparencyPlay = 0;
			transparencyExit = 0;
			transparencySaveResult = 0;
			transparencyNewGame = 0;
			}	
		else if(transparency.equals("transparencyNewGame")) {
			transparencyPlay = 0;
			transparencyExit = 0;
			transparencySaveResult = 0;
			transparencyShowResult = 0;	
			}		
	}
	public void update() {
		if (GamePanel.mouseX > GamePanel.WIDTH / 2 - buttonWidth / 2
				&& GamePanel.mouseX < GamePanel.WIDTH / 2 + buttonWidth / 2
				&& GamePanel.mouseY > GamePanel.HEIGTH / 3 - buttonHeigth / 2
				&& GamePanel.mouseY < GamePanel.HEIGTH / 3 + buttonHeigth / 2) {
			transparencyPlay = 60;
			UpdateTransparency("transparencyPlay");
			if (GamePanel.leftMouse) {
				if (Player.health == 0) { // NEW GAME
					GamePanel.player = new Player();
					GamePanel.bullets = new ArrayList<Bullet>();
					GamePanel.enemies = new ArrayList<Enemy>();
					GamePanel.wave = new Wave();
					GamePanel.lifePlayer = new LifePlayer();
					GamePanel.state = GamePanel.STATES.PLAY;
				} else {
					GamePanel.state = GamePanel.STATES.PLAY;
				}
			}
		} else if (GamePanel.mouseX > GamePanel.WIDTH / 2 - buttonWidth / 2
				&& GamePanel.mouseX < GamePanel.WIDTH / 2 + buttonWidth / 2
				&& GamePanel.mouseY > GamePanel.HEIGTH / 1.5 - buttonHeigth / 2
				&& GamePanel.mouseY < GamePanel.HEIGTH / 1.5 + buttonHeigth / 2) {
			transparencyExit = 60;
			UpdateTransparency("transparencyExit");
			if (GamePanel.leftMouse) {
				System.exit(0);
			}
		} else if (GamePanel.mouseX > GamePanel.WIDTH / 2 - buttonWidth / 2
				&& GamePanel.mouseX < GamePanel.WIDTH / 2 + buttonWidth / 2
				&& GamePanel.mouseY > GamePanel.HEIGTH / 2.25 - buttonHeigth / 2
				&& GamePanel.mouseY < GamePanel.HEIGTH / 2.25 + buttonHeigth / 2) {
			transparencySaveResult = 60;
			UpdateTransparency("transparencySaveResult");
			if (GamePanel.leftMouse) {
				if(GamePanel.player.getPoints()>0) {
				Result.ResultGame();
				}
				else {
					JOptionPane.showMessageDialog(null, "I am sorry 'Points' = 0 !");
				}
				GamePanel.leftMouse = false;
			}
		} else if (GamePanel.mouseX > GamePanel.WIDTH / 2 - buttonWidth / 2
				&& GamePanel.mouseX < GamePanel.WIDTH / 2 + buttonWidth / 2
				&& GamePanel.mouseY > GamePanel.HEIGTH / 1.8 - buttonHeigth / 2
				&& GamePanel.mouseY < GamePanel.HEIGTH / 1.8 + buttonHeigth / 2) {
			transparencyShowResult = 60;
			UpdateTransparency("transparencyShowResult");
			if (GamePanel.leftMouse) {
				Result.ShowResult();
				GamePanel.leftMouse = false;
			}			
		} else if (GamePanel.mouseX > GamePanel.WIDTH / 2 - buttonWidth / 2
				&& GamePanel.mouseX < GamePanel.WIDTH / 2 + buttonWidth / 2
				&& GamePanel.mouseY > GamePanel.HEIGTH / 5 - buttonHeigth / 2
				&& GamePanel.mouseY < GamePanel.HEIGTH / 5 + buttonHeigth / 2) {
			transparencyNewGame = 60;
			UpdateTransparency("transparencyNewGame");
			if (GamePanel.leftMouse) {
				GamePanel.player = new Player();
				GamePanel.bullets = new ArrayList<Bullet>();
				GamePanel.enemies = new ArrayList<Enemy>();
				GamePanel.wave = new Wave();
				GamePanel.lifePlayer = new LifePlayer();
				GamePanel.state = GamePanel.STATES.PLAY;
			}
		} else {
			transparencyPlay = 0;
			transparencyExit = 0;
			transparencyNewGame = 0;
			transparencySaveResult = 0;
			transparencyShowResult = 0;
		}
	}

	public void draw(Graphics2D g) {
		
		//Game over
		if(Player.health==0){
			g.setColor(Color.ORANGE);
			g.setFont(new Font("consolas",Font.BOLD,60));
			g.drawString("Game over!!!",GamePanel.WIDTH/5,GamePanel.HEIGTH/5);
		}		
		
		//Menu play
		g.setColor(color1);
		g.setStroke(new BasicStroke(3));
		g.drawRect((int) (GamePanel.WIDTH / 2 - buttonWidth / 2), (int) (GamePanel.HEIGTH / 3 - buttonHeigth / 2),
				buttonWidth, buttonHeigth);
		g.setColor(new Color(255, 255, 255, transparencyPlay));
		g.fillRect((int) (GamePanel.WIDTH / 2 - buttonWidth / 2), (int) (GamePanel.HEIGTH / 3 - buttonHeigth / 2),
				buttonWidth, buttonHeigth);
		g.setStroke(new BasicStroke(1));

		g.setColor(color1);
		g.setFont(new Font("Consolas", Font.BOLD, 20));
		long lengthPlay = (int) g.getFontMetrics().getStringBounds(play, g).getWidth();
		g.drawString(play, (int) (GamePanel.WIDTH / 2 - lengthPlay / 2),
				(int) (GamePanel.HEIGTH / 3 + buttonHeigth / 4));	
		
		//Menu saveResult
		g.setColor(color1);
		g.setStroke(new BasicStroke(3));
		g.drawRect((int) (GamePanel.WIDTH / 2 - buttonWidth / 2), (int) (GamePanel.HEIGTH / 2.25 - buttonHeigth / 2),
				buttonWidth, buttonHeigth);
		g.setColor(new Color(255, 255, 255, transparencySaveResult));
		g.fillRect((int) (GamePanel.WIDTH / 2 - buttonWidth / 2), (int) (GamePanel.HEIGTH / 2.25 - buttonHeigth / 2),
				buttonWidth, buttonHeigth);
		g.setStroke(new BasicStroke(1));

		g.setColor(color1);
		g.setFont(new Font("Consolas", Font.BOLD, 20));
		long lengthSaveResult = (int) g.getFontMetrics().getStringBounds(saveResult, g).getWidth();
		g.drawString(saveResult, (int) (GamePanel.WIDTH / 2 - lengthSaveResult / 2),
				(int) (GamePanel.HEIGTH / 2.25 + buttonHeigth / 4));
		
		//Menu showResult
		g.setColor(color1);
		g.setStroke(new BasicStroke(3));
		g.drawRect((int) (GamePanel.WIDTH / 2 - buttonWidth / 2), (int) (GamePanel.HEIGTH / 1.8 - buttonHeigth / 2),
				buttonWidth, buttonHeigth);
		g.setColor(new Color(255, 255, 255, transparencyShowResult));
		g.fillRect((int) (GamePanel.WIDTH / 2 - buttonWidth / 2), (int) (GamePanel.HEIGTH / 1.8 - buttonHeigth / 2),
				buttonWidth, buttonHeigth);
		g.setStroke(new BasicStroke(1));

		g.setColor(color1);
		g.setFont(new Font("Consolas", Font.BOLD, 20));
		long lengthShowResult = (int) g.getFontMetrics().getStringBounds(showResult, g).getWidth();
		g.drawString(showResult, (int) (GamePanel.WIDTH / 2 - lengthShowResult / 2),
				(int) (GamePanel.HEIGTH / 1.8 + buttonHeigth / 4));			

		//Menu exit
		g.setColor(color1);
		g.setStroke(new BasicStroke(3));
		g.drawRect((int) (GamePanel.WIDTH / 2 - buttonWidth / 2), (int) (GamePanel.HEIGTH / 1.5- buttonHeigth / 2),
				buttonWidth, buttonHeigth);
		g.setColor(new Color(255, 255, 255, transparencyExit));
		g.fillRect((int) (GamePanel.WIDTH / 2 - buttonWidth / 2), (int) (GamePanel.HEIGTH / 1.5 - buttonHeigth / 2),
				buttonWidth, buttonHeigth);
		g.setStroke(new BasicStroke(1));

		g.setColor(color1);
		g.setFont(new Font("Consolas", Font.BOLD, 20));
		long lengthExit = (int) g.getFontMetrics().getStringBounds(exit, g).getWidth();
		g.drawString(exit, (int) (GamePanel.WIDTH / 2 - lengthExit / 2),
				(int) (GamePanel.HEIGTH / 1.5 + buttonHeigth / 4));
		
		
		

		if (newGame) {
			g.setColor(color1);
			g.setStroke(new BasicStroke(3));
			g.drawRect((int) (GamePanel.WIDTH / 2 - buttonWidth / 2), (int) (GamePanel.HEIGTH / 4.5 - buttonHeigth / 2),
					buttonWidth, buttonHeigth);
			g.setColor(new Color(255, 255, 255, transparencyNewGame));
			g.fillRect((int) (GamePanel.WIDTH / 2 - buttonWidth / 2), (int) (GamePanel.HEIGTH / 4.5 - buttonHeigth / 2),
					buttonWidth, buttonHeigth);
			g.setStroke(new BasicStroke(1));
			g.setColor(color1);
			g.setFont(new Font("Consolas", Font.BOLD, 20));
			long lengthNewGame = (int) g.getFontMetrics().getStringBounds("New game", g).getWidth();
			g.drawString("New game", (int) (GamePanel.WIDTH / 2 - lengthNewGame / 2),
					(int) (GamePanel.HEIGTH / 4.5 + buttonHeigth / 4));
		}
	}
	
}
