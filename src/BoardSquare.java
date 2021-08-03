import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.util.Iterator;

public class BoardSquare {

    private static final double EDGE_X = 0;
    private static final double EDGE_Y = 0;
    private Stage stage;
    private Group pieceGroup;
    private int row;
    private int col;
    private Box square;
    private AmbientLight hover;
    private Group boardGroup;
    /**
     * Creates a single square making ub the
     * base of the board.
     */
    BoardSquare(Stage stage, Group g, Group pieces,  int r, int c) {
        row = r;
        col = c;
        pieceGroup = pieces;
        boardGroup = g;
        this.stage = stage;
        square = new Box(138, 138, 5);
        square.getTransforms().add(new Translate(EDGE_Y + 140 * c, EDGE_X + 140 * r, 0));
        setMaterial("/photos/square.jpg", square);
        g.getChildren().add(square);
        actions();
    }

    public Box getBox() {
        return square;
    }

    public void setMaterial (String file_path, Box box) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream(file_path)));
        box.setMaterial(material);
    }

    public void actions() {
        if (stage == null) {
            throw new IllegalStateException("BoardSquare actions called without stage.");
        }
        square.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setMaterial("/photos/glow.jpg", square);
            }
        });

        square.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setMaterial("/photos/square.jpg", square);
            }
        });

        square.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("x: " + row + ", col: " + col);
                int height = Board.getOpenSquareHeight(row, col);
                Board.addUserPiece(Board.getTurn(), row, col);
                System.out.println(Board.isGameDone(row, col, height, Board.getTurn()));
                Board.changeTurn();

            }
        });
    }
}
