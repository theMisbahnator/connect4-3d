import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;

public class Piece extends Square {

    private Player player;
    private boolean isEmpty;
    private Group group;
    private int height;
    private Cylinder piece;
    private int row;
    private int col;

    public Piece(Group g, int height, int row, int col) {
        this.row = row;
        this.col = col;
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
        this.row = x;
        this.col = y;
        activatePiece(team);
    }


    public void activatePiece(boolean team) {
        player = new Player(team);
        piece = new Cylinder(50, 30);
        PhongMaterial material = new PhongMaterial();
        if (team) {
            material.setDiffuseMap(new Image(getClass().getResourceAsStream("/photos/amogus.jpg")));
        } else {
            material.setDiffuseMap(new Image(getClass().getResourceAsStream("/photos/blue.jpg")));
        }
        isEmpty = false;
        piece.setMaterial(material);
        group.getChildren().add(piece);
        piece.translateXProperty().set(row * 140);
        piece.translateYProperty().set(col * 140);
        piece.translateZProperty().set(30 * height + 10);
        piece.rotationAxisProperty().set(new Point3D(1,0,0));
        piece.rotateProperty().set(90);
        actions();
    }

    public void actions() {
        piece.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                BoardSquare sqr = Board.getBoardSquare(col, row);
                sqr.setMaterial("/photos/glow.jpg", sqr.getBox());
            }
        });
        piece.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                BoardSquare sqr = Board.getBoardSquare(col, row);
                sqr.setMaterial("/photos/square.jpg", sqr.getBox());
            }
        });
        piece.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int height = Board.getOpenSquareHeight(col, row);
                Board.addUserPiece(Board.getTurn(), col, row);
                System.out.println(Board.isGameDone(col, row, height, Board.getTurn()));
                Board.changeTurn();
            }
        });
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
