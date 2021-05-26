public class Piece extends EmptySquare {

    private Player player;

    public Piece(boolean team) {
        if (team) {
            player = new Player(true);
        } else {
            player = new Player(false);
        }
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean getTeam() {
        return player.getTeam();
    }

    public String toString () {
        return getTeam() ? "X" : "Y";
    }
}
