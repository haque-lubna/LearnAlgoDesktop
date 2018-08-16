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
public class SelectionCodeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
     public void backButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("Selection sort.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
        
    }
        public void cSelectionButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeSelectionC.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
        
    }
        
         public void cPlusSelectionButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeSelectionCplus.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
    } 
         public void javaSelectionButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeSelectionJava.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
    }
         
         public void cSharpSelectionButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeSelectionCsharp.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
    }
          public void fortanSelectionButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeSelectionFortan.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
    }
          public void adaSelectionButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeSelectionAda.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
    }
         public void pythonSelectionnButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeSelectionPython.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
    }
          public void javascriptSelectionButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeSelectionJavascript.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
    }
          public void kotlinSelectionButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeSelectionKotlin.fxml"));
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
