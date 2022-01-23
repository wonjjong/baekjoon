package boj2579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] v = new int[301];
        int[] dp = new int[301];

        for (int i = 1; i <= n; i++) {
            v[i] = Integer.parseInt(reader.readLine());
        }
        dp[1] = v[1];
        dp[2] = v[1] + v[2];

        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i - 2] + v[i], dp[i - 3] + v[i - 1] + v[i]);
        }

        System.out.println(dp[n]);

    }
}
