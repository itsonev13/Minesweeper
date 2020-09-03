package Minepack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartActionListener implements ActionListener {
	GUI gui;

	public RestartActionListener(GUI gui) {
		// TODO Auto-generated constructor stub
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.gui.board = new Board(this.gui.board.x, this.gui.board.y);
		this.gui.init();
		this.gui.bWithBombs = this.gui.board.boardWithBombs(this.gui.board.x, this.gui.board.y);
		this.gui.alive = true;
		this.gui.setVisible(true);
		this.gui.repaint();

	}

}
