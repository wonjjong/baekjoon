package boj9095;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(reader.readLine());
        int[] v = new int[11];

        v[1] = 1;
        v[2] = 2;
        v[3] = 4;
        for (int i = 4; i <= 10; i++) {
            v[i] = v[i - 3] + v[i - 2] + v[i - 1];
        }

        for (int i = 0; i < testCase; i++) {
            int n = Integer.parseInt(reader.readLine());
            System.out.println(v[n]);
        }

    }
}
