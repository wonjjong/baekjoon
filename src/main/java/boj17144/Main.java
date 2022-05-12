package boj17144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int r, c, t;
    static int[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

        r = Integer.parseInt(stringTokenizer.nextToken());
        c = Integer.parseInt(stringTokenizer.nextToken());
        t = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[r][c];
        List<int[]> cleaner = new ArrayList<>();

        for (int i = 0; i < r; i++) {
            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (map[i][j] == -1)
                    cleaner.add(new int[]{i, j});
            }
        }
        while (t-- > 0) {
            int[][] spread = new int[r][c];
            spread(spread);
            int[] up = cleaner.get(0);
            upClean(up[0], up[1]);

            int[] down = cleaner.get(1);
            downClean(down[0], down[1]);

        }
        int answer = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] > 0) answer += map[i][j];
            }
        }
        System.out.println(answer);
    }

    public static void spread(int[][] spread) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] != -1 && map[i][j] != 0) {
                    int count = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (inside(nx, ny) && map[nx][ny] != -1) {
                            ++count;
                            spread[nx][ny] += (map[i][j] / 5);
                        }
                    }
                    if (count != 0) map[i][j] -= ((map[i][j] / 5) * count);
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] += spread[i][j];
            }
        }
    }

    static boolean inside(int x, int y) {
        return x >= 0 && y >= 0 && x < r && y < c;
    }

    static void upClean(int x, int y) {
        for (int i = x - 1; i > 0; i--)
            map[i][0] = map[i - 1][0];

        for (int i = 0; i < c - 1; i++)
            map[0][i] = map[0][i + 1];

        for (int i = 0; i < x; i++)
            map[i][c - 1] = map[i + 1][c - 1];

        for (int i = c - 1; i > y + 1; i--)
            map[x][i] = map[x][i - 1];

        map[x][y + 1] = 0;
    }

    static void downClean(int x, int y) {
        for (int i = x + 1; i < r - 1; i++) {
            map[i][0] = map[i + 1][0];
        }

        for (int i = 0; i < c - 1; i++) {
            map[r - 1][i] = map[r - 1][i + 1];
        }

        for (int i = r - 1; i > x; i--)
            map[i][c - 1] = map[i - 1][c - 1];

        for (int i = c - 1; i > y + 1; i--)
            map[x][i] = map[x][i - 1];

        map[x][y + 1] = 0;
    }

}
