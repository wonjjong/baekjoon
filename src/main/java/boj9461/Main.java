package boj9461;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(reader.readLine());
        long[] v= new long[101];
        v[1]= v[2] = 1;


        for (int j = 3; j <= 100; j++) {
            v[j] = v[j - 3] + v[j - 2];
        }

        for (int i = 0; i < testCase; i++) {
            int n = Integer.parseInt(reader.readLine());
            System.out.println(v[n]);
        }

    }
}
