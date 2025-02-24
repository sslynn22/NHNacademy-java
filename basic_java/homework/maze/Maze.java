package basic_java.homework.maze;

public class Maze {
    public static void main(String[] args) {
        int[][] maze = {
            {0,0,1,0,0,0,1,0,0,0},
            {1,0,0,0,1,1,1,0,0,0},
            {0,1,1,0,0,0,0,1,1,1},
            {1,0,0,0,0,0,0,1,1,1},
            {0,0,1,0,0,1,1,0,0,0},
            {0,0,1,0,0,0,1,0,0,0},
            {1,0,0,0,1,1,1,0,0,0},
            {0,1,1,0,0,0,0,1,1,1},
            {1,0,0,0,0,0,0,1,1,1},
            {0,0,1,0,0,1,0,0,0,0}
        };
        
        int move = 0;
        boolean escape = true;
        int x = 0, y = 0;
        int n = maze.length - 1;
        int m = maze[0].length - 1;
        
        do {
            if (x==n && y==m) {
                break;
            }
            else if (x<n && maze[x+1][y]==0) {
                maze[x][y] = -1;
                x++;
                move++;
            }
            else if (y<m && maze[x][y+1]==0) {
                maze[x][y] = -1;
                y++;
                move++;
            }
            else if (x>0 && maze[x-1][y]==0) {
                maze[x][y] = -1;
                x--;
                move++;
            }
            else if (y>0 && maze[x][y-1]==0) {
                maze[x][y] = -1;
                y--;
                move++;
            }
            else {
                escape = false;
                break;
            }
        } while(escape);

        if (escape) System.out.printf("----\nPass, %d\n----\n", move);
        else System.out.printf("----\nFail, %d\n----\n", move);
    }
}