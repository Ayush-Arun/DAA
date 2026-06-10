import java.util.*;

public class DijkstraPQ {

    static class Pair {
        int vertex;
        int distance;

        Pair(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    static void printPath(int[] parent, int v) {

        if (v == -1)
            return;

        printPath(parent, parent[v]);

        System.out.print(v + " ");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        int[][] graph = new int[V][V];

        System.out.println("Enter adjacency matrix:");

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        System.out.print("Enter source vertex: ");
        int source = sc.nextInt();

        long startTime = System.nanoTime();

        int[] dist = new int[V];
        int[] parent = new int[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        dist[source] = 0;

        PriorityQueue<Pair> pq =
                new PriorityQueue<>(
                        (a, b) -> a.distance - b.distance);

        pq.offer(new Pair(source, 0));

        while (!pq.isEmpty()) {

            Pair current = pq.poll();

            int u = current.vertex;

            for (int v = 0; v < V; v++) {

                if (graph[u][v] != 0 &&
                        dist[u] + graph[u][v] < dist[v]) {

                    dist[v] = dist[u] + graph[u][v];

                    parent[v] = u;

                    pq.offer(new Pair(v, dist[v]));
                }
            }
        }

        long endTime = System.nanoTime();

        System.out.println("\nShortest Paths:");

        for (int i = 0; i < V; i++) {

            System.out.print("0 -> " + i + " : ");

            printPath(parent, i);

            System.out.println(" Cost = " + dist[i]);
        }

        System.out.println("\nExecution Time: "
                + (endTime - startTime)
                + " ns");
        sc.close();
    }
}