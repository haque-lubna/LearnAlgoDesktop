/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnalgo;


 
import static java.awt.Color.blue;
import java.util.Random;
import java.util.Scanner;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.GREEN;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
 

public class SelectionVisu extends Application {
 
    private static final double ELEMENT_WIDTH = 55;
    private static final double ELEMENT_HEIGHT = 55;
    private static final double CURSOR_HEIGHT = 20;
    private static final double SPACING = 10;
 
    private static final int NUM_OF_ELEMENTS = 10;
    private static final int MAX_VALUE = 200;
 
    private static final double BORDER_RADII = 5;
    private static final double BORDER_WIDTH = 3;
    private static final Border ELEMENT_BORDER = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(BORDER_RADII), new BorderWidths(BORDER_WIDTH)));
    private static final double FONT_SIZE = 18;
    private static final Font DEFAULT_FONT = Font.font(FONT_SIZE);
 
    private static final int TRANSITION_DELAY = 400;
 
    private SequentialTransition transitions;
    private StackPane animationPane;
    private Element<Integer>[] elements;
    private Label iLabel, iPlusOneLabel, nLabel;
    private Label[] indexLabels;
    private Button startButton;
    private TextField txt;
    private Button inputButton;
    
 
    private static class Element<T extends Comparable<T>> {
 
        T value;
        Label label;
 
        Element(T value, Label label) {
            this.value = value;
            this.label = label;
        }

//        private void setUnFocusColor(Color web) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
 
    }
 
    String Input = "";
    @Override
    public void start(Stage primaryStage) {
        assert NUM_OF_ELEMENTS > 1 : "Only designed for n > 1 elements";
 
        elements = new Element[NUM_OF_ELEMENTS];
 
        animationPane = createAnimationPane();
        indexLabels = createIndexLabels();
        iLabel = createCursorLabel("i", 0, 0);
        iPlusOneLabel = createCursorLabel("i + 1", 1, 0);
        nLabel = createCursorLabel("n", NUM_OF_ELEMENTS - 1, 1);
         txt = new TextField();
         txt.setPadding(new Insets(20,20,20,20)) ;   
         txt.setText("65 5 12 45 87 43 11 40 76 33");
        startButton = new Button();
        inputButton = new Button();
       //inputButton.setPadding(new Insets(3,20,20,160)) ;
        inputButton.setText("Give Input");
        inputButton.setMaxWidth(Double.MAX_VALUE);
        inputButton.setContentDisplay(ContentDisplay.CENTER);
        startButton.setFont(DEFAULT_FONT);
        animationPane.setBackground(Background.EMPTY);
         
        initialise();
 
        VBox root = new VBox();
        root.setPadding(new Insets(SPACING));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(animationPane,txt,startButton);
        
        root.setStyle("-fx-background-color:white ");
        Scene scene = new Scene(root);
        primaryStage.setTitle("Selection Sort");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
         root.setStyle("-fx-background-color: white ");
    }
 
    private static StackPane createAnimationPane() {
        final double ANIMATION_PANE_WIDTH = 700;
        final double ANIMATION_PANE_HEIGHT = 500;
        StackPane pane = new StackPane();
        pane.setAlignment(Pos.TOP_LEFT);
        pane.setMinSize(ANIMATION_PANE_WIDTH, ANIMATION_PANE_HEIGHT);
        return pane;
    }
 
    private static Label createCursorLabel(String cursorLetter, int index, int yOffset) {
        Label label = new Label(cursorLetter);
        label.setFont(DEFAULT_FONT);
        label.setMinSize(ELEMENT_WIDTH, CURSOR_HEIGHT);
        label.setAlignment(Pos.CENTER);
        label.setTranslateY(ELEMENT_HEIGHT + SPACING * 2 + CURSOR_HEIGHT * (yOffset + 1));
        label.setTranslateX(getXPos(index));
        return label;
    }
 
    private static Label[] createIndexLabels() {
        Label[] labels = new Label[NUM_OF_ELEMENTS];
        for (int i = 0; i < NUM_OF_ELEMENTS; i++) {
            Label label = new Label(String.valueOf(i));
            label.setFont(DEFAULT_FONT);
            label.setMinSize(ELEMENT_WIDTH, CURSOR_HEIGHT);
            label.setAlignment(Pos.CENTER);
            label.setTranslateY(ELEMENT_HEIGHT + SPACING);
            label.setTranslateX(getXPos(i));
            labels[i] = label;
        }
        return labels;
    }
 
    private void initialise() {
        transitions = new SequentialTransition();
        transitions.setOnFinished((e) -> {
           // startButton.setText("Create Random Values");
            startButton.setText("Give Input Again");
            startButton.setOnAction((ev) -> initialise());
        });
 
        animationPane.getChildren().clear();
        animationPane.getChildren().addAll(iLabel, iPlusOneLabel, nLabel);
        animationPane.getChildren().addAll(indexLabels);
        iLabel.setTranslateX(getXPos(0));
        iPlusOneLabel.setTranslateX(getXPos(1));
        nLabel.setTranslateX(getXPos(NUM_OF_ELEMENTS - 1));
        populateElements();
 
        startButton.setText("Start Selection Sort");
        startButton.setOnAction((e) -> startSort());
    }
 

    private void populateElements() {
        Input = txt.getText();
        Random rand = new Random();
        Scanner sc = new Scanner(Input);
        for (int i = 0; i < NUM_OF_ELEMENTS; i++) {
           
            int value = 9;
            value = sc.nextInt();
            Label label;
            if (elements[i] == null) {
                label = new Label();
                label.setMaxSize(ELEMENT_WIDTH, ELEMENT_HEIGHT);
                label.setAlignment(Pos.CENTER);
                label.setBorder(ELEMENT_BORDER);
                label.setFont(DEFAULT_FONT);
                label.setTranslateX(getXPos(i));
                elements[i] = new Element(value, label);
            } else {
                label = elements[i].label;
                elements[i].value = value;
            }
            label.setText(String.valueOf(value));
 
            animationPane.getChildren().add(label);
        }
    }
 
    private void startSort() {
        bubbleSort();
        transitions.play();
        startButton.setText("Pause");
        startButton.setOnAction((e) -> pauseAnimation());
       //startButton.setOnAction((e) -> input());
    }
 
    private void pauseAnimation() {
        transitions.pause();
        startButton.setText("Resume");
        startButton.setOnAction((e) -> resumeAnimation());
    }
