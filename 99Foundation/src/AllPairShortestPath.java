import java.util.Scanner;

public class AllPairShortestPath {
    private int distancematrix[][];
    private static int numberofvertices;
    public static final int INFINITY = 999;

    public AllPairShortestPath(int numberofvertices) {
        distancematrix = new int[numberofvertices + 1][numberofvertices + 1];
        this.numberofvertices = numberofvertices;
    }

    public void allPairShortestPath(int adjacencymatrix[][]) {
        for (int source = 1; source <= numberofvertices; source++) {
            for (int destination = 1; destination <= numberofvertices; destination++) {
                distancematrix[source][destination] = adjacencymatrix[source][destination];
            }
        }

        for (int intermediate = 1; intermediate <= numberofvertices; intermediate++) {
            for (int source = 1; source <= numberofvertices; source++) {
                for (int destination = 1; destination <= numberofvertices; destination++) {
                    if (distancematrix[source][intermediate] + distancematrix[intermediate][destination]
                            < distancematrix[source][destination])
                        distancematrix[source][destination] = distancematrix[source][intermediate]
                                + distancematrix[intermediate][destination];
                }
            }
            printMatrix(intermediate, distancematrix);
        }

    }

    public static void printMatrix(int d, int[][] distancematrix) {
        System.out.printf("%-5d--------------\n", d);
        for (int source = 1; source <= numberofvertices; source++)
            System.out.print("\t" + source);

        System.out.println();
        for (int source = 1; source <= numberofvertices; source++) {
            System.out.print(source + "\t");
            for (int destination = 1; destination <= numberofvertices; destination++) {
                System.out.print(distancematrix[source][destination] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String... arg) {
        int adjacency_matrix[][];
        int numberofvertices;

        Scanner scan = new Scanner(System.in);
        numberofvertices = scan.nextInt();

        adjacency_matrix = new int[numberofvertices + 1][numberofvertices + 1];
        for (int source = 1; source <= numberofvertices; source++) {
            for (int destination = 1; destination <= numberofvertices; destination++) {
                adjacency_matrix[source][destination] = scan.nextInt();
                if (source == destination) {
                    adjacency_matrix[source][destination] = 0;
                    continue;
                }
                if (adjacency_matrix[source][destination] == 0) {
                    adjacency_matrix[source][destination] = INFINITY;
                }
            }
        }

        AllPairShortestPath allPairShortestPath = new AllPairShortestPath(numberofvertices);
        allPairShortestPath.allPairShortestPath(adjacency_matrix);

        scan.close();
    }
}