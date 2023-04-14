import java.util.LinkedList;
import java.util.Queue;

public class ColorPath {
    static int size;



    public static int[][] colorPath(int[][] image, int sourceRow, int sourceCol, int newColor){
        int[] start = new int[2];
        start[0] = sourceRow; start[1] = sourceCol;
        size = image.length;
        bfs(image, start, newColor);
        return image;

    }

    public static void bfs(int[][] image, int[] start, int newColor){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        size = image.length;
        while(queue.peek() != null){
            int[] curr = queue.poll();
            if(curr[1] - 1 >= 0 && image[curr[0]][curr[1] - 1] == 0){
                curr[1] --;
                image[curr[0]][curr[1]] = newColor;
                queue.add(curr);
            }
            if(curr[1] + 1 < size && image[curr[0]][curr[1] + 1] == 0){
                curr[1] ++;
                image[curr[0]][curr[1]] = newColor;
                queue.add(curr);
            }
            if(curr[0] - 1 >= 0 && image[curr[0] - 1][curr[1]] == 0){
                curr[0] --;
                image[curr[0]][curr[1]] = newColor;
                queue.add(curr);
            }
            if(curr[0] + 1 < size && image[curr[0] + 1][curr[1]] == 0){
                curr[0] ++;
                image[curr[0]][curr[1]] = newColor;
                queue.add(curr);
            }
        }
    }


    public static void dfs(int[][] image, int[] start, int newColor){}
}
