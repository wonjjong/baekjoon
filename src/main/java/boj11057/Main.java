package boj11057;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        long[][] v = new long[n + 1][10];
        for (int i = 0; i <= 9; i++)
            v[1][i] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k <= j; k++) {
                    v[i][j] += v[i - 1][k];
                    v[i][j] %= 10007;
                }
            }
        }

        long answer = 0;
        for (int i = 0; i <= 9; i++)
            answer = (answer + v[n][i]) % 10007;
        System.out.println(answer);
    }
}
