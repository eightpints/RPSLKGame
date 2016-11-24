/**
 * This is a subclass of Player. 
 * It's throws are the same each time. The specific throw
 * has been chosen by the programmer and can be changed by 
 * redefining the constant THROW
 * 
 * @author Galen Xing gx2113
 *
 */
public class RepeaterPlayer extends Player{
	
	private final char THROW = LIZARD;
	private final  String NAMEOFPLAYER = "Repeater Player";

	/**
	 * Default constructor
	 */
	public void RepeaterPlayer()
	{
		
	}
	
	/**
	 * returns the same throw each time
	 * 
	 * @return the throw to be played
	 */
	public char getThrow(){
		return THROW;
	}
	
	/**
	 * returns the name of the class as a String
	 * @return the name of the class
	 * 
	 */
	public String toString(){
		return NAMEOFPLAYER;
	}
}
