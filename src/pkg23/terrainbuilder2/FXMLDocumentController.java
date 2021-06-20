package pkg23.terrainbuilder2;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SnapshotParameters;
import javafx.scene.SubScene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.PickResult;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import static pkg23.terrainbuilder2.World3D.moduloFloor;

/**
 *
 * @author Preston Tang
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ChoiceBox<String> biomeSelection;

    @FXML
    private TextField seedN;

    @FXML
    private ChoiceBox<String> noiseSelection;

    @FXML
    private TextField frequencyN;

    @FXML
    private TextField octaveN;

    @FXML
    private TextField lacunN;

    @FXML
    private TextField gainN;

    @FXML
    private Button randomN;

    @FXML
    private TextField sizeN;

    @FXML
    private Button generateN;

    @FXML
    private Button deselect;

    @FXML
    public Slider speed;

    @FXML
    private CheckBox vegetationN;

    @FXML
    private CheckBox riversN;

    @FXML
    private TextField vegetationDensityN;

    @FXML
    private TextField riversFreqN;

    @FXML
    private TextField rowN;

    @FXML
    private ColorPicker colorSelection;

    @FXML
    private Button textureN;

    @FXML
    private Button removeN;

    @FXML
    private ChoiceBox<String> objectSelectionN;

    @FXML
    private TextField translateXN;

    @FXML
    private TextField translateYN;

    @FXML
    private TextField translateZN;

    @FXML
    private TextField lightColorN;

    @FXML
    private Button saveTransformationsN;

    @FXML
    private Button resetTransformationsN;

    @FXML
    private TextArea sourceN;

    @FXML
    private Pane base;

    @FXML
    private MenuItem importTerrain;

    @FXML
    private MenuItem exportTerrain;

    @FXML
    private MenuItem pictureN;

    @FXML
    private MenuItem clear;

    @FXML
    private MenuItem about;

    @FXML
    private MenuItem how;

    @FXML
    private ToggleButton toggleObjectModeN;

    @FXML
    private ToggleButton rowSelectionN;

    //So I can access these in the main class, for camera controls.
    public static PointLight single;
    public static SubScene subScene;
    public static PerspectiveCamera camera;

    public static Object objectToAdd = null;

    public static boolean hasLightTransformed = false;

    public static boolean rowSelectionMode = false;

    public RowMesh selectedRow;

    @FXML
    void deselectOnAction(ActionEvent event) {
        subScene.requestFocus();
    }

    @FXML
    void clearOnAction(ActionEvent event) {
    }

    @FXML
    void generateNOnAction(ActionEvent event) {
        subScene.requestFocus();

        FastNoise fn = new FastNoise();

        NoiseType type;

        //Noise Selection Checking
        switch (noiseSelection.getValue()) {
            case "Value":
                type = NoiseType.VALUE;
                break;

            case "Value Fractal":
                type = NoiseType.VALUE_FRACTAL;
                break;

            case "Perlin":
                type = NoiseType.PERLIN;
                break;

            case "Perlin Fractal":
                type = NoiseType.PERLIN_FRACTAL;
                break;

            case "Simplex":
                type = NoiseType.SIMPLEX;
                break;

            case "Simplex Fractal":
                type = NoiseType.SIMPLEX_FRACTAL;
                break;

            case "Cellular":
                type = NoiseType.CELLULAR;
                break;

            case "White":
                type = NoiseType.WHITE;
                break;

            case "Cubic":
                type = NoiseType.CUBIC;
                break;

            case "Cubic Fractal":
                type = NoiseType.CUBIC_FRACTAL;
                break;

            default:
                type = NoiseType.SIMPLEX_FRACTAL;
                break;
        }

        fn.SetSeed(seedN.getText().isEmpty() ? 123456789 : Integer.parseInt(seedN.getText()));
        fn.SetFrequency(frequencyN.getText().isEmpty() ? 0.011F : Float.parseFloat(frequencyN.getText()));

        int width = sizeN.getText().isEmpty() ? 100 : Integer.parseInt(sizeN.getText().split("x")[0]);
        int height = sizeN.getText().isEmpty() ? 100 : Integer.parseInt(sizeN.getText().split("x")[1]);

        //So putting it as zero is different from not declaring it?
        fn.SetFractalOctaves(0);
        fn.SetFractalLacunarity(0);
        fn.SetFractalGain(0);

        boolean vegetation = vegetationN.isSelected();
        double vegetationDensity = !vegetationDensityN.getText().isEmpty()
                ? Double.valueOf(vegetationDensityN.getText()) : 0.02;
        boolean rivers = riversN.isSelected();
        double riversFreq = !riversFreqN.getText().isEmpty()
                ? Double.valueOf(riversFreqN.getText()) : 0.5;

        World3D.getInstance().getChildren().clear();
        World3D.getInstance().getChildren().addAll(single, camera);
        World3D.getInstance().draw(width, height, fn, biomeSelection.getValue(), type,
                vegetation, vegetationDensity,
                rivers, riversFreq);

        List<RowMesh> rows = World3D.getInstance().getRows();

        Collections.sort(rows);

        for (int i = 0; i < rows.size(); i++) {
            RowMesh row = rows.get(i);
            int iteration = i;

            row.setOnMouseEntered(e -> {
                for (int o = 0; o < rows.size(); o++) {
                    rows.get(o).setSelected(false);
                }
                if (rowSelectionMode) {
                    colorSelection.setValue(row.getColor());
                    row.setSelected(true);
                    rowN.setText("" + iteration);
                    textureN.setText(selectedRow.getTexture() == null ? "No Texture Set" : selectedRow.getTexture().getName());
                    selectedRow = row;
                }
            });

            row.setOnMouseClicked(e -> {
                PickResult pr = e.getPickResult();

                objectToAdd = objectSelectionN.getValue();

                if (objectToAdd != null) {
                    switch ((String) objectToAdd) {
                        case "Tree":
                            Tree object = new Tree(Color.BROWN, Color.DARKGREEN, 7, 5);
                            object.setTranslateX(moduloFloor(pr.getIntersectedPoint().getX(), 20));
                            object.setTranslateY(moduloFloor(pr.getIntersectedPoint().getY(), 20));
                            object.setTranslateZ(pr.getIntersectedPoint().getZ() - 20);

                            object.setOnMouseClicked(ex -> {
                                if (objectToAdd != null && ex.getButton() == MouseButton.PRIMARY) {
                                    World3D.getInstance().getChildren().remove(object);
                                }
                            });

                            World3D.getInstance().getChildren().add(object);
                            break;

                        case "Large Cactus":
                            Cactus object1 = new Cactus(Color.GREEN, 0);
                            object1.setTranslateX(moduloFloor(pr.getIntersectedPoint().getX(), 20));
                            object1.setTranslateY(moduloFloor(pr.getIntersectedPoint().getY(), 20));
                            object1.setTranslateZ(pr.getIntersectedPoint().getZ() - 20);

                            object1.setOnMouseClicked(ex -> {
                                if (objectToAdd != null && ex.getButton() == MouseButton.PRIMARY) {
                                    World3D.getInstance().getChildren().remove(object1);
                                }
                            });

                            World3D.getInstance().getChildren().add(object1);
                            break;
                        case "Small Cactus":
                            Cactus object2 = new Cactus(Color.GREEN, 1);
                            object2.setTranslateX(moduloFloor(pr.getIntersectedPoint().getX(), 20));
                            object2.setTranslateY(moduloFloor(pr.getIntersectedPoint().getY(), 20));
                            object2.setTranslateZ(pr.getIntersectedPoint().getZ() - 20);

                            object2.setOnMouseClicked(ex -> {
                                if (objectToAdd != null && ex.getButton() == MouseButton.PRIMARY) {
                                    World3D.getInstance().getChildren().remove(object2);
                                }
                            });

                            World3D.getInstance().getChildren().add(object2);
                            break;

                        case "Dead Cactus":
                            Cactus object3 = new Cactus(Color.GREEN, 2);
                            object3.setTranslateX(moduloFloor(pr.getIntersectedPoint().getX(), 20));
                            object3.setTranslateY(moduloFloor(pr.getIntersectedPoint().getY(), 20));
                            object3.setTranslateZ(pr.getIntersectedPoint().getZ() - 20);

                            object3.setOnMouseClicked(ex -> {
                                if (objectToAdd != null && ex.getButton() == MouseButton.PRIMARY) {
                                    World3D.getInstance().getChildren().remove(object3);
                                }
                            });

                            World3D.getInstance().getChildren().add(object3);
                            break;
                    }
                }
            });
        }
    }

    @FXML
    void randomNOnAction(ActionEvent event) {
        //Random seed from -2,147,483,648 to +2,147,483,647
        seedN.setText("" + ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @FXML
    void textureNOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(TerrainBuilder2.stage);
        if (selectedFile != null) {
            selectedRow.setTexture(selectedFile);
            textureN.setText(selectedFile.getName());
        }
    }

    @FXML
    void removeNOnAction(ActionEvent event) {
        selectedRow.setTexture(null);
    }

    @FXML
    void importTerrainOnAction(ActionEvent event) {
    }

    @FXML
    void exportTerrainOnAction(ActionEvent event) {
    }

    @FXML
    void howOnAction(ActionEvent event) {
        Alert a = new Alert(AlertType.INFORMATION);
        a.setTitle("How To Use Terrain Builder");
        a.setHeaderText("");
        a.setContentText("Not included for this version.");
        a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        Stage stage = (Stage) a.getDialogPane().getScene().getWindow();
        stage.getIcons().add(
                new Image(this.getClass().getResource("Blue Mountain-01.png").toString()));

        ImageView iv = new ImageView(new Image(getClass().getResource("Blue Mountain-01.png").toExternalForm()));
        iv.setFitHeight(75);
        iv.setFitWidth(75);

        a.setGraphic(iv);

        a.showAndWait();
    }

    @FXML
    void aboutOnAction(ActionEvent event) {

    }

    @FXML
    void riversNOnAction(ActionEvent event) {
    }

    @FXML
    void vegetationNOnAction(ActionEvent event) {
        if (vegetationN.isSelected()) {
            vegetationDensityN.setDisable(false);
        } else {
            vegetationDensityN.setDisable(true);
        }
    }

    @FXML
    void toggleObjectModeNOnAction(ActionEvent event) {
        if (toggleObjectModeN.isSelected()) {
            rowSelectionN.setSelected(false);
            rowSelectionMode = false;
        } else {
            objectToAdd = null;
            subScene.requestFocus();
        }
    }

    @FXML
    void rowSelectionNOnAction(ActionEvent event) {
        if (rowSelectionN.isSelected()) {
            rowSelectionMode = true;
            toggleObjectModeN.setSelected(false);
            objectToAdd = null;

            //Enable some components
            rowN.setDisable(false);
            colorSelection.setDisable(false);
            removeN.setDisable(false);
            textureN.setDisable(false);
        } else {
            //Disable some components
            rowN.setDisable(true);
            colorSelection.setDisable(true);
            removeN.setDisable(true);
            textureN.setDisable(true);

            //Clear selection
            for (int i = 0; i < World3D.getInstance().getRows().size(); i++) {
                World3D.getInstance().getRows().get(i).setSelected(false);
            }

            selectedRow = null;

            rowSelectionMode = false;
            rowN.setText("");
            colorSelection.setValue(Color.WHITE);
            subScene.requestFocus();
        }
    }

    @FXML
    void saveTransformationsNOnAction(ActionEvent event) {
        hasLightTransformed = true;
        single.getTransforms().clear();

        single.setTranslateX(translateXN.getText().isEmpty() ? single.getTranslateX() : Double.valueOf(translateXN.getText()));
        single.setTranslateY(translateYN.getText().isEmpty() ? single.getTranslateY() : Double.valueOf(translateYN.getText()));
        single.setTranslateZ(translateZN.getText().isEmpty() ? single.getTranslateZ() : Double.valueOf(translateZN.getText()));

        //Reflection
        //https://stackoverflow.com/questions/2854043/converting-a-string-to-color-in-java
        Color color;
        try {
            Field field = Class.forName("javafx.scene.paint.Color").getField(lightColorN.getText().toUpperCase());
            color = (Color) field.get(null);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
            color = null; // Not defined
        }

        single.setColor(color == null ? Color.WHITE : color);
    }

    @FXML
    void resetTransformationsNOnAction(ActionEvent event) {
        hasLightTransformed = false;
    }

    //Kinda blurry gotta fix
    @FXML
    void pictureNOnAction(ActionEvent event) {
        WritableImage snapshot = pixelScaleAwareSubSceneSnapshot(1.0);
        subScene.snapshot(null, snapshot);

        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", byteOutput);
        } catch (IOException ex) {
        }

        FileOutputStream fos = null;
        try {

            FileChooser fileChooser = new FileChooser();

            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Portable Network Graphics File", "*.png");
            fileChooser.getExtensionFilters().add(extFilter);

            //Show save file dialog
            File file = fileChooser.showSaveDialog(TerrainBuilder2.stage);

            if (file != null) {
                fos = new FileOutputStream(file);

                byteOutput.writeTo(fos);
            }
        } catch (IOException ioe) {
            // Handle exception here
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
            }
        }

    }

    @FXML
    void colorSelectionOnAction(ActionEvent event) {
        selectedRow.setColor(colorSelection.getValue());
    }

    public static WritableImage pixelScaleAwareSubSceneSnapshot(double pixelScale) {
        WritableImage writableImage = new WritableImage((int) Math.rint(pixelScale * subScene.getWidth()), (int) Math.rint(pixelScale * subScene.getHeight()));
        SnapshotParameters spa = new SnapshotParameters();
        spa.setTransform(Transform.scale(pixelScale, pixelScale));
        return subScene.snapshot(spa, writableImage);
    }

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Faster int check
    //https://stackoverflow.com/questions/237159/whats-the-best-way-to-check-if-a-string-represents-an-integer-in-java
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Value, ValueFractal, Perlin, PerlinFractal, Simplex, SimplexFractal, Cellular, WhiteNoise, Cubic, CubicFractal
        noiseSelection.setValue("Simplex Fractal");
        noiseSelection.getItems().add("Value");
        noiseSelection.getItems().add("Value Fractal");
        noiseSelection.getItems().add("Perlin");
        noiseSelection.getItems().add("Perlin Fractal");
        noiseSelection.getItems().add("Simplex");
        noiseSelection.getItems().add("Simplex Fractal");
        noiseSelection.getItems().add("Cellular");
        noiseSelection.getItems().add("White");
        noiseSelection.getItems().add("Cubic");
        noiseSelection.getItems().add("Cubic Fractal");

        noiseSelection.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                if (noiseSelection.getValue().contains("Fractal")) {
                    octaveN.setDisable(true);
                    lacunN.setDisable(true);
                    gainN.setDisable(true);
                } else {
                    octaveN.setDisable(false);
                    lacunN.setDisable(false);
                    gainN.setDisable(false);
                }
            }
        });

        octaveN.setDisable(false);
        lacunN.setDisable(false);
        gainN.setDisable(false);

        //Disable some components
        rowN.setDisable(true);
        colorSelection.setDisable(true);
        removeN.setDisable(true);
        textureN.setDisable(true);

        //Classic, Rocky, Desert, Snow, Mesa, Ocean, Hell, Deathly, None
        biomeSelection.setValue("Classic");
        biomeSelection.getItems().add("Classic");
        biomeSelection.getItems().add("Rocky");
        biomeSelection.getItems().add("Desert");
        biomeSelection.getItems().add("Snow");
        biomeSelection.getItems().add("Mesa");
        biomeSelection.getItems().add("Ocean");
        biomeSelection.getItems().add("Hell");
        biomeSelection.getItems().add("Deathly");
        biomeSelection.getItems().add("None");

        //List of objects you can add
        objectSelectionN.setValue("Tree");
        objectSelectionN.getItems().add("Tree");
        objectSelectionN.getItems().add("Small Cactus");
        objectSelectionN.getItems().add("Large Cactus");
        objectSelectionN.getItems().add("Dead Cactus");

        rowN.textProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> {
            if (isInteger(rowN.getText()) && Integer.parseInt(rowN.getText()) < World3D.getInstance().getRows().size()) {
                Collections.sort(World3D.getInstance().getRows());
                selectedRow = World3D.getInstance().getRows().get(Integer.parseInt(rowN.getText()));

                for (int i = 0; i < World3D.getInstance().getRows().size(); i++) {
                    World3D.getInstance().getRows().get(i).setSelected(false);
                }

                colorSelection.setValue(selectedRow.getColor());
                textureN.setText(selectedRow.getTexture() == null ? "No Texture Set" : selectedRow.getTexture().getName());

                selectedRow.setSelected(true);
            }
        });

        //Initializing the camera
        camera = new PerspectiveCamera(true);

        //Best camera location for 100x100 (default)
        camera.setTranslateX(1000.0);
        camera.setTranslateY(2420.0);
        camera.setTranslateZ(-3830.0);
        camera.getTransforms().add(new Rotate(20, Rotate.X_AXIS));
        camera.setNearClip(0.1);
        camera.setFarClip(100000.0);

        //Initializing the light source
        single = new PointLight();
        single.setTranslateX(450);
        single.setTranslateY(2000);
        single.setTranslateZ(-8000);

        //Making the Subscene
        subScene = new SubScene(World3D.getInstance(), 664, 574, true, SceneAntialiasing.BALANCED);
        subScene.setFill(Color.TRANSPARENT);
        subScene.setCamera(camera);

        //Caching the SubScene to make it run smoother
        subScene.setCache(true);
        subScene.setCacheHint(CacheHint.SPEED);

        World3D.getInstance().getChildren().addAll(single, camera);

        base.getChildren().add(subScene);
    }
}
