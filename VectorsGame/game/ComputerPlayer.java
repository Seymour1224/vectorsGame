package game;

import java.util.Random;

public class ComputerPlayer {


	private Random rand = new Random();
	private char[][] boardCopy;
	private int[][] happyBoard;
	private int[] happiest = {0,0};
	
	
	ComputerPlayer(){}
	
	
	
	//         RANDY RANDOM
	
	public int[] randomComp() {
		int[] ran = {rand.nextInt(8),rand.nextInt(8)};
		return ran;
	}
	
	
	
	//         HAPPY HETHER
	
	public int[] runHH(char[][] board) {
		copy(board);
		int[] move = {0,0};
		
		
		return move;
	}
	
	
	
	
	public void copy(char[][] board) {
		int yindex,indexx;
		
		this.boardCopy = new char[board.length][board[0].length];
		
		for(yindex=0; yindex<board.length;yindex++) {
			for(indexx=0; indexx<board[yindex].length;indexx++) {
				this.boardCopy[yindex][indexx] = board[yindex][indexx];
			}
		}
	}
	
	
	
	
	
	//    make the board by searching for filled spots and making them a -1
	//    ignoring all 0's and 7's   check each spot and increase it's happiness but 1 per adjacent filled(-1)spot
	//    also never increase a -1
	
	public void intialHappiness(char[][] board) {
		this.happyBoard = new int[board.length][board[0].length];
		int yindex,indexx;
		
		
		for(yindex=0; yindex<board.length;yindex++) {
			for(indexx=0; indexx<board[yindex].length;indexx++) {
				if(spotCheck(indexx,yindex,board)) {
					this.happyBoard[yindex][indexx] = -1;
				}
			}
		}
		
		
	}
	
	public boolean spotCheck(int x,int y, char[][] board) {
		boolean filled = false;
		if(board[y][x] != ' ') {
			filled = true;
		}
		return filled;
	}
	
	public void show() {
		int index,yindex;
		
		System.out.println("Board Copy");
		
		for(yindex=0;yindex<this.boardCopy.length;yindex++) {
			for(index=0;index<this.boardCopy.length;index++) {
				System.out.printf("[%c]", this.boardCopy[yindex][index]);
			}
			System.out.println("");
		}
		
		System.out.println("\n\n\n\n\nBoard Happy");
		
		for(yindex=0;yindex<this.boardCopy.length;yindex++) {
			for(index=0;index<this.boardCopy.length;index++) {
				System.out.printf("[%d]", this.happyBoard[yindex][index]);
			}
			System.out.println("");
		}
	}
	
	
	
	
	
	
	
	
}
