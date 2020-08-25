package Minepack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GameTest {
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
			break;
		case 1:
			b = new Board(16, 16);
			break;
		case 2:
			b = new Board(24, 24);
			break;
		}

		while (alive) {
			System.out.print("\n");
			System.out.println("Current Status of Board :");
			b.ShowBoard();
			System.out.print("\n");
			System.out.print("Enter your move ,(row,column) ->");
			coordinates = coordinatesScan.nextLine();
			Queue<Integer> coordi = splitCoordinates(coordinates);
			int x = coordi.poll();
			int y = coordi.poll();
			if (b.array[x][y] != '-' && b.array[x][y] != '*') {
				System.out.println("You have already stepped on this square");
			}
			if (bWithBombs == null)
				bWithBombs = b.boardWithBombs(x, y);

			// test System.out.println("Cheatcode:");
			// test bWithBombs.ShowBoard();

			if (bWithBombs.array[x][y] == '*') {
				alive = false;
				continue;
			}
			b.UpdateBoardAfterStep(x, y, bWithBombs);
			if (b.isGameWon(bWithBombs)) {
				System.out.println("You won!");
				break;
			}

		}
		if (!alive) {
			System.out.println("You lost!");
			bWithBombs.ShowBoard();
		}
	}

	public static Queue<Integer> splitCoordinates(String coordinates) {
		Queue<Integer> splitcoords = new LinkedList<>();
		String[] arrOfCoords = coordinates.split(" ", 2);
		for (String s : arrOfCoords) {
			splitcoords.add(Integer.parseInt(s));
		}
		return splitcoords;
	}

}
