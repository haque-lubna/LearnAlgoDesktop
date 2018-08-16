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
public class HeapCodeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     public void backButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("Heap sort.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
        
    }
        public void cHeapButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeHeapC.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
        
    }
        
         public void cPlusHeapButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeHeapCplus.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
    } 
         public void javaHeapButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeHeapJava.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
    }
         
         public void cSharpHeapButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeHeapCsharp.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
    }
         public void fortanHeapButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeHeapFortan.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
    }
          public void adaHeapButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeHeapAda.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
    }
         public void pythonHeapButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeHeapPython.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
    }
          public void javascriptHeapButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeHeapJavascript.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
    }
          public void kotlinHeapButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeHeapKotlin.fxml"));
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
