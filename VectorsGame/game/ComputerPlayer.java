package game;

import java.util.Random;

public class ComputerPlayer {


	private Random rand = new Random();
	private char[][] boardCopy;
	private int[][] happyBoard;
	private int[][] happiest = {{0,0}};
	
	
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
		intialHappiness(board);
		move = move();
		System.out.printf("%d  %d's%n",happiest.length,happyBoard[happiest[0][0]][happiest[0][1]]);
		
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
		mineSweeper(this.happyBoard);
		bestSquares();
		
	} 
	 
	
	
	
	public void bestSquares() {
		int[][] best = 
			   {{1,1},{2,2},{3,3},{4,4},{5,5},{6,6},
				{6,1},{5,2},{4,3},{3,4},{2,5},{1,6},
				{3,3},{4,4},{4,3},{3,4}};
		int index;
		
		for(index=0;index<best.length;index++) {
			if(this.happyBoard[best[index][0]][best[index][1]] !=-1) {
				this.happyBoard[best[index][0]][best[index][1]] += 1;
			}
		}
		
	}
	
	
	
	
	public void mineSweeper(int[][] board) {
		int indexx,yindex;
		int happy=2;
		
		for(indexx=1;indexx<board.length-1;indexx++) {
			for(yindex=1;yindex<board[indexx].length-1;yindex++) {
				
				
				if(board[yindex][indexx] != -1) {
					if(board[yindex][indexx+1] == -1) {
						board[yindex][indexx] += happy;
					}
					if(board[yindex][indexx-1] == -1) {
						board[yindex][indexx] += happy;
					}
					if(board[yindex+1][indexx] == -1) {
						board[yindex][indexx] += happy;
					}
					if(board[yindex-1][indexx] == -1) {
						board[yindex][indexx] += happy;
					}
					if(board[yindex+1][indexx+1] == -1) {
						board[yindex][indexx] += happy;
					}
					if(board[yindex-1][indexx-1] == -1) {
						board[yindex][indexx] += happy;
					}
					if(board[yindex+1][indexx-1] == -1) {
						board[yindex][indexx] += happy;
					}
					if(board[yindex-1][indexx+1] == -1) {
						board[yindex][indexx] += happy;
					}
				}
			}
		}
	}
	
	
	
	
	///   HAPPY MOVE
	
	
	public int[] move() {
		int[] happyMove = {0,0};
		int mostHappy = 0;
		int indexx,yindex,index;
		
		for(yindex=0;yindex<this.happyBoard.length;yindex++) {
			for(indexx=0;indexx<this.happyBoard[yindex].length;indexx++) {
				if(this.happyBoard[yindex][indexx] > mostHappy) {
					mostHappy = this.happyBoard[yindex][indexx];
					int[][] temp = {{yindex,indexx}};
					this.happiest = temp;
				}else if(this.happyBoard[yindex][indexx] == mostHappy){
					int[][] temp = new int[this.happiest.length+1][2];
					for(index=0;index<this.happiest.length;index++) {
						temp[index][0] = this.happiest[index][0];
						temp[index][1] = this.happiest[index][1];
					}
					this.happiest = temp;
				}
			}
		}
		
		int num = rand.nextInt(this.happiest.length);
		happyMove[0] = this.happiest[num][0];
		happyMove[1] = this.happiest[num][1];     //TEST THIS
		
		
		return happyMove;
	}
	
	
	
	///  ^^^^^^^
	
	
	
	
	
	
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
				if(this.happyBoard[yindex][index] == -1) {
					System.out.printf("{-}");
				}else if(this.happyBoard[yindex][index]==0) {
					System.out.printf(" %d ",this.happyBoard[yindex][index]);
				}else {
					System.out.printf("[%d]", this.happyBoard[yindex][index]);
				}
				
			}
			System.out.println("");
		}
	}
	
	
	
	
	
	
	
	
}
