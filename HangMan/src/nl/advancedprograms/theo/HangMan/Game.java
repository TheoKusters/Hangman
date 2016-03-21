package nl.advancedprograms.theo.HangMan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Game {

	public static final String EXIT_COMMAND = "exit";
	public static final String RESET_COMMAND = "reset";	
	public static final String PROMPT = " > ";
	String input;
	RandomWord rw;
	char guessWord[];
	int attemptsMax = 7;
	int attempts = 0;

	public Game () throws IOException {
		int wordLength = 0;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Let's play HangMan.");
		System.out.println("Enter '" + EXIT_COMMAND + "' to quit, or '" + RESET_COMMAND + "' to restart the game");
		System.out.println("");
		do {
			System.out.println("Enter the length (between 3-20) of the word to play with");
			System.out.print(PROMPT); 
			input = br.readLine();
			if (input.matches(EXIT_COMMAND) || input.matches(RESET_COMMAND)) return;
			if (input.matches("\\d+")) wordLength = Integer.parseInt(input);
		}
		while (wordLength == 0);
		rw = new RandomWord(wordLength);
		guessWord = new char[rw.getRandomWord().length()];
		for (int x=0; x < rw.getRandomWord().length(); x++) {
			guessWord[x] = '.';
		}
	}

	public void playGame() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (attempts < attemptsMax && !(Arrays.equals(guessWord, rw.getRandomWord().toCharArray()))) {

			System.out.println(guessWord);
			System.out.print("Guess 1 character ");
			System.out.print(PROMPT);
			input = br.readLine().toLowerCase();

			if (input.equals(RESET_COMMAND) || input.equals(EXIT_COMMAND)) {
				return;
			}

			if (input.length() != 1 ) {Error("Wrong input, ");}
			else if (new String(guessWord).contains(input)) {Error("Character already selected, ");}
			else  {	
				int x = 0;
				x = rw.getRandomWord().indexOf(input, x);
				if (x == -1) {Error("Character not in word, ");}
				while (x > -1) {
					guessWord[x] = rw.getRandomWord().charAt(x++);
					x = rw.getRandomWord().indexOf(input, x);			
				}
			}
		}
		return;
	}

	void Error(String err) {
		attempts += 1;
		System.out.println(err + (attemptsMax - attempts) + " attempts left");
	}		
}

