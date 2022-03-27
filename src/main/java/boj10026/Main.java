package boj10026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int dx[] = {0, 0, 1, -1};
    static int dy[] = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        char[][] map = new char[n][n];
        char[][] map2 = new char[n][n];

        boolean[][] v = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String s = reader.readLine();
            for (int j = 0; j < s.length(); j++) {
                map2[i][j] = s.charAt(j);
                map[i][j] = s.charAt(j);
                if(s.charAt(j) == 'G') {
                    map2[i][j] = 'R';
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!v[i][j]) {
                    char c = map[i][j];
                    Queue<Pair> q = new LinkedList<>();
                    q.add(new Pair(i, j));
                    answer++;
                    while (!q.isEmpty()) {
                        Pair poll = q.poll();
                        for (int k = 0; k < 4; k++) {
                            int nx = poll.x + dx[k];
                            int ny = poll.y + dy[k];
                            if(inside(nx,ny,n) && map[nx][ny] == c && !v[nx][ny]) {
                                q.add(new Pair(nx,ny));
                                v[nx][ny] = true;
                            }
                        }
                    }
                }
            }
        }
        System.out.print(answer+ " ");
        v = new boolean[n][n];
        answer =0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!v[i][j]) {
                    char c = map2[i][j];
                    Queue<Pair> q = new LinkedList<>();
                    q.add(new Pair(i, j));
                    answer++;
                    while (!q.isEmpty()) {
                        Pair poll = q.poll();
                        for (int k = 0; k < 4; k++) {
                            int nx = poll.x + dx[k];
                            int ny = poll.y + dy[k];
                            if(inside(nx,ny,n) && map2[nx][ny] == c && !v[nx][ny]) {
                                q.add(new Pair(nx,ny));
                                v[nx][ny] = true;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(answer);
    }


    public static boolean inside(int x, int y, int n) {
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
