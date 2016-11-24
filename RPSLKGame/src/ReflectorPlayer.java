/**
 * This Player class will throw the same throw that it's opponent threw the previous
 * round
 * 
 * @author Galen Xing gx2113
 *
 */
public class ReflectorPlayer extends Player {

	private char constantThrow;
	private final String NAMEOFPLAYER = "Reflector Player";

	/**
	 * Default constructor. It sets the variable constantThrow to be Scissors. 
	 * This is for the first round when we dont know what the oppenent threw the previous
	 * round.
	 */
	public ReflectorPlayer() {
		constantThrow = 's';
	}

	/**
	 * Returns the throw of the opponent. If there was no previous throw, 
	 * then it will just throw the constant throw
	 * @return object to be thrown for RPSLK
	 * 
	 */
	public char getThrow() {
		char lastThrow = getOpponentThrow();

		if (lastThrow != ' ')
			return lastThrow;
		else
			return constantThrow;
	}

	/**
	 * Returns the name of the class as a String
	 * @return name of the class as a String
	 */
	public String toString() {
		return NAMEOFPLAYER;
	}

}
