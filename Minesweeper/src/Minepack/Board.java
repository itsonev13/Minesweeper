package Minepack;

import java.util.Random;

public class Board {
	int x;
	int y;
	char[][] array;

	Board(int x, int y) {
		this.x = x;
		this.y = y;
		array = new char[x][y];
		for (int i = 0; i < x; i++) {
			for (int k = 0; k < y; k++) {
				array[i][k] = '-';
			}
		}
	}

// ShowBoard method prints the board in the console
	public void ShowBoard() {
		for (int i = 0; i < this.x; i++) {

			if (i == 0) {
				System.out.print("  ");
				for (int j = 0; j < this.y; j++) {
					if (j > 10) {
						System.out.print("" + j + " ");
					} else
						System.out.print(" " + j + " ");
				}
				System.out.print("\n");
			}
			for (int k = 0; k < this.y; k++) {
				if (k == 0) {
					System.out.print(i);
					if (i < 10) {
						System.out.print(" ");
					}
				}

				System.out.print(" " + array[i][k] + " ");
			}
			System.out.print("\n");
		}
	}

//This method makes a second board that has only the bombs coordinates.
//It is used as a pattern showing where the bombs are and insures that the first move can't be on a bomb
	public Board boardWithBombs(int first_x, int first_y) {
		Random random = new Random();
		int random_x, random_y;
		Board bwb = new Board(this.x, this.y);
		if (this.x == 9 && this.y == 9) {
			for (int i = 0; i < 10; i++) {
				random_x = random.nextInt(9);
				random_y = random.nextInt(9);
				// If on the first step is generated a bomb the loop is makes one more iteration
				if (random_x == first_x && random_y == first_y) {
					i--;
					continue;
				}
				// If two bombs are generated on the same square the loop is makes one more
				// iteration
				if (bwb.array[random_x][random_y] == '*') {
					i--;
					continue;
				} else
					bwb.array[random_x][random_y] = '*';
			}

		}
		// The same as the previous but with 16x16 square
		if (this.x == 16 && this.y == 16) {
			for (int i = 0; i < 40; i++) {
				random_x = random.nextInt(16);
				random_y = random.nextInt(16);
				if (random_x == first_x && random_y == first_y) {
					i--;
					continue;
				}
				if (bwb.array[random_x][random_y] == '*') {
					i--;
					continue;
				} else
					bwb.array[random_x][random_y] = '*';
			}

		}
		// The same as the previous but with 24x24 square
		if (this.x == 24 && this.y == 24) {
			for (int i = 0; i < 99; i++) {
				random_x = random.nextInt(24);
				random_y = random.nextInt(24);
				if (random_x == first_x && random_y == first_y) {
					i--;
					continue;
				}
				if (bwb.array[random_x][random_y] == '*') {
					i--;
					continue;
				} else
					bwb.array[random_x][random_y] = '*';
			}

		}
		return bwb;
	}

	public void UpdateBoardAfterStep(int x, int y, Board Boardwithbombs) {
		int beforeX = 0, afterX = 0;
		int beforeY = 0, afterY = 0;
		int count = 0;
		if (x == 0) {
			beforeX = x;
			afterX = x + 1;
		} else if (x == this.x - 1) {
			afterX = x;
			beforeX = x - 1;
		} else {
			beforeX = x - 1;
			afterX = x + 1;
		}
		if (y == 0) {
			beforeY = y;
			afterY = y + 1;
		} else if (y == this.y - 1) {
			afterY = y;
			beforeY = y + 1;
		} else {
			beforeY = y - 1;
			afterY = y + 1;
		}
		for (int i = beforeX; i <= afterX; i++) {
			for (int j = beforeY; j <= afterY; j++) {
				if (i != x || j != y)
					if (Boardwithbombs.array[i][j] == '*')
						count++;
			}
		}
		if (count == 0) {
			clearAdjacent(x, y, Boardwithbombs);
		}
		this.array[x][y] = (char) (count + 48);

	}

	private void clearAdjacent(int x, int y, Board boardWithBombs) {
		int beforeX = 0, afterX = 0;
		int beforeY = 0, afterY = 0;
		int count = 0;
		if (x == 0) {
			beforeX = x;
			afterX = x + 1;
		} else if (x == this.x - 1) {
			afterX = x;
			beforeX = x - 1;
		} else {
			beforeX = x - 1;
			afterX = x + 1;
		}
		if (y == 0) {
			beforeY = y;
			afterY = y + 1;
		} else if (y == this.y - 1) {
			afterY = y;
			beforeY = y - 1;
		} else {
			beforeY = y - 1;
			afterY = y + 1;
		}
		this.recursion(afterY, beforeX, beforeY, afterX, beforeY, beforeX, x, y);
	}

	public boolean isGameWon(Board boardWithBombs) {
		int bombcount = 0;
		int freespacescount = 0;
		for (int i = 0; i < boardWithBombs.x; i++) {
			for (int k = 0; k < boardWithBombs.y; k++) {
				if (boardWithBombs.array[i][k] == '*')
					bombcount++;
			}
		}
		for (int i = 0; i < this.x; i++) {
			for (int k = 0; k < this.y; k++) {
				if (this.array[i][k] == '-')
					freespacescount++;
				if (freespacescount > bombcount)
					break;
			}
			if (freespacescount > bombcount)
				break;
		}
		if (freespacescount == bombcount) {
			return true;
		}
		return false;
	}

	private void recursion(int aftery, int i, int j, int afterx, int beforey, int beforex, int x, int y) {
		if (i <= afterx) {
			if (j <= aftery) {
				// inner loop when j < n
				// inner loop body

				if (i != x || j != y)
					if (this.array[i][j] == '-')
						this.array[i][j] = ' ';
				recursion(aftery, i, j + 1, afterx, beforey, beforex, x, y); // increment inner counter only

			} else { // when j has reached n...
						// outer loop, which restarts inner loop

				recursion(aftery, i + 1, beforey, afterx, beforey, beforex, x, y); // increment outer counter,
																					// reset
				// inner
				// since we're starting a new inner loop
			}
		}
	}

}
