import javafx.application.Application;
import javafx.event.EventType;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class mainGame extends Application implements CONNECT_CONSTANTS {

    private static final int WIDTH = 1400;
    private static final int HEIGHT = 800;

    public void start(Stage primaryStage) throws Exception {
        Group boardGroup = new Group();
        Group pieceGroup = new Group();
        Group allGroups = new Group();
        allGroups.getChildren().addAll(prepareBackground(), boardGroup, pieceGroup);

        /*
        ways to place peices in 3-D space
        - have poles sticking out from each square, clicking on the pole allows a peice to appear
        -
         */

        Board b = new Board(primaryStage, boardGroup, pieceGroup);

        // background
        PerspectiveCamera camera = new PerspectiveCamera();
        Scene scene = new Scene(allGroups, WIDTH, HEIGHT);
        scene.setFill(Color.BLACK);
        scene.setCamera(camera);

        b.addUserPiece(true, 0, 0);
        b.addUserPiece(false, 1,1);


        camera.setVerticalFieldOfView(false);

        camera.translateXProperty().set(-700);
        camera.translateYProperty().set(-400);
        camera.translateZProperty().set(-1500);

        // camera.setFieldOfView(30);
        camera.setNearClip(1);
        camera.setFarClip(10000);

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

        zoomFunction(primaryStage, boardGroup);
        zoomFunction(primaryStage, pieceGroup);
        angleBoard(primaryStage, boardGroup);
        angleBoard(primaryStage, pieceGroup);
//
//        final PerspectiveCamera camera = new PerspectiveCamera(true);

    }

    public void zoomFunction(Stage stage, Group group) {
        stage.addEventHandler(ScrollEvent.SCROLL, event -> {
            double scrollMovement = event.getDeltaY();
            System.out.println(group.getTranslateZ() + scrollMovement);
            group.translateZProperty().set(group.getTranslateZ() + scrollMovement);
        });
    }

    public void angleBoard(Stage stage, Group group) {
        // sets base rotation
        group.rotationAxisProperty().set(new Point3D(1,0,0));
        group.rotateProperty().set(140);

        stage.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            System.out.println(mouseEvent.getX());
            System.out.println(mouseEvent.getY());
        });

        stage.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            double mouseYMovement = event.getY();
            double mouseXMovement = event.getX();
            group.rotationAxisProperty().set(new Point3D(1,0,0));
            group.rotateProperty().set(-mouseXMovement);
        });
    }

    public void rotateBoard(Stage stage, Group group) {
        int start = 0;
        stage.addEventHandler(MouseEvent.MOUSE_PRESSED, clicked -> {

        });

        stage.addEventHandler(MouseEvent.MOUSE_RELEASED, clicked -> {

        });
    }

    public ImageView prepareBackground () {
        Image photo = new Image(getClass().getResourceAsStream("/photos/backround.jpg"),
                2.5 * WIDTH, 2.5 *  HEIGHT, false, false);
        ImageView image = new ImageView(photo);
        image.getTransforms().add(new Translate(-photo.getWidth()/2, -photo.getHeight()/2, 800));
        return image;
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
