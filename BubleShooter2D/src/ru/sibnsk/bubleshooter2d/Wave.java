package ru.sibnsk.bubleshooter2d;

import java.awt.*;

public class Wave {

	// Fields
	private int waveMultiplier;
	private int waveNumber;

	private String waveText;

	private long waveTimer;
	private long waveDilayr;
	private long waveTimerDiff;

	// Constructor
	public Wave() {
		waveNumber = 1;
		waveMultiplier = 5;

		waveTimer = 0;
		waveDilayr = 5000;
		waveTimerDiff = 0;

		waveText = " волна -";
	}

	// Functions
	public void createEnemies() {
		int enemyCount = waveNumber * waveMultiplier;
		if (waveNumber < 2) {
			while (enemyCount > 0) {
				int type = 1;
				int rank = 1;
				GamePanel.enemies.add(new Enemy(type, rank));
				enemyCount -= type * rank;
			}
		} else if (waveNumber > 1 && waveNumber < 3) {
			enemyCount = waveNumber * waveMultiplier;
			while (enemyCount > 0) {
				int type = 1;
				int rank = 2;
				GamePanel.enemies.add(new Enemy(type, rank));
				enemyCount -= type * rank;
			}
		} else if (waveNumber > 2 && waveNumber < 4) {
			enemyCount = waveNumber * waveMultiplier;
			while (enemyCount > 0) {
				int type = 2;
				int rank = 1;
				GamePanel.enemies.add(new Enemy(type, rank));
				enemyCount -= type * rank;
			}
		} else if (waveNumber > 3 && waveNumber < 5) {
			enemyCount = waveNumber * waveMultiplier;
			while (enemyCount > 0) {
				int type = 2;
				int rank = 2;
				GamePanel.enemies.add(new Enemy(type, rank));
				enemyCount -= type * rank;
			}
		}
		waveNumber++;
	}

	public void update() {
		if (GamePanel.enemies.size() == 0 && waveTimer == 0) {
			waveTimer = System.nanoTime();
		}
		if (waveTimer > 0) {
			waveTimerDiff += (System.nanoTime() - waveTimer) / 1000000;
			waveTimer = System.nanoTime();
		}
		if (waveTimerDiff > waveDilayr) {
			createEnemies();
			waveTimer = 0;
			waveTimerDiff = 0;
		}
	}

	public boolean showWave() {
		if (waveTimer != 0) {
			return true;
		} else
			return false;
	}

	public void draw(Graphics2D g) {
		double divider = waveDilayr / 180;
		double alpha = waveTimerDiff / divider;
		alpha = 255 * Math.sin(Math.toRadians(alpha));
		if (alpha < 0)
			alpha = 0;
		if (alpha > 255)
			alpha = 255;

		g.setFont(new Font("consolas", Font.PLAIN, 20));
		g.setColor(new Color(255, 255, 255, (int) alpha));
		String s = " -  " + waveNumber + "-ая" + waveText;
		long length = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
		g.drawString(s, GamePanel.WIDTH / 2 - (int) (length / 2), GamePanel.HEIGTH / 2);
	}

}
