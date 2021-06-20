package pkg23.terrainbuilder2;

import java.util.Random;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

/**
 *
 * @author Preston Tang
 */
public class Cactus extends Group {

    private final PhongMaterial cactusPM;
    private Color cactusColor;
    private int cactusCount;

    public Cactus(Color cactusColor, int id) {
        cactusPM = new PhongMaterial();
        cactusPM.setDiffuseColor(cactusColor);

        //Not facing the camera (I know there are better ways to do this)
        //Large Cactus
        if (id == 0) {
            if (new Random().nextInt(100) < 50) {
                Box b = new Box();
                b.setHeight(20);
                b.setWidth(20);
                b.setDepth(20);
                b.setTranslateX(super.getTranslateX());
                b.setTranslateY(super.getTranslateY());
                b.setTranslateZ(super.getTranslateZ() - 20);
                b.setMaterial(cactusPM);

                Box b1 = new Box();
                b1.setHeight(20);
                b1.setWidth(20);
                b1.setDepth(20);
                b1.setTranslateX(super.getTranslateX());
                b1.setTranslateY(super.getTranslateY());
                b1.setTranslateZ(super.getTranslateZ() - 40);
                b1.setMaterial(cactusPM);

                Box b2 = new Box();
                b2.setHeight(20);
                b2.setWidth(20);
                b2.setDepth(20);
                b2.setTranslateX(super.getTranslateX());
                b2.setTranslateY(super.getTranslateY());
                b2.setTranslateZ(super.getTranslateZ() - 60);
                b2.setMaterial(cactusPM);

                Box b3 = new Box();
                b3.setHeight(20);
                b3.setWidth(20);
                b3.setDepth(20);
                b3.setTranslateX(super.getTranslateX());
                b3.setTranslateY(super.getTranslateY());
                b3.setTranslateZ(super.getTranslateZ() - 80);
                b3.setMaterial(cactusPM);

                Box b4 = new Box();
                b4.setHeight(20);
                b4.setWidth(20);
                b4.setDepth(20);
                b4.setTranslateX(super.getTranslateX());
                b4.setTranslateY(super.getTranslateY());
                b4.setTranslateZ(super.getTranslateZ() - 100);
                b4.setMaterial(cactusPM);

                Box b5 = new Box();
                b5.setHeight(20);
                b5.setWidth(20);
                b5.setDepth(20);
                b5.setTranslateX(super.getTranslateX());
                b5.setTranslateY(super.getTranslateY());
                b5.setTranslateZ(super.getTranslateZ() - 120);
                b5.setMaterial(cactusPM);

                Box b6 = new Box();
                b6.setHeight(20);
                b6.setWidth(20);
                b6.setDepth(20);
                b6.setTranslateX(super.getTranslateX());
                b6.setTranslateY(super.getTranslateY());
                b6.setTranslateZ(super.getTranslateZ() - 140);
                b6.setMaterial(cactusPM);

                Box b7 = new Box();
                b7.setHeight(20);
                b7.setWidth(20);
                b7.setDepth(20);
                b7.setTranslateX(super.getTranslateX());
                b7.setTranslateY(super.getTranslateY() - 20);
                b7.setTranslateZ(super.getTranslateZ() - 80);
                b7.setMaterial(cactusPM);

                Box b8 = new Box();
                b8.setHeight(20);
                b8.setWidth(20);
                b8.setDepth(20);
                b8.setTranslateX(super.getTranslateX());
                b8.setTranslateY(super.getTranslateY() + 20);
                b8.setTranslateZ(super.getTranslateZ() - 80);
                b8.setMaterial(cactusPM);

                Box b9 = new Box();
                b9.setHeight(20);
                b9.setWidth(20);
                b9.setDepth(20);
                b9.setTranslateX(super.getTranslateX());
                b9.setTranslateY(super.getTranslateY() - 40);
                b9.setTranslateZ(super.getTranslateZ() - 100);
                b9.setMaterial(cactusPM);

                Box b10 = new Box();
                b10.setHeight(20);
                b10.setWidth(20);
                b10.setDepth(20);
                b10.setTranslateX(super.getTranslateX());
                b10.setTranslateY(super.getTranslateY() + 40);
                b10.setTranslateZ(super.getTranslateZ() - 100);
                b10.setMaterial(cactusPM);

                super.getChildren().addAll(b, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10);
            } else {
                //Facing the camera
                Box b = new Box();
                b.setHeight(20);
                b.setWidth(20);
                b.setDepth(20);
                b.setTranslateX(super.getTranslateX());
                b.setTranslateY(super.getTranslateY());
                b.setTranslateZ(super.getTranslateZ() - 20);
                b.setMaterial(cactusPM);

                Box b1 = new Box();
                b1.setHeight(20);
                b1.setWidth(20);
                b1.setDepth(20);
                b1.setTranslateX(super.getTranslateX());
                b1.setTranslateY(super.getTranslateY());
                b1.setTranslateZ(super.getTranslateZ() - 40);
                b1.setMaterial(cactusPM);

                Box b2 = new Box();
                b2.setHeight(20);
                b2.setWidth(20);
                b2.setDepth(20);
                b2.setTranslateX(super.getTranslateX());
                b2.setTranslateY(super.getTranslateY());
                b2.setTranslateZ(super.getTranslateZ() - 60);
                b2.setMaterial(cactusPM);

                Box b3 = new Box();
                b3.setHeight(20);
                b3.setWidth(20);
                b3.setDepth(20);
                b3.setTranslateX(super.getTranslateX());
                b3.setTranslateY(super.getTranslateY());
                b3.setTranslateZ(super.getTranslateZ() - 80);
                b3.setMaterial(cactusPM);

                Box b4 = new Box();
                b4.setHeight(20);
                b4.setWidth(20);
                b4.setDepth(20);
                b4.setTranslateX(super.getTranslateX());
                b4.setTranslateY(super.getTranslateY());
                b4.setTranslateZ(super.getTranslateZ() - 100);
                b4.setMaterial(cactusPM);

                Box b5 = new Box();
                b5.setHeight(20);
                b5.setWidth(20);
                b5.setDepth(20);
                b5.setTranslateX(super.getTranslateX());
                b5.setTranslateY(super.getTranslateY());
                b5.setTranslateZ(super.getTranslateZ() - 120);
                b5.setMaterial(cactusPM);

                Box b6 = new Box();
                b6.setHeight(20);
                b6.setWidth(20);
                b6.setDepth(20);
                b6.setTranslateX(super.getTranslateX());
                b6.setTranslateY(super.getTranslateY());
                b6.setTranslateZ(super.getTranslateZ() - 140);
                b6.setMaterial(cactusPM);

                Box b7 = new Box();
                b7.setHeight(20);
                b7.setWidth(20);
                b7.setDepth(20);
                b7.setTranslateX(super.getTranslateX() - 20);
                b7.setTranslateY(super.getTranslateY());
                b7.setTranslateZ(super.getTranslateZ() - 80);
                b7.setMaterial(cactusPM);

                Box b8 = new Box();
                b8.setHeight(20);
                b8.setWidth(20);
                b8.setDepth(20);
                b8.setTranslateX(super.getTranslateX() + 20);
                b8.setTranslateY(super.getTranslateY());
                b8.setTranslateZ(super.getTranslateZ() - 80);
                b8.setMaterial(cactusPM);

                Box b9 = new Box();
                b9.setHeight(20);
                b9.setWidth(20);
                b9.setDepth(20);
                b9.setTranslateX(super.getTranslateX() - 40);
                b9.setTranslateY(super.getTranslateY());
                b9.setTranslateZ(super.getTranslateZ() - 100);
                b9.setMaterial(cactusPM);

                Box b10 = new Box();
                b10.setHeight(20);
                b10.setWidth(20);
                b10.setDepth(20);
                b10.setTranslateX(super.getTranslateX() + 40);
                b10.setTranslateY(super.getTranslateY());
                b10.setTranslateZ(super.getTranslateZ() - 100);
                b10.setMaterial(cactusPM);

                super.getChildren().addAll(b, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10);
            }
            //Small Cactus
        } else if (id == 1) {
            Box b = new Box();
            b.setHeight(20);
            b.setWidth(20);
            b.setDepth(20);
            b.setTranslateX(super.getTranslateX());
            b.setTranslateY(super.getTranslateY());
            b.setTranslateZ(super.getTranslateZ() - 20);
            b.setMaterial(cactusPM);

            Box b1 = new Box();
            b1.setHeight(20);
            b1.setWidth(20);
            b1.setDepth(20);
            b1.setTranslateX(super.getTranslateX());
            b1.setTranslateY(super.getTranslateY());
            b1.setTranslateZ(super.getTranslateZ() - 40);
            b1.setMaterial(cactusPM);

            Box b2 = new Box();
            b2.setHeight(20);
            b2.setWidth(20);
            b2.setDepth(20);
            b2.setTranslateX(super.getTranslateX());
            b2.setTranslateY(super.getTranslateY());
            b2.setTranslateZ(super.getTranslateZ() - 60);
            b2.setMaterial(cactusPM);

            Box b3 = new Box();
            b3.setHeight(20);
            b3.setWidth(20);
            b3.setDepth(20);
            b3.setTranslateX(super.getTranslateX());
            b3.setTranslateY(super.getTranslateY());
            b3.setTranslateZ(super.getTranslateZ() - 80);
            b3.setMaterial(cactusPM);

            super.getChildren().addAll(b, b1, b2, b3);
            //Dead Cactus
        } else if (id == 2) {
            PhongMaterial rip = new PhongMaterial();
            rip.setDiffuseColor(Color.BLACK);

            Box b = new Box();
            b.setHeight(20);
            b.setWidth(20);
            b.setDepth(20);
            b.setTranslateX(super.getTranslateX());
            b.setTranslateY(super.getTranslateY());
            b.setTranslateZ(super.getTranslateZ() - 20);
            b.setMaterial(rip);

            Box b1 = new Box();
            b1.setHeight(20);
            b1.setWidth(20);
            b1.setDepth(20);
            b1.setTranslateX(super.getTranslateX());
            b1.setTranslateY(super.getTranslateY());
            b1.setTranslateZ(super.getTranslateZ() - 40);
            b1.setMaterial(rip);

            Box b2 = new Box();
            b2.setHeight(20);
            b2.setWidth(20);
            b2.setDepth(20);
            b2.setTranslateX(super.getTranslateX());
            b2.setTranslateY(super.getTranslateY());
            b2.setTranslateZ(super.getTranslateZ() - 60);
            b2.setMaterial(rip);

            Box b3 = new Box();
            b3.setHeight(20);
            b3.setWidth(20);
            b3.setDepth(20);
            b3.setTranslateX(super.getTranslateX());
            b3.setTranslateY(super.getTranslateY());
            b3.setTranslateZ(super.getTranslateZ() - 80);
            b3.setMaterial(rip);

            super.getChildren().addAll(b, b1, b2, b3);
        }
    }

    public Color getCactusColor() {
        return cactusColor;
    }

    public int getCactusCount() {
        return cactusCount;
    }
}
