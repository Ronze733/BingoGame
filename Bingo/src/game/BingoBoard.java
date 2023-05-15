package game;

import java.util.Random;

public class BingoBoard {
	private int line;
	private int[][] board;

	public BingoBoard() {
		// TODO Auto-generated constructor stub
	}

	public int[][] getBoard() {
		return board;
	}

	public BingoBoard(int line) {
		this.line = line;
	}

	public void MakingBoard() {
		board = new int[line][line];
		for (int i = 0; i < this.line; i++)
			for (int j = 0; j < this.line; j++)
				this.board[i][j] = line * i + j + 1;

		Random rand = new Random();
		for (int i = 0; i < line * line * 1000; i++) {
			int insert1 = rand.nextInt(line * line);
			int insert2;
			do {
				insert2 = rand.nextInt(line * line);
			} while (insert1 == insert2);
			int temp = this.board[insert1 / line][insert1 % line];
			this.board[insert1 / line][insert1 % line] = this.board[insert2 / line][insert2 % line];
			this.board[insert2 / line][insert2 % line] = temp;
		}
	}

	public void PrintBoard() {
		for (int i = 0; i < this.line; i++) {
			for (int j = 0; j < this.line; j++)
				System.out.printf("%3d", this.board[i][j]);
			System.out.println();
		}
	}

	public void changeBoard(int num) {
		aa: for (int i = 0; i < line; i++)
			for (int j = 0; j < line; j++)
				if (this.board[i][j] == num) {
					this.board[i][j] = 0;
					break aa;
				}
	}

	public int checkBingo() {
		int cntC = 0;
		int cntCi = 0;
		int cntR = 0;
		int cntRi = 0;
		int cntRC = 0;
		int cntLC = 0;

		int bingo = 0;
		
		for(int i = 0; i < this.line; i++) {
			for(int j = 0; j < this.line; j++) {
				if(this.board[i][j] == 0)
					cntCi++;
			}
			if(cntCi == this.line)
				cntC++;
			cntCi = 0;
		}
		
		for(int i = 0; i < this.line; i++) {
			for(int j = 0; j < this.line; j++) {
				if(this.board[j][i] == 0)
					cntRi++;
			}
			if(cntRi == this.line)
				cntR++;
			cntRi = 0;
		}
		
		for(int i = 0; i < this.line; i++) {
			if(this.board[i][i] == 0)
				cntRC++;
		}
		
		cntRC /= this.line;
		
		for(int i = 0; i < this.line; i++) {
			if(this.board[this.line - i - 1][i] == 0)
				cntLC++;
		}
		
		cntLC /= this.line;
		
		bingo = cntC + cntR + cntRC + cntLC;
		return bingo;
	}

}
