import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class End {
    static boolean answer ;
    //private static Scoreboard myscore ;
    public static void display(int Score, Stage stage){
        //myscore = Scoreboard.deserialize() ;
        Stage Window = new Stage() ;
        Window.setTitle("Submit Score");
        Window.initModality(Modality.APPLICATION_MODAL);
        Window.setTitle("Submit Score");
        Window.setMinWidth(250);
        Label label = new Label();
        label.setText("Enter Your Name") ;
        
        Button yesButton = new Button("Submit") ;
        Button noButton = new Button("Don't Submit") ;

        TextField nameInput = new TextField();
        Button button = new Button("Click Me") ;
        yesButton.setOnAction(e -> {
            
            try {
            	
                    
                
            }
            catch (Exception e3) {

            }
            Window.close();
            return;
        });

        noButton.setOnAction(e -> {
        	
            Window.close();
            return;
        });

        //Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(nameInput, yesButton,noButton) ;

        Scene scene = new Scene(layout, 300,250) ;
        Window.setScene(scene);
        Window.show() ;
    }
}
