package boj21608;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static int[][] like;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        map = new int[n][n];
        like = new int[n * n][4];
        for (int i = 0; i < n * n; i++) {
            ArrayList<Info> arr = new ArrayList<>();
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int stuNum = Integer.parseInt(stringTokenizer.nextToken());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            like[stuNum - 1][0] = a;
            int b = Integer.parseInt(stringTokenizer.nextToken());
            like[stuNum - 1][1] = b;
            int c = Integer.parseInt(stringTokenizer.nextToken());
            like[stuNum - 1][2] = c;
            int d = Integer.parseInt(stringTokenizer.nextToken());
            like[stuNum - 1][3] = d;
            int infoIdx = 0;

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (map[j][k] != 0) continue;
                    int stuCnt = 0, blankCnt = 0;

                    for (int l = 0; l < 4; l++) {
                        int x = j + dx[l];
                        int y = k + dy[l];
                        if (inside(x, y)) {
                            if (map[x][y] == a || map[x][y] == b || map[x][y] == c || map[x][y] == d) {
                                ++stuCnt;
                            }
                            if (map[x][y] == 0) ++blankCnt;
                        }
                    }
                    arr.add(new Info(j, k, blankCnt, stuCnt));
                }
            }
            Collections.sort(arr);
            map[arr.get(0).x][arr.get(0).y] = stuNum;
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];

                    if (inside(x, y)) {
                        if (map[x][y] == like[map[i][j] - 1][0] || map[x][y] == like[map[i][j] - 1][1] || map[x][y] == like[map[i][j] - 1][2] || map[x][y] == like[map[i][j] - 1][3])
                            ++cnt;
                    }
                }
                if (cnt == 1) answer += 1;
                else if (cnt == 2) answer += 10;
                else if (cnt == 3) answer += 100;
                else if (cnt == 4) answer += 1000;
            }
        }
        System.out.println(answer);
    }

    static boolean inside(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }

    static class Info implements Comparable<Info> {
        int x, y, blankCnt, stuCnt;

        public Info(int x, int y, int blankCnt, int stuCnt) {
            this.x = x;
            this.y = y;
            this.blankCnt = blankCnt;
            this.stuCnt = stuCnt;
        }

        @Override
        public int compareTo(Info o) {
            if (stuCnt == o.stuCnt) {
                if (blankCnt == o.blankCnt) {
                    if (x == o.x) {
                        return Integer.compare(y, o.y);
                    }
                    return Integer.compare(x, o.x);
                }
                return Integer.compare(o.blankCnt, blankCnt);
            }
            return Integer.compare(o.stuCnt, stuCnt);
        }
    }
}
