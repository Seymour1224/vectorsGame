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
				{' ',' ',' ','g',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ','h',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '}, 
				{' ',' ',' ',' ',' ',' ',' ','c'}};
		
		int[] move = hether.runHH(happy);
		hether.show();
		System.out.printf("%d %d",move[1],move[0]);
		
		
	}

}
