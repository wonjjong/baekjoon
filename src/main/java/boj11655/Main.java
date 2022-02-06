package boj11655;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        String answer = new String();
        //영어 알파벳을 13글자씩 밀어서 만든다.
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLowerCase(c)) {
                if (c + 13 > 122)
                    c = (char) ((c + 13) % 123 + 97);
                else
                    c = (char) ((c + 13));
                answer += c;

            }
            else if (Character.isUpperCase(c)) {
                if (c + 13 > 90)
                    c = (char) ((c + 13) % 91 + 65);
                else
                    c = (char) (c + 13);
                answer += c;
            } else {
                answer += c;
            }
        }
        System.out.println(answer);

    }
}
