package pkg23.terrainbuilder2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import org.fxyz3d.geometry.Point3D;
import org.fxyz3d.shapes.primitives.helper.MarkerFactory;

/**
 *
 * @author Preston Tang
 */
public class World3D extends Group {

    private static World3D instance;

    private List<RowMesh> rowMeshList;

    private World3D() {
        rowMeshList = new ArrayList<>();
    }

    public static World3D getInstance() {
        if (instance == null) {
            instance = new World3D();
        }
        return instance;
    }

    public void draw(int width, int height, FastNoise fn, String biome, NoiseType type,
            boolean vegetation, double vegetationDensity, boolean rivers, double riverDensity) {

        rowMeshList.clear();

        String toAdd = "";

        List<RowMesh> scatterMeshList;

        List<Point3D> blocks = new ArrayList<>();
        List<Color> colors = new ArrayList<>();

        for (int r = 0; r < width; r++) {
            for (int c = 0; c < height; c++) {
                Point3D block = new Point3D(0, 0, 0, 0);

                block.x = 20 * r;
                block.y = 20 * c;

                switch (type) {
                    case VALUE:
                        block.z = (float) (-20 * Math.abs(Math.floor(fn.GetValue(r, c) * 20)));
                        break;
                    case VALUE_FRACTAL:
                        block.z = (float) (-20 * Math.abs(Math.floor(fn.GetValueFractal(r, c) * 20)));
                        break;
                    case PERLIN:
                        block.z = (float) (-20 * Math.abs(Math.floor(fn.GetPerlin(r, c) * 20)));
                        break;
                    case PERLIN_FRACTAL:
                        block.z = (float) (-20 * Math.abs(Math.floor(fn.GetPerlinFractal(r, c) * 20)));
                        break;
                    case SIMPLEX:
                        block.z = (float) (-20 * Math.abs(Math.floor(fn.GetSimplex(r, c) * 20)));
                        break;
                    case SIMPLEX_FRACTAL:
                        block.z = (float) (-20 * Math.abs(Math.floor(fn.GetSimplexFractal(r, c) * 20)));
                        break;
                    case CELLULAR:
                        block.z = (float) (-20 * Math.abs(Math.floor(fn.GetCellular(r, c) * 20)));
                        break;
                    case WHITE:
                        block.z = (float) (-20 * Math.abs(Math.floor(fn.GetWhiteNoise(r, c) * 20)));
                        break;
                    case CUBIC:
                        block.z = (float) (-20 * Math.abs(Math.floor(fn.GetCubic(r, c) * 20)));
                        break;
                    case CUBIC_FRACTAL:
                        block.z = (float) (-20 * Math.abs(Math.floor(fn.GetCubicFractal(r, c) * 20)));
                        break;
                }

                Color color = Color.WHITE;
                switch (biome) {
                    case "Classic":
                        if (Math.abs(block.z / 20) < 2 && rivers) {
                            block.z = -20;
                            color = Color.CORNFLOWERBLUE;
                        } else if (Math.abs(block.z / 20) < 3) {
                            color = Color.LEMONCHIFFON;
                        } else if (Math.abs(block.z / 20) < 12) {
                            color = Color.MEDIUMSEAGREEN;
                        } else {
                            color = Color.WHITESMOKE;
                        }
                        toAdd = "Tree";
                        break;

                    case "Rocky":
                        if (Math.abs(block.z / 20) < 2 && rivers) {
                            block.z = -20;
                            color = Color.CORNFLOWERBLUE;
                        } else if (Math.abs(block.z / 20) < 3) {
                            color = Color.LEMONCHIFFON;
                        } else if (Math.abs(block.z / 20) < 5) {
                            color = Color.MEDIUMSEAGREEN;
                        } else {
                            color = Color.rgb(181, 178, 178);
                        }
                        toAdd = "Tree";
                        break;

                    case "Desert":
                        if (Math.abs(block.z / 20) < 2 && rivers) {
                            block.z = -20;
                            color = Color.DEEPSKYBLUE;
                        } else {
                            color = Color.LEMONCHIFFON;
                        }
                        toAdd = "Cactus";
                        break;

                    case "Snow":
                        if (Math.abs(block.z / 20) < 2 && rivers) {
                            block.z = -20;
                            color = Color.CORNFLOWERBLUE;
                        } else {
                            color = Color.WHITESMOKE;
                        }
                        toAdd = "Tree";
                        break;

                    case "Mesa": //Designed using this program!
                        if (Math.abs(block.z / 20) < 2 && rivers) {
                            block.z = -20;
                            color = Color.rgb(80, 125, 207);
                        } else if (Math.abs(block.z / 20) == 4
                                || Math.abs(block.z / 20) == 5) {
                            color = Color.rgb(153, 77, 0);
                        } else if (Math.abs(block.z / 20) == 6) {
                            color = Color.rgb(204, 102, 51);
                        } else if (Math.abs(block.z / 20) == 8) {
                            color = Color.rgb(201, 69, 46);
                        } else if (Math.abs(block.z / 20) == 9) {
                            color = Color.rgb(213, 213, 186);
                        } else if (Math.abs(block.z / 20) == 12) {
                            color = Color.rgb(230, 230, 204);
                        } else if (Math.abs(block.z / 20) == 13) {
                            color = Color.rgb(230, 230, 204);
                        } else {
                            color = Color.DARKORANGE;
                        }
                        toAdd = "Tree & Cactus";
                        break;

                    case "Ocean":
                        if (Math.abs(block.z / 20) < 8 && rivers) {
                            block.z = -140;
                            color = Color.CORNFLOWERBLUE;
                        } else if (Math.abs(block.z / 20) < 9) {
                            color = Color.LEMONCHIFFON;
                        } else {
                            color = Color.MEDIUMSEAGREEN;
                        }
                        toAdd = "Tree";
                        break;

                    case "Hell":
                        if (Math.abs(block.z / 20) < 2 && rivers) {
                            block.z = -20;
                            color = Color.rgb(255, 144, 0);
                        } else if (Math.abs(block.z / 20) < 3) {
                            color = Color.rgb(44, 38, 0);
                        } else {
                            color = Color.BROWN;
                        }
                        toAdd = "BurntTree & BurntCactus";
                        break;

                    case "Deathly":
                        if (Math.abs(block.z / 20) < 2 && rivers) {
                            block.z = -20;
                            color = Color.rgb(35, 35, 35);
                        } else if (Math.abs(block.z / 20) < 3) {
                            color = Color.rgb(113, 109, 106);
                        } else {
                            color = Color.rgb(55, 55, 55);
                        }
                        toAdd = "BurntTree & BurntCactus";
                        break;

                    case "None":
                        //Uses Default Color
                        color = (Color.WHITE);
                        toAdd = "Null";
                        break;
                }

                if (!colors.contains(color)) {
                    colors.add(color);
                }

                if (toAdd.equals("Tree")) {
                    if (new Random().nextInt(1000) < vegetationDensity * 10 && color != Color.CORNFLOWERBLUE
                            && vegetation) {
                        Tree t = new Tree(Color.BROWN, Color.DARKGREEN, 7, 5);
                        t.setTranslateX(block.x);
                        t.setTranslateY(block.y);
                        t.setTranslateZ(block.z - 20);

                        super.getChildren().add(t);
                    }
                }
                if (toAdd.contains("Cactus")) {
                    if (new Random().nextInt(1000) < vegetationDensity * 10 && color != Color.CORNFLOWERBLUE
                            && vegetation) {
                        Cactus t = new Cactus(Color.LIMEGREEN, new Random().nextInt(3));
                        t.setTranslateX(block.x);
                        t.setTranslateY(block.y);
                        t.setTranslateZ(block.z - 20);

                        super.getChildren().add(t);
                    }
                }

                block.f = colors.indexOf(color);

                blocks.add(block);
            }
        }

        List<Float> rows = blocks.stream()
                .map(b -> b.z)
                .distinct()
                .collect(Collectors.toList());

        scatterMeshList = rows.stream()
                .map(row -> {
                    List<Point3D> blocksPerRow = blocks.stream()
                            .filter(p -> p.z == row)
                            .collect(Collectors.toList());
                    RowMesh scatterMesh = new RowMesh(blocksPerRow, 20);
                    scatterMesh.setMarker(MarkerFactory.Marker.CUBE);
                    scatterMesh.setColor(colors.get((int) blocksPerRow.get(0).f));

                    return scatterMesh;
                })
                .collect(Collectors.toList());

        rowMeshList = scatterMeshList;
        this.getChildren().addAll(scatterMeshList);
    }

    public List<RowMesh> getRows() {
        return rowMeshList;
    }

    //https://stackoverflow.com/questions/27175439/how-do-you-round-down-to-the-nearest-multiple-of-20in-java
    public static double moduloFloor(double toRound, double modulo) {
        return toRound - (toRound % modulo);
    }
}
