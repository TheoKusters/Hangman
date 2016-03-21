package nl.advancedprograms.theo.HangMan;

import java.net.*;
import java.io.*;

public class RandomWord {

	private static String word;
	private static final String URLPART1 = "http://randomword.setgetgo.com/get.php";
	private static String urlPart2 = "";

	RandomWord(int len) {
		if (len >= 3 && len <= 20) urlPart2 = "?len=" + len;
		try {
			URL url = new URL(URLPART1.concat(urlPart2));
			try {
				URLConnection conn = url.openConnection();
				InputStreamReader is = new InputStreamReader(conn.getInputStream());
				BufferedReader bufferedReader = new BufferedReader(is);
				setRandomWord((bufferedReader.readLine()));
			}
			catch (Exception e) {System.out.println(e.getMessage()); return;}
			finally {}		
		}
		catch (Exception e) {System.out.println(e.getMessage()); return;}
		finally {}
	}

	RandomWord() {
		this(0);
	}

	public String getRandomWord() {
		return word.toLowerCase();
	}

	private void setRandomWord(String word) {
		RandomWord.word = word.toLowerCase();
	}
}