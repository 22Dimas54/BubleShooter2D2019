package ru.sibnsk.bubleshooter2d;

import javax.swing.JFrame;

public class GameStart {

	public static void main(String[] args) {

		GamePanel panel = new GamePanel();
		JFrame startFrame = new JFrame("BubleShooter2D");
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		startFrame.setContentPane(panel);
		startFrame.setResizable(false);
		startFrame.pack();
		startFrame.setLocationRelativeTo(null);
		startFrame.setVisible(true);
		panel.start();

	}

}
