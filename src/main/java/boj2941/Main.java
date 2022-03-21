package boj2941;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static String[] convert = {"c=","c-","dz=","d-","lj","nj","s=","z="};
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();

        for(int i=0;i<convert.length;i++) {
            s = s.replace(convert[i], "a");
        }
        System.out.println(s.length());

    }
}
