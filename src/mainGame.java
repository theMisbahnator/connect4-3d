import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Scanner;

public class mainGame extends Application {

    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 800, Color.HONEYDEW);

        primaryStage.setScene(scene);
        primaryStage.show();
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
        while(!done) {
            if(turn % 2 == 0) {

            } else {

            }
            turn++;
        }
    }
}
