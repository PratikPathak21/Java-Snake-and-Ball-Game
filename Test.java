import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.util.Duration;

import javafx.scene.control.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import javafx.scene.image.*;
import javafx.scene.*;

import javafx.animation.*;
import javafx.scene.layout.*;
import javafx.scene.transform.Translate;
import javafx.scene.text.*;
import javafx.scene.media.Media;  
import javafx.scene.media.MediaPlayer;  
import javafx.scene.media.MediaView; 
public class Test extends Application {
	public AnimationTimer Timer;
    public static ArrayList<Tokens> tokens=new ArrayList<>();
    public static Stack<Circle> snakecircle=new Stack<>();
    public static ArrayList<Blocks> blocks=new ArrayList<>();
    public static ArrayList<Text> texts = new ArrayList<>();
    public Text SnakeCount;
    public Text ShieldActivate=new Text("Shield Activated");
    public Text MagnetActivate=new Text("Magnet Activated");
    private final int[] angles={0,55,110,165,220};
    private static double SnakeHeadX;
    private static double SnakeHeadY;
    Pane root =new Pane();
    public int gamescore=6;
    Random RandomGen = new Random();
    private static int timecount=0;
    private static int timecount1=0;
    private static Circle SnakeHead;
    private static boolean shield=false;
    private static boolean magnet=false;
    private static int magnettimer=0;
    private static int shieldtimer=0;
    public Text Score;
    public static int score=0;
    public Stage stage;
    private int flag=0;
    public void moveleft() {
    	if(SnakeHeadX>=20) {
    		double count=0;
    		SnakeHeadX-=10;
    		for(Circle c : snakecircle){
    			c.setTranslateX(SnakeHeadX);
            	c.setTranslateY(SnakeHeadY+(count*20));
            	count+=1;
    		}
    	}
    }
    
    public void close() {
    	root = new Pane();
    	Text Gameover = new Text("Game Over");
    	Score.setText("Score"+Integer.toString(score)) ;
		Score.setTranslateX(50);
		Score.setTranslateY(200);
		Score.setFill(Color.WHITE);
		Score.setStyle("-fx-font-size: 30px");
		Gameover.setTranslateX(50);
		Gameover.setTranslateY(100);
		Gameover.setFill(Color.WHITE);
		Gameover.setStyle("-fx-font-size: 30px");
        root.getChildren().add(Score);
        root.getChildren().add(Gameover);
        
    }
    public void moveright() {
    	if(SnakeHeadX<root.getWidth()-10){
    		SnakeHeadX+=10;
    		double count=0;
    		for(Circle c : snakecircle){
    			c.setTranslateX(SnakeHeadX);
            	c.setTranslateY(SnakeHeadY+(count*20));
            	count+=1;
    		}
    	}
    }
    
    public void play(Blocks s) {
    	Media m1= new Media(new File("C:/Users/Pratik/Desktop/Explosion.mp4").toURI().toString()); 
        MediaPlayer mediaplayer=new MediaPlayer(m1);
        MediaView mediaview=new MediaView(mediaplayer);
		mediaview.setX(s.getX());
        mediaview.setFitWidth(50);
        mediaview.setY(s.getY());
        mediaview.setFitHeight(50);
        mediaplayer.play();
    }
    public void addballs() {
    	if(snakecircle.size()<10) {
    		Circle snakeadd = new Circle(10,Color.RED);
        	snakeadd.setTranslateX(SnakeHeadX);
        	snakeadd.setTranslateY(SnakeHeadY+(snakecircle.size()*20));
        	root.getChildren().add(snakeadd);
        	snakecircle.add(snakeadd);
    	}
    	root.getChildren().remove(SnakeCount);
    	SnakeCount.setText(Integer.toString(gamescore)) ;
		SnakeCount.translateXProperty().bind(SnakeHead.translateXProperty().add(-4));
		SnakeCount.translateYProperty().bind(SnakeHead.translateYProperty().add(-10));
		SnakeCount.setFill(Color.WHITE);
		SnakeCount.setStyle("-fx-font-size: 14px");
        root.getChildren().add(SnakeCount);
    }
    
