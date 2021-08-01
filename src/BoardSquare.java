import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class BoardSquare {

    private static final double EDGE_X = -280;
    private static final double EDGE_Y = -140;
    private Stage stage;
    private int row;
    private int col;
    private Box square;
    /**
     * Creates a single square making ub the
     * base of the board.
     */
    BoardSquare(Stage stage, Group g, int r, int c) {
        row = c;
        col = r;
        this.stage = stage;
        square = new Box(140, 140, 5);
        square.getTransforms().add(new Translate(EDGE_X + 140 * r, EDGE_Y + 140 * c, 0));
        setMaterial("/photos/square.jpg");
        g.getChildren().add(square);
        actions();
    }

    public void setMaterial (String file_path) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream(file_path)));
        square.setMaterial(material);
    }

    public void actions() {
        if (stage == null) {
            throw new IllegalStateException("BoardSquare actions called without stage.");
        }
        square.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setMaterial("/photos/glow.jpg");
            }
        });

        square.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setMaterial("/photos/square.jpg");
            }
        });

        square.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
    }
}
