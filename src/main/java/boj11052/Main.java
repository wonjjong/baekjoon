package boj11052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[] s = reader.readLine().split(" ");
        int[] v= new int[n+1];
        int[] sum = new int[n+1];

        for(int i=1;i<=n;i++) {
            v[i] = Integer.parseInt(s[i-1]);
        }
        sum[1] =v[1];

        for(int i=1; i<=n;i++) {
            for(int j=i-1; j>=0;j--) {
                sum[i] = Math.max(sum[j]+ v[i-j], sum[i]);
            }
        }
        System.out.println(sum[n]);
    }
}

