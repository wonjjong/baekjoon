package boj11005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int b = Integer.parseInt(stringTokenizer.nextToken());
        StringBuilder answer =new StringBuilder();

        while(n !=0) {
            int mod = n % b;
            n /= b;
            if(mod >= 10 && mod <=35) {
                answer.append((char)('A'+mod-10));
            }else
                answer.append(String.valueOf(mod));
        }
        System.out.println(answer.reverse());
    }
}
