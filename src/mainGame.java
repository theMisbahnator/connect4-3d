import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import java.util.Scanner;

import static java.awt.image.ImageObserver.WIDTH;

public class mainGame extends Application {

    private static final int WIDTH = 1400;
    private static final int HEIGHT = 800;

    public void start(Stage primaryStage) throws Exception {
        // adds sphere
        Sphere sphere = new Sphere(50);
        Group group = new Group();
        group.getChildren().add(sphere);

        Box base = new Box(30,30,5);
        group.getChildren().add(base);
        base.translateXProperty().set(500);
        base.translateYProperty().set(500);

        Board b = new Board(group);

        // background
        Camera camera = new PerspectiveCamera(true);
        Scene scene = new Scene(group, WIDTH, HEIGHT);
        scene.setFill(Color.BLANCHEDALMOND);
        scene.setCamera(camera);

        b.addUserPiece(true, 0, 0);

        camera.translateXProperty().set(0);
        camera.translateYProperty().set(0);
        camera.translateZProperty().set(-700);
        camera.setNearClip(1);
        camera.setFarClip(1000);

//        BorderPane root = new BorderPane();
//        Scene scene = new Scene(root, 800, 800, true);
//        scene.setFill(Color.BLUE);
//
//        Group group = new Group();
//
//
//        Piece p = new Piece(true, group);
//

        // scene.setOnMouseDragged(event -> {});
        primaryStage.setTitle("3D Connect 4");
        primaryStage.setScene(scene);
        primaryStage.show();

//
//        final PerspectiveCamera camera = new PerspectiveCamera(true);

    }

    public static void main(String[] args) {
        launch(args);
        game();
    }

    private static void game() {
        Player playOne = new Player(true);
        Player playTwo = new Player(false);
        boolean done = false;
        int turn = 0;
    }
}
