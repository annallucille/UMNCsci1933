import java.util.Scanner;

public class Histogram{
    public int lowerbound;
    public int upperbound;
    public int[] array = {};
    public static String str = "";

    public Histogram(int lowerbound, int upperbound){
        this.lowerbound = lowerbound;
        this.upperbound = upperbound;
        int range = upperbound - lowerbound;
        array = new int[range+1];
        for (int i=0; i<=range; i++){
            array[i] = 0;
        }
    }

    public boolean add(int i){
        if(i >= lowerbound && i <= upperbound){
            array[i-lowerbound]+=1;
            return true;
        }
        else{
            return false;
        }
    }

    public String toString(){
        int i;
        for(i=lowerbound; i<=upperbound; i++){
            str += i + ":";
            for(int x=array[i-lowerbound]; x>0; x-=1){
                str +="*";
            }
            str +="\n";
        }
        return str;
    }

    public static void main(String args[]) {
        Histogram histo = new Histogram(0, 5);
        histo.add(3);
        histo.add(2);
        histo.add(1);
        histo.add(2);
        histo.add(3);
        histo.add(0);
        histo.add(1);
        histo.add(5);
        histo.add(3);
        System.out.println(histo);
        }
}