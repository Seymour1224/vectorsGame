package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Vectors {
	protected int rounds, round=0;
	protected int playerPoints=0, comPoints=0;
	protected int highScore;
	protected String name = "Colby";
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
	private ComputerPlayer randy = new ComputerPlayer();
	
	Vectors(){
		this.rounds = 20;
	}
	Vectors(int rounds){
		if(rounds > 32) {  //i think at 32 rounds the board would be filled
			rounds = 32;   // 8x8 board = 64 tiles  2 tiles fill each round so 32 rounds
		}
		this.rounds = rounds;
	}
	
	
	public void run() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("highscore.txt"));
		String temp = br.readLine();
		if(temp == null) {
			this.highScore = 0;
		}else {
			this.highScore = Integer.parseInt(temp);
		}
		br.close();
		show();
		for(this.round=this.round+1; this.rounds>=this.round;this.round++) {
			playerTurn();
			int[] randy = this.randy.randomComp();
			while(spotCheck(randy[1], randy[0])) {
				randy = this.randy.randomComp();
			}
			this.board[randy[0]][randy[1]] = 'c';
			this.playerPoints += allScore('x');
			this.comPoints += allScore('c');
			show();
		}
		if(this.highScore < this.playerPoints-this.comPoints) {
			this.highScore = this.playerPoints-this.comPoints;
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter("highscore.txt"));
		bw.write(this.highScore+"");
		bw.close();
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
	
	public void showRow(char[] row) {
		int index;
		for(index=0; index < row.length; index++) {
			System.out.print("| "+row[index]+" ");
		}
		System.out.print("| ");
	}
	public void show() {
		int index;
		
		System.out.println("*********************************");
		System.out.println("- V E C T E R S\t\t\t*");
		System.out.println("*********************************");
		System.out.printf("- High Score:  %s - %d\t*\n",this.name,this.highScore);
		System.out.println("*********************************");
		System.out.printf("- Player Score: %d\t\t*\n",this.playerPoints);
		System.out.printf("- Computer Score: %d\t\t*\n",this.comPoints);
		System.out.println("*********************************");
		System.out.printf("- Turn %d of %d\t\t\t*\n",this.round,this.rounds);
		System.out.println("*********************************");
		System.out.println("  0   1   2   3   4   5   6   7");
		System.out.println("---------------------------------");
		for(index=0;index<this.board.length;index++) {
			showRow(this.board[index]);
			System.out.printf("%d\n",index);
			System.out.println("---------------------------------");
		}
		
		
	}
}



