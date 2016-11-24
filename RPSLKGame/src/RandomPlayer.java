import java.util.Random;
/**
 * This Player throws random hands
 * 
 * @author Galen Xing gx2113
 *
 */
public class RandomPlayer extends Player {

	/**
	 * The name of the player in string
	 */
	private final String NAMEOFPLAYER = "Random Player";

	/**
	 * Default constructor
	 */
	public RandomPlayer() {

	}

	/**
	 * Returns a random throw
	 * 
	 * @return the throw
	 */
	public char getThrow() {
		Random r = new Random();
		int randomInt = r.nextInt(5);

		if (randomInt == 0)
			return ROCK;
		else if (randomInt == 1)
			return PAPER;
		else if (randomInt == 2)
			return SCISSORS;
		else if (randomInt == 3)
			return LIZARD;
		else
			return SPOCK;

	}

	/**
	 * Returns the name of the Player in a String
	 * @return the name of the Playing in a String
	 */
	public String toString() {
		return NAMEOFPLAYER;
	}

}
