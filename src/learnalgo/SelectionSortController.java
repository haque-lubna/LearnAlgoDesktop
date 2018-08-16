/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnalgo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
public class SelectionSortController implements Initializable {

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
        Parent p = FXMLLoader.load(getClass().getResource("DesSelection.fxml"));
        Scene s = new Scene(p);
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();

    }

    public void codeSelectionButtonAction(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("Selection code.fxml"));
        Scene s = new Scene(p);
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();

    }

    public void linkSelectionButtonAction(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("Selection link.fxml"));
        Scene s = new Scene(p);
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();

    }

    public void visuSelectionButtonAction(ActionEvent event) throws IOException {
        
        //BubbleVisu.main(new String[0]);
        //Application.launch(BubbleVisu.class, new String[0]);
        SelectionVisu visu = new SelectionVisu();
        Stage newStage = new Stage();
        visu.start(newStage);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
