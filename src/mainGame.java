import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import java.util.Scanner;

import static java.awt.image.ImageObserver.WIDTH;

public class mainGame extends Application {

    private static final int WIDTH = 1400;
    private static final int HEIGHT = 800;

    public void start(Stage primaryStage) throws Exception {
        Sphere sphere = new Sphere(50);

        Group group = new Group();
        group.getChildren().add(sphere);

        Board b = new Board(group);

        Camera camera = new PerspectiveCamera();
        Scene scene = new Scene(group, WIDTH, HEIGHT);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);

        sphere.translateXProperty().set(WIDTH / 2);
        sphere.translateYProperty().set(HEIGHT / 2);


//        BorderPane root = new BorderPane();
//        Scene scene = new Scene(root, 800, 800, true);
//        scene.setFill(Color.BLUE);
//
//        Group group = new Group();
//
//
//        Piece p = new Piece(true, group);
//
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
