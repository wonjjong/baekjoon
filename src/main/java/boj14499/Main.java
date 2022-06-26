package boj14499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k, cntX, cntY;
    static int[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[] dice = {0, 0, 0, 0, 0, 0};
    public static boolean in(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    public static void mvToEast() {
        int t = dice[0];
        dice[0] = dice[2];
        dice[2] = dice[1];
        dice[1] = dice[3];
        dice[3] = t;

    }

    public static void mvToSouth() {
        int t = dice[0];
        dice[0] = dice[5];
        dice[5] = dice[1];
        dice[1] = dice[4];
        dice[4] = t;
    }

    public static void mvToWest() {
        int t = dice[0];
        dice[0] = dice[3];
        dice[3] = dice[1];
        dice[1] = dice[2];
        dice[2] = t;
    }

    public static void mvToNorth() {
        int t = dice[0];
        dice[0] = dice[4];
        dice[4] = dice[1];
        dice[1] = dice[5];
        dice[5] = t;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        cntX = Integer.parseInt(stringTokenizer.nextToken());
        cntY = Integer.parseInt(stringTokenizer.nextToken());
        k = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        stringTokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < k; i++) {
            int dir = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            if (!in(cntX + dx[dir], cntY + dy[dir])) {
                continue;
            }
            cntX += dx[dir];
            cntY += dy[dir];
            switch (dir) {
                case 0:
                    mvToEast();
                    break;
                case 1:
                    mvToWest();
                    break;
                case 2:
                    mvToNorth();
                    break;
                case 3:
                    mvToSouth();
                    break;
            }
            if (map[cntX][cntY] == 0) {
                map[cntX][cntY] = dice[1];
            } else {
                dice[1] = map[cntX][cntY];
                map[cntX][cntY] = 0;
            }
            System.out.println(dice[0] + " ");
        }
    }
}