//      private void input() {
//        transitions.play();
//        inputButton.setText("Pause");
//        inputButton.setOnAction((e) -> populateElements());
//    }
 
    private void resumeAnimation() {
        transitions.play();
        startButton.setText("Pause");
        startButton.setOnAction((e) -> pauseAnimation());
    }
 
    private void bubbleSort() {
        int n =NUM_OF_ELEMENTS;
        int index = 0,min;
        while (index <n) {
           int mn = elements[index].value;
           int tempindex = -1;
           for(int i = index; i<n; i++)
           {
              transitions.getChildren().add(moveCursorLabels(i, n-1));
               if(elements[i].value < mn)
               {
                   mn = elements[i].value;
                   tempindex = i;
               }
           }
           if(tempindex!=-1)
           {
               
                   Element temp = elements[index];
                    elements[index] = elements[tempindex];
                    elements[tempindex] = temp;
                    transitions.getChildren().add(swap(index, tempindex));
                   // transitions.getChildren().add(color(index, tempindex));
                    //elements[index].setUnFocusColor(Color.web("#ff0000"));
                   // System.out.println( elements[index].label);
                   
                     //paint(index,blue);
                 
                    
           }
           index++;
        }
        transitions.getChildren().add(move(nLabel, n-1));
    }
    
 
    private ParallelTransition swap(int index1, int index2) {
         // elements[index1].label.("-fx-color: red");
        return new ParallelTransition(move(elements[index1].label, index1), move(elements[index2].label, index2));
    }
 
    private ParallelTransition moveCursorLabels(int i, int n) {
        return new ParallelTransition(move(iLabel, i), move(iPlusOneLabel, i + 1), move(nLabel, n));
    }
 
    private static Transition move(Node node, int index) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(TRANSITION_DELAY), node);
        tt.setToX(getXPos(index));
        return tt;
    }
 
    private static double getXPos(int index) {
        return (SPACING + ELEMENT_WIDTH) * index + SPACING;
    }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
 
}