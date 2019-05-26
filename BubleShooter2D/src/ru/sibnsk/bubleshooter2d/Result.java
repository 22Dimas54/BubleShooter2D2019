package ru.sibnsk.bubleshooter2d;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Result {

	private static ArrayList<Profile> profiles = new ArrayList<Profile>();

	@SuppressWarnings("unchecked")
	public static void ResultGame() {
		profiles = (ArrayList<Profile>) deserializableDate("profiles");
		System.out.println(profiles.size());
		Profile profile = new Profile();
		profile.setName(JOptionPane.showInputDialog(null, "Input name: "));
		profile.setRezult(0);
		profiles.add(profile);
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
