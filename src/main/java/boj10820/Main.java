package boj10820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        while((s =reader.readLine()) != null) {
            int upper = 0, lower =0, digit =0, whiteSpace = 0;

            for(int i=0;i<s.length();i++) {
                char c = s.charAt(i);
                if(Character.isLowerCase(c)) {
                    lower++;
                } else if(Character.isUpperCase(c)){
                    upper++;
                }else if(Character.isDigit(c)) {
                    digit++;
                } else if(Character.isWhitespace(c)) {
                    whiteSpace++;
                }
            }
            System.out.println(lower+ " " + upper+ " " + digit + " "+whiteSpace);
        }

    }
}
