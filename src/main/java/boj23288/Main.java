package boj23288;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k, dir = 0, cntX = 0, cntY = 0, answer = 0;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[] dice = {1, 6, 4, 3, 5, 2};

    public static boolean in(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    public static void revDir() {
        dir = (dir + 2) % 4;
    }

    public static void cw() {
        dir = (dir + 1) % 4;
    }

    public static void ccw() {
        dir = (dir - 1 + 4) % 4;
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
        k = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        for (int i = 0; i < k; i++) {
            if (!in(cntX + dx[dir], cntY + dy[dir])) {
                revDir();
            }
            cntX += dx[dir];
            cntY += dy[dir];
            switch (dir) {
                case 0:
                    mvToEast();
                    break;
                case 1:
                    mvToSouth();
                    break;
                case 2:
                    mvToWest();
                    break;
                case 3:
                    mvToNorth();
                    break;
            }
            int mapVal = getScore(cntX, cntY, map[cntX][cntY]) * map[cntX][cntY];
            if (dice[1] > map[cntX][cntY]) {
                cw();
            } else if (dice[1] < map[cntX][cntY]) {
                ccw();
            }
            answer += mapVal;
        }
        System.out.println(answer);
    }

    public static int getScore(int x, int y, int val) {
        int count = 1;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] v = new boolean[n][m];
        v[x][y] = true;
        q.add(new int[]{x, y});
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = poll[0] + dx[i];
                int ny = poll[1] + dy[i];
                if (in(nx, ny) && map[nx][ny] == val && !v[nx][ny]) {
                    q.add(new int[]{nx, ny});
                    v[nx][ny] = true;
                    ++count;
                }
            }
        }
        return count;
    }
}