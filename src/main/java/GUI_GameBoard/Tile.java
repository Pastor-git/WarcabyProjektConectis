package GUI_GameBoard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tile {

    private int y;
    private int x;
    private JButton button;

    public Tile(int y, int x, JButton button) {
        this.y = y;
        this.x = x;
        this.button = button;
    }



    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }



    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }
}
