package game;

import java.io.IOException;

public class Driver {

	public static void main(String[] args) throws IOException {
		
		Vectors game = new Vectors(5);
		ComputerPlayer hether = new ComputerPlayer();
		//game.run();
		char[][] happy = 
		       {{' ',' ',' ','h',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '}, 
				{' ',' ',' ',' ',' ',' ',' ',' '}};
		
		
		
		int[] move = hether.runHH(happy);
		/*
		hether.showHappyBoard();
		System.out.printf("%d %d%n",move[1],move[0]);
		int index;
		for(index=0;index<hether.getHappiest().length;index++) {
			System.out.printf("{%d %d}  ",hether.getHappiest()[index][1],hether.getHappiest()[index][0]);
		}
		*/
		hether.scoreMove();
		hether.show();
		
		
		
		
	}

}
