/**
 * This  class represents a TicTacToeCondoleController.
 * It implements TicTacToeController interface.
 */

package cs5004.tictactoe;

import java.io.IOException;

import java.util.Scanner;

/**
 * This class represents a TicTacToeCondoleController. It implements
 * TicTacToeController interface.
 * 
 * @author Zuo
 *
 */
public class TicTacToeConsoleController implements TicTacToeController {
  private Readable input;
  private Appendable output;

  public TicTacToeConsoleController(Readable in, Appendable out) {
    this.input = in;
    this.output = out;
  }

  @Override
  public void playGame(TicTacToe m) {
    Scanner scan = new Scanner(this.input);
    boolean badInput = false;

    while (!m.isGameOver()) {
      if (!badInput) {

        try {
          this.output.append(m.toString() + "\n");
        } catch (IOException e1) {
          scan.close();
          throw new IllegalStateException("Could not output current game state.");
        }

        try {
          this.output.append("Enter a move for " + m.getTurn().toString() + ":\n");
        } catch (IOException e1) {
          scan.close();
          throw new IllegalStateException("Could not prompt user for input.");
        }
      }
      badInput = false;

      String first = "";
      String second = "";

      if (scan.hasNext()) {
        first = scan.next();
      }

      if (first.toLowerCase().equals("q")) {
        break;
      }

      if (!first.equals("1") && !first.equals("2") && !first.equals("3")) {
        badInput = true;
      }

      if (scan.hasNext()) {
        second = scan.next();
      }

      if (second.toLowerCase().equals("q")) {
        break;
      }

      if (!second.equals("1") && !second.equals("2") && !second.equals("3")) {
        badInput = true;
      }

      if (second.equals("") && first.equals("")) {
        badInput = true;
        continue;
      }

      if (badInput) {
        try {
          this.output.append("Input must be 2 consecutive integers, or the letter q.\n");
        } catch (IOException e) {
          scan.close();
          throw new IllegalStateException("Could not tell user they gave bad input.");
        }
        continue;
      }

      try {
        m.move(Integer.parseInt(first) - 1, Integer.parseInt(second) - 1);
      } catch (IllegalArgumentException iae) {
        badInput = true;
        try {
          this.output.append("That space is already occupied.\n");
        } catch (IOException e) {
          scan.close();
          throw new IllegalStateException("Could not tell user spot was occupied.");
        }
      }
    }

    if (m.isGameOver()) {
      try {
        this.output.append(m.toString() + "\n");
      } catch (IOException e) {
        scan.close();
        throw new IllegalStateException("Could not output the final game state.");
      }
      try {
        this.output.append("Game is over! ");
      } catch (IOException e) {
        scan.close();
        throw new IllegalStateException("Could not output game is over.");
      }
      if (m.getWinner() == null) {
        try {
          this.output.append("Tie game.\n");
        } catch (IOException e) {
          scan.close();
          throw new IllegalStateException("Could not append tie game.");
        }
      } else {
        try {
          this.output.append(m.getWinner().toString() + " wins.\n");
        } catch (IOException e) {
          scan.close();
          throw new IllegalStateException("Could not output game winner.");
        }
      }
    } else {

      try {
        this.output.append("Game quit! Ending game state:\n" + m.toString() + "\n");
      } catch (IOException e) {
        scan.close();
        throw new IllegalStateException("Could not output that the game was quit.");
      }
    }

    scan.close();

  }
}
