public class Car extends Vehicle{
    private double mpg;

    public Car(double mpg){
        this.mpg = mpg;
        nVehicles +=1;
    }

    public Car(){
        this.mpg = 30.0;
        nVehicles +=1;
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
