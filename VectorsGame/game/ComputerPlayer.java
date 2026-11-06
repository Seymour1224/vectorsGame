package game;

import java.util.Random;

public class ComputerPlayer {


	private Random rand = new Random();
	private char[][] boardCopy;
	private int[][] happyBoard;
	private int[][] happiest = {{0,0}};
	
	
	ComputerPlayer(){}
	
	
	public int[][] getHappiest(){
		return this.happiest;
	}
	
	
	
	
	
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
		scoreMove();
		move = move();
		//System.out.printf("%d  %d's%n",happiest.length,happyBoard[happiest[0][0]][happiest[0][1]]);
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
	
	
	
	public void scoreMove() {
		int yindex,indexx;
		
		for(yindex=0;yindex<this.boardCopy.length;yindex++) {
			//System.out.println("");
			for(indexx=0;indexx<this.boardCopy[yindex].length;indexx++) {
				if(!spotCheck(indexx,yindex,this.boardCopy)) {
					char tempChar = this.boardCopy[yindex][indexx];
					this.boardCopy[yindex][indexx] = 'c';
					
					int score = allScore('c');
					//System.out.printf("The all score is  %d   for (y:x) %d %d%n", score, yindex, indexx);
					if(score>0) {
						score+=4;
					}
					this.happyBoard[yindex][indexx] += score;
					
					this.boardCopy[yindex][indexx] = tempChar;
				}
			}
		}
		
		for(yindex=0;yindex<this.boardCopy.length;yindex++) {
			//System.out.println("");
			for(indexx=0;indexx<this.boardCopy[yindex].length;indexx++) {
				if(!spotCheck(indexx,yindex,this.boardCopy)) {
					char tempChar = this.boardCopy[yindex][indexx];
					this.boardCopy[yindex][indexx] = 'x';
					
					int score = allScore('x');
					//System.out.printf("The all score is  %d   for (y:x) %d %d%n", score, yindex, indexx);
					if(score>0) {
						score+=2;
					}
					this.happyBoard[yindex][indexx] += score;
					
					this.boardCopy[yindex][indexx] = tempChar;
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
					temp[temp.length-1][0] = yindex;
					temp[temp.length-1][1] = indexx;
					this.happiest = temp;
				}
			}
		}
		
		int num = rand.nextInt(this.happiest.length);
		happyMove[0] = this.happiest[num][0];
		happyMove[1] = this.happiest[num][1];
		
		
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
		
		
		System.out.println("\n");
		
		
		System.out.println("Board Happy");
		
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
	
	public void showHappyBoard() {
		int index,yindex;
		
		System.out.println("Board Happy");
		
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
	
	public void showBoardCopy() {
		int index,yindex;
		
		System.out.println("Board Copy");
		
		for(yindex=0;yindex<this.boardCopy.length;yindex++) {
			for(index=0;index<this.boardCopy.length;index++) {
				System.out.printf("[%c]", this.boardCopy[yindex][index]);
			}
			System.out.println("");
		}
	}
	
	
	
	
	public int[] scoring(char[] check, char player, int index) {
		int count=0;
		boolean start=false;
		
		for(index=index+0;index<check.length;index++) {
			if(player == check[index]) {
				count++;
				start=true;
			}else if(start) {
				break;
			}
		}
		int[] feedback = {count,index};
		return feedback;
	}
	
	public int score(char[] check, char player) {
		int index=0,breakCount=0,count=0,score=0;
		int[] back;
		
		while(index!=check.length && breakCount<10) {
			back = scoring(check,player,index);
			count = back[0];
			index = back[1];
			breakCount++;
			if(count==8) {
				score+=14;
			}else if(count>=5) {
				score+=10;
			}else if(count==4) {
				score+=6;
			}else if(count==3) {
				score+=4;
			}
		}
		
		return score;
	}
	
	public int allScore(char player) {
		int allScore=0;
		allScore = horizontal(allScore,player);
		allScore = vertical(allScore,player);
		allScore = rightDiagnal(allScore,player);
		allScore = leftDiagnal(allScore,player);
		
		return allScore;
	}
	
	
	public int horizontal(int score,char player) {
		int index=0;
		
		for(index=0;index<this.boardCopy.length;index++) {
			score += score(this.boardCopy[index],player);
		}
		return score;
	}
	
	
	public int vertical(int score,char player) {
		int index=0,jindex=0;
		char[] temp = new char[8];
		
		for(index=0;index<this.boardCopy.length;index++) {
			for(jindex=0;jindex<this.boardCopy.length;jindex++) {
				temp[jindex] = this.boardCopy[jindex][index];
			}
			score += score(temp,player);
		}
		return score;
	}
	
	
	public int rightDiagnal(int score, char player) {//  NOT DONE  !!!  CHANGE
		int index,jindex,bindex;
		char[] temp = {' ',' ',' ',' ',' ',' ',' ',' '};
		int[] idk = {3,4,5,6,7,8,7,6,5,4,3};
		int x=0,y=5;
		
		
		for(index=0;index<idk.length;index++) {
			for(jindex=0;jindex<idk[index];jindex++) {
				temp[jindex] = this.boardCopy[y+jindex][x+jindex];
			}
			score += score(temp,player);
			for(bindex=0;bindex<temp.length;bindex++) {
				temp[bindex] = ' ';
			}
			if(y>0) {
				y--;
			}else if(y==0) {
				x++;
			}
		}
		return score;
	}
	
	public int leftDiagnal(int score, char player) {
		int index,jindex,bindex;
		char[] temp = {' ',' ',' ',' ',' ',' ',' ',' '};
		int[] idk = {3,4,5,6,7,8,7,6,5,4,3};
		int x=7,y=5;
		
		
		for(index=0;index<idk.length;index++) {
			for(jindex=0;jindex<idk[index];jindex++) {
				temp[jindex] = this.boardCopy[y+jindex][x-jindex];
			}
			score += score(temp,player);
			for(bindex=0;bindex<temp.length;bindex++) {
				temp[bindex] = ' ';
			}
			if(y>0) {
				y--;
			}else if(y==0) {
				x--;
			}
		}
		return score;
	}
	
	
	
	
	
	
	
	
}
