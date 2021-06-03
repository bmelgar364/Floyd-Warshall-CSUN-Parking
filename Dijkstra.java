import java.util.*;
import java.lang.*;
import java.io.*;

class Dijkstra {
    final static int M = 99999, V = 497;
	
    int minDistance(int dist[], Boolean sptSet[]){
        int min = M , min_index = -1;

        for (int v = 0; v<V; v++)
            if(sptSet[v] == false && dist[v] <= min){
                min = dist[v];
                min_index = v;
            }
        return min_index;
    }



    void printSolution(int dist[]){
        System.out.println("Vertex \t\t Distance from Source");
        for(int i = 0; i<V; i++)
            System.out.println(i + " \t\t " + dist[i]);
    }

    void dijstras (int graph[][], int source){
        int dist [] = new int [V];
        Boolean sptSet[] = new Boolean[V];

        for(int i=0; i<V; i++){
            dist[i] = M;
            sptSet[i] = false;
        }

        dist[source] = 0;

        for (int count = 0; count <V-1; count++){
            int u = minDistance(dist,sptSet);
            sptSet[u] = true;
            for(int v = 0; v<V; v++)
                if(!sptSet[v] && graph[u][v] != 0 && dist[u] != M && dist[u] + graph[u][v] < dist[v])
                dist[v] = dist[u] + graph[u][v];
        }
        printSolution(dist);
    }

    static int [][] FileToArray (String F) throws FileNotFoundException{
        Scanner sc = new Scanner (new BufferedReader(new FileReader(F)));
        int r = 497;
        int c = 497;
        int [] [] Matrix = new int [r] [c];
        while(sc.hasNextLine()){
            for (int i=0; i<Matrix.length; i++){
                String [] line = sc.nextLine().replaceAll("\\s+",",").split(",");
                for (int j=0; j<line.length; j++){  
                    Matrix[i][j] = Integer.parseInt(line[j]);
                }
            }
        }
        System.out.println("MATRIX: \n" +Arrays.deepToString(Matrix).replace("],", "],\n").replace("99999","INF"));
        return Matrix;
    }

    public static void main (String [] args) throws Exception{
        Dijkstra X = new Dijkstra();
        int [] [] DijstraMatrix;
        DijstraMatrix = FileToArray("VersionNew.TSV");
        X.dijstras(DijstraMatrix,0);
    }
}