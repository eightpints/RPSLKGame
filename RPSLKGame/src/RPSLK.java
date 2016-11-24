import java.util.Scanner;

/**
 * RPSLK this is the game object. It is the backbone for this entire project.
 * Input from the user is split between this class and the Player classes.
 * 
 * @author Galen Xing gx2113
 * 
 */
public class RPSLK {
	/**
	 * Player object cannot be null for program to run
	 */
	private Player player1;
	/**
	 * Object cannot be null for program to run
	 */
	private Player player2;
	/**
	 * Welcome message that introduces the game to the user
	 */
	private String welcomeMessage = "Welcome to Rock Paper Scissors Lizard "
			+ "Spock! \n\nThis game is very similar to standard Rock, Paper, Scissors \n"
			+ "but it has an added bonus: Lizard and Spock! Thus Rock crushes Scissors \ncuts "
			+ "Paper covers Rock crushes Lizard poisons Spock smashes Scissors \n"
			+ "decapitates Lizard eats Paper disproves Spock vaporizes Rock. ";

	/**
	 * String that tells the user how to select Player 1
	 */
	private String selectPlayer1 = "First, select Player 1 by entering its corresponding number:";
	/**
	 * String that tells the user how to select Player 2
	 */
	private String selectPlayer2 = "Now, select Player 2 by entering its corresponding number:";
	/**
	 * String that tells the user the Player Options
	 */
	private String playerSelections = "\n [1] \t Human \n [2] \t Repeater"
			+ "\n [3] \t Rotater \n [4] \t Reflector"
			+ "\n [5] \t Randomizer \n [6] \t Mixer \n [7] \t Thinker";
	/**
	 * String that will provide the reason one throw beat another ie. Paper
	 * "covers" Rock
	 */
	private String reason;

	/**
	 * String that holds the name of Player 1's type of player (ie. Random,
	 * Rotator, Reflector)
	 */
	private String p1Name;

	/**
	 * String that holds the name of Player 2's type of player (ie. Random,
	 * Rotator, Reflector)
	 */
	private String p2Name;

	/**
	 * String that hold the message displayed in the case of a tie
	 */
	private String resultTieMessage = "It was a tie!";
	/**
	 * Holds the number of times player 1 has won
	 */
	private int player1Wins;
	/**
	 * Holds the number of times player 2 has won
	 */
	private int player2Wins;
	/**
	 * Holds the number of rounds played
	 */
	private int roundsPlayed;
	/**
	 * Holds the number of rounds the player wants the computer to play against
	 * itself Only used if both Players are automated
	 */
	private int roundsRequested;
	/**
	 * If player1 is a MixerPlayer object, then this holds how often it wants to
	 * change the type of thrower
	 */
	private int p1MixerN;
	/**
	 * If player2 is a MixerPlayer object, then this holds how often it wants to
	 * change the type of thrower
	 */
	private int p2MixerN;
	/**
	 * Counts the number of times a particular thrower has gone when the
	 * MixerPlayer is selected for player 1.
	 */
	private int p1MixerCounter;
	/**
	 * Counts the number of times a particular thrower has gone when the
	 * MixerPlayer is selected for player 1.
	 */
	private int p2MixerCounter;
	/**
	 * Counts for which thrower to rotate to next when a MixerPlayer is selected
	 * for player 1
	 */
	private int p1RotationCounter;
	/**
	 * Counts for which thrower to rotate to next when a MixerPlayer is selected
	 * for player 2
	 */
	private int p2RotationCounter;
	/**
	 * When its true, the game will keep going until user types 'z' to stop the
	 * game
	 */
	private boolean keepPlaying;
	/**
	 * If player 1 is a MixerPlayer
	 */
	private boolean p1IsMixer;
	/**
	 * If player 2 is a MixerPlayer
	 */
	private boolean p2IsMixer;
	/**
	 * Holds player 1's move
	 */
	private char player1Move;
	/**
	 * Holds player 2's move
	 */
	private char player2Move;

	/**
	 * For readability and functionality, all the throws are denoted as Constant
	 * with their value being the first lettter of the throw except for in the
	 * case of spock which is 'k'
	 */
	public final char ROCK = 'r', PAPER = 'p', SCISSORS = 's', LIZARD = 'l',
			SPOCK = 'k', STOP = 'z';

