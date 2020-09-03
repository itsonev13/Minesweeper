package Minepack;

import java.util.Scanner;

public class GameTest {
	static GUI gui = null;

	public static void main(String[] args) {
		int choise = -1;

		Board b = null;
		Board bWithBombs = null;
		Scanner choiseScan = new Scanner(System.in);
		boolean alive = true;
		String coordinates;
		int rowSelection, columnSelection;
		Scanner coordinatesScan = new Scanner(System.in);
		do {
			System.out.println(
					"Enter the Difficulty Level\nPress 0 for BEGINNER (9*9 Cells and 10 Mines)\nPress 1 for INTERMEDIATE (16*16 Cells and 40 Mines)\nPress 2 for ADVANCED (24*24 Cells and 99Mines)");
			choise = choiseScan.nextInt();
			if (!(choise >= 0 && choise <= 2)) {
				System.out.println("Invalid choise.Please try again ");
			}
		} while (choise > 2 || choise < 0);

		switch (choise) {
		case 0:
			b = new Board(9, 9);
			gui = new GUI(750, 829);
			gui.setBoard(b);
			break;
		case 1:
			b = new Board(16, 16);
			gui = new GUI(750, 829);
			gui.setBoard(b);
			break;
		case 2:
			b = new Board(24, 24);
			gui = new GUI(980, 1050);
			gui.setBoard(b);
			break;
		}
	}
}
