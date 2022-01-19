package boj2156;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] wine = new int[n + 1];
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            wine[i] = Integer.parseInt(reader.readLine());
        }
        dp[1] = wine[1];

        if (n > 1)
            dp[2] = wine[1] + wine[2];

        int answer = n >= 2 ? dp[2] : dp[1];

        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + wine[i], dp[i - 3] + wine[i - 1] + wine[i]));
            answer = Math.max(answer, dp[i]);
        }

        //dp[n] 출력
        System.out.println(answer);
    }
}