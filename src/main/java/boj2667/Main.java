package boj2667;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static int dx[] = {0, 0, -1, 1};
    public static int dy[] = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n;
        n = Integer.parseInt(reader.readLine());
        int[][] complex = new int[n][n];
        boolean[][] v = new boolean[n][n];

        //input
        for (int i = 0; i < n; i++) {
            String s = reader.readLine();
            for (int j = 0; j < n; j++) {
                complex[i][j] = s.charAt(j) - '0';
            }
        }

        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (complex[i][j] == 1 && !v[i][j]) {
                    int count = 0;
                    v[i][j] = true;
                    Queue<Pair> q = new LinkedList<>();
                    q.add(new Pair(i, j));
                    while (!q.isEmpty()) {
                        Pair poll = q.poll();
                        ++count;
                        int x = poll.x;
                        int y = poll.y;
                        for (int k = 0; k < 4; k++) {
                            int nx = x + dx[k];
                            int ny = y + dy[k];
                            if (isInside(nx, ny, n) && complex[nx][ny] == 1 && !v[nx][ny]) {
                                v[nx][ny] = true;
                                q.add(new Pair(nx, ny));
                            }
                        }
                    }
                    answer.add(count);
                }
            }
        }

        Collections.sort(answer);
        System.out.println(answer.size());
        for (Integer integer : answer) {
            System.out.println(integer);
        }
    }

    static boolean isInside(int x, int y, int n) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
