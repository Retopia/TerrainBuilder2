package pkg23.terrainbuilder2;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

/**
 *
 * @author Preston Tang
 */
public class Tree extends Group {

    private PhongMaterial logPM;
    private PhongMaterial leavesPM;

    private final Color logColor;
    private final Color leavesColor;

    private final int logCount;
    private final int leavesDiameter;

    public Tree(Color logColor, Color leavesColor, int logCount, int leavesDiameter) {
        this.logColor = logColor;
        this.leavesColor = leavesColor;
        this.logCount = logCount;
        this.leavesDiameter = leavesDiameter;

        for (int i = 1; i < logCount + 1; i++) {
            Box b = new Box();
            b.setWidth(20);
            b.setHeight(20);
            b.setDepth(20);

            b.setTranslateX(super.getTranslateX());
            b.setTranslateY(super.getTranslateY());
            b.setTranslateZ(super.getTranslateZ() - (i * 20));

            logPM = new PhongMaterial();
            logPM.setDiffuseColor(logColor);
            b.setMaterial(logPM);

            super.getChildren().add(b);
        }

        for (int x = 1; x < leavesDiameter + 1; x++) {
            for (int y = 1; y < leavesDiameter + 1; y++) {
                for (int z = 1; z < leavesDiameter + 1; z++) {
                    Box b = new Box();
                    b.setWidth(20);
                    b.setHeight(20);
                    b.setDepth(20);

                    b.setTranslateX(super.getTranslateX() - (((leavesDiameter - 1) / 2 + 1) * 20) + (20 * x));
                    b.setTranslateY(super.getTranslateY() - (((leavesDiameter - 1) / 2 + 1) * 20) + (20 * y));
                    b.setTranslateZ(super.getTranslateZ() - (logCount * 20) - (z * 20));

                    leavesPM = new PhongMaterial();
                    leavesPM.setDiffuseColor(leavesColor);
                    b.setMaterial(leavesPM);

                    super.getChildren().add(b);
                }
            }
        }
    }

    public Color getLogColor() {
        return logColor;
    }

    public Color getLeavesColor() {
        return leavesColor;
    }

    public int getLogCount() {
        return logCount;
    }

    public int getLeavesDiameter() {
        return leavesDiameter;
    }
}
