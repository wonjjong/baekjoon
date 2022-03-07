package boj4963;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int dx[] = {0, 0, -1, 1, -1, -1, 1, 1};
    public static int dy[] = {-1, 1, 0, 0, -1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int w = Integer.parseInt(stringTokenizer.nextToken());
            int h = Integer.parseInt(stringTokenizer.nextToken());
            if (w == 0 && h == 0) {
                break;
            }

            int[][] map = new int[h][w];
            boolean[][] v = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            int count = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == 1 && !v[i][j]) {
                        ++count;
                        v[i][j] = true;
                        Queue<Pair> q = new LinkedList<>();
                        q.add(new Pair(i, j));
                        while (!q.isEmpty()) {
                            Pair poll = q.poll();
                            int x = poll.x;
                            int y = poll.y;
                            for (int k = 0; k < 8; k++) {
                                int nx = x + dx[k];
                                int ny = y + dy[k];
                                if (isInside(nx, ny, h, w) && !v[nx][ny] && map[nx][ny] == 1) {
                                    v[nx][ny] = true;
                                    q.add(new Pair(nx, ny));
                                }
                            }
                        }
                    }
                }
            }
            System.out.println(count);
        } // end while

    }

    public static boolean isInside(int x, int y, int h, int w) {
        return x >= 0 && y >= 0 && x < h && y < w;
    }

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
