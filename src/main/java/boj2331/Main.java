package boj2331;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] d = new int[1000000];
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

        int a = Integer.parseInt(stringTokenizer.nextToken());
        int p = Integer.parseInt(stringTokenizer.nextToken());
        int count = 0;
        d[a] = ++count;
        int answer = 0;

        while (true) {
            int newA = 0;

            while (a != 0) {
                newA += Math.pow(a % 10, p);
                a = a / 10;
            }
            if(d[newA] != 0) {
                answer = d[newA] -1 ;
                break;
            } else {
                d[newA] = ++count;
                a = newA;
            }

        }
        System.out.println(answer);

    }
}
