package boj2225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n, k;
        String[] s = reader.readLine().split(" ");

        n = Integer.parseInt(s[0]);
        k = Integer.parseInt(s[1]);

        long[][] dp = new long[k + 1][n + 1];
        Arrays.fill(dp[1], 1);

        for (int i = 2; i <= k; i++) {
            for (int j = 0; j <= n; j++) {
                for (int z = j; z >= 0; z--)
                    dp[i][j] += dp[i - 1][z];
                dp[i][j] %= 1000000000;
            }
        }

        System.out.println(dp[k][n]);
    }
}
