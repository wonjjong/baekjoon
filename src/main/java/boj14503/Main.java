package boj14503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static boolean[][] clean;
    static int n, m, r, c, d;
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(reader.readLine());
        r = Integer.parseInt(stringTokenizer.nextToken());
        c = Integer.parseInt(stringTokenizer.nextToken());
        d = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[n][m];
        clean = new boolean[n][m];
        clean[r][c] = true;
        int cleanCount = 1;
        int breakCount = 0;

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        while (true) {
            int nx = r, ny = c, nd = d;
            int backX = r, backY = c;

            switch (d) {
                case 0:
                    nx = r + dx[3];
                    ny = c + dy[3];
                    nd = 3;
                    break;
                case 1:
                    nx = r + dx[0];
                    ny = c + dy[0];
                    nd = 0;
                    break;
                case 2:
                    nx = r + dx[1];
                    ny = c + dy[1];
                    nd = 1;
                    break;
                case 3:
                    nx = r + dx[2];
                    ny = c + dy[2];
                    nd = 2;
                    break;
            }

            if (map[nx][ny] == 0 && !clean[nx][ny]) {
                clean[nx][ny] = true;
                ++cleanCount;
                r = nx;
                c = ny;
                d = nd;
                breakCount = 0;
                continue;
            } else {
                ++breakCount;
                d = nd;
            }
            if (breakCount == 4) {
                switch (d) {
                    case 0:
                        backX = r + dx[2];
                        backY = c + dy[2];
                        break;
                    case 1:
                        backX = r + dx[3];
                        backY = c + dy[3];
                        break;
                    case 2:
                        backX = r + dx[0];
                        backY = c + dy[0];
                        break;
                    case 3:
                        backX = r + dx[1];
                        backY = c + dy[1];
                        break;
                }

                if (map[backX][backY] == 1) {
                    break;
                } else {
                    r = backX;
                    c = backY;
                    breakCount = 0;
                }
            }
        }
        System.out.println(cleanCount);
    }

}
