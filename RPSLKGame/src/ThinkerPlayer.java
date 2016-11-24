import java.util.Random;

/**
 * This is the Thinker class. 
 * It saves the number of times each object is thrown by its oppenent. 
 * Then, based on the ratio of each object being thrown, this class will
 * throw the objects that beat the oppenent's throws at a higher frequency. 
 * Thus if it is identified that its opponent throws Rock a lot, then Paper and 
 * Spock will be thrown at higher frequencies. However, there is still an element 
 * of randomness so that this method does not fall into the "Gambler's Fallacy"
 * 
 * @author Galen Xing gx2113
 *
 */
public class ThinkerPlayer extends Player {

	private final String NAMEOFPLAYER = "Thinker Player";
	private char lastThrow = ' ', opponentThrow = ' ';
	private double numR, numP, numS, numL, numK, totalThrows = 0;
	private double rLean, pLean, sLean, lLean, kLean;
	private double rCutoff, pCutoff, sCutoff, lCutoff, kCutoff;

	/**
	 * The default constructor sets all the initial variables to 0;
	 */
	public ThinkerPlayer() {
		numR = 0;
		numP = 0;
		numS = 0;
		numL = 0;
		numK = 0;
	}

	/**
	 * Returns a throw that is more likely to win based on the opponent's 
	 * previous throws;
	 * 
	 * @return the throw to be played
	 */
	public char getThrow() {
		Random rand = new Random();
		double throwNum = rand.nextDouble() * 2;

		if (throwNum < rCutoff)
			return ROCK;
		else if (throwNum < pCutoff)
			return PAPER;
		else if (throwNum < sCutoff)
			return SCISSORS;
		else if (throwNum < lCutoff)
			return LIZARD;
		else
			return SPOCK;

	}

	/**
	 * Allows the last throw by the ThinkerPlayer to be stored
	 * @param move throw to be stored
	 */
	public void setLastThrow(char move) {
		lastThrow = move;

	}

	/**
	 * Stores the Opponent's last throw.
	 * Once this is done, the the updateStats()	method is called which will update
	 * the frequencies for each throw to be thrown at. 
	 * 
	 * @param move the throw to be stored
	 */
	public void setOpponentThrow(char move) {
		opponentThrow = move;
		totalThrows++;
		updateStats(move);
	}

	/**
	 * Updates the frequencies for which the throws are to be thrown at.
	 * This is based on what throws the opponent has been throwing. This will allow 
	 * this class to throw the ones that have a higher chance of beating the opponent
	 * at a higher frequency than the throws that have a lower chance of beating 
	 * the opponent.
	 * @param move the move that the opponent threw last round
	 */
	private void updateStats(char move) {
		if (move == ROCK) {
			numR++;
		} else if (move == PAPER) {
			numP++;

		} else if (move == SCISSORS) {
			numS++;
		} else if (move == LIZARD) {
			numL++;
		} else if (move == SPOCK) {
			numK++;
		}

		rLean = (numS / totalThrows) + (numL / totalThrows);
		pLean = (numR / totalThrows) + (numK / totalThrows);
		sLean = (numP / totalThrows) + (numL / totalThrows);
		lLean = (numP / totalThrows) + (numK / totalThrows);
		kLean = (numR / totalThrows) + (numS / totalThrows);

		rCutoff = rLean;
		pCutoff = rLean + pLean;
		sCutoff = rLean + pLean + sLean;
		lCutoff = rLean + pLean + sLean + lLean;
		kCutoff = rLean + pLean + sLean + lLean + kLean;

	}

	/**
	 * Returns the name of the class as a String
	 * @return the name of the class 
	 */
	public String toString() {
		return NAMEOFPLAYER;
	}

}
