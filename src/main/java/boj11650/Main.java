package boj11650;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        ArrayList<Pointer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String[] s = reader.readLine().split(" ");
            int x = Integer.parseInt(s[0]);
            int y = Integer.parseInt(s[1]);
            list.add(new Pointer(x, y));
        }

        Collections.sort(list);
        for (Pointer pointer : list) {
            sb.append(pointer.x).append(" ").append(pointer.y).append("\n");
        }

        System.out.println(sb.toString());
    }

    static class Pointer implements Comparable<Pointer> {
        int x, y;

        public Pointer(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pointer o) {
            if (this.x == o.x) {
                return Integer.compare(this.y, o.y);
            }
            return Integer.compare(this.x, o.x);
        }
    }
}
