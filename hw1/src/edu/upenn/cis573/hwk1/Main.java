package edu.upenn.cis573.hwk1;

import java.io.*;
import java.util.*;

public class Main {

	public static int shiftValue = 3;
	public static float totalCorrect = 0;
	public static float total = 0;

	/**
	 * This opens the file from the folder, then encrypts each one and uses the
	 * others to form model. In the next it uses the model to decrypt the
	 * encrypted file and compares to the original file and give the result.
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws IOException, FileNotFoundException {

		String path = args[0];
		File folderPath = new File(path);

		// Check if number of arguments is 1
		if (args.length != 1) {
			System.out.println("Invalid number of arguments, Exiting program");
		}
		// Check if the folder is existed.
		if (!folderPath.exists()) {
			System.out.println("The folder doesn't exist!");
			return;
		}
		// Check if path of the folder is valid
		if (!folderPath.isDirectory()) {
			System.out.println("The folder doesn't exist!");
			return;
		}
		// Check if the folder is empty
		if (folderPath.list().length <= 0) {
			System.out.println("Directory is empty!");
			return;
		}
		// Check if the folder can be read.
		if (!folderPath.canRead()) {
			System.out.println("The folder cannot be read!");
			return;
		}
		String originalText = new String();
		String encryptText = new String();
		String decryptText = new String();

		File[] listOfFiles = folderPath.listFiles();

		// Iterate through each file within the corpus
		for (File currentFile : listOfFiles) {
			if (currentFile.isFile()) {
				// Convert the current file to string and encrypt it.
				originalText = fileToStr(currentFile);
				encryptText = encrypt(originalText);
				int[] currentModel = new int[26];
				int[] otherModel = new int[26];
				// Form model from current file.
				formModel(encryptText, currentModel);
				// Iterate through other file within the corpus
				for (File otherFile : listOfFiles) {
					if (otherFile != currentFile) {
						String otherText = new String();
						// Convert the other file to string and encrypt it.
						otherText = fileToStr(otherFile);
						// Form model from current file.
						formModel(otherText, otherModel);
					}
				}
				// Decrypt current file
				decryptText = decrypt(encryptText, otherModel, currentModel);
				// compare and print.
				compareAndPrint(currentFile.getName(), originalText, decryptText);
			}
		}
		// Give the summary.
		System.out.println("Total: " + totalCorrect + " correct, " + (total - totalCorrect) + " incorrect");
		System.out.println("Accuracy: " + (totalCorrect / total));
	}

	/**
	 * This method converts the file.txt to string.
	 */
	public static String fileToStr(File readFile) {
		BufferedReader br;
		StringBuilder sb = new StringBuilder();
		try {
			br = new BufferedReader(new FileReader(readFile));
			String line;
			try {
				line = br.readLine();
				while (line != null) {
					sb.append(line);
					sb.append(System.lineSeparator());
					line = br.readLine();
				}
				br.close();
				return sb.toString();
			} catch (IOException e) {
				System.out.println("Error: Is the file correct?");
				return null;
			}

		} catch (FileNotFoundException e) {
			System.out.println("Error: Is the file correct?");
			return null;
		}
	}

	/**
	 * This method encrypts the string which converted from the original file.
	 */
	public static String encrypt(String text) throws IOException {
		char[] chars = text.toCharArray();
		// Loop through all the characters in the file and add an offset to the
		// ASCII character
		for (int i = 0; i < text.length(); i++) {
			char c = chars[i];
			if (Character.isLetter(c)) {
				c = Character.toLowerCase(c);
				if (c > 119) {
					chars[i] = (char) ((c + shiftValue) % 123 + 97);
				} else {
					chars[i] = (char) ((c + shiftValue));
				}
			}
		}
		String encrypted = new String(chars);
		return encrypted;
	}

	/**
	 * This method forms the frequency model from a String, return a array which
	 * contains each char's frequency.
	 */
	public static void formModel(String strForModel, int[] Model) {
		char[] chars = strForModel.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (Character.isLetter(c)) {
				c = Character.toLowerCase(c);
				int index = c - 'a';
				Model[index] += 1;
			}
		}
	}

	/**
	 * This method returns the max frequency of a char from a given array.
	 */
	public static char getMaxChar(int[] Model) {
		char c = 0;
		int max = -1;
		int maxIndex = -1;
		for (int i = 0; i < Model.length; i++) {
			if (Model[i] > max) {
				max = Model[i];
				c = (char) (97 + i);
				maxIndex = i;
			}
		}
		Model[maxIndex] = -1;
		return c;
	}

	/**
	 * This method decrypts the encrypted string, using the encrypted model and
	 * the model formed from other files.
	 */
	public static String decrypt(String toDecrypt, int[] otherModel, int[] enModel) {
		char[] corpusModel = new char[26];
		char[] encryptModel = new char[26];
		for (int i = 0; i < otherModel.length; i++) {
			corpusModel[i] = getMaxChar(otherModel);
		}
		for (int i = 0; i < enModel.length; i++) {
			encryptModel[i] = getMaxChar(enModel);
		}
		HashMap<Character, Character> map = new HashMap<Character, Character>();
		for (int i = 0; i < corpusModel.length; i++) {
			map.put(encryptModel[i], corpusModel[i]);
		}
		StringBuffer retrunString = new StringBuffer();
		for (int i = 0; i < toDecrypt.length(); i++) {
			if (map.containsKey(toDecrypt.charAt(i))) {
				retrunString.append(map.get(toDecrypt.charAt(i)));
			} else {
				retrunString.append(toDecrypt.charAt(i));
			}
		}
		return retrunString.toString();
	}

	/**
	 * This method compares the decrypted string and the original string and
	 * print the result.
	 */
	public static void compareAndPrint(String filename, String originalText, String decryptedText) {
		float curCorrect = 0;
		float curTotal = 0;
		for (int i = 0; i < originalText.length(); i++) {
			char c = originalText.charAt(i);
			if (Character.isLetter(c)) {
				c = Character.toLowerCase(c);
				curTotal++;
				if (c == decryptedText.charAt(i))
					curCorrect++;
			}
		}
		total += curTotal;
		totalCorrect += curCorrect;
		System.out.println(filename + ": " + curCorrect + " correct, " + (curTotal - curCorrect) + " incorrect");
	}

	/**
	 * This method was used to test compareAndPrint.
	 */
	public static float compareAndPrint1(String filename, String originalText, String decryptedText) {
		float curCorrect = 0;
		float curTotal = 0;
		for (int i = 0; i < originalText.length(); i++) {
			char c = originalText.charAt(i);
			if (Character.isLetter(c)) {
				c = Character.toLowerCase(c);
				curTotal++;
				if (c == decryptedText.charAt(i))
					curCorrect++;
			}
		}
		total += curTotal;
		totalCorrect += curCorrect;
		System.out.println(filename + ": " + curCorrect + " correct, " + (curTotal - curCorrect) + " incorrect");
		return curTotal - curCorrect;
	}
}
