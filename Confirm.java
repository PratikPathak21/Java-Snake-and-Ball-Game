import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class Confirm 
{

    
    static int answer;

    public static int show(String message) 
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL); //Defines a modal window that blocks events from being delivered to any other application window.
        window.setTitle("Confirmation");
        window.setMinWidth(200);
        
        Button yesB = new Button("Yes");
        yesB.setOnAction(e -> 
        {
            answer = 1;
            // Platform.exit(); not working
            window.close();
        });
        
        Button noB = new Button("No");
        noB.setOnAction(e -> 
        {
            answer = 0;
            // Platform.exit();
            window.close();
        });
        
        Label label = new Label();
        label.setText(message);

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, yesB, noB);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout,1000,1000);
        scene.getStylesheets().add(Confirm.class.getResource("stylesheet.css").toExternalForm());
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }

}