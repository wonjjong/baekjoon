package boj1759;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int L, C;
    static char[] alpha;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
        L = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());
        alpha = new char[C];
        stringTokenizer = new StringTokenizer(reader.readLine());

        for (int i = 0; i < C; i++) {
            alpha[i] = stringTokenizer.nextToken().charAt(0);
        }
        Arrays.sort(alpha);

        for (int i = 0; i <= C - L; i++) {
            dfs(i, 1, ""+ alpha[i]);
        }
    }

    private static boolean validation(String s) {
        //한개 이상의 모음과 두개 이상의 자음
        int jaCount = 0; //자음
        int moCount =0; //모음
        for(int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if(c == 'a' || c == 'e' || c == 'i' || c =='o' || c=='u')
                moCount++;
            else jaCount++;
        }

        return moCount >= 1 && jaCount >=2;
    }

    private static void dfs(int index, int count, String s) {
        if(count == L) {
            if(validation(s)) {
                System.out.println(s);
            }
            return;
        }

        for(int i=index+1;i<C;i++) {
           dfs(i, count + 1, s + alpha[i]);
        }

    }
}
