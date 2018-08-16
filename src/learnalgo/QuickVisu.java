/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnalgo;

import java.util.Arrays;
 import javafx.application.Application;
 import javafx.geometry.Pos;
 import javafx.scene.Scene;
 import javafx.scene.control.Button;
 import javafx.scene.layout.BorderPane;
 import javafx.scene.layout.HBox;
 import javafx.scene.layout.Pane;
 import javafx.scene.paint.Color;
 import javafx.scene.shape.Line;
 import javafx.scene.shape.Rectangle;
 import javafx.scene.text.Text;
 import javafx.stage.Stage;

 public class QuickVisu extends Application {
    public final static int ARRAY_SIZE = 20;
    private int[] array = new int[ARRAY_SIZE];
    private int leftP = 1;
    private int rightP = array.length - 1;
    private int pivot = 0;

public void start(Stage primaryStage) {
    AnimationPane pane = new AnimationPane();

    Button btStep = new Button("Step");
    Button btReset = new Button("Reset");

    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(btStep, btReset);
    hBox.setAlignment(Pos.CENTER);

    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(pane);
    borderPane.setBottom(hBox);

    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, 700, 600);
    primaryStage.setTitle("QuickSort");
    primaryStage.setScene(scene);
    primaryStage.show();
    borderPane.setStyle("-fx-background-color: white");
    initializeArray();

    pane.repaint();

    btStep.setOnAction(e -> {
        if (step()) {
            pane.repaint();
        } else {
            pane.repaint();
        }
    });

    btReset.setOnAction(e -> {
        reset();
        pane.repaint();
    });
}

public static void main(String[] args) {
    launch(args);
}

public void initializeArray() {

    for (int i = 0; i < array.length; i++) {
        array[i] = (int) (Math.random() * 999 + 1);
    }

    // Arrays.sort(array);

}

public void reset() {
    leftP = 1;
    rightP = array.length - 1;
    pivot = 0;
    initializeArray();
}

public boolean step() {
    // quicksort(array);
    int begin = 0;
    int pivotVal = array[0];
    int array1;

    if (leftP <= rightP && array[leftP] <= pivotVal) {
        leftP++;
        array1 = array[leftP];
        array[leftP] = array[rightP];
        array[rightP] = array1;
    }

    if (rightP >= leftP && array[rightP] > pivotVal) {
        rightP--;
    }
    if (rightP > leftP) {
        if (rightP != begin) {
            array1 = array[rightP];
            array[rightP] = array[begin];
            array[begin] = array1;
        }
    }
    // Return the index of the pivot element.
    return true;
}

class AnimationPane extends Pane {
    private int startingX = 20;
    private int startingY = 20;
    private int boxWidth = 30;
    private int boxHeight = 20;

    protected void repaint() {
        this.getChildren().clear();

        int x = startingX + 10;
        int y = startingY + 40;

        // Display array
        x = startingX + 10;
        getChildren().add(new Text(x - 15, y + 55, "Array "));
        x += 20;
        getChildren().add(new Text(x + pivot * boxWidth, y + 120, "Pivot"));
        drawArrowLine(x + 15 + pivot * boxWidth, y + 100, x + 15 + pivot * boxWidth, y + 40 + boxHeight);

        getChildren().add(new Text(x - 14 + leftP * boxWidth, startingY, "Left Pointer"));
        drawArrowLine(x + 15 + leftP * boxWidth, startingY, x + 15 + leftP * boxWidth, y + 40);

        getChildren().add(new Text(x - 14 + rightP * boxWidth, startingY, "Right Pointer"));
        drawArrowLine(x + 15 + rightP * boxWidth, startingY, x + 15 + rightP * boxWidth, y + 40);

        for (int k = 0; k < array.length; k++) {
            Rectangle rectangle = new Rectangle(x, y + 40, boxWidth, boxHeight);
            rectangle.setFill(Color.WHITE);
            rectangle.setStroke(Color.BLACK);
            getChildren().add(rectangle);
            if (array[k] != 0) {
                getChildren().add(new Text(x + 5, y + 55, array[k] + ""));
            }
            x = x + boxWidth;
        }
    }

    public void drawArrowLine(double x1, double y1, double x2, double y2) {
        getChildren().add(new Line(x1, y1, x2, y2));

        // find slope of this line
        double slope = ((((double) y1) - (double) y2)) / (((double) x1) - (((double) x2)));

        double arctan = Math.atan(slope);

        // This will flip the arrow 45 off of a
        // perpendicular line at pt x2
        double set45 = 1.57 / 2;

        // arrows should always point towards i, not i+1
        if (x1 < x2) {
            // add 90 degrees to arrow lines
            set45 = -1.57 * 1.5;
        }

        // set length of arrows
        int arrlen = 15;

        // draw arrows on line
        getChildren().add(new Line(x2, y2, (x2 + (Math.cos(arctan + set45) * arrlen)),
                ((y2)) + (Math.sin(arctan + set45) * arrlen)));

        getChildren().add(new Line(x2, y2, (x2 + (Math.cos(arctan - set45) * arrlen)),
                ((y2)) + (Math.sin(arctan - set45) * arrlen)));
    }
}
 }