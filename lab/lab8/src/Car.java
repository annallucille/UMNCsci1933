public class Car extends Vehicle{
    private double mpg;

    public Car(double mpg){
        this.mpg = mpg;
    }

    public Car(){
        this.mpg = 30.0;
    }

    public double getMPG(){
        return mpg;
    }
    
    public void movingBackward(){
        System.out.println("Car Moving Backward");
    }

    public void movingForward() {
        System.out.println("Car Moving Forward");
    }
}
