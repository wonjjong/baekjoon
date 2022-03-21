package boj11047;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        int[] coin = new int[n];
        for(int i=0;i<n;i++) {
            coin[i] = Integer.parseInt(reader.readLine());
        }

        int answer =0;
        while(k!=0) {
            int index =0;
            for(; index<n;index++) {
                if(coin[index] > k) {
                    break;
                }

            }
            --index;

            answer += ( k / coin[index]);
            k %= coin[index];
        }

        System.out.println(answer);

    }
}
