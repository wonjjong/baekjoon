package boj10451;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(reader.readLine());
        while (--testCase >= 0) {
            int n = Integer.parseInt(reader.readLine());

            int[] arr = new int[n];
            boolean[] v = new boolean[n];

            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            }

            int answer = 0;
            for (int i = 0; i < n; i++) {
                if (!v[i]) {
                    ++answer;
                    v[i] = true;
                    dfs(arr[i], v, arr);
                }
            }
            System.out.println(answer);
        }
    }

    public static void dfs(int vertex, boolean[] v, int[] arr) {
        if (!v[vertex]) {
            v[vertex] = true;
            dfs(arr[vertex], v, arr);
        }
    }
}
