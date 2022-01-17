package boj9465;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(reader.readLine());

        for (int i = 0; i < testCase; i++) {
            int n = Integer.parseInt(reader.readLine());
            int[][] dp = new int[2][n + 1];

            for (int j = 0; j < 2; j++) {
                String[] values = reader.readLine().split(" ");
                for(int x =1; x <= values.length; x++)
                    dp[j][x] = Integer.parseInt(values[x-1]);
            }
            for(int j=2;j<=n;j++) {
                dp[0][j] = Math.max(dp[1][j-1] + dp[0][j] , Math.max(dp[1][j-2]+ dp[0][j], dp[0][j-2]+dp[0][j]));
                dp[1][j] = Math.max(dp[0][j-1] + dp[1][j] , Math.max(dp[1][j-2]+ dp[1][j], dp[0][j-2]+dp[1][j]));

            }

            System.out.println(Math.max(dp[0][n], dp[1][n]));
        }
    }
}
