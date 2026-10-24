package game;

import java.util.Scanner;

public class Vectors {
	protected int rounds;
	protected Scanner scan = new Scanner(System.in);
	protected char[][] board = 
			   {{'c',' ',' ',' ',' ',' ',' ',' '},
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
	public void scoring(int count) {
		
	}
	public void horizontal() {
		
	}
	public void vertical() {
		
	}
	public void diagnal() {
		
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



