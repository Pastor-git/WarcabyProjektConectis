package Players;

public class Player {
    String name;
    boolean turn;
    int pawnsLeft;


    public Player(String name, boolean turn) {
        this.name = name;
        this.turn = turn;
        this.pawnsLeft = 12;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public int isPawnsLeft() {
        return pawnsLeft;
    }

    public void setPawnsLeft(int pawnsLeft) {
        this.pawnsLeft = pawnsLeft;
    }

    public int getPawnsLeft() {
        return pawnsLeft;
    }
}

