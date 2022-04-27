package boj21610;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[][] map;
    static int n, m;
    static ArrayList<Cloud> cloud;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        cloud = new ArrayList<>();
        cloud.add(new Cloud(n - 1, 0));
        cloud.add(new Cloud(n - 1, 1));
        cloud.add(new Cloud(n - 2, 0));
        cloud.add(new Cloud(n - 2, 1));

        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(reader.readLine());
            int d = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int s = Integer.parseInt(stringTokenizer.nextToken());
            int moveSize = s % n;
            boolean[][] v = new boolean[n][n];

            for (Cloud c : cloud) {
                for (int ms = 0; ms < moveSize; ms++) {
                    int nx = c.x + dx[d];
                    int ny = c.y + dy[d];
                    if (nx < 0) nx = n - 1;
                    else if (nx >= n) nx = 0;
                    if (ny < 0) ny = n - 1;
                    else if (ny >= n) ny = 0;
                    c.x = nx;
                    c.y = ny;
                }
                v[c.x][c.y] = true;
                map[c.x][c.y] += 1;
            }

            rain();
            ArrayList<Cloud> newCloud = new ArrayList<Cloud>();

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (!v[j][k] && map[j][k] >= 2) {
                        map[j][k] -= 2;
                        newCloud.add(new Cloud(j, k));
                    }
                }
            }
            cloud = newCloud;

        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                count += map[i][j];
            }
        }

        System.out.println(count);
    }


    public static void rain() {
        for (Cloud c : cloud) {
            if (c.x - 1 >= 0 && c.y - 1 >= 0 && map[c.x - 1][c.y - 1] >= 1)
                map[c.x][c.y]++;
            if (c.x - 1 >= 0 && c.y + 1 < n && map[c.x - 1][c.y + 1] >= 1)
                map[c.x][c.y]++;
            if (c.x + 1 < n && c.y + 1 < n && map[c.x + 1][c.y + 1] >= 1)
                map[c.x][c.y]++;
            if (c.x + 1 < n && c.y - 1 >= 0 && map[c.x + 1][c.y - 1] >= 1)
                map[c.x][c.y]++;
        }
    }

    public static class Cloud {
        int x, y;

        public Cloud(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