	private HumanPlayer humanThrower;
	private RepeaterPlayer repeaterThrower;
	private RotatorPlayer rotatorThrower;
	private ReflectorPlayer reflectorThrower;
	private RandomPlayer randomThrower;
	private MixerPlayer mixerThrower;
	private ThinkerPlayer thinkerThrower;

	/**
	 * The default constructor
	 */
	public RPSLK() {
	}

	/**
	 * This constructor allows a tester class to specify exactly what type of
	 * Player it wants
	 * 
	 * @param playerOne
	 *            The Player that will be first
	 * @param playerTwo
	 *            The Player that will be second
	 */
	public RPSLK(Player playerOne, Player playerTwo) {
		player1 = playerOne;
		player2 = playerTwo;
	}

	/**
	 * Starts the whole game. It contains a while loop that will loop through
	 * until either the user presses 'z' during a Human vs. Automated match or
	 * when the number of rounds the user specified has occured.
	 * 
	 */
	public void start() {
		player1Wins = 0;
		player2Wins = 0;
		roundsPlayed = 0;
		p1MixerCounter = 0;
		p2MixerCounter = 0;
		keepPlaying = true;
		p1IsMixer = false;
		p2IsMixer = false;
		p1RotationCounter = 0;
		p2RotationCounter = 0;

		// allows the user to pick a player
		pickPlayers();

		// gets those player's names
		p1Name = player1.toString();
		p2Name = player2.toString();

		// if both players are computers, then it will ask for the number of
		// rounds you want to play
		if (bothComputer())
			roundsRequested = player1.requestNumRounds();

		// while this is true, the game will continue
		while (keepPlaying == true) {
			// gets each players throws and also performs the necessary get and
			// set
			// methods
			player1Move = player1.getThrow();
			player1.setLastThrow(player1Move);
			player2Move = player2.getThrow();
			player2.setLastThrow(player2Move);
			player1.setOpponentThrow(player2Move);
			player2.setOpponentThrow(player1Move);
			roundsPlayed++;
			p1MixerCounter++;
			p2MixerCounter++;
			reason = checkIfBeat(player1Move, player2Move);

			// if either player entered the stop char, then the game will stop
			if ((player1Move == STOP) || (player2Move == STOP)) {
				keepPlaying = false;
				// or if there is a tie, then a tie will be called and displayed
			} else if (player1Move == player2Move) {
				System.out.println(resultTieMessage);
				// if the player 1 won, then display the message
			} else if (reason != null) {
				p1WinMessage();
				player1Wins++;
				// if player 1 didnt win, then player 2 must have, therefore,
				// display message
			} else {
				reason = checkIfBeat(player2Move, player1Move);
				p2WinMessage();
				player2Wins++;
			}

			// if either is a MixerPlayer, then count the times it
			// wants to mix
			if (p1IsMixer || p2IsMixer) {
				if (p1MixerN == p1MixerCounter) {
					mixPlayers(1);
					p1MixerCounter = 0;
					p1RotationCounter++;
				} else if (p2MixerN == p2MixerCounter) {
					mixPlayers(2);
					p2MixerCounter = 0;
					p2RotationCounter++;
				}
			}
			// if the number of rounds played is the number the user wanted
			// then stop the game
			if (roundsPlayed == roundsRequested) {
				keepPlaying = false;
			}

			// allow the user to keep track of the number of rounds played
			System.out.println("Rounds Played: " + roundsPlayed);
			System.out.println("");
		}
		// once its out of the while loop, end the game
		endGame();
	}


	/**
	 * set the player1 variable
	 * 
	 * @param playerOne
	 *            the Player object for the player1 variable to reference
	 */
	public void addPlayer1(Player playerOne) {
		player1 = playerOne;
	}

	/**
	 * set the player2 variable 
	 * @param playerTwo the Player object for the player1 variable to reference
	 */
	public void addPlayer2(Player playerTwo) {
		player2 = playerTwo;
	}

	/**
	 * Gets the throw for player1
	 */
	public void getPlayer1Throw() {
		player1.getThrow();
	}

	/**
	 * Gets the throw for player 2
	 */
	public void getPlayer2Throw() {
		player2.getThrow();
	}
	/**
	 * When there is a MixerPlayer once the specified number of rounds (N) has
	 * gone by, switch the type of Player (thrower) that the player is. It then
	 * passes the rotation necessary to either rotatePlayer1() or
	 * rotatePlayer2() to change the player
	 * 
	 * @param playerNumber
	 *            Either 1 or 2. Denotes whether it is player1 or player2 that
	 *            is switching players
	 */
	private void mixPlayers(int playerNumber) {
		if (playerNumber == 1) {
			rotatePlayer1(p1RotationCounter);
		} else if (playerNumber == 2) {
			rotatePlayer2(p2RotationCounter);
		}
	}

