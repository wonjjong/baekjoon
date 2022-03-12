package boj2146;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, answer = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[][] v;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        map = new int[n][n];
        v = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!v[i][j] && map[i][j] == 1) {
                    ++index;
                    Queue<Pair> q = new LinkedList<>();
                    map[i][j] = index;
                    v[i][j] = true;
                    q.add(new Pair(i, j));

                    while (!q.isEmpty()) {
                        Pair poll = q.poll();
                        for (int k = 0; k < 4; k++) {
                            int nx = poll.x + dx[k];
                            int ny = poll.y + dy[k];
                            if (isInside(nx, ny) && map[nx][ny] == 1 && !v[nx][ny]) {
                                v[nx][ny] = true;
                                map[nx][ny] = index;
                                q.add(new Pair(nx, ny));
                            }
                        }
                    }
                }
            }
        }

        for (int i = 1; i <= index; i++) {
            answer = Math.min(answer,getMinDistance(i));
        }

        System.out.println(answer);

    }

    private static int getMinDistance(int index) {
        int distance = 0;
        boolean[][] v = new boolean[n][n];
        Queue<Pair> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == index) {
                    q.add(new Pair(i, j));
                    v[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            int queueSize = q.size();
            for (int i = 0; i < queueSize; i++) {
                Pair poll = q.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = poll.x + dx[j];
                    int ny = poll.y + dy[j];
                    if (isInside(nx, ny)) {
                        if (map[nx][ny] != index && map[nx][ny] != 0) {

                            return distance;
                        } else if ( map[nx][ny] == 0 && !v[nx][ny]) {
                            q.add(new Pair(nx, ny));
                            v[nx][ny] = true;
                        }
                    }
                }
            }
            distance++;
        }

        return  Integer.MAX_VALUE;
    }

    public static boolean isInside(int x, int y) {
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
