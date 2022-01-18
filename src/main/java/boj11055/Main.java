package boj11055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

        dp[0] = value[0];
        int answer = dp[0];

        for (int i = 1; i < n; i++) {

            for (int j = 0; j < i; j++) {
                if (value[j] < value[i] && dp[j] + value[i] > dp[i])
                    dp[i] = dp[j] + value[i];
            }

            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }

}
