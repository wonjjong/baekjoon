package boj21609;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, answer = 0;
    static int[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

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

        while (true) {
            ArrayList<Info> infos = new ArrayList<>();
            boolean[][] v = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] > 0 && !v[i][j]) {
                        Info info = new Info(i, j);
                        info.loc.add(new int[]{i, j});
                        info.groupCnt = 1;
                        int color = map[i][j];
                        Queue<int[]> q = new LinkedList<>();
                        q.add(new int[]{i, j});
                        v[i][j] = true;
                        while (!q.isEmpty()) {
                            int[] poll = q.poll();
                            int x = poll[0];
                            int y = poll[1];

                            for (int k = 0; k < 4; k++) {
                                int nx = x + dx[k];
                                int ny = y + dy[k];
                                if (inside(nx, ny) && !v[nx][ny]) {
                                    if (map[nx][ny] == color || map[nx][ny] == 0) {
                                        v[nx][ny] = true;
                                        ++info.groupCnt;
                                        info.loc.add(new int[]{nx, ny});
                                        q.add(new int[]{nx, ny});
                                        if (map[nx][ny] == 0) ++info.rainbowCnt;
                                    }
                                }
                            }
                        } // while loop end

                        if (info.groupCnt >= 2) {
                            infos.add(info);
                        }

                        for (int k = 0; k < n; k++) {
                            for (int l = 0; l < n; l++) {
                                if (map[k][l] == 0 && v[k][l]) v[k][l] = false;
                            }
                        }
                    }
                }
            } // for i end
            Collections.sort(infos);

            if (infos.size() > 0) {
                answer += infos.get(0).groupCnt * infos.get(0).groupCnt;
                rmBlocks(infos.get(0)); //블록 제거
                gravity(); //중력
                ccw(); //반시계
                gravity(); //중력
            } else {
                break;
            }
        } // while end
        System.out.println(answer);
    }

    private static void rmBlocks(Info info) {
        ArrayList<int[]> loc = info.loc;

        for (int[] ints : loc) {
            map[ints[0]][ints[1]] = -100;
        }
    }

    static class Info implements Comparable<Info> {
        int x, y;
        int groupCnt, rainbowCnt;
        ArrayList<int[]> loc = new ArrayList<>();

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Info o) {
            if (groupCnt == o.groupCnt) {
                if (rainbowCnt == o.rainbowCnt) {
                    if (x == o.x) {
                        return Integer.compare(o.y, y);
                    }
                    return Integer.compare(o.x, x);
                }
                return Integer.compare(o.rainbowCnt, rainbowCnt);
            }
            return Integer.compare(o.groupCnt, groupCnt);
        }
    }

    static boolean inside(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }

    static void ccw() {
        int[][] tmp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tmp[i][j] = map[j][n - i - 1];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = tmp[i][j];
            }
        }
    }

    static void gravity() {
        int insIdx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (map[j][i] < -1) {
                    insIdx = j; //빈 곳 인덱스
                    int mvIdx = -1; //움직여질 인덱스
                    for (int k = j - 1; k >= 0; k--) {
                        if (map[k][i] == -1) break;
                        if (map[k][i] >= 0) {
                            mvIdx = k;
                            break;
                        }
                    }
                    if (mvIdx != -1) {
                        map[insIdx][i] = map[mvIdx][i];
                        map[mvIdx][i] = -100;
                    }
                }
            }
        }
    }
}