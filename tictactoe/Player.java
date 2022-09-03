/**
 * This class represents a player.
 * A player can be either X or O.
 * 
 * @author Zuo
 *
 */

package cs5004.tictactoe;

/**
 * This class represents a player. A player can be either X or O.
 * 
 * @author Zuo
 *
 */
public enum Player {
  X, O;

  @Override
  public String toString() {
    return this.name();
  }
}
