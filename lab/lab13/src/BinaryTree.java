import java.util.Arrays;
import java.lang.Math;
public class BinaryTree {
    // TODO complete this method
    public static boolean isValid(int[] arr) {
        if((Math.log(arr.length) / Math.log(2)) % 1 == 0){
            for(int i = 2; i < arr.length/2; i++){
                if(arr[2*i] > arr[i] || arr[2*i+1] < arr[i]){
                    return false;
                }
                if(i % 2 != 0 && arr[2*i] < arr[1]) {
                    return false;
                }
                if(i % 2 == 0 && arr[2*i+1] > arr[1]){
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    public static void main (String args[]) {
      // milestone 1
      int[] arr1 = new int[]{-1,7,4,10,3,6,8,15};
      int[] arr2 = new int[]{-1,20,12,32,5,18,25,38};
      int[] arr3 = new int[]{-1,11,3,33,2,8,10,44};
      int[] arr4 = new int[]{-1,55,44,77,33,48,54,95,22,34,45,57,53,70,85,98};

      System.out.println("arr1 valid: " + isValid(arr1));  // expected: true
      System.out.println("arr2 valid: " + isValid(arr2));  // expected: true
      System.out.println("arr3 valid: " + isValid(arr3));  // expected: false
      System.out.println("arr4 valid: " + isValid(arr4));  // expected: false
    }
}