    public void deleteballs() {
    	root.getChildren().remove(snakecircle.pop());
    	root.getChildren().remove(SnakeCount);
    	SnakeCount.setText(Integer.toString(gamescore)) ;
		SnakeCount.translateXProperty().bind(SnakeHead.translateXProperty().add(-4));
		SnakeCount.translateYProperty().bind(SnakeHead.translateYProperty().add(-10));
		SnakeCount.setFill(Color.WHITE);
		SnakeCount.setStyle("-fx-font-size: 14px");
        root.getChildren().add(SnakeCount);
    }
    
    public void UpdateScore() {
    	root.getChildren().remove(Score);
    	Score.setText("Score"+Integer.toString(score)) ;
		Score.setTranslateX(root.getWidth()-75);
		Score.setTranslateY(20);
		Score.setFill(Color.WHITE);
		Score.setStyle("-fx-font-size: 20px");
        root.getChildren().add(Score);
    }
    
    public void Shield() {
    	root.getChildren().add(ShieldActivate);
    	ShieldActivate.setTranslateX(0);
    	ShieldActivate.setTranslateY(15);
    	ShieldActivate.setFill(Color.WHITE);
    	ShieldActivate.setStyle("-fx-font-size: 10px");
        
    	
    }
    
    public void Magnet() {
    	root.getChildren().add(MagnetActivate);
    	MagnetActivate.setTranslateX(80);
    	MagnetActivate.setTranslateY(15);
    	MagnetActivate.setFill(Color.WHITE);
    	MagnetActivate.setStyle("-fx-font-size: 10px");
    }
    public void gameloop()
    {
    	timecount++;
    	shieldtimer++;
    	magnettimer++;
    	timecount1++;
    	if(shieldtimer>1000) {
    		shield = false;
    		root.getChildren().remove(ShieldActivate);
    	}
    	
    	if(magnettimer>1000) {
    		magnet = false;
    		root.getChildren().remove(MagnetActivate);
    	}
    	if(timecount>60) {
    		score++;
    		int choice = RandomGen.nextInt(100);
    		if(choice<25){
    			
    			int numofblocks=RandomGen.nextInt(10);
    			
    			if(numofblocks<4) {
    				for(int i=0;i<5;i++) {
    					Blocks b1 = new Blocks(angles[i],0);
    					blocks.add(b1);
    					int random=RandomGen.nextInt(20);
    					random+=1;
    					b1.text= new Text(Integer.toString(random));
    					b1.value=random;
    	        		b1.text.translateXProperty().bind(b1.translateXProperty().add(20));
    	        		b1.text.translateYProperty().bind(b1.translateYProperty());
    	        		b1.text.setFill(Color.WHITE);
    	        		b1.text.setStyle("-fx-font-size: 14px");
    	                texts.add(b1.text);
    	                root.getChildren().add(b1.text);
    					root.getChildren().add(b1);
    					//root.getChildren().add(b1.text);
    				}
    			}
    			
    			else if(numofblocks<7) {
    				int odd=RandomGen.nextInt(5);
    				for(int i=0;i<5;i++) {
    					if(i==odd)
    						continue;
    					else {
    						Blocks b1 = new Blocks(angles[i],0);
        					blocks.add(b1);
        					int random=RandomGen.nextInt(20);
        					random+=1;
        					b1.text= new Text(Integer.toString(random));
        					b1.value=random;
        	        		b1.text.translateXProperty().bind(b1.translateXProperty().add(20));
        	        		b1.text.translateYProperty().bind(b1.translateYProperty());
        	        		b1.text.setFill(Color.WHITE);
        	        		b1.text.setStyle("-fx-font-size: 14px");
        	                texts.add(b1.text);
        	                root.getChildren().add(b1.text);
        					root.getChildren().add(b1);
        					//root.getChildren().add(b1.text);
    					}
    						
    				}
    			}
    			
    			else if(numofblocks<10) {
    				int odd1=RandomGen.nextInt(4);
    				int odd2=RandomGen.nextInt(4);
    				for(int i=0;i<5;i++) {
    					if(i==odd1||i==odd2)
    						continue;
    					else {
    						Blocks b1 = new Blocks(angles[i],0);
        					blocks.add(b1);
        					int random=RandomGen.nextInt(20);
        					random+=1;
        					b1.text= new Text(Integer.toString(random));
        					b1.value=random;
        	        		b1.text.translateXProperty().bind(b1.translateXProperty().add(20));
        	        		b1.text.translateYProperty().bind(b1.translateYProperty());
        	        		b1.text.setFill(Color.WHITE);
        	        		b1.text.setStyle("-fx-font-size: 14px");
        	                texts.add(b1.text);
        	                root.getChildren().add(b1.text);
        					root.getChildren().add(b1);
        					//root.getChildren().add(b1.text);
    					}
    						
    				}
    			}
    				
    		}
    		
    		else if(choice<60) {
    			int xcoord = RandomGen.nextInt(4);
    			Tokens newToken = new Tokens(angles[xcoord],0,"Ball"); 
        		tokens.add(newToken);
        		int random=RandomGen.nextInt(10);
        		random+=1;
        		newToken.text= new Text(Integer.toString(random)) ;
        		newToken.value=random;
        		newToken.text.translateXProperty().bind(newToken.translateXProperty().add(-5));
        		newToken.text.translateYProperty().bind(newToken.translateYProperty().add(-10));
        		newToken.text.setFill(Color.WHITE);
        		newToken.text.setStyle("-fx-font-size: 14px");
                texts.add(newToken.text);
                root.getChildren().add(newToken.text) ;
        		root.getChildren().add(newToken);
    			
    		}
    		
    		else if(choice<70) {
    			int xcoord = RandomGen.nextInt(4);
    			Tokens newToken = new Tokens(angles[xcoord],0,"Shield"); 
    			tokens.add(newToken);	
    			root.getChildren().add(newToken);
    		}
    		
    		else if(choice<80) {
    			int xcoord = RandomGen.nextInt(4);
    			Tokens newToken = new Tokens(angles[xcoord],0,"Magnet"); 
    			tokens.add(newToken);	
    			root.getChildren().add(newToken);
    		}
    		
    		else if(choice<90) {
    			int xcoord = RandomGen.nextInt(4);
    			Tokens newToken = new Tokens(angles[xcoord],0,"Destroyer"); 
    			tokens.add(newToken);	
    			root.getChildren().add(newToken);
    		}
    		
    		timecount=0;
    		UpdateScore();
    	}
    	
    	tokens.forEach(s -> s.MoveDown());
    	blocks.forEach(s -> s.MoveDown());
    	if(timecount1>40) {
    		if(magnet==true && shield==true) {
        		for(Tokens s: tokens) {
        			
            		if(s.getTranslateY()>root.getHeight()) {
            			root.getChildren().remove(s);
            		}
            		
            		else if(s.getType().equals("Ball") && s.getTranslateY()>SnakeHeadY) {
            				root.getChildren().remove(s.text);
            				gamescore+=s.value;
            				addballs();
            				flag=1;
            				root.getChildren().remove(s);
            				break;
            		}
            		else if(s.getBoundsInParent().intersects(SnakeHead.getBoundsInParent())) {
            			
            			if(s.getType().equals("Magnet")) {
            				if(magnet==false)
            					Magnet();
            				magnet=true;
            				magnettimer=0;
            				
            				root.getChildren().remove(s);
            				flag=1;
            				break;
            			}
            			
            			else if(s.getType().equals("Shield")) {
            				if(shield==false)
            					Shield();
            				shield=true;
            				shieldtimer=0;
            				
            				root.getChildren().remove(s);
            				flag=1;
            				break;
            			}
            			
            			else if(s.getType().equals("Destroyer")) {
            				blocks.forEach(p -> {
            					root.getChildren().remove(p.text);
            					root.getChildren().remove(p);
            				});
            				root.getChildren().remove(s);
            				flag=1;
            				break;
            			}
            			
            			
            		}
            	
            	};
            	
            	
            	for(Blocks s: blocks) {	
            		if(s.getBoundsInParent().intersects(SnakeHead.getBoundsInParent())) {
            			root.getChildren().remove(s.text);
            			root.getChildren().remove(s);
            			flag=1;
            			play(s);
            			break;
            			
            		}
            	
            	};
        	}
        	
        	else if(magnet==true) {
        		for(Tokens s: tokens) {
        			
            		if(s.getTranslateY()>root.getHeight()) {
            			root.getChildren().remove(s);
            		}
            		
            		else if(s.getType().equals("Ball") && s.getTranslateY()>SnakeHeadY) {
            				root.getChildren().remove(s.text);
            				gamescore+=s.value;
            				addballs();
            				root.getChildren().remove(s);
            				flag=1;
            				break;
            		}
            		else if(s.getBoundsInParent().intersects(SnakeHead.getBoundsInParent())) {
            			
            			if(s.getType().equals("Magnet")) {
            				if(magnet==false)
            					Magnet();
            				magnet=true;
            				magnettimer=0;
            				
            				root.getChildren().remove(s);
            				flag=1;
            				break;
            			}
            			
            			else if(s.getType().equals("Shield")) {
            				if(shield==false)
            					Shield();
            				shield=true;
            				shieldtimer=0;
            				
            				root.getChildren().remove(s);
            				flag=1;
            				break;
            			}
            			
            			else if(s.getType().equals("Destroyer")) {
            				blocks.forEach(p -> {
            					root.getChildren().remove(p.text);
            					root.getChildren().remove(p);
            				});
            				root.getChildren().remove(s);
            				flag=1;
            				break;
            			}
            		
            		}
            	
            	};
            	
            	
            	for(Blocks s: blocks) {	
            		if(s.getBoundsInParent().intersects(SnakeHead.getBoundsInParent())) {
            			gamescore-=s.value;
            			if(gamescore<0) {
            				Timer.stop();
            				End.display(score,stage);
            				HomeScreen hm1=new HomeScreen();
            	            hm1.start(stage);
            			}
            				
            			root.getChildren().remove(s.text);
            			root.getChildren().remove(s);
            			deleteballs();
            			flag=1;
            			play(s);
            			break;
            		}
            	
            	};
        		
        	}
        	
        	else if(shield==true) {
        		for(Tokens s: tokens) {
            		if(s.getTranslateY()>root.getHeight()) {
            			root.getChildren().remove(s);
            		}
            		else if(s.getBoundsInParent().intersects(SnakeHead.getBoundsInParent())) {
            			
            			if(s.getType().equals("Ball")) {
            				root.getChildren().remove(s.text);
            				gamescore+=s.value;
            				addballs();
            				root.getChildren().remove(s);
            				flag=1;
            				break;
            			}
            			
            			else if(s.getType().equals("Magnet")) {
            				if(magnet==false)
            					Magnet();
            				magnet=true;
            				magnettimer=0;
            				
            				root.getChildren().remove(s);
            				flag=1;
            				break;
            			}
            			
            			else if(s.getType().equals("Shield")) {
            				if(shield==false)
            					Shield();
            				shield=true;
            				shieldtimer=0;
            				root.getChildren().remove(s);
            				flag=1;
            				break;
            			}
            			else if(s.getType().equals("Destroyer")) {
            				blocks.forEach(p -> {
            					root.getChildren().remove(p.text);
            					root.getChildren().remove(p);
            				});
            				root.getChildren().remove(s);
            				flag=1;
            				break;
            			}
            		
            		}
            	
            	};
            	
            	
            	for(Blocks s: blocks) {	
            		if(s.getBoundsInParent().intersects(SnakeHead.getBoundsInParent())) {
            			root.getChildren().remove(s.text);
            			root.getChildren().remove(s);
            			flag=1;
            			play(s);
            			break;
            		}
            	
            	};
        		
        	}
        	
        	else {
        		for(Tokens s: tokens) {
            		if(s.getTranslateY()>root.getHeight()) {
            			root.getChildren().remove(s);
            		}
            		else if(s.getBoundsInParent().intersects(SnakeHead.getBoundsInParent())) {
            			
            			if(s.getType().equals("Ball")) {
            				root.getChildren().remove(s.text);
            				gamescore+=s.value;
            				addballs();
            				//System.out.println(gamescore);
            				root.getChildren().remove(s);
            				flag=1;
            				break;
            				
            			}
            			
            			else if(s.getType().equals("Magnet")) {
            				if(magnet==false)
            					Magnet();
            				magnet=true;
            				magnettimer=0;
            				
            				root.getChildren().remove(s);
            				flag=1;
            				break;
            			}
            			
            			else if(s.getType().equals("Shield")) {
            				if(shield==false){
            					Shield();
            				}
            				shield=true;
            				shieldtimer=0;
            				root.getChildren().remove(s);
            				flag=1;
            				break;
            			}
            			
            			else if(s.getType().equals("Destroyer")) {
            				blocks.forEach(p -> {
            					root.getChildren().remove(p.text);
            					root.getChildren().remove(p);
            					
            				});
            				root.getChildren().remove(s);
            				flag=1;
            				break;
            			}
            			
            		}
            	
            	};
            	
            	
            	for(Blocks s: blocks) {	
            		if(s.getBoundsInParent().intersects(SnakeHead.getBoundsInParent())) {
            			gamescore-=s.value;
            			if(gamescore<0) {
            				Timer.stop();
            				End.display(score,stage);
            				HomeScreen hm1=new HomeScreen();
            	            hm1.start(stage);
            			}
            			deleteballs();
            			root.getChildren().remove(s.text);
            			root.getChildren().remove(s);
            			flag=1;
            			play(s);
            			break;
            		}
            	
            	};
        	}
    		if(flag==1) {
    			timecount1=0;
    			flag=0;
    		}
    		
    	}
    	
	}
    	
    
    public void start(Stage primaryStage) {
    	stage=primaryStage;
    	root.setStyle("-fx-background-color: #000000" );
    	final Scene scene = new Scene(root, 270, 450, Color.BLACK);
        //scene = new Scene(root, 800, 600, Color.BLACK);
        primaryStage.setScene(scene);
        //primaryStage.show();
        Snake snake1=new Snake(root);
        SnakeHeadY=root.getHeight()-10;
        SnakeHeadX=root.getWidth()/2;
        SnakeHead = new Circle(10,Color.RED);
        SnakeHead.setTranslateX(SnakeHeadX);
    	SnakeHead.setTranslateY(SnakeHeadY);
        snakecircle.add(SnakeHead);
        root.getChildren().add(SnakeHead);
        SnakeHeadY-=20;
        SnakeCount= new Text(Integer.toString(gamescore)) ;
		SnakeCount.translateXProperty().bind(SnakeHead.translateXProperty().add(-4));
		SnakeCount.translateYProperty().bind(SnakeHead.translateYProperty().add(-10));
		SnakeCount.setFill(Color.WHITE);
		SnakeCount.setStyle("-fx-font-size: 14px");
        root.getChildren().add(SnakeCount);
    	Score = new Text("0"); 
		Score.setTranslateX(root.getWidth()-75);
		Score.setTranslateY(20);
		Score.setFill(Color.WHITE);
		Score.setStyle("-fx-font-size: 20px");
        root.getChildren().add(Score);
        double temp=380;
        while(snakecircle.size()<6){
        	Circle snakeadd = new Circle(10,Color.RED);
        	snakeadd.setTranslateX(SnakeHeadX);
        	snakeadd.setTranslateY(SnakeHeadY);
            snakecircle.add(snakeadd);
            root.getChildren().add(snakeadd);
            SnakeHeadY-=20;
        }
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) 
            {
  
            case LEFT:
                moveleft();
                break;
            case RIGHT:
                moveright();
                break;
            }
        //root.show()}
        });
        
        primaryStage.setOnCloseRequest(e -> {
        	Timer.stop();
        	Stage Window = new Stage() ;
            Window.setTitle("Menu");
            Window.setMinWidth(250);
            Label label = new Label();
            label.setText("Enter Your Name") ;
            
            Button yesButton = new Button("Resume");
            Button noButton = new Button("Restart");
            Button yes1Button = new Button("Exit to Main Menu");
            yesButton.setOnAction(f -> {
                //myscore.add(Score, nameInput.getText());
                
                Window.close();
                primaryStage.show();
                Timer.start();
            });
            
            noButton.setOnAction(f -> {
            	Window.close();
            	
            	this.start(primaryStage);
            	primaryStage.show();
                
            });
            yes1Button.setOnAction(f -> {
            	HomeScreen obj1=new HomeScreen();
            	obj1.start(primaryStage);
            	Window.close();
            });
            //yes1Button.setOnAction(arg0);
            //Layout
            VBox layout = new VBox(10);
            layout.setPadding(new Insets(10));
            layout.getChildren().addAll(yesButton,noButton,yes1Button) ;

            Scene scene1 = new Scene(layout, 300,250) ;
            Window.setScene(scene1);
            Window.show();
			});
    	
        Timer=new AnimationTimer() {
            @Override
            public void handle(long now) {
            	gameloop();
            }
        };
        Timer.start();
        primaryStage.show();
    	
    	
    	
    	
    	
        
        
        
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
        

}