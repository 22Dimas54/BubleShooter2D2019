package ru.sibnsk.bubleshooter2d;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JOptionPane;

public class Result {

	// FIELDS
	private static ArrayList<Profile> profiles = new ArrayList<Profile>();

	// CONSTRUCTOR

	// FUNCTiONS
	public static void ShowResult() {
		profiles = (ArrayList<Profile>) deserializableDate("profiles");
		System.out.println(profiles.size());
		Collections.sort(profiles);
		/*
		 * @Override public int compare(Profile o1, Profile o2) { return
		 * o1.getRezult().compareTo(o2.getRezult()); } }); }
		 */
		for (Profile p : profiles) {
			System.out.println("Name: " + p.name + " Points: " + p.rezult);
		}
	}

	@SuppressWarnings("unchecked")
	public static void ResultGame() {
		 profiles = (ArrayList<Profile>) deserializableDate("profiles");
		//profiles = new ArrayList<Profile>();
		System.out.println(profiles.size());
		Profile profile = new Profile();
		profile.setName(JOptionPane.showInputDialog(null, "Input name: "));
		profile.setRezult((Integer) GamePanel.player.getPoints());
		profiles.add(profile);
		Collections.sort(profiles, new Comparator<Profile>() {
			@Override
			public int compare(Profile o1, Profile o2) {
				return 0;
			}
		});
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
