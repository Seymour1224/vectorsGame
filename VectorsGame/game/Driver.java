package game;

import java.io.IOException;

public class Driver {

	public static void main(String[] args) throws IOException {
		
		Vectors game = new Vectors(5);
		ComputerPlayer hether = new ComputerPlayer();
		//game.run();
		char[][] happy = 
		       {{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ','x','c',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '}, 
				{' ',' ',' ',' ',' ',' ',' ',' '}};
		
		hether.copy(happy);
		hether.intialHappiness(happy);
		hether.show();
		
		
	}

}
