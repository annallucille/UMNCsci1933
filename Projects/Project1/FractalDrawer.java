// FractalDrawer class draws a fractal of a shape indicated by user input
import java.awt.Color;
import java.lang.Math;
import java.util.Random;

public class FractalDrawer {
    private double totalArea=0; 
    Random rand = new Random();  // member variable for tracking the total area

    public FractalDrawer() {

    }  // contructor


    //TODO:
    // drawFractal creates a new Canvas object
    // and determines which shapes to draw a fractal by calling appropriate helper function
    // drawFractal returns the area of the fractal
    public double drawFractal(String type) {
        Canvas c1 = new Canvas(800,800);
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color c = new Color(r, g, b);
        double x = 0; double y = 0; double w = 0; double h = 0; double rad = 0; int l = 1;
        if(type.equals("T")){
            x = 250; y = 600; w = 300; h = 300;
           drawTriangleFractal(w,h,x,y,c,c1,l);
        }
        if(type.equals("C")){
            x = 400; y = 400; rad = 200;
            drawCircleFractal(rad,x,y,c, c1, l);
        }
        if(type.equals("R")){
            x = 250; y = 200; w = 200; h = 250;
            drawRectangleFractal(w,h,x,y,c, c1, l);
        }
        return totalArea;
        
    }



    public void drawTriangleFractal(double width, double height, double x, double y, Color c, Canvas can, int level){
        Triangle t = new Triangle(x, y, height, width,c); // Shape classes take in a color argument, only way i could get the color randomizer to work
        totalArea+= t.calculateArea();
        can.drawShape(t);
        float r = rand.nextFloat() / 2f; float g = rand.nextFloat(); float b = rand.nextFloat() / 2f;
        Color nc = new Color(r,g,b);
        if(level<=8){
            double nw = width/1.8; double nh = height/1.8;
            for (int z=0; z<3; z++){
                if(z==0){
                    double nx = x - nw;
                    double ny = y;
                    drawTriangleFractal(nw, nh, nx, ny, nc, can, (level+1));
                }
                else if(z==1){
                    double nx = x + width;
                    double ny = y;
                    drawTriangleFractal(nw, nh, nx, ny, nc, can, (level+1));
                }
                else if(z==2){
                    double nx = x + .5 * (width - nw);
                    double ny = y - height;
                    drawTriangleFractal(nw, nh, nx, ny, nc, can, (level+1));
                }
            }
        }

    }



    public void drawCircleFractal(double radius, double x, double y, Color c, Canvas can, int level) {
        Circle cir = new Circle(x, y, radius,c);    // Shape classes take in a color argument, only way i could get the color randomizer to work
        totalArea += cir.calculateArea();
        can.drawShape(cir);
        if(level<=8){         // each level produces 4 smaller circles 
            float r = rand.nextFloat();
            float g = rand.nextFloat() / 2f;
            float b = rand.nextFloat();
            Color nc = new Color(r,g,b);
            double nr = radius/2;
            for (int z=1; z<=4;z++){ // the 4 smaller circles
                if(z%2==0){
                    double nx = x + radius*(z-3); // - +
                    double ny = y + radius; // + +
                    drawCircleFractal(nr,nx,ny,nc,can, level+1);
                }
                if (z%2!=0){
                    double nx = x + radius*(z-2); // - +
                    double ny = y - radius; // - -
                    drawCircleFractal(nr,nx,ny,nc,can, level+1);
                }
            }
        }
    }


    //TODO:
    // drawRectangleFractal draws a rectangle fractal using recursive techniques
    public void drawRectangleFractal(double width, double height, double x, double y, Color c, Canvas can, int level) {
        Rectangle rec = new Rectangle(x, y, height, width,c);
        totalArea += rec.calculateArea();
        can.drawShape(rec);
            if(level<=6){
                float r = rand.nextFloat(); 
                float g = rand.nextFloat() / 2f;
                float b = rand.nextFloat() / 2f;
                Color nc = new Color(r,g,b);
                double nh = height/2; double nw = width/2;
                for (int z=0; z<4; z++){
                    if(z==0){
                        double nx = x - nw;
                        double ny = y + height;
                        drawRectangleFractal(nw,nh,nx,ny,nc,can, level+1);
                    }
                    else if(z==1){
                        double nx = x + width;
                        double ny = y + height;
                        drawRectangleFractal(nw,nh,nx,ny,nc,can, level+1);
                    }
                    else if(z==2){
                        double nx = x + width;
                        double ny = y - nh;
                        drawRectangleFractal(nw,nh,nx,ny,nc,can, level+1);
                    }
                    else{
                        double nx = x - nw;
                        double ny = y - nh;
                        drawRectangleFractal(nw,nh,nx,ny,nc,can, level+1);
                    }
                }
            }
        }
    
    


    //TODO:
    // main should ask user for shape input, and then draw the corresponding fractal.
    // should print area of fractal
    public static void main(String[] args){
        
        FractalDrawer f1 = new FractalDrawer();
        double area1 = f1.drawFractal("R");
        System.out.println(area1);



        FractalDrawer f2 = new FractalDrawer();
        double area2 = f2.drawFractal("C");
        System.out.println(area2);

        FractalDrawer f3 = new FractalDrawer();
        double area3 = f3.drawFractal("T");
        System.out.println(area3);


    }
}
