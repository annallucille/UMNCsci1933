public class Train extends Vehicle{
    private double mpg;

    public Train(double mpg){
        this.mpg = mpg;
    }

    public Train(){
        this.mpg = 470.0;
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
        System.out.println("Train Entering Staion");
    }

    public void leavingStation(){
        System.out.println("Train Leaving Staion");
    }
}


