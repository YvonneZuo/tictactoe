//package cs5004.tictactoe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cs5004.tictactoe.TicTacToe;
import cs5004.tictactoe.TicTacToeConsoleController;
import cs5004.tictactoe.TicTacToeController;
import cs5004.tictactoe.TicTacToeModel;
import java.io.StringReader;
import java.util.Arrays;
import org.junit.Test;

/**
 * Test cases for the tic tac toe controller, using mocks for readable and
 * appendable.
 */
public class TicTacToeControllerTest {

  /**
   * Test case where the move is integers, but out of range.
   * 
   */
  @Test
  public void testOutRangMove() {
    TicTacToe m = new TicTacToeModel();
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(new StringReader("5 2 q"), gameLog);
    c.playGame(m);
    assertEquals(
        "   |   |  \n" + "-----------\n" + "   |   |  \n" + "-----------\n" + "   |   |  \n"
            + "Enter a move for X:\n" + "Input must be 2 consecutive integers, or the letter q.\n"
            + "Game quit! Ending game state:\n" + "   |   |  \n" + "-----------\n" + "   |   |  \n"
            + "-----------\n" + "   |   |  \n",
        gameLog.toString());
  }

  /**
   * Test case where the move is integers, but invalid since the cell is occupied.
   * 
   */
  @Test
  public void testOccupiedMove() {
    TicTacToe m = new TicTacToeModel();
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(new StringReader("2 2 2 2 q"), gameLog);
    c.playGame(m);
    assertEquals(
        "   |   |  \n" + "-----------\n" + "   |   |  \n" + "-----------\n" + "   |   |  \n"
            + "Enter a move for X:\n" + "   |   |  \n" + "-----------\n" + "   | X |  \n"
            + "-----------\n" + "   |   |  \n" + "Enter a move for O:\n"
            + "That space is already occupied.\n" + "Game quit! Ending game state:\n"
            + "   |   |  \n" + "-----------\n" + "   | X |  \n" + "-----------\n" + "   |   |  \n",
        gameLog.toString());
  }

  /**
   * Test with the input where the q comes instead of an integer for the column.
   */
  @Test
  public void testQinCol() {
    TicTacToe m = new TicTacToeModel();
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(new StringReader("2 q"), gameLog);
    c.playGame(m);
    assertEquals(
        "   |   |  \n" + "-----------\n" + "   |   |  \n" + "-----------\n" + "   |   |  \n"
            + "Enter a move for X:\n" + "Game quit! Ending game state:\n" + "   |   |  \n"
            + "-----------\n" + "   |   |  \n" + "-----------\n" + "   |   |  \n",
        gameLog.toString());
  }

  /**
   * Test with the input where the q comes instead of an integer for the row.
   */
  @Test
  public void testQinRow() {
    TicTacToe m = new TicTacToeModel();
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(new StringReader("q 2"), gameLog);
    c.playGame(m);
    assertEquals(
        "   |   |  \n" + "-----------\n" + "   |   |  \n" + "-----------\n" + "   |   |  \n"
            + "Enter a move for X:\n" + "Game quit! Ending game state:\n" + "   |   |  \n"
            + "-----------\n" + "   |   |  \n" + "-----------\n" + "   |   |  \n",
        gameLog.toString());
  }

  /**
   * Test to play game to completion, where there is a winner.
   */
  @Test
  public void testCompletion() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 2 1 2 2 3 2 3 3");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(36, lines.length);
    assertEquals("Game is over! X wins.", lines[lines.length - 1]);
  }

  /**
   * Test to see what happens when the input ends "abruptly". No more input, but
   * not quit, and game not over.
   * 
   */
  @Test
  public void testHangEnd() {
    TicTacToe m = new TicTacToeModel();
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(new StringReader("2 2 1 1"), gameLog);

    Thread thread = new Thread() {
      @Override
      public void run() {
        c.playGame(m);
      }
    };
    thread.start();

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      // do nothing
    }

    assertTrue(thread.isAlive());

    String[] lines = gameLog.toString().split("\n");
    assertEquals("Enter a move for X:", lines[lines.length - 1]);
  }

  /**
   * Test for a single valid move.
   */
  @Test
  public void testSingleValidMove() {
    TicTacToe m = new TicTacToeModel();
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(new StringReader("2 2 q"), gameLog);
    c.playGame(m);
    assertEquals("   |   |  \n" + "-----------\n" + "   |   |  \n" + "-----------\n"
        + "   |   |  \n" + "Enter a move for X:\n" + "   |   |  \n" + "-----------\n"
        + "   | X |  \n" + "-----------\n" + "   |   |  \n" + "Enter a move for O:\n"
        + "Game quit! Ending game state:\n" + "   |   |  \n" + "-----------\n" + "   | X |  \n"
        + "-----------\n" + "   |   |  \n", gameLog.toString());
  }

  /**
   * Test case where non-integer garbage comes instead of an integer for the row.
   */
  @Test
  public void testBogusInputAsRow() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("!#$ 2 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    // check that it's the correct number of lines
    assertEquals(13, lines.length);
    // check that the last 6 lines are correct
    String lastMsg = String.join("\n", Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals("Game quit! Ending game state:\n" + "   |   |  \n" + "-----------\n"
        + "   |   |  \n" + "-----------\n" + "   |   |  ", lastMsg);
    // note no trailing \n here, because of the earlier split
  }

  /**
   * Test case where non-integer garbage comes instead of an integer for the row.
   */
  @Test
  public void testBogusInputAsCol() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 !#$ q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    // check that it's the correct number of lines
    assertEquals(13, lines.length);
    // check that the last 6 lines are correct
    String lastMsg = String.join("\n", Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals("Game quit! Ending game state:\n" + "   |   |  \n" + "-----------\n"
        + "   |   |  \n" + "-----------\n" + "   |   |  ", lastMsg);
    // note no trailing \n here, because of the earlier split
  }

  /**
   * Test for the tie game.
   */
  @Test
  public void testTieGame() {
    TicTacToe m = new TicTacToeModel();
    // note the entire sequence of user inputs for the entire game is in this one
    // string:
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(60, lines.length);
    assertEquals("Game is over! Tie game.", lines[lines.length - 1]);
  }

  /**
   * Test for the failingAppendable.
   */
  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    Appendable gameLog = new FailingAppendable();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
  }

}
