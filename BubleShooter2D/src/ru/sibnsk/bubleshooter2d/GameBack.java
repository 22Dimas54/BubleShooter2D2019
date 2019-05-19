package ru.sibnsk.bubleshooter2d;

import java.awt.*;

import ru.sibnsk.bubleshooter2d.GamePanel.STATES;

public class GameBack {

	// Fields
	private Color color;

	// Constructor
	public GameBack() {
		color = Color.BLUE;
	}

	// Functions
	public void update(Graphics2D g) {
		if (GamePanel.state.equals(STATES.MENUE)) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGTH);
		} else {
			g.setColor(Color.RED);
			g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGTH);
		}
	}

	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGTH);
	}

}
