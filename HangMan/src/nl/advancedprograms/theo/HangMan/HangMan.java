package nl.advancedprograms.theo.HangMan;

import java.util.Arrays;

public class HangMan {

	public static final String EXIT_COMMAND = "exit";
	public static final String RESET_COMMAND = "reset";	
	public static final String PROMPT = " > ";	

	public static void main(String[] args) {

		Game play;
		do {
			try {
				play = new Game();
			}
			catch (Exception exc) {System.out.println("Exception 1: " + exc.getMessage()); return;}
			finally {}	

			if (!(play.input.equals(RESET_COMMAND) || play.input.equals(EXIT_COMMAND))) {
				try {
					play.playGame();
				}
				catch (Exception exc) {System.out.println("Exception 2: " + exc.getMessage()); return;}
				finally {}
			}
				
			if (play.input.equals(EXIT_COMMAND)) {
				System.out.println("Lets play again another time.");
				return;
			}
			else if (play.input.equals(RESET_COMMAND)) continue; 

			if (Arrays.equals(play.guessWord, play.rw.getRandomWord().toCharArray())) {
				System.out.println("Congratulations you guessed the word " + play.rw.getRandomWord() + ".");
				System.out.println("");
			}
			else {
				System.out.println("You hang! The word to guess was " + play.rw.getRandomWord() + ".");
				System.out.println("");
			}
		}
		while (play.input != EXIT_COMMAND);
	}
}