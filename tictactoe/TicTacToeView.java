package cs5004.tictactoe;

//import java.awt.event.ActionEvent;

public interface TicTacToeView {

  /**
   * Display the user interface.
   */
  void display(TicTacToeGUIController control);
  
  String getMoveCommand();
  
  void showErrorMessage(String message);

  void updateBoard(Player[][] board);

}
