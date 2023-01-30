import java.awt.Color;


// Circle Class 
public class Circle {
    private double xPos = 0;   //declair object varibles
    private double yPos = 0;
    private double radius = 0;
    private Color color = Color.black;

// main constructor 
    public Circle(double a, double b, double r){     
        xPos = a;
        yPos = b;
        radius = r;
    }
    public Circle() {}

// accessor methods 
    public void setColor(Color value ){  // set the color (takes in value of type color)
        color = value; 
    }
    public void setRadius(double r){ // set the radius 
        radius = r;
    }
    public void setPos(double x, double y){ //set the position of the center of the circle
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
    public double getRadius(){ // getting the radius
        return radius;
    }

// opperators 
    public double calculateCircumference(){ // calculate circumference (i will not allow myself to call this perimeter)
        return 2*3.14*radius;   // circumference
    }   
    public double calculateArea(){  // calculate area 
        return 3.14*radius*radius;  // area 
    }
}