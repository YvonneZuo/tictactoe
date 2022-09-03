package cs5004.tictactoe;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class TicTacToePanel extends JPanel {
  
  Player[][] board;
  
  public TicTacToePanel(Player[][] board) {
    super(true);
    
    this.setBackground(Color.WHITE);
    this.setSize(500, 500);
    this.setLocation(0, 0);
    
    this.board = board;
    
  }
  
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    g.fillRect(165, 0, 2, 500);
    g.fillRect(332, 0, 2, 500);
    g.fillRect(0, 165, 500, 2);
    g.fillRect(0, 332, 500, 2);
    
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        Player p = this.board[row][col];
        if (p == Player.O) {
          // x is col, y is row
          g.fillOval(col * 165 + 45, row * 165 + 45, 75, 75);
        } else if (p == Player.X) {
          g.fillRect(col * 165 + 45, row * 165 + 45, 75, 75);
        }
      }
    }
    
    
  }
  
  public void setBoard(Player[][] board) {
    this.board = board;
  }
  
}
