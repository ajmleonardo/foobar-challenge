import java.util.Vector;

    public class Solution {
        public static int solution(int src, int dest) {
            

        class GFG { 

            // Class for storing a cell's data 
            class cell { 
                int x, y; 
                int dis; 
        
                public cell(int x, int y, int dis) 
                { 
                    this.x = x; 
                    this.y = y; 
                    this.dis = dis; 
                } 
            } 
        
            // Utility method returns true if (x, y) lies 
            // inside Board 
            boolean isInside(int x, int y, int N) 
            { 
                if (x >= 1 && x <= N && y >= 1 && y <= N) 
                    return true; 
                return false; 
            } 
        
            // Method returns minimum step 
            // to reach target position 
            int minStepToReachTarget( 
                int knightPos[], int targetPos[], 
                int N) 
            { 
                // x and y direction, where a knight can move 
                int dx[] = { -2, -1, 1, 2, -2, -1, 1, 2 }; 
                int dy[] = { -1, -2, -2, -1, 1, 2, 2, 1 }; 
        
                // queue for storing states of knight in board 
                Vector<cell> q = new Vector<>(); 
        
                // push starting position of knight with 0 distance 
                q.add(new cell(knightPos[0], knightPos[1], 0)); 
        
                cell t; 
                int x, y; 
                boolean visit[][] = new boolean[N + 1][N + 1]; 
        
                // make all cell unvisited 
                for (int i = 1; i <= N; i++) 
                    for (int j = 1; j <= N; j++) 
                        visit[i][j] = false; 
        
                // visit starting state 
                visit[knightPos[0]][knightPos[1]] = true; 
        
                // loop untill we have one element in queue 
                while (!q.isEmpty()) { 
                    t = q.firstElement(); 
                    q.remove(0); 
        
                    // if current cell is equal to target cell, 
                    // return its distance 
                    if (t.x == targetPos[0] && t.y == targetPos[1]) 
                        return t.dis; 
        
                    // loop for all reachable states 
                    for (int i = 0; i < 8; i++) { 
                        x = t.x + dx[i]; 
                        y = t.y + dy[i]; 
        
                        // If reachable state is not yet visited and 
                        // inside board, push that state into queue 
                        if (isInside(x, y, N) && !visit[x][y]) { 
                            visit[x][y] = true; 
                            q.add(new cell(x, y, t.dis + 1)); 
                        } 
                    } 
                } 
                return Integer.MAX_VALUE; 
            }
            

        }

        int N = 8;
        GFG gfg = new GFG();

        int[][] posMatrix = new int[N][N];


        // Creating position matrix
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                posMatrix[i][j] = i*N + j;

        // Flipping Matrix
        int aux;
        for(int i = 0; i < N/2; i++)
            for(int j = 0; j < N; j++){
                aux = posMatrix[i][j];
                posMatrix[i][j] = posMatrix[N-1-i][j];
                posMatrix[N-1-i][j] = aux;
            }

        // Finding coordinates based on position
        int knightPos[] = { 0, 0 }; 
        int targetPos[] = { 0, 0 }; 
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++){
                if(src == posMatrix[i][j]){
                    knightPos[0] = j+1; 
                    knightPos[1] = i+1; 
                }

                if(dest == posMatrix[i][j]){
                    targetPos[0] = j+1; 
                    targetPos[1] = i+1; 
                }
            }

                
        return gfg.minStepToReachTarget(knightPos, targetPos, N);

    }
} 
