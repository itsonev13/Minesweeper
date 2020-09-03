package Minepack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame {
	int mx = -100;
	int my = -100;
	int spacing = 3;

	Board board = null;
	Board bWithBombs = null;
	int[] coords = null;
	boolean alive = true;
	GamePanel panel = null;

	public GUI(int x, int y) {

		this.setTitle("Minesweeper");
		this.setSize(x, y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
		this.setVisible(true);
		this.setResizable(false);
	}

	protected void init() {
		panel = new GamePanel();
		this.setContentPane(panel);
		MouseMove mousemove = new MouseMove();
		this.addMouseMotionListener(mousemove);

		MouseClick click = new MouseClick();
		this.addMouseListener(click);

	}

	public int[] getCoords() {
		return coords;
	}

	public void setCoords(int[] coords) {
		this.coords = coords;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Board getbWithBombs() {
		return bWithBombs;
	}

	public void setbWithBombs(Board bWithBombs) {
		this.bWithBombs = bWithBombs;
	}

	public class MouseMove implements MouseMotionListener {
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			mx = e.getX();
			my = e.getY();

			if (board.x == 9) {
				for (int i = 0; i < board.x; i++) {
					for (int j = 0; j < board.y; j++) {
						if (mx >= spacing + i * 80 && mx < i * 80 + 80 - 2 * spacing && my >= spacing + j * 80 + 80 + 26
								&& my < spacing + j * 80 + 26 + 80 + 80 - 2 * spacing) {
							repaint();
						}
					}
				}
			}
			if (board.x == 16) {
				for (int i = 0; i < board.x; i++) {
					for (int j = 0; j < board.y; j++) {
						if (mx >= spacing + i * 45 && mx < i * 45 + 45 - 2 * spacing && my >= spacing + j * 45 + 45 + 26
								&& my < spacing + j * 45 + 26 + 45 + 45 - 2 * spacing) {
							repaint();
						}
					}
				}
			}
			if (board.x == 24) {
				for (int i = 0; i < board.x; i++) {
					for (int j = 0; j < board.y; j++) {
						if (mx >= spacing + i * 40 && mx < i * 40 + 40 - 2 * spacing && my >= spacing + j * 40 + 40 + 26
								&& my < spacing + j * 40 + 26 + 40 + 40 - 2 * spacing) {
							repaint();

						}
					}
				}
			}

		}

	}

	public class MouseClick implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			coords = inBox();
			if (coords[0] != -1 && coords[1] != -1) {
				int x = coords[0], y = coords[1];
				if (board.array[x][y] != '-' && board.array[x][y] != '*') {
					System.out.println("You have already stepped on this square");
				}
				if (bWithBombs == null)
					bWithBombs = board.boardWithBombs(x, y);
				if (bWithBombs.array[x][y] == '*') {
					alive = false;
				}
				board.UpdateBoardAfterStep(x, y, bWithBombs);
				if (board.isGameWon(bWithBombs)) {
					System.out.println("You won!");
				}
				if (!alive)
					System.out.println("You lost");

			}
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public class GamePanel extends JPanel {
		public void SetAliveGamePanel(boolean alivee) {
			alive = alivee;
		}

		public void paintComponent(Graphics g) {
			g.setColor(Color.lightGray);
			if (board.x == 24)
				g.fillRect(0, 0, 980, 1050);
			if (board.x == 16)
				g.fillRect(0, 0, 750, 829);
			if (board.x == 9)
				g.fillRect(0, 0, 750, 829);
			for (int i = 0; i < board.x; i++) {
				for (int j = 0; j < board.y; j++) {
					if (board.x == 24) {
						g.setColor(Color.gray);
						if (mx >= spacing + i * 40 && mx < i * 40 + 40 - 2 * spacing && my >= spacing + j * 40 + 40 + 26
								&& my < spacing + j * 40 + 26 + 40 + 40 - 2 * spacing) {
							g.setColor(Color.red);
						}
						if (bWithBombs != null)
							if (bWithBombs.array[i][j] == '*' && !alive) {
								g.setColor(Color.YELLOW);
								g.fillRect(spacing + i * 40, spacing + j * 40 + 40, 40 - 2 * spacing, 40 - 2 * spacing);
								JButton restart = new JButton("Restart!");
								restart.setBounds(425, 10, 80, 30);
								restart.setBackground(Color.YELLOW);
								this.add(restart);
								restart.addActionListener(new RestartActionListener(GUI.this));
							}
						if (board.array[i][j] == '-' && bWithBombs == null) {
							g.fillRect(spacing + i * 40, spacing + j * 40 + 40, 40 - 2 * spacing, 40 - 2 * spacing);
						}
						g.fillRect(spacing + i * 40, spacing + j * 40 + 40, 40 - 2 * spacing, 40 - 2 * spacing);
						if (board.array[i][j] >= '0' && board.array[i][j] <= '8' && alive) {
							g.setColor(Color.white);
							g.fillRect(spacing + i * 40, spacing + j * 40 + 40, 40 - 2 * spacing, 40 - 2 * spacing);
						}
						if (board.array[i][j] >= '0' && board.array[i][j] <= '2' && alive) {
							g.setColor(Color.green);
							g.setFont(new Font("Tahoma", Font.BOLD, 20));
							g.drawString(Integer.toString((int) board.array[i][j] - 48), i * 40 + 14, j * 40 + 40 + 27);
						}
						if (board.array[i][j] >= '3' && board.array[i][j] <= '5' && alive) {
							g.setColor(Color.ORANGE);
							g.setFont(new Font("Tahoma", Font.BOLD, 20));
							g.drawString(Integer.toString((int) board.array[i][j] - 48), i * 40 + 14, j * 40 + 40 + 27);
						}
						if (board.array[i][j] >= '6' && board.array[i][j] <= '8' && alive) {
							g.setColor(Color.RED);
							g.setFont(new Font("Tahoma", Font.BOLD, 20));
							g.drawString(Integer.toString((int) board.array[i][j] - 48), i * 40 + 14, j * 40 + 40 + 27);
						}
						if (board.array[i][j] == ' ' && alive) {
							g.setColor(Color.white);
							g.fillRect(spacing + i * 40, spacing + j * 40 + 40, 40 - 2 * spacing, 40 - 2 * spacing);

						}

					}
					if (board.x == 16) {
						g.setColor(Color.gray);
						if (mx >= spacing + i * 45 && mx < i * 45 + 45 - 2 * spacing && my >= spacing + j * 45 + 45 + 26
								&& my < spacing + j * 45 + 26 + 45 + 45 - 2 * spacing) {
							g.setColor(Color.red);
						}
						if (bWithBombs != null)
							if (bWithBombs.array[i][j] == '*' && !alive) {
								g.setColor(Color.YELLOW);
								g.fillRect(spacing + i * 45, spacing + j * 45 + 45, 45 - 2 * spacing, 45 - 2 * spacing);
								JButton restart = new JButton("Restart!");
								restart.setBounds(300, 10, 80, 30);
								restart.setBackground(Color.YELLOW);
								this.add(restart);
								restart.addActionListener(new RestartActionListener(GUI.this));
							}
						if (board.array[i][j] == '-' && bWithBombs == null) {
							g.fillRect(spacing + i * 45, spacing + j * 45 + 45, 45 - 2 * spacing, 45 - 2 * spacing);
						}
						g.fillRect(spacing + i * 45, spacing + j * 45 + 45, 45 - 2 * spacing, 45 - 2 * spacing);
						if (board.array[i][j] >= '0' && board.array[i][j] <= '8' && alive) {
							g.setColor(Color.white);
							g.fillRect(spacing + i * 45, spacing + j * 45 + 45, 45 - 2 * spacing, 45 - 2 * spacing);
						}
						if (board.array[i][j] >= '0' && board.array[i][j] <= '2' && alive) {
							g.setColor(Color.green);
							g.setFont(new Font("Tahoma", Font.BOLD, 20));
							g.drawString(Integer.toString((int) board.array[i][j] - 48), i * 45 + 13, j * 45 + 45 + 26);
						}
						if (board.array[i][j] >= '3' && board.array[i][j] <= '5' && alive) {
							g.setColor(Color.ORANGE);
							g.setFont(new Font("Tahoma", Font.BOLD, 20));
							g.drawString(Integer.toString((int) board.array[i][j] - 48), i * 45 + 13, j * 45 + 45 + 26);
						}
						if (board.array[i][j] >= '6' && board.array[i][j] <= '8' && alive) {
							g.setColor(Color.RED);
							g.setFont(new Font("Tahoma", Font.BOLD, 20));
							g.drawString(Integer.toString((int) board.array[i][j] - 48), i * 45 + 13, j * 45 + 45 + 26);
						}
						if (board.array[i][j] == ' ' && alive) {
							g.setColor(Color.white);
							g.fillRect(spacing + i * 45, spacing + j * 45 + 45, 45 - 2 * spacing, 45 - 2 * spacing);

						}

					}
					if (board.x == 9) {
						g.setColor(Color.gray);
						if (mx >= spacing + i * 80 && mx < i * 80 + 80 - 2 * spacing && my >= spacing + j * 80 + 80 + 26
								&& my < spacing + j * 80 + 26 + 80 + 80 - 2 * spacing) {
							g.setColor(Color.red);
						}

						if (bWithBombs == null) {
							g.fillRect(spacing + i * 80, spacing + j * 80 + 80, 80 - 2 * spacing, 80 - 2 * spacing);
						}
						if (bWithBombs != null) {
							if (bWithBombs.array[i][j] == '*' && !alive) {
								g.setColor(Color.YELLOW);
								g.fillRect(spacing + i * 80, spacing + j * 80 + 80, 80 - 2 * spacing, 80 - 2 * spacing);
								JButton restart = new JButton("Restart!");
								restart.setBounds(280, 20, 80, 50);
								restart.setBackground(Color.YELLOW);
								this.add(restart);
								restart.addActionListener(new RestartActionListener(GUI.this));
							}
							g.fillRect(spacing + i * 80, spacing + j * 80 + 80, 80 - 2 * spacing, 80 - 2 * spacing);
						}
						if (board.array[i][j] >= '0' && board.array[i][j] <= '8' && alive) {
							g.setColor(Color.white);
							g.fillRect(spacing + i * 80, spacing + j * 80 + 80, 80 - 2 * spacing, 80 - 2 * spacing);
						}
						if (board.array[i][j] >= '0' && board.array[i][j] <= '2' && alive) {
							g.setColor(Color.green);
							g.setFont(new Font("Tahoma", Font.BOLD, 40));
							g.drawString(Integer.toString((int) board.array[i][j] - 48), i * 80 + 27, j * 80 + 80 + 55);
						}
						if (board.array[i][j] >= '3' && board.array[i][j] <= '4' && alive) {
							g.setColor(Color.ORANGE);
							g.setFont(new Font("Tahoma", Font.BOLD, 40));
							g.drawString(Integer.toString((int) board.array[i][j] - 48), i * 80 + 27, j * 80 + 80 + 55);
						}
						if (board.array[i][j] >= '5' && board.array[i][j] <= '8' && alive) {
							g.setColor(Color.RED);
							g.setFont(new Font("Tahoma", Font.BOLD, 40));
							g.drawString(Integer.toString((int) board.array[i][j] - 48), i * 80 + 27, j * 80 + 80 + 55);
						}
						if (board.array[i][j] == ' ' && alive) {
							g.setColor(Color.white);
							g.fillRect(spacing + i * 80, spacing + j * 80 + 80, 80 - 2 * spacing, 80 - 2 * spacing);
						}

					}
				}
			}
		}
	}

	public int[] inBox() {
		int[] coords = new int[2];
		if (board.x == 9) {
			for (int i = 0; i < board.x; i++) {
				for (int j = 0; j < board.y; j++) {
					if (mx >= spacing + i * 80 && mx < i * 80 + 80 - 2 * spacing && my >= spacing + j * 80 + 80 + 26
							&& my < spacing + j * 80 + 26 + 80 + 80 - 2 * spacing) {
						coords[0] = i;
						coords[1] = j;
						return coords;
					}
				}
			}
		}
		if (board.x == 16) {
			for (int i = 0; i < board.x; i++) {
				for (int j = 0; j < board.y; j++) {
					if (mx >= spacing + i * 45 && mx < i * 45 + 45 - 2 * spacing && my >= spacing + j * 45 + 45 + 26
							&& my < spacing + j * 45 + 26 + 45 + 45 - 2 * spacing) {
						coords[0] = i;
						coords[1] = j;
						return coords;
					}
				}
			}
		}
		if (board.x == 24) {
			for (int i = 0; i < board.x; i++) {
				for (int j = 0; j < board.y; j++) {
					if (mx >= spacing + i * 40 && mx < i * 40 + 40 - 2 * spacing && my >= spacing + j * 40 + 40 + 26
							&& my < spacing + j * 40 + 26 + 40 + 40 - 2 * spacing) {
						coords[0] = i;
						coords[1] = j;
						return coords;
					}
				}
			}
		}
		coords[0] = -1;
		coords[1] = -1;
		return coords;
	}

}
