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
public class CodeHeapCsharpController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public void backButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("Heap code.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
