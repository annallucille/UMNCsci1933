import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;

public class OwlPopulation{
    private String fName;
    private Owl[] data;
    private int lines = 0;

    public OwlPopulation(String fname)throws FileNotFoundException{
        this.fName = fname;
        populateData();
    }

    public int populateData() throws FileNotFoundException{
        File f = new File(fName); Scanner scan = new Scanner(f); 
        String s; String[] lst;
        while(scan.hasNextLine()){
            s = scan.nextLine();
            lines++;
        }
        scan.close();
        data = new Owl[lines];

        scan = new Scanner(f);
        for(int i = 0; i <= lines-1; i++){
            s = scan.nextLine();
            lst = s.split(",");
            Owl owl = new Owl(lst[0], Integer.parseInt(lst[1]), Double.parseDouble(lst[2]));
            data[i] = owl;
        }
        return 1;
    }

    public double averageAge(){
        int totalAge = 0; double avgAge =0; int i;
        for(i = 0; i <= data.length-2; i++){
            totalAge += data[i].getAge();
        }
        avgAge = totalAge/data.length;
        return avgAge;
    }

    public Owl getYoungest(){
        Owl youngestAge= new Owl("name",5,0); int i = 0;
        while(i<=data.length-2){
            if (data[i].getAge()<youngestAge.getAge()){
                youngestAge = data[i];
                i++;
            }
            else{
                i++;
            }
        }
        return youngestAge;
    }

    public Owl getHeaviest(){
        Owl heaviest = new Owl("name",5,0); int i = 0;
        while(i<=data.length-2){
            if (data[i].getWeight()> heaviest.getWeight()){
                heaviest = data[i];
                i++;
            }
            else{
                i++;
            }
        }
        return heaviest;
    }

    public String toString(){
        Owl owl1; Owl owl2;
        System.out.println("The average age is " + this.averageAge());
        owl1 = this.getHeaviest();
        System.out.println("The heaviest owl is "+owl1.getName()+", which weighs " + owl1.getWeight());
        owl2 = this.getYoungest();
        System.out.println("The youngest owl is "+owl2.getName()+", which is " + owl2.getAge());
        return "s";
    }

    public void merge(OwlPopulation other){
        int i=0; int z=0; int x=0;
        for(i=0;i<=this.data.length-1;i++){
            for(z=0;z<=other.data.length-1;z++){
                if(data[i].equals(other.data[z])){
                    x++;
                }
            }
        }
        Owl[] total = new Owl[i+z-x];
        for(i=0;i<=this.data.length-1;i++){
            total[i] = this.data[i];
            for(z=0;z<=other.data.length-1;z++){
                if(total[i].equals(other.data[z])){
                    x++;
                }
                else{
                    total[data.length+z-x]=other.data[z];
                }
            }
        }
        this.data = total;

    }

    public static void main(String[] args){
        try{
            OwlPopulation pop1 = new OwlPopulation("owlPopulation1.csv");
            System.out.println(pop1);
            OwlPopulation pop2 = new OwlPopulation("owlPopulation2.csv");
            System.out.println(pop2);
            //System.out.println(pop2.popSize());
            pop1.merge(pop2);
            System.out.println(pop1);
            //System.out.println(pop1.popSize());
        }
        catch (FileNotFoundException f){
            System.out.println("File not found.");
        }
    }
}