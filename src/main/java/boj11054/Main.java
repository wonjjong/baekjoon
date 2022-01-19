package boj11054;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] value = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];

        String[] s = reader.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            value[i] = Integer.parseInt(s[i]);
        }

        for (int i = 1; i < value.length; i++) {
            for (int j = 0; j < i; j++) {
                if (value[j] < value[i]) {
                    left[i] = Math.max(left[i], left[j] + 1);
                }
            }
        }
        for (int i = value.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < value.length; j++) {
                if (value[j] < value[i]) {
                    right[i] = Math.max(right[i], right[j] + 1);
                }
            }
        }

        int answer =0;
        for(int i=0;i<value.length;i++) {
            answer = Math.max(answer, left[i]+right[i]+1);
        }
        System.out.println(answer);
    }

}
