package game;

import java.util.Scanner;
import java.io.IOException;

public class Main {
	
	public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
	
	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		System.out.print("컴퓨터와 하시려면 1 둘이서 하시려면 2를 눌러주세요 : ");
		int play = sc.nextInt();
		System.out.print("몇 줄짜리 빙고를 하시겠습니까?(추천 : 4~7) : ");
		int line = sc.nextInt();

		Game games = new Game(play, line);

		boolean playGame = true;
		int select = 0;
		int bingo1 = 0;
		int bingo2 = 0;

		while (playGame) {
			games.getP1().MakingBoard();
			games.getP2().MakingBoard();

			do {
				System.out.println();
				games.getP1().PrintBoard();
				System.out.println("1P bingoline = " + games.getP1().PBB.checkBingo());
				System.out.println("-------------------");
				games.getP2().PrintBoard();
				System.out.println("2P bingoline = " + games.getP2().PBB.checkBingo());

				System.out.print("지우실 숫자를 입력해주세요 : ");
				select = sc.nextInt();
				games.getP1().setSelect(select);
				games.getP2().setSelect(select);

				if (play == 1) {
					select = games.getP2().setSelect();
					games.getP1().setSelect(select);
					System.out.print("컴퓨터가 생각하는 중");
					Thread.sleep(500);
					System.out.print(".");
					Thread.sleep(500);
					System.out.print(".");
					Thread.sleep(500);
					System.out.print(".");
					Thread.sleep(500);
				} else {
					System.out.print("지우실 숫자를 입력해주세요 : ");
					select = sc.nextInt();
					games.getP1().setSelect(select);
					games.getP2().setSelect(select);
				}

				bingo1 = games.getP1().PBB.checkBingo();
				bingo2 = games.getP2().PBB.checkBingo();
				clearScreen();
			} while (bingo1 < line && bingo2 < line);
			
			if(bingo1 == line && bingo2 == line)
				System.out.println("무승부");
			else if(bingo1 == line)
				System.out.println("1P 승리");
			else if(bingo2 == line)
				System.out.println("2P 승리");

			System.out.print("게임을 끝내시려면 1, 더 하시려면 2를 눌러주세요.");
			select = sc.nextInt();
			playGame = games.endRetry(select);
			bingo1 = 0;
			bingo2 = 0;
		}
		sc.close();
	}
}
