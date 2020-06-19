package Checkers;

import GUI_GameBoard.BoardPanel;
import GUI_GameBoard.Const;
import GUI_GameBoard.Tile;
import Players.Player;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameManager {

    public static Player graczCzarny = new Player("black", false);
    public static Player graczBialy = new Player("bialy", true);

    public static Tile tile1 = null;
    public static Tile tile2 = null;
    public static Tile tiletobeKilled = null;

    public static List<Tile> tileList = new ArrayList();

    public static void addTilesToList(Tile tile) {
        tileList.add(tile);
    }

    public static void gameManagerMethod(Tile tile) {
        if (tile1IsNotSet() == true && tileHasIcon(tile) && tileBelongstoActivePlayer(tile)) {
            tile1 = tile;
            tile1.getButton().setBorder(BorderFactory.createLineBorder(Color.green));
            System.out.println("aktywny x i y" + tile1.getY() + " " + tile1.getX());
        }
        else if (!tile1IsNotSet() && tile2IsNotSet() == true) {
            if (tile.equals(tile1)) {
                tile1.getButton().setBorderPainted(false);
                System.out.println("niekatywny x i y" + tile1.getY() + " " + tile1.getX());
                tile1 = null;

            }
            else {
                tile2 = tile;
                if (isaLady(tile1) && ((graczCzarny.isTurn() && tile1.getButton().getIcon() == Const.blackLady) ||
                        (graczBialy.isTurn() && tile1.getButton().getIcon() == Const.whiteLady))) {
                    if (ladyCanKill() == true) {
                        System.out.println("x i y zabitego" + tiletobeKilled.getX() + " " + tiletobeKilled.getY());
                        ladyKill();
                        if (isVictory()) {
                            gameOver();
                        }
                    } else if (ladyCanMove(tile) == true) {
                        System.out.println("niekatywny x i y" + tile1.getY() + " " + tile1.getX());
                        ladyMove();

                    } else {

                        System.out.println("nie mogę wykonać ruchu na to pole");
                        System.out.println(tile1.getY() + " " + tile1.getX());
                        tile2 = null;
                    }

                }

                else {
                    if  (cankill()) {
                        System.out.println("x i y zabitego" + tiletobeKilled.getX() + " " + tiletobeKilled.getY());
                        kill();
                        if (isVictory()) {
                            gameOver();
                        }
                    } else if (canMove(tile)) {
                        System.out.println("niekatywny x i y" + tile1.getY() + " " + tile1.getX());
                        makeAmove();

                    } else {

                        System.out.println("nie mogę wykonać ruchu na to pole");
                        System.out.println(tile1.getY() + " " + tile1.getX());
                        tile2 = null;
                    }
                }
            }
        }
    }



    public static boolean tile1IsNotSet() {
        if (tile1 == null) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean tile2IsNotSet() {
        if (tile2 == null) {
            return true;
        } else {
            return false;
        }
    }

    public static void makeAmove() {

        tile2.getButton().setIcon(tile1.getButton().getIcon());
        if (canbeLady(tile2)) {
            setLady(tile2);
        }
        tile1.getButton().setIcon(null);
        tile1.getButton().setBorderPainted(false);
        tile1.getButton().repaint();
        tile1 = null;
        tile2 = null;
        changePlayer();

    }

    public static boolean tileHasIcon(Tile tile) {
        return tile.getButton().getIcon() != null;
    }

    public static boolean canMove(Tile tile) {
        if (((tile1.getButton().getIcon() == Const.whitePawn && tileHasIcon(tile) == false && (tile.getY() == tile1.getY() - 1) && (tile.getX() == tile1.getX() - 1 || tile.getX() == tile1.getX() + 1)) ||
                (tile1.getButton().getIcon() == Const.blackPawn && tileHasIcon(tile) == false && (tile.getY() == tile1.getY() + 1)) && (tile.getX() == tile1.getX() - 1 || tile.getX() == tile1.getX() + 1))) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isVictory() {
        if (graczCzarny.isPawnsLeft()==0 || graczBialy.isPawnsLeft()==0) {
            return true;
        }
        return false;
    }

    public static void gameOver() {
        System.out.println("Game Over");
        if (graczCzarny.isPawnsLeft()==0) {
            System.out.println("wygrał Biały");
        }
        else {
            System.out.println("wygrał Czarny");
        }

        for (Tile tile : tileList) {
            tile.getButton().setEnabled(false);
        }
    }

    public static boolean tileBelongstoActivePlayer(Tile tile) {
        if (graczBialy.isTurn() && tile.getButton().getIcon() == Const.whitePawn || tile.getButton().getIcon() == Const.whiteLady) {
            return true;
        } else if (graczCzarny.isTurn() && tile.getButton().getIcon() == Const.blackPawn || tile.getButton().getIcon() == Const.blackLady) {
            return true;
        } else {
            return false;
        }
    }

    public static void changePlayer() {
        if (graczBialy.isTurn()) {
            graczBialy.setTurn(false);
            System.out.println(graczBialy.isTurn());
            graczCzarny.setTurn(true);
            System.out.println(graczCzarny.isTurn());
        }
        else {
            graczBialy.setTurn(true);
            System.out.println(graczBialy.isTurn());
            graczCzarny.setTurn(false);
            System.out.println(graczCzarny.isTurn());
        }

    }

    public static void setTiletobeKilled() {

        int x = xfiledBetwenTile(tile1, tile2);
        int y = yfieldBetwenTile(tile1, tile2);

        for (Tile currentTile : tileList) {

            if (y == currentTile.getY() && x == currentTile.getX())
                tiletobeKilled = currentTile;

        }
    }

    private static int yfieldBetwenTile(Tile tile1, Tile tile2){
        int yBetween;
        if (tile1.getY() > tile2.getY()) {
            yBetween = tile1.getY() - 1;
        }
        else {
            yBetween = tile1.getY() + 1;
        }
        return yBetween;
    }

    private static int xfiledBetwenTile(Tile tile1, Tile tile2){
        int xBetween;
        if (tile1.getX() > tile2.getX()) {
            xBetween = tile1.getX() - 1;
        }
        else {
            xBetween = tile1.getX() + 1;
        }
        return xBetween;
    }

    public static boolean cankill() {

        setTiletobeKilled();
        boolean b1 = tile1.getButton().getIcon() == Const.whitePawn;
        boolean b2 = !tileHasIcon(tile2) ;
        boolean b3 = (tile2.getY() == tile1.getY() - 2);
        boolean b4 = tile2.getX() == tile1.getX() - 2;
        boolean b5 = tile2.getX() == tile1.getX() + 2;
        if ((
                ((tile1.getButton().getIcon() == Const.whitePawn && !tileHasIcon(tile2)  && (tile2.getY() == tile1.getY() - 2) && (tile2.getX() == tile1.getX() - 2
                        || tile2.getX() == tile1.getX() + 2)) )
                        ||
                        (((tile1.getButton().getIcon() == Const.blackPawn && !tileHasIcon(tile2)  && (tile2.getY() == tile1.getY() + 2)) && (tile2.getX() == tile1.getX() - 2
                                || tile2.getX() == tile1.getX() + 2)) )
        ) && isenemyPawn()
        )
        {
            return true;
        } else {

            return false;
        }

    }

    public static void kill() {
        if ((tiletobeKilled.getButton().getIcon() == Const.whitePawn) || (tiletobeKilled.getButton().getIcon() == Const.whiteLady)) {
            graczBialy.setPawnsLeft((graczBialy.getPawnsLeft()-1));
            System.out.println(graczBialy.getPawnsLeft());
        }
        else if ((tiletobeKilled.getButton().getIcon() == Const.blackPawn) || (tiletobeKilled.getButton().getIcon() == Const.blackLady)) {
            graczCzarny.setPawnsLeft((graczCzarny.getPawnsLeft()-1));
            System.out.println(graczCzarny.getPawnsLeft());
        }
        else {
            System.out.println("nikt nie traci");
        }

        tiletobeKilled.getButton().setIcon(null);

        tile2.getButton().setIcon(tile1.getButton().getIcon());
        if (canbeLady(tile2)) {
            setLady(tile2);
        }
        tile1.getButton().setIcon(null);
        tile1.getButton().setBorderPainted(false);
        tile1.getButton().repaint();
        tile2.getButton().repaint();
        tiletobeKilled.getButton().repaint();
        tile1 = null;
        tile2 = null;
        tiletobeKilled = null;

        changePlayer();

    }


    public static boolean isenemyPawn() {


        if ((tiletobeKilled.getButton().getIcon() == Const.whitePawn || tiletobeKilled.getButton().getIcon() == Const.blackPawn) ||
                (tiletobeKilled.getButton().getIcon() == Const.whiteLady || tiletobeKilled.getButton().getIcon() == Const.blackLady)) {
            return  true;
        }
        return false;
    }




    public static void ladyMove() {

        tile2.getButton().setIcon(tile1.getButton().getIcon());
        tile1.getButton().setIcon(null);
        tile1.getButton().setBorderPainted(false);
        tile1.getButton().repaint();
        tile1 = null;
        tile2 = null;
        changePlayer();

    }

    public static boolean ladyCanMove(Tile tile) {
        if (((isaLady(tile1) && tileHasIcon(tile) == false && (tile.getY() == tile1.getY() - 1 || tile.getY() == tile1.getY() + 1) &&
                (tile.getX() == tile1.getX() - 1 || tile.getX() == tile1.getX() + 1)))){
            return true;
        } else {
            return false;
        }
    }

    public static boolean ladyCanKill() {
        setTiletobeKilled();

        if ((((isaLady(tile1) && tileHasIcon(tile2) == false && ((tile2.getY() == tile1.getY() - 2) && (tile2.getX() == tile1.getX() - 2
                || tile2.getX() == tile1.getX() + 2)))  || ((tile2.getY() == tile1.getY() + 2)) && (tile2.getX() == tile1.getX() - 2
                || tile2.getX() == tile1.getX() + 2)))  && isenemyPawn()) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void ladyKill() {
        if (isaLady(tile1)) {
            if ((tiletobeKilled.getButton().getIcon() == Const.whitePawn) || (tiletobeKilled.getButton().getIcon() == Const.whiteLady)) {
                graczBialy.setPawnsLeft((graczBialy.getPawnsLeft() - 1));
                System.out.println(graczBialy.getPawnsLeft());
            } else if ((tiletobeKilled.getButton().getIcon() == Const.blackPawn) || (tiletobeKilled.getButton().getIcon() == Const.blackLady)) {
                graczCzarny.setPawnsLeft((graczCzarny.getPawnsLeft() - 1));
                System.out.println(graczCzarny.getPawnsLeft());
            } else {
                System.out.println("nikt nie traci");
            }
            tiletobeKilled.getButton().setIcon(null);
            tile2.getButton().setIcon(tile1.getButton().getIcon());
            tile1.getButton().setIcon(null);
            tile1.getButton().setBorderPainted(false);
            tile1.getButton().repaint();
            tile2.getButton().repaint();
            tiletobeKilled.getButton().repaint();
            tile1 = null;
            tile2 = null;
            tiletobeKilled = null;
            changePlayer();
        }
    }


    public static void setLady(Tile tile) {

        if (tile.getButton().getIcon() == Const.whitePawn) {
            tile.getButton().setIcon(Const.whiteLady);
        }
        else if (tile.getButton().getIcon() == Const.blackPawn) {
            tile.getButton().setIcon(Const.blackLady);
        }

    }

    public static boolean isaLady (Tile tile) {
        if (tile.getButton().getIcon() == Const.whiteLady || tile.getButton().getIcon() == Const.blackLady) {
            return true;
        }
        return false;
    }

    public static boolean canbeLady(Tile tile) {
        if (tile.getY() == 0 && tile.getButton().getIcon() == Const.whitePawn) {
            return true;
        }
        else if (tile.getY() == 7 && tile.getButton().getIcon() == Const.blackPawn) {
            return true;
        }

        return false;
    }

    public static boolean isaPawn(Tile tile) {
        if (tile.getButton().getIcon() == Const.whitePawn || tile.getButton().getIcon() == Const.blackPawn) {
            return true;
        }

        return false;
    }


//    public static void anotherkill() {
//
//    }

}
