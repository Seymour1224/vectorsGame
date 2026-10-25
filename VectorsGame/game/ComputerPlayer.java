package game;

import java.util.Random;

public class ComputerPlayer {

	private int x,y;
	private Random rand = new Random();
	
	public int[] random() {
		int[] ran = {rand.nextInt(8),rand.nextInt(8)};
		return ran;
	}
}
