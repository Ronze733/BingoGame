package game;

import java.util.Random;

public class Computer extends Player {
	
	public Computer(int line) {
		this.PBB = new BingoBoard(line);
	}
	

	public int setSelect() {
		int[][] board = PBB.getBoard();
		Random rand = new Random();
		int select = 0;
		int cnt = 0;
		do {
			select = rand.nextInt(board.length * board.length) + 1;
			for(int i = 0; i < board.length; i++)
				for(int j = 0; j < board.length; j++)
					if(board[i][j] == select)
						cnt++;
		} while (cnt == 0);
		PBB.changeBoard(select);
		
		return select;
	}
}
