import java.util.*;

public class PrimsMST {

    static class Edge {
        int vertex;
        int weight;

        Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        int[][] graph = new int[V][V];

        System.out.println(
                "Enter edges (u v weight):");

        for (int i = 0; i < E; i++) {

            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            if (graph[u][v] == 0) {

                graph[u][v] = w;
                graph[v][u] = w;
            }
            else {

                graph[u][v] =
                        Math.min(graph[u][v], w);

                graph[v][u] =
                        Math.min(graph[v][u], w);
            }
        }

        long startTime = System.nanoTime();

        boolean[] visited = new boolean[V];

        PriorityQueue<Edge> pq =
                new PriorityQueue<>(
                        (a, b) ->
                                a.weight - b.weight);

        pq.offer(new Edge(0, 0));

        int mstCost = 0;

        while (!pq.isEmpty()) {

            Edge current = pq.poll();

            int u = current.vertex;

            if (visited[u])
                continue;

            visited[u] = true;

            mstCost += current.weight;

            for (int v = 0; v < V; v++) {

                if (graph[u][v] != 0 &&
                        !visited[v]) {

                    pq.offer(new Edge(v,graph[u][v]));
                }
            }
        }

        long endTime = System.nanoTime();

        System.out.println(
                "\nMinimum Cost = "
                        + mstCost);

        System.out.println(
                "Execution Time = "
                        + (endTime - startTime)
                        + " ns");

        sc.close();
    }
}