public class Helicopter extends Vehicle{
    private double mpg;

    public Helicopter(){
        this.mpg = .3;
    }


    public double getMPG(){
        return mpg;
    }

    public void movingBackward(){
        System.out.print("Helicopter Moving Backward");
    }

    public void movingForward() {
        System.out.print("Helicopter Moving Forward");
    }

    public void movingUp(){
        System.out.print("Helicopter Moving Up");
    }

    public void movingDown(){
        System.out.print("Helicopter Moving Down");
    }
}
