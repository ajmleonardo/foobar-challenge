import java.lang.Math;

public class Solution{
    public static int solution( int[][] map ){

        class Dijkstra {

            public int[] doDijkstra(int[][] costMatrix, int src) {
                int V = costMatrix.length;
                int dist[] = new int[V];
                boolean sptSet[] = new boolean[V];
        
                for(int v = 0; v < V; v++){
                    dist[v] = Integer.MAX_VALUE;
                    sptSet[v] = false;
                }

                dist[src] = 0;

                for( int count = 0; count < V-1; count++ ){
                    int u = minDistance(dist, sptSet);

                    if(u == -1) //Se u = -1 é por que não existe nenhum link conectado ao vértice src
                        return dist;

                    sptSet[u] = true; //nó visitado

                    for(int v=0; v < V ; v++)
                        if( sptSet[v] == false && (costMatrix[u][v] != 0) && dist[u] + costMatrix[u][v] < dist[v] )
                            dist[v] = dist[u] + costMatrix[u][v];

                }

                return dist;
            }

            public int minDistance(int dist[], boolean sptSet[]){
                int minDist = Integer.MAX_VALUE;
                int minDistIndex = -1;

                for( int v=0; v < dist.length; v++)
                    if(sptSet[v] == false && dist[v] < minDist){
                        minDist = dist[v];
                        minDistIndex = v;
                    }

                return minDistIndex;
            }

            public int[][] mapToCostMatrix(int[][] map){

                int H = map.length;
                int W = map[0].length;
                int V = H*W;

                int[][] costMatrix = new int[V][V];

                for(int i = 0; i < H; i++)
                    for(int j = 0; j < W; j++){

                        int k = i*W+j;

                        int w = k+1;
                        if( j<W-1 && map[i][j]==0 && map[i][j+1]==0){

                            costMatrix[k][w] = 1; // Connection from left to right
                            costMatrix[w][k] = 1; // Connection from right to left
                        }

                        if( i<H-1 && map[i][j] == 0 && map[i+1][j]==0){

                            costMatrix[k][k+W] = 1; // Connection from top to bottom
                            costMatrix[k+W][k] = 1; // Connection from bottom to top
                        }

                        
                    }

                    return costMatrix;
            }
        
            public int findConnectable(int[] entryDistArray, int[] exitDistArray, int W, int H) {

                int[][] connectableMatrix = new int[entryDistArray.length][exitDistArray.length];

                int shortest = Integer.MAX_VALUE;
                for(int i = 0; i < entryDistArray.length; i++)
                    for(int j = 0; j < exitDistArray.length; j++){

                        connectableMatrix[i][j] = Integer.MAX_VALUE;
                        if( entryDistArray[i]<Integer.MAX_VALUE && exitDistArray[j]<Integer.MAX_VALUE )
                            if( isThereNeighborIntersection(i, j, W, H) )
                                connectableMatrix[i][j] = entryDistArray[i] + exitDistArray[j];
                        if(connectableMatrix[i][j] < shortest)
                            shortest = connectableMatrix[i][j];

                    }
                                       
                return shortest +1 +1 +1;

            }

            // Finds if there is a intersection of nodes in a vicinity between a nEntry and nExit
            boolean isThereNeighborIntersection(int nEntry, int nExit, int W, int H){

                int[] entryNeighbors = new int[4];

                for(int i = 0; i < H; i++){
                    if(nEntry == i*W)
                        entryNeighbors[0] = -1;
                    if(nEntry == i*W + W-1)
                        entryNeighbors[1] = -1;
                }

                if(entryNeighbors[0] != -1)
                    entryNeighbors[0] = nEntry - 1;

                if(entryNeighbors[1] != -1)
                    entryNeighbors[1] = nEntry + 1;

                entryNeighbors[2] = nEntry - W;
                entryNeighbors[3] = nEntry + W;
                

                int[] exitNeighbors = new int[4];

                for(int i = 0; i < H; i++){
                    if(exit == i*W)
                        exitNeighbors[0] = -1;
                    if(exit == i*(W+1) -1)
                        exitNeighbors[1] = -1;
                }

                if(nExitNeighbors[0] != -1)
                    exitNeighbors[0] = nExit - 1;

                if(nExitNeighbors[1] != -1)
                    exitNeighbors[1] = nExit + 1;

                exitNeighbors[2] = nExit - W;
                exitNeighbors[3] = nExit + W;


                for(int i = 0; i < 4; i++)
                    for(int j = 0; j < 4; j++)
                        if( entryNeighbors[i] == exitNeighbors[j]  && entryNeighbors[i] > 0 )
                            return true;
            }
        }

        
        Dijkstra dijkstra = new Dijkstra();
        int[][] costMatrix = dijkstra.mapToCostMatrix(map);


        for(int i = 0; i < costMatrix.length; i++ ){
            for(int j = 0; j < costMatrix.length; j++ )
                System.out.print(costMatrix[i][j] + "\t");
            System.out.println();}

        int entry = map.length*map[0].length-1;
        int exit = 0;

        int[] entryDistArray = dijkstra.doDijkstra(costMatrix, entry);
        int[] exitDistArray = dijkstra.doDijkstra(costMatrix, exit);

        int shortest =  dijkstra.findConnectable(entryDistArray, exitDistArray, map[0].length, map.length);


        return shortest;
    }
}