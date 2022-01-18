package boj11722;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] value = new int[n];
        int[] dp = new int[n];
        String[] s = reader.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            value[i] = Integer.parseInt(s[i]);
        }

        Arrays.fill(dp, 1);
        int answer = dp[0];

        for (int i = 1; i < n; i++) {

            for (int j = 0; j < i; j++) {
                if (value[j] > value[i] && dp[j] >= dp[i])
                    dp[i]++;
            }

            answer = Math.max(answer, dp[i]);
        }
        System.out.println(answer);
    }
}
