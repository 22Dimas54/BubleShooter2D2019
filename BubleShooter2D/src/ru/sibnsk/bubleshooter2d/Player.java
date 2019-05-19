package ru.sibnsk.bubleshooter2d;

import java.awt.*;

public class Player {

	// Fields
	private double x, y;
	private int r;

	private double dx, dy;// MOVE Coef;

	private int speed;

	public static int health;
	private int points;

	private Color color1;

	public static boolean down;
	public static boolean left;
	public static boolean right;
	public static boolean up;
	public static boolean isFiring;

	// Constroctor
	public Player() {
		x = GamePanel.WIDTH / 2;
		y = GamePanel.HEIGTH / 2;

		dx = 0;
		dy = 0;

		r = 5;

		speed = 5;

		health = 5;
		setPoints(0);

		color1 = Color.WHITE;

		up = false;
		down = false;
		left = false;
		right = false;
		isFiring = false;
	}

	// Functions
	public void points(int type, int rank) {
		if (type == 1 && rank == 2) {
			points+=2;
		} else if (type == 2 && rank == 1) {
			points+=3;
		} else if (type == 2 && rank == 2) {
			points+=4;
		} else {
			points++;
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getR() {
		return r;
	}

	public void hit() {
		health--;
	}

	public void update() {

		if (up && y > r) {
			dy = -speed;
		}
		if (down && y < GamePanel.HEIGTH - r) {
			dy = speed;
		}
		if (left && x > r) {
			dx = -speed;
		}
		if (right && x < GamePanel.WIDTH - r) {
			dx = speed;
		}

		if (up && left || up && right || down && left || down && right) {
			double angel = Math.toRadians(45);
			dy = dy * Math.sin(angel);
			dx = dx * Math.cos(angel);
		}

		y += dy;
		x += dx;

		dy = 0;
		dx = 0;

		if (isFiring) {
			GamePanel.bullets.add(new Bullet());
		}

	}

	public void draw(Graphics2D g) {
		g.setColor(color1);
		g.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
		g.setStroke(new BasicStroke(3));
		g.setColor(color1.darker());
		g.drawOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
		g.setStroke(new BasicStroke(1));
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

}
