package boj10844;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        long[][] v = new long[n + 1][10];
        for (int i = 1; i <= 9; i++)
            v[1][i] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j == 0) {
                    v[i][j] = v[i - 1][j + 1];
                } else if (j == 9) {
                    v[i][j] = v[i - 1][j - 1];
                } else {
                    v[i][j] = (v[i - 1][j - 1] + v[i - 1][j + 1]) % 1000000000;
                }
            }
        }

        long answer = 0;
        for (int i = 0; i <= 9; i++)
            answer = (answer + v[n][i]) % 1000000000;

        System.out.println(answer);
    }
}
