package game;

import java.util.Scanner;

public class Vectors {
	protected int rounds;
	protected int playerPoints, comPoints;
	protected Scanner scan = new Scanner(System.in);
	protected char[][] board = 
			   {{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '}};
	
	Vectors(){
		this.rounds = 20;
	}
	Vectors(int rounds){
		if(rounds > 32) {  //i think any at 32 rounds the board would be filled
			rounds = 32;   // 8x8 board = 64 tiles  2 tiles fill each round so 32 rounds
		}
		this.rounds = rounds;
	}
	
	
	public void run() {
		show();
		for(this.rounds=this.rounds-1; this.rounds>=0;this.rounds--) {
			playerTurn();
			show();
			System.out.println("      "+this.rounds+" left");
		}
	}
	
	public void playerTurn() {
		boolean flag = true;
		int x=0,y=0;
		while(flag) {
			System.out.print("x: ");
			x = this.scan.nextInt();
			System.out.print("y: ");
			y = this.scan.nextInt();
			if((x>=0 && x<=7)&&(y>=0 && y<=7)) {
				if(!spotCheck(x,y)) {
					flag=false;
				}else {
					System.out.println("That spot is filled");
				}
			}else {
				System.out.println("Make sure both numbers are between 0 and 7");
			}
		}
		this.board[y][x] = 'x';
	}
	
	public boolean spotCheck(int x,int y) {
		boolean filled = false;
		if(this.board[y][x] != ' ') {
			filled = true;
		}
		return filled;
	}
	
	
	//  scoring
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
	
	public int allScore() {// will run horiz vert and diagnal
		int allScore=0;
		char player='x';
		allScore = horizontal(allScore,player);
		allScore = vertical(allScore,player);
		allScore = rightDiagnal(allScore,player);
		allScore = leftDiagnal(allScore,player);
		
		return allScore;
	}
	
	
	public int horizontal(int score,char player) {
		int index=0;
		
		for(index=0;index<this.board.length;index++) {
			score += score(this.board[index],player);
		}
		return score;
	}
	
	
	public int vertical(int score,char player) {
		int index=0,jindex=0;
		char[] temp = new char[8];
		
		for(index=0;index<this.board.length;index++) {
			for(jindex=0;jindex<this.board.length;jindex++) {
				temp[jindex] = this.board[jindex][index];
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
				temp[jindex] = this.board[y+jindex][x+jindex];
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
				temp[jindex] = this.board[y+jindex][x-jindex];
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
	
	//  scoring^
	
	
	public void show() {
		int index,index1;
		System.out.print(" 0  1  2  3  4  5  6  7\n");
		for(index=0; index < this.board.length; index++) {
			for(index1=0; index1 < this.board[0].length; index1++) {
				System.out.printf("[%c]", this.board[index][index1]);
			}
			
			System.out.println(" "+index);
		}
	}
}



