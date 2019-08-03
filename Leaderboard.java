/**
 * Java Leaderboard Class
 * @author Kanishk
 * @version 1.00, 1 Dec 2018

 */

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Leaderboard extends Application 
{
 
    static Stage firststage;
    TextField nameInput;
    /**
     *
     * Simple class definition of Leaderboard.
     
     * This class is used for handling Leaderboard HiScore
    
     * @since version 1.00
     */

    private void CommonClose()
        {
            int answer=Confirm.show("Are you Sure?");
            if(answer==1)
            {
                firststage.close();
            }
        }
    private final TableView<Person> table = new TableView<>();
    private final ObservableList<Person> data =
            FXCollections.observableArrayList(new Person("A", 1.0));
    final HBox hb = new HBox();
 
    public static void main(String[] args) 
    {
        launch(args);
    }

    /**
     *
     * Start method.
     
     * The methods starts up the Leaderboard
     
     * @since version 1.00
     */
 
    @Override
    public void start(Stage stage) 
    {
        firststage=stage;
        // stage.setAlwaysOnTop(true);
        Scene scene = new Scene(new Group());
        firststage.setWidth(450);
        firststage.setHeight(550);
 
 
        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<>("firstName"));
 
        TableColumn lastNameCol = new TableColumn("HighScore");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<>("hiScore"));
 
        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol);
 
        final Button addButton = new Button("Add");
        
        addButton.setOnAction((ActionEvent e) -> {
            addButtonClicked();
         });
 
        hb.getChildren().add(addButton);
        //hb.getChildren().add(button2);
        hb.setSpacing(3);
 
        nameInput = new TextField();
        nameInput.setPromptText("Name");
        nameInput.setMinWidth(100);

        hb.getChildren().add(nameInput);

        final Button exitMain = new Button("Exit to Main Menu");
        
        exitMain.setOnAction((ActionEvent e) -> 
        {
            HomeScreen hm1=new HomeScreen();
            hm1.start(stage);
         });
        hb.getChildren().add(exitMain);
        //hb.getChildren().add(button2);
        hb.setSpacing(3);

        final Button exitGame = new Button("Exit Game ");
        exitGame.setOnAction((ActionEvent e)->
        {
            CommonClose();
        });
        hb.getChildren().add(exitGame);
        //hb.getChildren().add(button2);
        hb.setSpacing(3);
        
        
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);
        vbox.getChildren().addAll(table, hb);
        

        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        //((Group) scene.getRoot()).getChildren().add(button2);
        firststage.setScene(scene);
        firststage.show();
    }

    public void addButtonClicked()
    {
        Person person = new Person(nameInput.getText(),0.0d);
        person.setFirstName(nameInput.getText());
        
        table.getItems().add(person);
        nameInput.clear();
        
    }
    
    public void addscore(String name, double score) {
    	Person person = new Person(name,score);
    	table.getItems().add(person);
    }
    
    public static class Person 
    {
         /**
     *
     * Simple class definition of Person.
     
     * This class is used for handling Persons on Leaderboard
    
     * @since version 1.00
     */
        private final SimpleStringProperty firstName;
        private final SimpleDoubleProperty hiScore;
 
        private Person(String fName, Double hiScore) 
        {
            this.firstName = new SimpleStringProperty(fName);
            this.hiScore = new SimpleDoubleProperty(hiScore);
        }
 
         /**
     *
     * GetFirstName method.
     
     * The methods returns the name of the Person
     *
     
     * @return firstName
     * @since version 1.00
     */
        public String getFirstName() 
        {
            return firstName.get();
        }
 
        public void setFirstName(String fName) 
        {
            firstName.set(fName);
        }

          /**
     *
     * GetHiScore method.
     
     * The methods returns the hiScore
     *
     
     * @return hiScore
     * @since version 1.00
     */
 
        public Double getHiScore() {
            return hiScore.get();
        }
 
        // public void setHiScore(Number hiScore) 
        // {
        //     hiScore.setValue(hiScore);
        // }
    }
} 