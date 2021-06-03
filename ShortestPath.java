import java.util.*;
import java.lang.*;
import java.io.*;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

class shortestPath{
    final static int M = 99999, V = 497;
    static int [][] dist = new int [V][V];
    static int [][] Next = new int [V][V];
    static long starttime=0;
    static long endtime=0;
    static long totaltime=0;
    static int [] buildings = {0,230,233,241,250,214,210,245,51,53,282,284,286,288,289,297,298,315,316,309,303,181,182,194,396,193,33,32,25,56,64,68,69,
                              402,404,412,414,409,410,430,429,427,442,78,454,459,464,463,76,71,453,458,80,85,89,466,355,353,128,130,440,123,141,134,
                              345,110,106,107};
    static String [] parkingNames = {"Vincennes St.","Vincennes St.","B6 Parking", "B6 Parking" , "B5 Parking" , "B5 Parking", "Prairie St.","Prairie St.","Dearborn St." , "Dearborn St." , "B4 Parking ", "B4 Parking ","B4 Parking" ,"B4 Parking","B4 Parking","B3 Parking","B3 Parking","B3 Parking","B3 Parking","B3 Parking","B3 Parking","B3 Parking","B2 Parking","B2 Parking","B1 Parking","B1 Parking","E6 Parking","E6 Parking","E6 Parking","E6 Parking","E6 Parking","E6 Parking","E6 Parking","F10 Parking","G3 Parking","G3 Parking","G3 Parking",
                                  "G3 Parking","G3 Parking","G4 Parking","Halsted St.","Prairie St.","Prairie St.","Prairie St.","Prairie St.","Prairie St."};
    
    static int [] parking =   {175,176,223,224,217,208,170,169,164,165,196,197,184,186,191,384,391,150,418,381,151,149,146,156,145,158,258,273,272,279,277,
                              267,265,268,87,372,362,359,95,375,486,472,474,475,476,470};

    static String [] buildingNames = {"Oviatt Library","Bookstein Hall (North Entrace)","Bookstein Hall (North West Entrace)","Bookstein Hall (South Entrace)","Bookstein Hall (North East Entrace)","Michael D. Eisner Education Building (North West Entrace)","Michael D. Eosmer Education Building (North Entrace)","Education Building (North East Entrace)","Jacranda Hall (North Entrace)","Jacranda Hall (North Entrace Facing Sequoia Hall)","Jacranda Hall (South Entrace)","Jacranda Hall (Second South Entrace)","Jacranda Hall (North West Entrace)","Jacranda Hall (North West Entrace Facing Arbor Grill)","Jacranda Hall (North East Entrace)","Redwood Hall (North East Entrace)","Redwood Hall (Second North East Entrace)","Redwood Hall (South Entrace)","Redwood Hall (Second South Entrace)","Redwood Hall (North East Entrace, Matadome)","Redwood Hall (North Entrace)","Extended University Commons (North West Entrace)","Extended University Commons (Second North West Entrace)","University Hall (North West Entrace)","University Hall (North East Entrace)","University Hall (North Entrace)","Bayramian Hall (North West Entrene) ","Bayramian Hall (Second North Entrace, Facing Ralph Prator Fountain) ","Bayramian Hall (North Entrace)","Sequoia Hall (North East Entrace)","Sequoia Hall (North West Entrace)","Magnolia Hall (North Entrace)","Magnolia Hall (South Entremce)" ,"Jerome Richfield Hall (South Entrace)","Sierra Tower (North East Entrace)","Jerome Richfield Hall (North West Entrace)","Sierra Hall (South East Entrace)","Sierra Hall (South Entrace)","Sierra Hall (Second South Entrace)","Sierra Hall (North Entrace)","Sierra Hall (Second North Entrace)","Sierra Hall (Third North Entrace)","Sierra Hall (Fourth North Entrace, Facing Manzanita Hall)","Eucalyptus Hall (North East Entrace)","Eucalyptus Hall(Second North East Entrace, Facing Sierra Quad)","Eucalyptus Hall (Walkway Path) ","Eucalyptus Hall (Walkway Path)","Live Oak Hall (North East Entrace)","Live Oak Hall (North Entrace)","Live Oak Hall","Live Oak Hall (North East Entrace)","Planetarium/ Citrus Hall (South Entrace)","Chaparral Hall (North Entrace)","Jeanne Chisholm Hall (South East Entrace)","Jeanne Chisholm Hall (South East Entrace)","Jeanne Chisholm Hall (Inside)","Monerey Hall (South Entrace)","Monerey Hall (North Entrace)","Manzita Hall (South East Entrace)","Manzita Hall (North West Entrace)","Manzita Hall (South West Entrace)","Campus Store (South Entrace)","Nordhoff Hall (North Entrace)","Cypress Hall (North West Entrace)","Cypress Hall (North East Entrace)","The Soraya (South Entrace)","Kurkland Hall (North East Entrace)","Kurkland Hall (North East Entrace)"};

