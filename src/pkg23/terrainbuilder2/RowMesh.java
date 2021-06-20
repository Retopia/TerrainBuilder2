package pkg23.terrainbuilder2;

import java.io.File;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import org.fxyz3d.geometry.Point3D;
import org.fxyz3d.shapes.primitives.ScatterMesh;

/**
 *
 * @author Preston Tang
 */
public class RowMesh extends ScatterMesh implements Comparable<RowMesh> {

    private boolean selected = false;

    private PhongMaterial pm;
    private File file;

    private Color c;

    public RowMesh(List<Point3D> points, int size) {
        super(points, size);
    }

    public void setColor(Color c) {
        this.c = c;
        super.setTextureModeNone(c);
    }

    public Color getColor() {
        return this.c;
    }

    public void setTexture(File file) {
        if (file != null) {
            this.file = file;
            super.setTextureModeImage("file:/" + file.getAbsolutePath());
        } else {
            this.file = null;
            this.setTextureModeNone(this.getColor());
        }
    }

    public void setSelected(boolean selected) {
        this.selected = selected;

        if (selected) {
            if (this.getTexture() == null) {
                this.setTextureModeNone(Color.BLACK);
            } else {
                this.setTextureModeNone(Color.BLACK);
                this.setTextureModeImage(null);
            }
        } else {
            if (this.getTexture() == null) {
                this.setTextureModeNone(this.getColor());
            } else {
                this.setTexture(this.getTexture());
                this.setTextureModeNone(null);
            }
        }
    }

    public boolean isSelected() {
        return this.selected;
    }

    public File getTexture() {
        return this.file;
    }

    @Override
    public int compareTo(RowMesh o) {
        if (this.getTranslateZ() > o.getTranslateZ()) {
            return -1;
        }

        if (this.getTranslateZ() < o.getTranslateZ()) {
            return 1;
        }

        return 0;
    }
}
