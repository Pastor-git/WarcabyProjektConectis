package Checkers;

import GUI_GameBoard.BoardFrame;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
//Powinno wszsytko działąć - jeśli tu jest tekst to najnowsza działająca werjsa
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                BoardFrame play = new BoardFrame();
            }
        });
    }
}