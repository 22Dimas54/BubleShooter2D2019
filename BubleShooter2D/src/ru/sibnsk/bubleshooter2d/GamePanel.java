package ru.sibnsk.bubleshooter2d;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.util.*;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	public static int WIDTH = 600;
	public static int HEIGTH = 600;

	public static int mouseX;
	public static int mouseY;
	public static boolean leftMouse;

	private Thread thread;

	private BufferedImage image;
	private Graphics2D g;

	private int FPS;
	private double milliesToFPS;
	private long timerFPS;
	private int sleepTime;

	public static enum STATES {
		MENUE, PLAY
	}

	public static STATES state = STATES.MENUE;

	public GameBack background;
	public static Player player;
	public static ArrayList<Bullet> bullets;
	public static ArrayList<Enemy> enemies;
	public static Wave wave;
	public static Menue menue;
	public static LifePlayer lifePlayer;
	public static Bonus bonus;

	// Constructor
	public GamePanel() {
		super();

		setPreferredSize(new Dimension(WIDTH, HEIGTH));
		setFocusable(true);
		requestFocus();

		addKeyListener(new Listeners());
		addMouseMotionListener(new Listeners());
		addMouseListener(new Listeners());
	}

	// Functions

	public void start() {
		thread = new Thread(this);
		thread.start();
	}

	public void run() {

		FPS = 30;
		milliesToFPS = 1000 / FPS;
		sleepTime = 0;

		image = new BufferedImage(WIDTH, HEIGTH, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		leftMouse = false;
		background = new GameBack();
		player = new Player();
		bullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
		//// TODO add these enemies
		wave = new Wave();
		menue = new Menue();
		lifePlayer = new LifePlayer();
		bonus = new Bonus();

		Toolkit kit = Toolkit.getDefaultToolkit();
		BufferedImage buffered = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g3 = (Graphics2D) buffered.getGraphics();
		g3.setColor(new Color(255, 255, 255));
		g3.drawOval(0, 0, 4, 4);
		g3.drawLine(2, 0, 2, 4);
		g3.drawLine(0, 2, 4, 2);
		Cursor myCursor = kit.createCustomCursor(buffered, new Point(3, 3), "myCursor");
		g3.dispose();

		while (true) {// TODO States

			this.setCursor(myCursor);
			timerFPS = System.nanoTime();

			if (state.equals(STATES.MENUE)) {
				background.draw(g);
				background.update(g);
				menue.update();
				menue.draw(g);
				gameDraw();
			}
			if (state.equals(STATES.PLAY)) {
				gameUpdate();
				gameRender();
				gameDraw();
			}

			timerFPS = (System.nanoTime() - timerFPS) / 1000000;
			if (milliesToFPS > timerFPS) {
				sleepTime = (int) (milliesToFPS - timerFPS);
			} else {
				sleepTime = 1;
			}
			try {
				Thread.sleep(sleepTime); // TODO FPS
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			timerFPS = 0;
			sleepTime = 1;

			// Game over
			if (Player.health == 0) {
				state = STATES.MENUE;
			}

		}
	}

	public void gameUpdate() {

		// background update
		background.update(this.g);

		// Player update
		player.update();

		// Bullets update
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update();
			boolean remove = bullets.get(i).remove();
			if (remove) {
				bullets.remove(i);
				i--;
			}
		}

		// Enemies update
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
		}

		// Bullets- enemies collide
		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			double ex = e.getX();
			double ey = e.getY();

			for (int j = 0; j < bullets.size(); j++) {
				Bullet b = bullets.get(j);
				double bx = b.getX();
				double by = b.getY();

				double dx = ex - bx;
				double dy = ey - by;
				double dist = Math.sqrt(dx * dx + dy * dy);
				if ((int) dist <= e.getR() + b.getR()) {
					e.hit();
					bullets.remove(j);
					j--;
					boolean remove = e.remove();
					if (remove) {
						enemies.remove(i);
						player.points(e.getType(), e.getRank());
						i--;
						break;
					}
				}
			}
		}
		// Player-enemy collides
		for (

				int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			double ex = e.getX();
			double ey = e.getY();

			double px = player.getX();
			double py = player.getY();
			double dx = ex - px;
			double dy = ey - py;
			double dist = Math.sqrt(dx * dx + dy * dy);
			if ((int) dist <= e.getR() + player.getR()) {
				e.hit();
				boolean remove = e.remove();
				if (remove) {
					enemies.remove(i);
					player.hit();
					lifePlayer.update();
					background.update(this.g);
					i--;
				}
			}
		}
		// Wave update
		wave.update();
		// Bonus update
		bonus.update();
		background.update(this.g);

	}

	public void gameRender() {

		// background draw
		background.draw(g);

		// Life player draw
		lifePlayer.draw(g);

		// Player draw
		player.draw(g);

		// Bullets draw
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(g);
			;
		}

		// Enemies draw
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}

		// Wave draw
		if (wave.showWave()) {
			wave.draw(g);
		}

		// Bonus draw
		bonus.draw(g);
	}

	private void gameDraw() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}

}