	/**
	 * Called when its time to rotate player1
	 * 
	 * @param rotationCount
	 *            The player to rotate to (0 - randomThrower, 1 - rotatorThrower
	 *            2 - reflectorThrower, 3 - repeaterThrower)
	 */
	private void rotatePlayer1(int rotationCount) {
		if (rotationCount == 0)
			addPlayer1(randomThrower);
		else if (rotationCount == 1)
			addPlayer1(rotatorThrower);
		else if (rotationCount == 2)
			addPlayer1(reflectorThrower);
		else if (rotationCount == 3) {
			addPlayer1(repeaterThrower);
			p1RotationCounter = 0;
		}
	}

	/**
	 * Called when its time to rotate player2
	 * 
	 * @param rotationCount
	 *            The player to rotate to (0 - randomThrower, 1 - rotatorThrower
	 *            2 - reflectorThrower, 3 - repeaterThrower)
	 */
	private void rotatePlayer2(int rotationCount) {
		if (rotationCount == 0)
			addPlayer2(randomThrower);
		else if (rotationCount == 1)
			addPlayer2(rotatorThrower);
		else if (rotationCount == 2)
			addPlayer2(reflectorThrower);
		else if (rotationCount == 3) {
			addPlayer2(repeaterThrower);
			p2RotationCounter = 0;
		}
	}

