package ru.sibnsk.bubleshooter2d;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;;

public class Bonus {

	// FIELDS
	private Color color1;
	private int transparencyBonus = 0;
	private double activeSpeed;
	private boolean activeBonus;
	private long activeBonusTime;
	private double angel;

	// CONSTRUKTOR
	public Bonus() {
		color1 = Color.RED;
		activeBonus = false;
		activeBonusTime = 0;
		activeSpeed = 1;
		angel = Math.toRadians(Math.random() * 360);
	}

	// FUNCTIONS
	public void update() {
		if (GamePanel.player.getPoints() >= 1) {
			color1 = Color.DARK_GRAY;
		} else {
			color1 = Color.RED;
		}
//		if (activeBonus) {
//			if (System.nanoTime() - activeBonusTime >1000) {
//				for (Enemy e : GamePanel.enemies) {
//					e.setSpeed(activeSpeed);
//					e.setDx(Math.sin(angel) * activeSpeed);
//					e.setDy(Math.cos(angel) * activeSpeed);
//					activeBonus = false;
//				}
//			}
//		}

		if (GamePanel.mouseX > 265 && GamePanel.mouseX < 340 && GamePanel.mouseY > 0 && GamePanel.mouseY < 25) {
			transparencyBonus = 200;
			if (GamePanel.leftMouse) {
				if (GamePanel.player.getPoints() >= 1) {
					activeBonusTime = System.nanoTime();
					activeBonus = true;
					GamePanel.leftMouse = false;
					GamePanel.player.setPoints(GamePanel.player.getPoints() - 1);
					for (Enemy e : GamePanel.enemies) {
						activeSpeed = e.getSpeed();
						System.out.println(e.getSpeed());
						if (activeSpeed > 1) {
							e.setSpeed(activeSpeed - 10);
							e.setDx(angel * (activeSpeed - 10));
							e.setDy(angel * (activeSpeed - 10));
						}
						System.out.println(e.getSpeed());
					}
				} else {
					JOptionPane.showMessageDialog(null, "I am sorry 'Points' < 100 !");
				}
			}
		} else {
			transparencyBonus = 0;
		}
	}

	public void draw(Graphics2D g) {
		g.setColor(color1);
		g.fillOval(270, 5, 70, 20);
		g.setStroke(new BasicStroke(10));
		g.setColor(Color.CYAN);
		g.drawString("Bonus", 280, 20);
		g.setColor(new Color(0, 255, 255, transparencyBonus));
		g.drawOval(270, 5, 70, 20);

	}
}
