public class Train {
    private double mpg;

    public Train(){
        this.mpg = 470.0;
    }

    public double getMPG(){
        return mpg;
    }

    public void enteringStation(){
        System.out.println("Train Entering Staion");
    }

    public void leavingStation(){
        System.out.println("Train Leaving Staion");
    }
}


