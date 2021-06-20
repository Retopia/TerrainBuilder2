package pkg23.terrainbuilder2;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Camera;
import javafx.scene.Parent;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Preston Tang
 */
public class TerrainBuilder2 extends Application {

    private boolean camW, camA, camS, camD, camPgDn, camPgUp;

    private PointLight single;
    private Camera camera;

    private Slider speed;
    
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("FXMLDocument.fxml").openStream());
        Scene scene = new Scene(root);

        FXMLDocumentController controller = fxmlLoader.getController();

        //<editor-fold defaultstate="collapsed" desc="camera input checking">
        scene.setOnKeyPressed(
                (KeyEvent event) -> {
                    switch (event.getCode()) {
                        case UP:
                            camW = true;
                            break;
                        case DOWN:
                            camS = true;
                            break;
                        case LEFT:
                            camA = true;
                            break;
                        case RIGHT:
                            camD = true;
                            break;
                        case PAGE_UP:
                            camPgUp = true;
                            break;
                        case PAGE_DOWN:
                            camPgDn = true;
                        default:
                            break;
                    }
                }
        );

        scene.setOnKeyReleased(
                (KeyEvent event) -> {
                    switch (event.getCode()) {
                        case UP:
                            camW = false;
                            break;
                        case DOWN:
                            camS = false;
                            break;
                        case LEFT:
                            camA = false;
                            break;
                        case RIGHT:
                            camD = false;
                            break;
                        case PAGE_UP:
                            camPgUp = false;
                            break;
                        case PAGE_DOWN:
                            camPgDn = false;
                        default:
                            break;
                    }
                });
        //</editor-fold>

        stage.getIcons().add(new Image(getClass().getResourceAsStream("Blue Mountain-01.png")));
        stage.setTitle("23-TerrainBuilder2 By Preston Tang - Icon by Vanessa Kang");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        
        this.stage = stage;

        single = FXMLDocumentController.single;
        camera = FXMLDocumentController.camera;
        speed = controller.speed;

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!FXMLDocumentController.hasLightTransformed) {
                    single.setTranslateX(camera.getTranslateX() + 1000);
                    single.setTranslateY(camera.getTranslateY() + 1000);

                    single.setTranslateZ(camera.getTranslateZ() * 1.55 - 4000);
                    
                    single.setColor(Color.WHITE);
                }
                //<editor-fold defaultstate="collapsed" desc="camera movement">
                if (camW) {
                    camera.setTranslateY(camera.getTranslateY() - speed.getValue() * 2);
                }
                if (camA) {
                    camera.setTranslateX(camera.getTranslateX() - speed.getValue() * 2);
                }
                if (camS) {
                    camera.setTranslateY(camera.getTranslateY() + speed.getValue() * 2);
                }
                if (camD) {
                    camera.setTranslateX(camera.getTranslateX() + speed.getValue() * 2);
                }
                if (camPgUp) {
                    camera.setTranslateZ(camera.getTranslateZ() - speed.getValue() * 2);
                }
                if (camPgDn) {
                    camera.setTranslateZ(camera.getTranslateZ() + speed.getValue() * 2);
                }
//</editor-fold>
            }
        };
        timer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
