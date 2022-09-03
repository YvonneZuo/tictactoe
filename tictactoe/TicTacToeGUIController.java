package cs5004.tictactoe;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUIController implements TicTacToeController, ActionListener {
// make GUIController a actionlistener to buttons.
  
  TicTacToeView view;
  TicTacToe model;

  public TicTacToeGUIController(TicTacToeView tv) {
    this.view = tv;
    this.model = null;
  }
  
  @Override
  public void playGame(TicTacToe m) {
    this.model = m;
    view.display(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Component c = (Component) e.getSource(); // get the object on which the event occured.
    
    if (c.getName().equals("Move")) {
      String command = this.view.getMoveCommand();
      // should make sure there are one int one space one int
      int coord1 = Integer.parseInt("" + command.charAt(0));
      int coord2 = Integer.parseInt("" + command.charAt(2));
      try {
      model.move(coord1 - 1, coord2 - 1);
      } catch(IllegalArgumentException exception) {
        // send the message to view to display
        view.showErrorMessage(exception.getMessage());
      } catch(IllegalStateException exception) {
        // send the message to view to display
        view.showErrorMessage(exception.getMessage());
      }
      
      // by careful the getBoard should be deepclone
      view.updateBoard(model.getBoard());
      
      if (model.isGameOver()) {
        String overString = "Game is over.\n";
        if (model.getWinner() == Player.X) {
          overString += "X wins!";
        } else if (model.getWinner() == Player.O) {
          overString += "O wins!";
        } else {
          overString += "Tie game!";
        }
        view.showErrorMessage(overString);
      } 
    }
  }

}
