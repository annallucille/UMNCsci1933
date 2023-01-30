import java.awt.Color;
import java.lang.Math;


// Rectangle Class 
public class Rectangle {
    private double xPos = 0;   //declair object varibles
    private double yPos = 0;
    private double height = 0;
    private double width = 0;
    private Color color = Color.black;

// main constructor 
    public Rectangle(double a, double b, double h, double w){     
        xPos = a;
        yPos = b;
        height = h;
        width = w;
    }
    public Rectangle() {}

// accessor methods 
    public void setColor(Color value ){  // set the color (takes in value of type color)
        color = value; 
    }
    public void setHeight(double h){ // set the height
        height = h;
    }
    public void setWidth(double w){
        width = w;
    }
    public void setPos(double x, double y){ //set the position of the left bottom corner 
        xPos = x; //x position
        yPos = y; // y position 
    }
    public Color getColor(){ // getting the color
        return color;
    }
    public double getXPos(){ // geting the x position
        return xPos;
    }
    public double getYPos(){ // getting the y position 
        return yPos;
    }
    public double getHeight(){ // getting the height
        return height;
    }

// opperators 
    public double calculatePerimeter(){ // calculate perimeter
        return (2 * width) + (2 * height);   // perimeter
    }   
    public double calculateArea(){  // calculate area 
        return height * width;  // area 
    }
}