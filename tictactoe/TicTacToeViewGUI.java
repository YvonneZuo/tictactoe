package cs5004.tictactoe;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TicTacToeViewGUI extends JFrame implements TicTacToeView, ActionListener {
  
  private JMenuBar menuBar;
  private JMenu file;
  private JMenuItem exit;
  
  private JPanel buttonPane;
  private JButton moveButtom;
 
  private JTextField textField;
  private JButton quitButton;
  private TicTacToePanel tttp;

  public TicTacToeViewGUI() {
    super("TIcTacToe"); // make the view GUI as a JFrame window, and name 
    this.setSize(500, 600); // this refers to the JFrame, because it extends JF.
    this.setLocation(0, 0); // up right corner;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // if user clicks esc.
    
    this.menuBar = new JMenuBar(); // create a new JMenuBar
    this.setJMenuBar(this.menuBar); // set the JFrame's menubar to the new menubar
    this.file = new JMenu("File"); // create the Jmenu called file
    this.menuBar.add(this.file); // add the file menu to the menuBar
    
    this.exit = new JMenuItem("Exit");
    this.exit.setName("Quit");
    this.file.add(this.exit);
    this.exit.addActionListener(this);
    
    this.buttonPane = new JPanel(true); // true to visible
    this.buttonPane.setBackground(Color.WHITE);
    this.buttonPane.setSize(200, 500);
    this.buttonPane.setLocation(0, 500);
    this.buttonPane.setLayout(new FlowLayout()); // add bars in an line order.
    
    this.moveButtom = new JButton("Move");
    this.moveButtom.setName("Move");
    this.buttonPane.add(this.moveButtom);
    
    this.textField = new JTextField(5); // length of the textField
    this.buttonPane.add(this.textField);
    
    this.quitButton = new JButton("Quit");
    this.quitButton.setName("Quit");
    this.buttonPane.add(this.quitButton);
    
    this.moveButtom.addActionListener(this);
    this.quitButton.addActionListener(this);
    
    this.add(buttonPane);
    
    tttp = new TicTacToePanel(new Player[3][3]);
    this.add(tttp);
    
    this.setVisible(true); // make to be seen.
    
    this.tttp.repaint(); // let java to pain the paintcomponent
  }
  
  @Override
  public void display(TicTacToeGUIController control) {
    // set the controller to listen to the moveButton
    this.moveButtom.addActionListener(control);
    

  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    Component c = (Component) e.getSource();
    if (c.getName().equals("Quit")) {
      System.exit(0);
    }
  }

  @Override
  public String getMoveCommand() {
    String command = this.textField.getText();
    this.textField.setText(""); // texts disappear after command done
    return command;
  }

  @Override
  public void showErrorMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
    
  }

  @Override
  public void updateBoard(Player[][] board) {
    this.tttp.setBoard(board); // reset the board
    this.tttp.repaint(); // show the board
  }

}
