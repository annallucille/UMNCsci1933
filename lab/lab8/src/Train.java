public class Train extends Vehicle{
    private double mpg;

    public Train(double mpg){
        this.mpg = mpg;
        nVehicles +=1;
    }

    public Train(){
        this.mpg = 470.0;
        nVehicles +=1;
    }

    public double getMPG(){
        return mpg;
    }
        
    public void movingBackward(){
        System.out.println("Train Moving Backward");
    }

    public void movingForward() {
        System.out.println("Train Moving Forward");
    }


    public void enteringStation(){
        System.out.println("Train Entering Station");
    }

    public void leavingStation(){
        System.out.println("Train Leaving Station");
    }
}


