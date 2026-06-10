import java.util.*;

public class TopologicalSortDFS {

    static void dfs(int v,
                    ArrayList<ArrayList<Integer>> graph,
                    boolean[] visited,
                    Stack<Integer> stack) {

        visited[v] = true;

        for (int neighbor : graph.get(v)) {

            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited, stack);
            }
        }

        stack.push(v);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        ArrayList<ArrayList<Integer>> graph =
                new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        System.out.println("Enter edges (u v):");

        for (int i = 0; i < E; i++) {

            int u = sc.nextInt();
            int v = sc.nextInt();

            graph.get(u).add(v);
        }


        boolean[] visited = new boolean[V];

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < V; i++) {

            if (!visited[i]) {
                dfs(i, graph, visited, stack);
            }
        }

        System.out.println("\nTopological Order:");

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }

        sc.close();
    }
}