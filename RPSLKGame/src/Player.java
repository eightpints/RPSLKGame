import java.util.Random;
import java.util.Scanner;

/**
 * The is the Superclass for all Player objects The main purpose of Player
 * objects is to provide RPSLK with a throw
 * 
 * @author Galen Xing gx2113
 * 
 */
public class Player {

	public final char ROCK = 'r', PAPER = 'p', SCISSORS = 's', LIZARD = 'l',
			SPOCK = 'k';
	public char lastThrow = ' ', opponentThrow = ' ';
	private final String NAMEOFPLAYER = "player";

	/**
	 * default constructor for Player. Instance variables can be set and get later
	 * with set and get methods
	 */
	public Player() {
	}

	/**
	 * allows you to store what the oppenent threw the last round
	 * @param oppMove	the oppenent's throw last round
	 */
	public void setOpponentThrow(char oppMove) {
		opponentThrow = oppMove;
	}

	/**
	 * Returns what the oppenent threw last round
	 * @return the opponent's last throw
	 */
	public char getOpponentThrow() {
		return opponentThrow;
	}

	/**
	 * The default throw is a random throw 
	 * @return a throw for the game
	 */
	public char getThrow() {
		Random r = new Random();
		int randomInt = r.nextInt(5);

		if (randomInt == 0)
			return ROCK;
		if (randomInt == 1)
			return PAPER;
		if (randomInt == 2)
			return SCISSORS;
		if (randomInt == 3)
			return LIZARD;
		else
			return SPOCK;

	}

	/**
	 * Allows the storage of the last throw
	 * @param move the last throw by this player
	 */
	public void setLastThrow(char move) {
		lastThrow = move;
	}

	/**
	 * Allows the user to get the last throw
	 * 
	 * @return the last throw
	 */
	public char getLastThrow() {
		return lastThrow;
	}

	/**
	 * Returns the name of the player in a string
	 * @return the name of the Player
	 */
	public String toString() {
		return NAMEOFPLAYER;
	}

	/**
	 * Gets the user to input the number of rounds it would like to be played
	 * this method is only run when both players are automated
	 * @return the number of rounds the user would like to be run
	 */
	public int requestNumRounds() {
		System.out
				.println("How many rounds would you like the computer to play?");

		boolean validInput = false;
		int userIn = 0;
		Scanner myScn = new Scanner(System.in);
		while (validInput == false) {
			if (myScn.hasNextInt()) {
				userIn = myScn.nextInt();
				validInput = true;
			} else {
				System.out.println("That was not a valid input. "
						+ "Please try again");
				myScn.next();
			}
		}
		return userIn;

	}
}
