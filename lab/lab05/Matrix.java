package lab.lab05;

public class Matrix {
    private int nrows; private int ncols;
    private int[][] matrix;

    public Matrix(int i, int j){
        nrows = i; ncols = j; 
        matrix  = new int[i][j];
    }

    public Matrix(int[][] arr){
        nrows = arr.length; ncols = arr[0].length;
        matrix = new int[nrows][ncols];
        for(int i = 0; i<=nrows-1; i++){
            for(int j = 0; j<= ncols-1; j++)
                matrix[i][j] = arr[i][j];
        }
    }
    public Matrix transpose(){
        Matrix newMatrix = new Matrix(nrows,ncols);
        for(int i = 0; i<=nrows-2; i++){
            for(int j = 0; j<= ncols-2; j++)
                newMatrix.matrix[j][i] = matrix[i][j];
        }
        return newMatrix;
    }

    public String toString(){
        String s = "\0";
        for(int i = 0; i<=nrows-1; i++){
            for(int j = 0; j<= ncols-1; j++){
                s += String.valueOf((int)matrix[i][j]) + " ";
            }
            s += "\n";
        }
        return s;
    }

    public static void main(String[] args) {
        int[][] array = {{1,1,1,1,1},{0,1,2,3,4},{0,1,4,9,16},{0,1,8,27,64}};

        Matrix matrixA = new Matrix(array);
        Matrix transA = matrixA.transpose();

        System.out.println(matrixA);
        System.out.println(transA);
    }
}
