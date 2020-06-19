package GUI_GameBoard;

import Checkers.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BoardPanel extends JPanel {


    public BoardPanel() {

        setVisible(true);
        int y = 0;
        int x = 0;
        setLayout(null);

        if (GameManager.tileList.isEmpty()) {
            for (int panley = 0; panley <= 280; panley = panley + 40) {

                System.out.println();
                for (int panelx = 0; panelx <= 280; panelx = panelx + 40) {

                    final Tile tile = new Tile(y, x, new JButton());
                    tile.getButton().setBounds(panley, panelx, 40, 40);
                    if ((y + x) % 2 == 0) {
                        tile.getButton().setBackground(Color.red);
                        tile.setY(y);
                        tile.setX(x);
                        tile.getButton().addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                GameManager.gameManagerMethod(tile);
                            }
                        });

                        System.out.println("x" + tile.getX() + "y" + tile.getY());
                        if (y == 0 || y == 1 || y == 2) {
                            tile.getButton().setIcon(Const.blackPawn);
                        } else if (y == 5 || y == 6 || y == 7) {
                            tile.getButton().setIcon(Const.whitePawn);
                        }
                        tile.getButton().repaint();
                        setVisible(true);
                    }
                    else {
                        tile.getButton().setBackground(Color.white);
                        tile.setY(y);
                        tile.setX(x);
                        System.out.println("x" + tile.getX() + "y" + tile.getY());
                        tile.getButton().repaint();
                        setVisible(true);
                    }
                    tile.getButton().setVisible(true);
                    add(tile.getButton());
                    repaint();
                    tile.getButton().repaint();
                    GameManager.addTilesToList(tile);
                    y++;
                }
                x++;
                y = 0;

            }
            setVisible(true);
        }

    }

}