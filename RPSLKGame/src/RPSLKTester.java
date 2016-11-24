/**
 * This class runs the Game. 
 * It creates an object of RPSLK and then starts the game 
 * by running the method start();
 * 
 * @author Galen Xing gx2113
 *
 */
public class RPSLKTester {

	/**
	 * Main method. This is the method that is run when the program is run
	 * It creates an RPSLK object and then runs the method start()
	 * @param args arguments acessed from cmd
	 */
	public static void main(String[] args) {
		RPSLK game = new RPSLK();
		game.start();
	}

}