    static void pathInitialize(int V, int [][] graph){
        for (int i =0; i<V; i++){
            for (int j=0; j<V; j++){
                dist[i][j] = graph[i][j]; 
                if(graph[i][j]==M) 
                    Next[i][j] = -1;
                else
                    Next[i][j] = j;
            }
        }
    }

    static Vector<Integer> pathConstruct (int u, int v){
        if(Next[u][v]==-1)
            return null; // Meaning that nNo path exists between these two Nodes.
        
        Vector<Integer> path = new Vector <Integer>();
        path.add(u);
        while(u!=v){
            u=Next[u][v];
            path.add(u);
        }
        return path;
    }


    static void floydWarshall (int V){
    starttime= System.currentTimeMillis(); // calculating start time
        int i, j, k;

        for(k=0; k<V; k++){
            for(i=0; i<V; i++){
                for(j=0; j<V; j++){
                    if(dist[i][k] == M || dist[k][j]==M)
                        continue;
                    if(dist[i][j] > dist[i][k] + dist[k][j]){
                        dist[i][j] = dist[i][k]+dist[k][j];
                        Next[i][j] = Next[i][k];
                    }
                }
            }
        }
        printSolution(dist);
       endtime= System.currentTimeMillis();    // calculating end time
      totaltime= endtime - starttime;  
    }


    static void printPath(Vector<Integer> path){
        int n = path.size();
        for(int i = 0; i<n-1; i++)
        System.out.print(path.get(i) + " -> ");
        System.out.print(path.get(n-1)+"\n");
    }

    static void  printSolution(int distancegraph[][]){
        System.out.println("Shortest Distances Between Vectors:");
        for(int i=0; i<V; ++i){
            for(int j=0; j<V; ++j){
                if(distancegraph[i][j]==99999)
                    System.out.print("INF ");
                else
                    System.out.print(distancegraph[i][j] + "  ");
            }
            System.out.println("");
        }
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


    static void calculateParking(int start){
    
        int distance = M;
        Vector <Integer> path;
        System.out.println("Displaying Best Parking Lots In Regards To Your Building:\n ---------------------------------------------------------------------");
        for(int i = 0; i<parking.length; i++){
            if(dist[start][parking[i]]< distance){ 
                path = pathConstruct (start,parking[i]);
                printPath(path);
                System.out.println( "Parking Lot/Street:" + parkingNames[i]);
                System.out.println("Total Distance To Lot/Street: " + dist[start][parking[i]] + " meters \nEstimated Time: " + dist[start][parking[i]]/60 + " minutes\n");
                distance = dist[start][parking[i]];
            }
        }
        System.out.print("---------------------------------------------------------------------\nBest Possible Path above, with Distance: " + distance + " meters.");
      System.out.print("\nExecution Time : " + (totaltime/1000) + " Seconds ("+ totaltime + " Milliseconds)");
    }



    public static void main(String args[])throws Exception{
        int [] [] FloydMatrix;
        FloydMatrix = FileToArray("VersionNew.TSV");
        pathInitialize(497, FloydMatrix);
        floydWarshall(497);
        String Menu = (String)JOptionPane.showInputDialog(null,"Please Select Your Destination/Building","Fastest Path!" ,JOptionPane.QUESTION_MESSAGE, null,buildingNames,buildingNames[0]);
        int index = Arrays.asList(buildingNames).indexOf(Menu);
        if(index ==-1){
            System.exit(0);
        }
        calculateParking(buildings[index]);   
    }
 }

