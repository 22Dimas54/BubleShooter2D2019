package ru.sibnsk.bubleshooter2d;

import java.awt.*;

public class Enemy {

	// Fields
	private double x, y;
	private int r;

	private double speed;
	private double dx;
	private double dy;

	private double health;

	private int type;
	private int rank;

	private Color color;

	// Constructor
	public Enemy(int type, int rank) {
		this.setType(type);
		this.setRank(rank);

		double angle = Math.toRadians(Math.random() * 360);
		System.out.println(rank);
		if (type == 1) {
			color = Color.GREEN;
			if (rank == 1) {
				// case 1:
				x = Math.random() * GamePanel.WIDTH;
				y = 0;

				r = 7;

				speed = 11;

				health = 1;

				dx = Math.sin(angle) * speed;
				dy = Math.cos(angle) * speed;
			}

			else if (rank == 2) {
				x = Math.random() * GamePanel.WIDTH;
				y = 0;

				r = 10;

				speed = 2;

				health = 2;

				dx = Math.sin(angle) * speed;
				dy = Math.cos(angle) * speed;
			}
		} else if (type == 2) {
			color = Color.ORANGE;
			if (rank == 1) {
				// case 1:
				x = Math.random() * GamePanel.WIDTH;
				y = 0;

				r = 7;

				speed = 3;

				health = 3;

				dx = Math.sin(angle) * speed;
				dy = Math.cos(angle) * speed;
			}

			else if (rank == 2) {
				// case 2:
				x = Math.random() * GamePanel.WIDTH;
				y = 0;

				r = 10;

				speed = 3;

				health = 4;

				dx = Math.sin(angle) * speed;
				dy = Math.cos(angle) * speed;
			}
		}
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	// Functions
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getR() {
		return r;
	}

	public boolean remove() {
		if (health <= 0) {
			return true;
		}
		return false;

	}

	public void hit() {
		health--;
	}

	public void update() {
		x += dx;
		y += dy;

		if (x < 0 && dx < 0)
			dx = -dx;
		if (x > GamePanel.WIDTH && dx > 0)
			dx = -dx;

		if (y < 0 && dy < 0)
			dy = -dy;
		if (y > GamePanel.HEIGTH && dy > 0)
			dy = -dy;

	}

	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
		g.setStroke(new BasicStroke(3));
		g.setColor(color.darker());
		g.drawOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
		g.setStroke(new BasicStroke(1));
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
