package boj2193;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        long[] v = new long[91];
        v[1] = v[2] = 1;
        for(int i=3;i<=n;i++) {
            v[i] = v[i-2]+ v[i-1];
        }
        System.out.println(v[n]);

    }
}
