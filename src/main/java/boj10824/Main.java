package boj10824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
        String a = stringTokenizer.nextToken();
        String b = stringTokenizer.nextToken();
        String c = stringTokenizer.nextToken();
        String d = stringTokenizer.nextToken();
        long ab = Long.parseLong(a+b);
        long cd = Long.parseLong(c+d);
        System.out.println(ab+cd);
    }
}
