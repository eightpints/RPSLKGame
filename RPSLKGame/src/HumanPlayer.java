import java.util.Scanner;

/**
 * This is the Human Player class. It is a Player object. 
 * The throws that this class throws are submitted by the user. 
 * 
 * @author Galen Xing gx2113
 *
 */

public class HumanPlayer extends Player {

	//messages of the rules for user input
	private String getThrowMessage = "Human Player, please select your throw by entering its " +
			"corresponding character: ";
	private String throwOptions = "\n [r] \t Rock \n [p] \t Paper \n [s] \t Scissors \n [l] \t Lizard " +
			"\n [k] \t Spock";
	private String badThrow = "That wasn't a valid input, please enter one of the following characters: ";
	private String endGameMessage = "\n\n In order to end the game: \n" +
			" [z] \t End Game";
	private final String NAMEOFPLAYER = "Human Player";
	
	/**
	 * The default constructor
	 */
	public HumanPlayer()
	{
		
	}
	/**
	 * Get a throw input from the user. Checks to make sure that the input is 
	 * one of the possible choices (r,p,s,l,k)
	 * 
	 * @return the throw to be played
	 */
	public char getThrow()
	{
		System.out.println(getThrowMessage);
		System.out.println(throwOptions);
		System.out.println(endGameMessage);
		
		char returnChar = 0;
		boolean validInput = false;
		
		while (validInput == false)
		{
			System.out.println(" ");
			Scanner myScn = new Scanner (System.in);
			returnChar = myScn.next().charAt(0);
			
			if(checkInput (returnChar))
				validInput = true;
			else if (checkInput (returnChar) != true){
				System.out.println(badThrow);
				System.out.println(throwOptions);
				System.out.println(endGameMessage);
			}
			
				
		}
		return returnChar;
	}
	
	/**
	 * Checks the input of the user so that the program doesn't crash
	 * 
	 * @param checkChar the character to be checked
	 * @return true if the charecter is valid, false if it is not
	 */
	public boolean checkInput(char checkChar)
	{
		if (checkChar == 'r')
			return true;
		else if (checkChar == 'p')
			return true;
		else if (checkChar == 's')
			return true;
		else if (checkChar == 'l')
			return true;
		else if (checkChar == 'k')
			return true;
		else if (checkChar == 'z')
			return true;
		else 
			return false;
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
