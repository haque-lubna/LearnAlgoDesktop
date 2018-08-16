/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnalgo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class DijkstraController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     public void backButtonAction(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene s = new Scene(p);
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();

    }

    public void desButtonAction(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("DesDijkstra.fxml"));
        Scene s = new Scene(p);
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();

    }

    public void codeDijkstraButtonAction(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("Dijkstra code.fxml"));
        Scene s = new Scene(p);
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();

    }

    public void linkDijkstraButtonAction(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("Dijkstra link.fxml"));
        Scene s = new Scene(p);
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();

    }
    
    public void visuDijkstraButtonAction(ActionEvent event) throws IOException {
        
        //BubbleVisu.main(new String[0]);
        //Application.launch(BubbleVisu.class, new String[0]);
//        DijkstraVisu visu = new DijkstraVisu();
//        //Stage newStage = new Stage();
//        //visu.start(newStage);
//        LearnAlgo.launch();
        DijkstraVisu.main(new String[0]);
       LearnAlgo.main(DijkstraVisu.class, new String[0]);
//        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   
    
}
