import java.util.*;

class Edge {

    int source, destination, weight;

    Edge(int s, int d, int w) {
        source = s;
        destination = d;
        weight = w;
    }
}

public class BellmanFord {

    public static void bellmanFord(List<Edge> edges,
                                   int vertices,
                                   int source) {

        int[] distance = new int[vertices];
    
        // Initialize distances
        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[source] = 0;

        // Relax all edges V-1 times
        for (int i = 1; i < vertices; i++) {

            for (Edge edge : edges) {

                int u = edge.source;
                int v = edge.destination;
                int w = edge.weight;

                if (distance[u] != Integer.MAX_VALUE &&
                    distance[u] + w < distance[v]) {

                    distance[v] = distance[u] + w;
                }
            }
        }

        // Check negative cycle
        boolean negativeCycle = false;

        for (Edge edge : edges) {

            int u = edge.source;
            int v = edge.destination;
            int w = edge.weight;

            if (distance[u] != Integer.MAX_VALUE &&
                distance[u] + w < distance[v]) {

                negativeCycle = true;
                break;
            }
        }

        // Print result
        if (negativeCycle) {

            System.out.println("Negative Weight Cycle Detected");

        } else {

            System.out.println("Shortest distances from source:");

            for (int i = 0; i < vertices; i++) {

                System.out.println("Vertex " + i +
                                   " -> Distance = " + distance[i]);
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int vertices = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int edgesCount = sc.nextInt();

        List<Edge> edges = new ArrayList<>();

        System.out.println("Enter source destination weight:");

        for (int i = 0; i < edgesCount; i++) {

            int s = sc.nextInt();
            int d = sc.nextInt();
            int w = sc.nextInt();

            edges.add(new Edge(s, d, w));
        }

        System.out.print("Enter source vertex: ");
        int source = sc.nextInt();

        bellmanFord(edges, vertices, source);

        sc.close();
    }
}