import javafx.application.Application;
import javafx.scene.paint.ImagePattern;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.util.Duration;

import javafx.scene.control.*;
import java.io.*;
import java.util.Random;
import java.util.Stack;
import javafx.scene.image.*;
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.animation.*;
import javafx.scene.layout.*;
import javafx.scene.transform.Translate;
public class Tokens extends Circle {
	
	private Random randomgenerator=new Random();
	private String type;
	public Text text;
	public int value;
	private static int [][]Colors=new int[][] {{255,0,0},{128,0,0},{255,255,0},{128,128,0},{0,255,0},{0,128,0},{0,255,255},{0,128,128},{0,0,255},{0,0,128},{255,0,255},{128,0,128}};
	public String getType(){
		return type;
	}

	public Tokens(int x, int y, String s){
		super(10,Color.BLACK);
		type=s;
		value=0;
		x+=10;
		try {
			if (type.equals("Magnet")) {
                Image image1=new Image(new FileInputStream("C:/Users/Pratik/Desktop/Magnet.png"));
                setFill(new ImagePattern(image1));
            } 
			else if (type.equals("Destroyer")) {
                Image image1=new Image(new FileInputStream("C:/Users/Pratik/Desktop/Destroyer.jpeg"));
                setFill(new ImagePattern(image1));
            } 
			else if(type.equals("Shield")) {
                Image image1=new Image(new FileInputStream("C:/Users/Pratik/Desktop/Shield.jpeg"));
                setFill(new ImagePattern(image1));
			}
			else if(type.equals("Ball")) {
                setFill(Color.RED);
			}
				
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		this.setTranslateX(x);
		this.setTranslateY(y);
	}
	
	public void MoveDown() {
        setTranslateY(getTranslateY() + 2);
    }
	
	public static Color getRandomColor() {
        Random random = new Random() ;
        int a = random.nextInt(12);
        return (Color.rgb(Colors[a][0], Colors[a][1], Colors[a][2]));
	}
}
