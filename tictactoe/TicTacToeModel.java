/**
 * This class represents a TicTacToeModel. It implements TicTacToe interface,
 * has a board and a winner.
 * 
 * @author Zuo
 *
 */

package cs5004.tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * This class represents a TicTacToeModel. It implements TicTacToe interface,
 * has a board and a winner.
 * 
 * @author Zuo
 *
 */
public class TicTacToeModel implements TicTacToe {
  // add your implementation here

  private Player[][] board;
  private Player winner;

  public TicTacToeModel() {
    this.board = new Player[3][3];
  }

  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays
        .stream(getBoard()).map(row -> " " + Arrays.stream(row)
            .map(p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | ")))
        .collect(Collectors.joining("\n-----------\n"));
  }

  @Override
  public void move(int r, int c) throws IllegalStateException, IllegalArgumentException {
    if (this.isGameOver()) {
      throw new IllegalStateException("This is game is over. No more plays can be made.");

    }

    if (r < 0 || c < 0 || r > 2 || c > 2) {
      throw new IllegalArgumentException("That position is not on the board.");
    }

    if (this.getMarkAt(r, c) == Player.X || this.getMarkAt(r, c) == Player.O) {
      throw new IllegalArgumentException("That position is occupied.");
    }

    this.board[r][c] = this.getTurn();

    if ((this.board[r][0] == this.board[r][1] && this.board[r][1] == this.board[r][2])
        || this.board[0][c] == this.board[1][c] && this.board[1][c] == this.board[2][c]
        || (this.board[0][0] == this.board[1][1] && this.board[1][1] == this.board[2][2]
            && this.board[1][1] != null)
        || (this.board[2][0] == this.board[1][1] && this.board[1][1] == this.board[0][2]
            && this.board[1][1] != null)) {
      this.winner = this.board[r][c];
    }
  }

  @Override
  public Player getTurn() {
    int xtotal = 0;
    int ytotal = 0;
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        if (this.board[row][col] == Player.X) {
          xtotal++;
        } else if (this.board[row][col] == Player.O) {
          ytotal++;
        }
      }
    }
    if (xtotal > ytotal) {
      return Player.O;
    }
    return Player.X;
  }

  @Override
  public boolean isGameOver() {
    int spacesFilled = Arrays.stream(this.board)
        .map(row -> Arrays.stream(row).map(p -> p == null ? 0 : 1).reduce(0, (a, b) -> a + b))
        .reduce(0, (a, b) -> a + b);
    return spacesFilled >= 9 || this.getWinner() != null;

  }

  @Override
  public Player getWinner() {
    return this.winner;
  }

  @Override
  public Player[][] getBoard() {
    return Arrays.stream(board).map(Player[]::clone).toArray(b -> board.clone());
  }

  @Override
  public Player getMarkAt(int r, int c) {
    if (r < 0 || c < 0 || r > 2 || c > 2) {
      throw new IllegalArgumentException("That position is not on the board.");
    }
    return this.board[r][c];
  }
}
