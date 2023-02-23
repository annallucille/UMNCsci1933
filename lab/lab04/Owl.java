

public class Owl{
    public int age;
    private String name;
    public double weight;

    public Owl(String name, int age, double weight){
        this.name = name; this.age = age; this.weight = weight;
    }
    public Owl(){}

    public String getName(){
        return this.name;
    }
    public int getAge(){
        return this.age;
    }
    public double getWeight(){
        return this.weight;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setWeight(double weight){
        this.weight = weight;
    }

    public boolean equals(Owl other){
        if(this.name.equals(other.name) && this.age==other.age && this.weight==other.weight){
            return true;
        }
        else{
            return false;
        }
    }

    public static void main(String[] args){
        Owl owl1 = new Owl("owl1", 5, 12.0);
        Owl owl2 = new Owl("owl2", 5, 12.0);
        Owl owl3 = new Owl("owl1", 5, 12.0);
        System.out.println(owl1.equals(owl2));
        System.out.println(owl1.equals(owl3));
    }
}