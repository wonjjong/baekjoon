package boj11651;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        //[i][0] : y, [i][1] = x
        int[][] pointer = new int[n][2];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String[] s = reader.readLine().split(" ");
            int x = Integer.parseInt(s[0]);
            int y = Integer.parseInt(s[1]);
            pointer[i][0] = x;
            pointer[i][1] = y;
        }

        //y좌표 -> x좌표순 정렬
        Arrays.sort(pointer, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return Integer.compare(o1[0], o2[0]);
            }
            return Integer.compare(o1[1], o2[1]);
        });

        for (int i = 0; i < pointer.length; i++) {
            sb.append(pointer[i][0]).append(" ").append(pointer[i][1]).append("\n");
        }

        System.out.println(sb.toString());
    }
}
