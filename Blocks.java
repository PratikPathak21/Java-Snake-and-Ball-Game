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

import javafx.scene.text.Text;

import javafx.scene.image.*;
import javafx.scene.*;

import javafx.animation.*;
import javafx.scene.layout.*;
import javafx.scene.transform.Translate;
public class Blocks extends Rectangle{
	
	public static int [][]Colors=new int[][] {{255,0,0},{128,0,0},{255,255,0},{128,128,0},{0,255,0},{0,128,0},{0,255,255},{0,128,128},{0,0,255},{0,0,128},{255,0,255},{128,0,128}};
	public Text text;
	public int value;
	Blocks(int x,int y){
		super(50,50,getRandomColor());
		this.setTranslateX(x);
		this.setTranslateY(y);
		this.setArcHeight(10);
		this.setArcWidth(10);
		value=0;
	}
	
	public void MoveDown() {
        this.setTranslateY(getTranslateY() + 2);
    }
	
	public static Color getRandomColor() {
        Random random = new Random() ;
        int a = random.nextInt(12);
        return (Color.rgb(Colors[a][0], Colors[a][1], Colors[a][2]));
	}

}
