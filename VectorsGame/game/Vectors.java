package game;

public class Vectors {
	protected int rounds;
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
		this.rounds = rounds;
	}
	
	
	public void run() {
		
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
		
		for(index=0; index < this.board.length; index++) {
			for(index1=0; index1 < this.board[0].length; index1++) {
				System.out.printf("[%c]", this.board[index][index1]);
			}
			System.out.println();
		}
	}
}



