package boj20058;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, q, powN, powL;
    static int[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] answer = new int[2];

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        q = Integer.parseInt(stringTokenizer.nextToken());
        powN = (int) Math.pow(2, n);

        map = new int[powN][powN];

        for (int i = 0; i < powN; i++) {
            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < powN; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        stringTokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < q; i++) {
            int L = Integer.parseInt(stringTokenizer.nextToken());
            powL = (int) Math.pow(2, L);
            rotate(0,0, powL);
            melt();
        }

        for (int i = 0; i < powN; i++) {
            for (int j = 0; j < powN; j++) {
                answer[0] += map[i][j];
            }
        }
        mass();

        System.out.println(answer[0]);
        System.out.println(answer[1]);
    }

    public static void rotate(int x, int y, int size) {
        int[][] tmp = new int[powN][powN];
        for (int i = 0; i < powN; i++) {
            for (int j = 0; j < powN; j++) {
                tmp[i][j] = map[i][j];
            }
        }

        for (int i = 0; i< powN; i += size) {
            for (int j = 0; j< powN; j += size) {
                rot(i,j,size,tmp);
            }
        }

        for (int i = 0; i < powN; i++) {
            for (int j = 0; j < powN; j++) {
                map[i][j] = tmp[i][j];
            }
        }
    }

    private static void rot(int i, int j, int size, int[][] tmp) {
        for (int k = 0; k < size; k++) {
            for (int l = 0; l < size; l++) {
                tmp[i+k][j+l] = map[size-1-l+i][k+j];
            }
        }
    }

    public static void mass() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] v = new boolean[powN][powN];

        for (int i = 0; i < powN; i++) {
            for (int j = 0; j < powN; j++) {
                if (map[i][j] > 0 && !v[i][j]) {
                    v[i][j] = true;
                    int count = 1;
                    q.add(new int[]{i, j});
                    while (!q.isEmpty()) {
                        int[] poll = q.poll();
                        for (int dir = 0; dir < 4; dir++) {
                            int nx = poll[0] + dx[dir];
                            int ny = poll[1] + dy[dir];
                            if (nx >= 0 && ny >= 0 && nx < powN && ny < powN && !v[nx][ny] && map[nx][ny] > 0) {
                                v[nx][ny] = true;
                                ++count;
                                q.add(new int[]{nx, ny});
                            }
                        }
                    }
                    answer[1] = Math.max(answer[1], count);
                }
            }
        }

    }

    public static void melt() {
        int count = 0;
        int[][] copy = new int[powN][powN];
        for (int i = 0; i < powN; i++) {
            for (int j = 0; j < powN; j++) {
                copy[i][j] = map[i][j];
            }
        }
        for (int i = 0; i < powN; i++) {
            for (int j = 0; j < powN; j++) {
                int iceCnt = 0;
                for (int z = 0; z < 4; z++) {
                    int nx = i + dx[z];
                    int ny = j + dy[z];
                    if (nx >= 0 && ny >= 0 && nx < powN && ny < powN && map[nx][ny] > 0) {
                        iceCnt++;
                    }
                }
                if (iceCnt < 3 && copy[i][j] > 0) {
                    --copy[i][j];
                }
            }
        }
        for (int i = 0; i < powN; i++) {
            for (int j = 0; j < powN; j++) {
                map[i][j] = copy[i][j];
            }
        }
    }
}