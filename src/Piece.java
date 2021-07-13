import javafx.scene.Group;
import javafx.scene.shape.Cylinder;

public class Piece extends Square {

    private Player player;
    private boolean isEmpty;
    private Group group;
    private int height;
    private int x;
    private int y;

    public Piece(Group g, int height, int x, int y) {
        this.x = x;
        this.y = y;
        this.height = height;
        group = g;
        isEmpty = true;
    }

    public Piece() {
        isEmpty = true;
    }

    public Piece(boolean team) {
        player = new Player(team);
        Cylinder c = new Cylinder(10, 20);
    }

    public Piece(boolean team, Group g, int x, int y) {
        player = new Player(team);
        group = g;
        this.x = x;
        this.y = y;
        activatePiece(team);
    }

    public void activatePiece(boolean team) {
        player = new Player(team);
        Cylinder c = new Cylinder(40, 20, 10);
        group.getChildren().add(c);
        c.translateXProperty().set(x * 30 + 200);
        c.translateYProperty().set(y * 30 + 200);
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public boolean getTeam() {
        return player.getTeam();
    }

    public String toString () {
        return getTeam() ? "X" : "Y";
    }
}
