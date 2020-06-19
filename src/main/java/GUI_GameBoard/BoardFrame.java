package GUI_GameBoard;

import javax.swing.*;


public class BoardFrame extends JFrame {

    public  BoardFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(365, 365);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Checkers");
        add(new BoardPanel());

    }
}