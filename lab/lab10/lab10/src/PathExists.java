import java.util.LinkedList;
import java.util.Queue;

public class PathExists {

    public static boolean doesPathExist(char[][] grid, int sourceRow, int sourceCol){
        int i = sourceRow; int j = sourceCol;

        return dfs(grid, i, j);

    }


    public static boolean bfs(char[][] image, int[] start){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        int size = image.length;
        while(queue.peek() != null){
            int[] curr = queue.poll();
            if(curr[1] - 1 >= 0 && image[curr[0]][curr[1] - 1] == 'p'){
                curr[1] --;
                queue.add(curr);
            }
            if(curr[1] + 1 < size && image[curr[0]][curr[1] + 1] == 'p'){
                curr[1] ++;
                queue.add(curr);
            }
            if(curr[0] - 1 >= 0 && image[curr[0] - 1][curr[1]] == 'p'){
                curr[0] --;
                queue.add(curr);
            }
            if(curr[0] + 1 < size && image[curr[0] + 1][curr[1]] == 'p'){
                curr[0] ++;
                queue.add(curr);
            }
            if(image[curr[0]][curr[1]] == 'v'){
                return true;
            }
        }
        return false;
    }

    public static boolean dfs(char[][] image, int i, int j, ) {
        int size = image.length;
        if (i < 0 || j < 0 || i >= size || j>= size || image[i][j] == 'v') {
            return image[i][i] == 'v';
        }
        if (image[j][i] == 'p') {
            pair[0] += 1;
            dfs(image, pair);
            pair[0] -= 1;
            pair[1] += 1;
            dfs(image, pair);
            pair[0] -= 1;
            pair[1] -= 1;
            dfs(image, pair);
            pair[1] -= 1;
            dfs(image, pair);
        }  
        return false;
    }
}
