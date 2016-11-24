/**
 * This subclass of Player rotates through the possible 
 * throws. It will cycle through the choices before restarting
 * at the top
 * 
 * @author Galen Xing gx2113
 *
 */
public class RotatorPlayer extends Player {

	private int counter;
	private final String NAMEOFPLAYER = "Rotator Player";
	
	/**
	 * Default constructor. Sets the counter for the throw obj to 0
	 */
	public RotatorPlayer() {
		counter = 0;
	}

	/**
	 * Returns a throw to be played in RPSLK. It cycles through the possible choices
	 * based on the counter. Once it has reached the last possible throw, the counter will
	 * reset and start cycling through the possible throws again. 
	 * 
	 * @return the throw to be played
	 */
	public char getThrow() {
		counter++;
		if (counter == 1)
			return ROCK;
		else if (counter == 2)
			return PAPER;
		else if (counter == 3)
			return SCISSORS;
		else if (counter == 4)
			return LIZARD;
		else {
			counter = 0;
			return SPOCK;
		}

	}
	
	/**
	 * returns the name of the class as a String
	 * @return the name of the class
	 * 
	 */
	public String toString ()
	{
		return NAMEOFPLAYER;
	}
}
