// import sun.audio.*;
import java.io.File;
import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.util.Duration;
import javafx.animation.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import java.io.*;
// import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;  
import javafx.scene.media.MediaPlayer;  
import javafx.scene.media.MediaView; 
public class HomeScreen extends Application
{
	static Stage firststage;

	public static void main(String[] args) 
	{
		Application.launch(args);
		// TODO Auto-generated method stub

	}

	private void CommonClose()
		{
			int answer=Confirm.show("Are you Sure?");
			if(answer==1)
			{
				firststage.close();
			}
		}

	@Override
	public void start(Stage stage)
	{
		firststage=stage;

		firststage.setTitle("Home Screen");
		Button button1=new Button("Play Game");
		String path="C:/Users/Pratik/Desktop/tune.mp3";
		// AudioClip clip=new AudioClip(path);
		// clip.setCycleCount(AudioClip.INDEFINITE); 
  //       clip.play(); 
		 //Instantiating Media class  
        Media media = new Media(new File(path).toURI().toString());  
          
        // Instantiating MediaPlayer class   
        MediaPlayer mediaPlayer = new MediaPlayer(media);  
          
        //by setting this property to true, the audio will be played   
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
		{
    	mediaPlayer.setOnEndOfMedia(new Runnable() 
    	{
        public void run() 
        {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        }
    	});
      
		}
		button1.setOnAction(e -> 
		{
			try
			{	
				Test test=new Test();
				test.start(firststage);
			}
			catch (Exception e1)
			{

			}
		});
		
		Button button2=new Button("Resume");
		button2.setOnAction(e -> 
		{
			try
			{Test test=new Test();
				test.start(firststage);
			}
			catch (Exception e1)
			{
				
			}
		});
		Button button3=new Button("Leader Board");
		 button3.setOnAction (e ->
		 {
		 	Leaderboard lb=new Leaderboard();
		 	lb.start(firststage);
		 }
		 );
		Button button4=new Button("Exit");
		button4.setOnAction(e ->{ 
			CommonClose();
		});
		
		
		
		VBox vbox=new VBox(20,button1,button2,button3,button4);
		vbox.setAlignment(Pos.CENTER);
		// button4.setOnAction(e -> Platform.exit());
		Scene firstscene = new Scene(vbox,1000,1000);
		//firstscene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
		//firstscene = new Scene(button1,button2,200,100);
		//Button button2=new Button("Second Button");
		firststage.setScene(firstscene);
		firststage.setOnCloseRequest(e -> {
			e.consume();
			CommonClose();});
		firststage.show();
		// TODO Auto-generated method stub
		
	}

}