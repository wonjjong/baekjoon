package boj10808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int[] alpha = new int[26];
        for(int i=0;i<s.length();i++) {
            alpha[s.charAt(i)-'a']++;
        }
        for (int i : alpha) {
            System.out.print(i+ " ");
        }
        System.out.println();
    }
}
