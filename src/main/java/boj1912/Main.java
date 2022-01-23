package boj1912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] v = new int[n + 1];
        int answer = Integer.MIN_VALUE;

        String[] s = reader.readLine().split(" ");

        for (int i = 1; i <= n; i++) {
            v[i] = Integer.parseInt(s[i - 1]);
        }

        for (int i = 1; i <= n; i++) {
            v[i] = Math.max(v[i], v[i - 1] + v[i]);
            answer = Math.max(answer, v[i]);
        }

        System.out.println(answer);
    }
}
