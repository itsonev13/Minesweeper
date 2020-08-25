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

	// This method checks if the adjacent squares are without a bomb and
	// if there is a bomb it counts them
	public void UpdateBoardAfterStep(int x, int y, Board Boardwithbombs) {
		int count = 0;
		// above the chosen space
		if (x <= this.x - 1 && x > 0) {
			if (Boardwithbombs.array[x - 1][y] == '*')
				count++;
		}
		// under the chosen space
		if (x < this.x - 1 && x >= 0) {
			if (Boardwithbombs.array[x + 1][y] == '*')
				count++;
		}
		// right of the chosen space
		if (y < this.y - 1 && y >= 0) {
			if (Boardwithbombs.array[x][y + 1] == '*')
				count++;
		}
		// left of the chosen space
		if (y <= this.y - 1 && y > 0) {
			if (Boardwithbombs.array[x][y - 1] == '*')
				count++;
		}
		// up and right of the chosen space
		if (y < this.y - 1 && x <= this.x - 1 && y >= 0 && x > 0) {
			if (Boardwithbombs.array[x - 1][y + 1] == '*')
				count++;
		}
		// up and left of the chosen space
		if (y <= this.y - 1 && x <= this.x - 1 && y > 0 && x > 0) {
			if (this.array[x - 1][y - 1] == '*')
				count++;
		}
		// down and right of the chosen space
		if (y < this.y - 1 && x < this.x - 1 && y >= 0 && x >= 0) {
			if (Boardwithbombs.array[x + 1][y + 1] == '*')
				count++;
		}
		// down and left of the chosen space
		if (y <= this.y - 1 && x < this.x - 1 && y > 0 && x >= 0) {
			if (Boardwithbombs.array[x + 1][y - 1] == '*')
				count++;
		}
		// the number of bombs next to the space
		this.array[x][y] = (char) (count + 48);
		if (count == 0) {
			clearAdjacent(x, y, Boardwithbombs);
		}

	}

	private void clearAdjacent(int x, int y, Board boardWithBombs) {

		// above the chosen space
		if (x <= this.x - 1 && x > 0) {
			if (this.array[x - 1][y] == '-' && boardWithBombs.array[x - 1][y] != '*')
				this.array[x - 1][y] = ' ';
		}
		// under the chosen space
		if (x < this.x - 1 && x >= 0) {
			if (this.array[x + 1][y] == '-' && boardWithBombs.array[x + 1][y] != '*')
				this.array[x + 1][y] = ' ';

		}
		// right of the chosen space
		if (y < this.y - 1 && y >= 0) {
			if (this.array[x][y + 1] == '-' && boardWithBombs.array[x][y + 1] != '*')
				this.array[x][y + 1] = ' ';
		}
		// left of the chosen space
		if (y <= this.y - 1 && y > 0) {
			if (this.array[x][y - 1] == '-' && boardWithBombs.array[x][y - 1] != '*')
				this.array[x][y - 1] = ' ';

		}
		// up and right of the chosen space
		if (y < this.y - 1 && x <= this.x - 1 && y >= 0 && x > 0) {
			if (this.array[x - 1][y + 1] == '-' && boardWithBombs.array[x - 1][y + 1] != '*')
				this.array[x - 1][y + 1] = ' ';

		}
		// up and left of the chosen space
		if (y <= this.y - 1 && x <= this.x - 1 && y > 0 && x > 0) {
			if (this.array[x - 1][y - 1] == '-' && boardWithBombs.array[x - 1][y - 1] != '*')
				this.array[x - 1][y - 1] = ' ';
		}
		// down and right of the chosen space
		if (y < this.y - 1 && x < this.x - 1 && y >= 0 && x >= 0) {
			if (this.array[x + 1][y + 1] == '-' && boardWithBombs.array[x + 1][y + 1] != '*')
				this.array[x + 1][y + 1] = ' ';
		}
		// down and left of the chosen space
		if (y <= this.y - 1 && x < this.x - 1 && y > 0 && x >= 0) {
			if (this.array[x + 1][y - 1] == '-' && boardWithBombs.array[x + 1][y - 1] != '*')
				this.array[x + 1][y - 1] = ' ';
		}
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
		for (int i = 0; i < boardWithBombs.x; i++) {
			for (int k = 0; k < boardWithBombs.y; k++) {
				if (this.array[i][k] == '-')
					freespacescount++;
				if (freespacescount > bombcount)
					break;
			}
			if (freespacescount > bombcount)
				break;
		}
		if (freespacescount == bombcount)
			return true;

		return false;
	}
}
