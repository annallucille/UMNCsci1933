public class Car extends Vehicle{
    private double mpg;

    public Car(double mpg){
        this.mpg =mpg;
    }

    public Car(){
        this.mpg = 30.0;
    }
    public void movingBackward(){
        System.out.print("Car Moving Backward");
    }

    public void movingForward() {
        System.out.print("Car Moving Forward");
    }
}
