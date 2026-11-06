package game;

import java.io.IOException;

public class Driver {

	public static void main(String[] args) throws IOException {
		
		Vectors game = new Vectors();
		ComputerPlayer hether = new ComputerPlayer();
		game.run();
		
		/**
		
		char[][] happy = 
		       {{' ',' ',' ','c','c','c',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ','x'}, 
				{' ',' ',' ',' ',' ',' ',' ','x'}};
		
		
		
		int[] move = hether.runHH(happy);
		/*
		hether.showHappyBoard();
		System.out.printf("%d %d%n",move[1],move[0]);
		int index;
		for(index=0;index<hether.getHappiest().length;index++) {
			System.out.printf("{%d %d}  ",hether.getHappiest()[index][1],hether.getHappiest()[index][0]);
		}
		*/
		//hether.scoreMove();
		//System.out.printf("%d %d%n",move[1],move[0]);
		//hether.show();
		//hether.scoreMove();
		
		
		
	}

}