	/**
	 * Gets the number of rounds that the user wants to occur before having the
	 * MixerPlayer switch Players
	 * 
	 * @return how many rounds the user wants before having the Mixer switch
	 */
	private int requestMixerN() {
		System.out.println("You chose a Mixer!\n"
				+ "How often would you like the players to be Mixed?");
		boolean validInput = false;
		boolean inRange = false;
		int userIn = 0;
		Scanner myScn = new Scanner(System.in);
		while ((validInput == false) && (inRange == false)) {
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

	/**
	 * Displays a message if player 1 wins
	 */
	private void p1WinMessage() {
		System.out.println("Player 1 (" + player1.toString() + ")" + " wins! "
				+ nameOfMove(player1Move) + " " + reason + " "
				+ nameOfMove(player2Move) + "!");
	}

	/**
	 * Displays a message if player 2 wins
	 */
	private void p2WinMessage() {
		System.out.println("Player 2 (" + player2.toString() + ") wins! "
				+ nameOfMove(player2Move) + " " + reason + " "
				+ nameOfMove(player1Move) + "!");

	}

	/**
	 * Checks to see if both players are automated
	 * 
	 * @return true if both players are automated, false if one or both are not
	 *         automated
	 */
	public boolean bothComputer() {
		boolean isComputer = true;
		if (p1Name == "Human Player")
			isComputer = false;
		else if (p2Name == "Human Player")
			isComputer = false;

		return isComputer;
	}

	/**
	 * Ends the game. But before it does, it prints out: Player 1 wins, player 2
	 * wins, player 1 win percentage, player 2 win percentage, and percentage of
	 * ties.
	 */
	public void endGame() {
		int numTies = roundsPlayed - (player1Wins + player2Wins);

		double p1WinPercentage = (double) player1Wins / roundsPlayed * 100;
		double p2WinPercentage = (double) player2Wins / roundsPlayed * 100;
		double tiePercentage = (double) numTies / roundsPlayed * 100;

		System.out.println("Player 1 (" + p1Name + ")" + " won " + player1Wins
				+ " rounds while Player 2 (" + p2Name + ") won " + player2Wins
				+ " rounds.");
		System.out.println("Number of Ties: " + numTies);
		System.out.println("Player 1 Win Percentage: " + p1WinPercentage);
		System.out.println("Player 2 Win Percentage: " + p2WinPercentage);
		System.out.println("Tie Percentage: " + tiePercentage);
	}

	/**
	 * Displays the introduction messages that talks about the game rules
	 */
	public void outputIntroMessages() {
		System.out.println(welcomeMessage);
		System.out.println();

	}

	/**
	 * Displays messages teaching user how to select player 1
	 */
	public void outputPlayer1Selection() {
		System.out.println(selectPlayer1);
		System.out.println(playerSelections);
	}

	/**
	 * Displays messages teaching user how to select player 2
	 */
	public void outputPlayer2Selection() {
		System.out.println(selectPlayer2);
		System.out.println(playerSelections);
	}

	/**
	 * Converts the char a move is to a string for output purposes
	 * 
	 * @param throwChar
	 *            the char of the move that was thrown
	 * @return String name of throw
	 */
	private String nameOfMove(char throwChar) {
		if (throwChar == 'r')
			return "Rock";
		else if (throwChar == 'p')
			return "Paper";
		else if (throwChar == 's')
			return "Scissors";
		else if (throwChar == 'l')
			return "Lizard";
		else
			return "Spock";
	}

	/**
	 * Checks to see if one player beat another
	 * 
	 * @param player1Move
	 *            move to check if it beats
	 * @param player2Move
	 *            move to be checked if its beaten
	 * @return the reason player1Move beat player2Move This method was inspired
	 *         by user baavgai on
	 *         http://www.dreamincode.net/forums/topic/222021-
	 *         rock-paper-scissors-lizard-spock/
	 */
	private String checkIfBeat(char player1Move, char player2Move) {
		if (player1Move == ROCK) {
			if (player2Move == SCISSORS) {
				return "crushes";
			}
			if (player2Move == LIZARD) {
				return "crushes";
			}
		} else if (player1Move == PAPER) {
			if (player2Move == ROCK) {
				return "covers";
			}
			if (player2Move == SPOCK) {
				return "disproves";
			}
		} else if (player1Move == SCISSORS) {
			if (player2Move == PAPER) {
				return "cut";
			}
			if (player2Move == LIZARD) {
				return "decapitate";
			}
		} else if (player1Move == LIZARD) {
			if (player2Move == SPOCK) {
				return "poisons";
			}
			if (player2Move == PAPER) {
				return "eats";
			}
		} else if (player1Move == SPOCK) {
			if (player2Move == SCISSORS) {
				return "smashes";
			}
			if (player2Move == ROCK) {
				return "vaporizes";
			}
		}
		return null;
	}

	/**
	 * Gets the user to pick out its player First it displays the instructions,
	 * then gets the user to select a player once a player is selected, that
	 * Player object is added as a player
	 */
	public void pickPlayers() {
		outputIntroMessages();
		outputPlayer1Selection();
		System.out.println("");

		humanThrower = new HumanPlayer();
		repeaterThrower = new RepeaterPlayer();
		rotatorThrower = new RotatorPlayer();
		reflectorThrower = new ReflectorPlayer();
		randomThrower = new RandomPlayer();
		mixerThrower = new MixerPlayer();
		thinkerThrower = new ThinkerPlayer();

		int playerOne = getPlayer();

		if (playerOne == 1)
			addPlayer1(humanThrower);
		else if (playerOne == 2)
			addPlayer1(repeaterThrower);
		else if (playerOne == 3)
			addPlayer1(rotatorThrower);
		else if (playerOne == 4)
			addPlayer1(reflectorThrower);
		else if (playerOne == 5)
			addPlayer1(randomThrower);
		else if (playerOne == 6) {
			addPlayer1(mixerThrower);
			p1MixerN = requestMixerN();
			p1IsMixer = true;
		} else if (playerOne == 7) {
			addPlayer1(thinkerThrower);
		}
		outputPlayer2Selection();

		int playerTwo = getPlayer();
		if (playerTwo == 1)
			addPlayer2(humanThrower);
		else if (playerTwo == 2)
			addPlayer2(repeaterThrower);
		else if (playerTwo == 3)
			addPlayer2(rotatorThrower);
		else if (playerTwo == 4)
			addPlayer2(reflectorThrower);
		else if (playerTwo == 5)
			addPlayer2(randomThrower);
		else if (playerTwo == 6) {
			addPlayer2(mixerThrower);
			p2MixerN = requestMixerN();
			p2IsMixer = true;
		} else if (playerTwo == 7) {
			addPlayer2(thinkerThrower);
		}
	}

	/**
	 * Allows the user to select a Player Checks to make sure that the input is
	 * a number
	 * 
	 * @return the int of the selection of the player
	 */
	public int getPlayer() {
		boolean validInput = false;
		boolean inRange = false;
		int userIn = 0;
		Scanner myScn = new Scanner(System.in);
		while ((validInput == false) && (inRange == false)) {
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
