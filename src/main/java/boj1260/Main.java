package boj1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int v = Integer.parseInt(stringTokenizer.nextToken());

        int[][] graph = new int[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            StringTokenizer stringTokenizer1 = new StringTokenizer(reader.readLine());
            int v1 = Integer.parseInt(stringTokenizer1.nextToken());
            int v2 = Integer.parseInt(stringTokenizer1.nextToken());
            graph[v1][v2] = graph[v2][v1] = 1;
        }

        dfs(new boolean[n + 1], graph, v, n);
        System.out.println(sb.toString());

        sb.delete(0, sb.length());
        bfs(graph, v, n);
        System.out.println(sb.toString());

    }

    public static void bfs(int[][] graph, int startVertex, int vertexNum) {
        boolean v[] = new boolean[vertexNum + 1];
        Queue<Integer> q = new LinkedList<>();
        v[startVertex] = true;
        q.add(startVertex);

        while (!q.isEmpty()) {
            int vertex = q.poll();
            sb.append(vertex + " ");

            for (int i = 0; i < graph[vertex].length; i++) {
                if (graph[vertex][i] == 1 && !v[i]) {
                    v[i] = true;
                    q.add(i);
                }
            }
        }
        sb.append("\n");

    }

    public static void dfs(boolean[] v, int[][] graph, int vertex, int vertexNum) {
        v[vertex] = true;
        sb.append(vertex + " ");
        for (int i = 0; i < graph[vertex].length; i++) {
            if (graph[vertex][i] == 1 && !v[i]) {
                v[i] = true;
                dfs(v, graph, i, vertexNum);
            }
        }
    }
}
