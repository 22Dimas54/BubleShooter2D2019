package ru.sibnsk.bubleshooter2d;

import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

public class LifePlayer {

	// Fields

	private Color color1;

	// COnstructors
	public LifePlayer() {
		color1 = Color.GREEN;
	}

	// Functions
	public void update() {

		if (Player.health == 5) {
			color1 = Color.green;
		}
		if (Player.health == 4) {
			color1 = Color.green;
		} else if (Player.health == 3) {
			color1 = Color.ORANGE;
		} else if (Player.health == 2) {
			color1 = Color.GRAY;
		} else if (Player.health == 1) {
			color1 = Color.LIGHT_GRAY;
		} else {
			color1 = Color.RED;
		}

	}

	public void draw(Graphics2D g) {

		g.setColor(color1);
		g.fillOval(20, 30, 10, 10);
		g.setStroke(new BasicStroke(10));
		g.setColor(Color.WHITE);
		g.setFont(new Font("Consolas", Font.BOLD, 20));
		g.drawString("Life", 10, 20);
		g.setColor(color1.darker());
		g.drawOval(20, 30, 10, 10);
		g.setStroke(new BasicStroke(1));

		g.setColor(Color.WHITE);
		g.setFont(new Font("Consolas", Font.BOLD, 20));
		g.drawString(String.valueOf(Player.health), 20, 42);

		// Game over
		if (Player.health == 0) {
			g.setColor(Color.ORANGE);
			g.setFont(new Font("consolas", Font.BOLD, 60));
			g.drawString("Game over!!!", GamePanel.WIDTH / 5, GamePanel.HEIGTH / 2);
		}
		
		//Points
		g.setColor(Color.YELLOW);
		g.fillRect(520,22,50,20);
		g.setFont(new Font("Consolas",Font.BOLD,20));
		g.drawString("Points", 520, 20);
		g.setColor(Color.YELLOW.darker());
		g.setColor(Color.MAGENTA);
		g.setFont(new Font("Consolas",Font.BOLD,20));
		g.drawString(String.valueOf(GamePanel.player.getPoints()), 520, 40);
		
	}

}
