package ru.sibnsk.bubleshooter2d;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

public class Result {

	// FIELDS
	private static ArrayList<Profile> profiles = new ArrayList<Profile>();
	private static String str;

	// CONSTRUCTOR

	// FUNCTiONS
	@SuppressWarnings("unchecked")
	public static void ShowResult() {
		str = String.format("%-35s  %10s \t \n", "Points", "Name");
		str = str + String.format("%-15s  %35s \t \n", "", "");
		profiles = (ArrayList<Profile>) deserializableDate("profiles");
		Collections.sort(profiles);

		for (Profile p : profiles) {
			str = str + String.format("%-15s  %35s \t \n", p.rezult, p.name);

		}
		JOptionPane.showMessageDialog(null, str);
	}

	@SuppressWarnings("unchecked")
	public static void ResultGame() {
		profiles = (ArrayList<Profile>) deserializableDate("profiles");
		// profiles = new ArrayList<Profile>();
		Profile profile = new Profile();
		profile.setName(JOptionPane.showInputDialog(null, "Input name: "));
		profile.setRezult((Integer) GamePanel.player.getPoints());
		profiles.add(profile);
		Collections.sort(profiles);
		if (profiles.size() > 9) {
			try {
				profiles.remove(10);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error delete from profiles!");
			}

		}
		serializableDate("profiles", profiles);
	}

	private static void serializableDate(String fileName, Object obj) {

		try {
			FileOutputStream fileOut = new FileOutputStream(fileName + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(obj);
			fileOut.close();
			out.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File is not find!");
			System.exit(1);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error input/output!");
			System.exit(2);
		}
	}

	private static Object deserializableDate(String fileName) {
		Object retObject = null;
		try {
			FileInputStream fileIn = new FileInputStream(fileName + ".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			retObject = in.readObject();
			fileIn.close();
			in.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File is not find!");
			System.exit(1);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error input/output!");
			System.exit(2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Class is not find!");
			System.exit(1);
		}
		return retObject;
	}

}
