import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
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
        Cylinder piece = new Cylinder(40, 20);
        PhongMaterial material = new PhongMaterial();
        if (team) {
            material.setDiffuseColor(Color.AQUA);
        } else {
            material.setDiffuseColor(Color.BEIGE);
        }
        piece.setMaterial(material);
        group.getChildren().add(piece);
        piece.translateXProperty().set(x * 100);
        piece.translateYProperty().set(y * 100);
        piece.translateZProperty().set(0);
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
