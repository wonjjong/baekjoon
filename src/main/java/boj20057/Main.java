package boj20057;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, answer = 0, cntX, cntY;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        map = new int[n + 4][n + 4];
        cntX = cntY = (n + 4) / 2;
        for (int i = 2; i < n + 2; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            for (int j = 2; j < n + 2; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        for (int i = 1; i <= n - 1; i++) {
            if (i % 2 == 1) {
                left(cntX, cntY, i);
                down(cntX, cntY, i);
            } else {
                right(cntX, cntY, i);
                up(cntX, cntY, i);
            }
        }
        left(cntX, cntY, n - 1);

        int answer = 0;
        for (int i = 0; i < n + 4; i++) {
            for (int j = 0; j < n + 4; j++) {
                if (i >= 2 && i < n + 2 && j >= 2 && j < n + 2) continue;
                answer += map[i][j];
            }
        }
        System.out.println(answer);
    }

    public static void left(int x, int y, int size) {
        for (int i = 0; i < size; i++) {
            cal(cntX, cntY, 0);
            cntY -= 1;
        }
    }

    public static void down(int x, int y, int size) {
        for (int i = 0; i < size; i++) {
            cal(cntX, cntY, 1);
            cntX += 1;
        }
    }

    public static void right(int x, int y, int size) {
        for (int i = 0; i < size; i++) {
            cal(cntX, cntY, 2);
            cntY += 1;
        }
    }

    public static void up(int x, int y, int size) {
        for (int i = 0; i < size; i++) {
            cal(cntX, cntY, 3);
            cntX -= 1;
        }
    }

    public static void cal(int x, int y, int dir) {
        int val;
        if (dir == 0) {
            val = map[x][y - 1];
        } else if (dir == 1) {
            val = map[x + 1][y];
        } else if (dir == 2) {
            val = map[x][y + 1];
        } else val = map[x - 1][y];

        switch (dir) {
            case 0:
                map[x][y - 1] = 0;
                map[x - 1][y] += (int) (val * 0.01);
                map[x + 1][y] += (int) (val * 0.01);

                map[x - 1][y - 1] += (int) (val * 0.07);
                map[x + 1][y - 1] += (int) (val * 0.07);

                map[x - 2][y - 1] += (int) (val * 0.02);
                map[x + 2][y - 1] += (int) (val * 0.02);

                map[x + 1][y - 2] += (int) (val * 0.1);
                map[x - 1][y - 2] += (int) (val * 0.1);

                map[x][y - 3] += (int) (val * 0.05);
                map[x][y - 2] += (val - (2 * (int) (val * 0.01) + 2 * (int) (val * 0.07) + 2 * (int) (val * 0.02) + (int) (val * 0.05) + 2 * (int) (val * 0.1)));
                break;
            case 1:
                map[x + 1][y] = 0;

                map[x][y - 1] += (int) (val * 0.01);
                map[x][y + 1] += (int) (val * 0.01);

                map[x + 1][y - 1] += (int) (val * 0.07);
                map[x + 1][y + 1] += (int) (val * 0.07);

                map[x + 1][y - 2] += (int) (val * 0.02);
                map[x + 1][y + 2] += (int) (val * 0.02);

                map[x + 2][y - 1] += (int) (val * 0.1);
                map[x + 2][y + 1] += (int) (val * 0.1);

                map[x + 3][y] += (int) (val * 0.05);
                map[x + 2][y] += (val - (2 * (int) (val * 0.01) + 2 * (int) (val * 0.07) + 2 * (int) (val * 0.02) + (int) (val * 0.05) + 2 * (int) (val * 0.1)));
                break;
            case 2:
                map[x][y + 1] = 0;

                map[x - 1][y] += (int) (val * 0.01);
                map[x + 1][y] += (int) (val * 0.01);

                map[x - 1][y + 1] += (int) (val * 0.07);
                map[x + 1][y + 1] += (int) (val * 0.07);

                map[x - 2][y + 1] += (int) (val * 0.02);
                map[x + 2][y + 1] += (int) (val * 0.02);

                map[x + 1][y + 2] += (int) (val * 0.1);
                map[x - 1][y + 2] += (int) (val * 0.1);

                map[x][y + 3] += (int) (val * 0.05);

                map[x][y + 2] += (val - (2 * (int) (val * 0.01) + 2 * (int) (val * 0.07) + 2 * (int) (val * 0.02) + (int) (val * 0.05) + 2 * (int) (val * 0.1)));
                break;
            case 3:
                map[x - 1][y] = 0;

                map[x][y - 1] += (int) (val * 0.01);
                map[x][y + 1] += (int) (val * 0.01);

                map[x - 1][y + 1] += (int) (val * 0.07);
                map[x - 1][y - 1] += (int) (val * 0.07);

                map[x - 1][y + 2] += (int) (val * 0.02);
                map[x - 1][y - 2] += (int) (val * 0.02);

                map[x - 2][y - 1] += (int) (val * 0.1);
                map[x - 2][y + 1] += (int) (val * 0.1);

                map[x - 3][y] += (int) (val * 0.05);

                map[x - 2][y] += (val - (2 * (int) (val * 0.01) + 2 * (int) (val * 0.07) + 2 * (int) (val * 0.02) + (int) (val * 0.05) + 2 * (int) (val * 0.1)));
                break;
        }
    }
}