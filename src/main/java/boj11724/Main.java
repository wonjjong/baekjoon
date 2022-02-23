package boj11724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());

        int[][] graph = new int[n + 1][n + 1];
        boolean[] visited = new boolean[n + 1];
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(reader.readLine());
            int u = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());
            graph[u][v] = graph[v][u] = 1;
        }

        int answer = 0;
        for(int i=1;i<=n;i++) {
            if(!visited[i]) {
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                ++answer;
                while(!q.isEmpty()) {
                    int v = q.poll();
                    visited[v] = true;
                    for(int j=1; j<=n;j++) {
                        if(graph[v][j] == 1 && !visited[j]) {
                            visited[j]= true;
                            q.add(j);
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
