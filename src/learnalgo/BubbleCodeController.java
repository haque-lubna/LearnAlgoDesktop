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
public class BubbleCodeController implements Initializable {
    
      public void backButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("Bubble sort.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
        
    }
        public void cBubbleButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeBubbleC.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
        
    }
        
         public void cPlusBubbleButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeBubbleCplus.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
    } 
         public void javaBubbleButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeBubbleJava.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
    }
         
         public void cSharpBubbleButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeBubbleCsharp.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
    }
          public void fortanBubbleButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeBubbleFortan.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
    }
          public void adaBubbleButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeBubbleAda.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
    }
         public void pythonBubbleButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeBubblePython.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
    }
          public void javascriptBubbleButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeBubbleJavascript.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
    }
          public void kotlinBubbleButtonAction(ActionEvent event)throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("codeBubbleKotlin.fxml"));
        Scene s = new Scene(p);
        Stage st =(Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show();
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
