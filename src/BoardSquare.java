import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class BoardSquare {

    private static final double EDGE_X = -280;
    private static final double EDGE_Y = -140;
    /**
     * Creates a single square making ub the
     * base of the board.
     */
    BoardSquare(Group g, int r, int c) {
        Box square = new Box(140, 140, 5);
        square.translateXProperty().set(EDGE_X + 140 * r);
        square.translateYProperty().set(EDGE_Y + 140 * c);
        square.translateZProperty().set(0);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("square.jpg")));
        square.setMaterial(material);
        g.getChildren().add(square);
    }
}
