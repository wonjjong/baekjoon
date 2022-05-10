package boj1261;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {-1, 1, 0, 0};
    static int n, m;
    static int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        int[][] map = new int[m][n];
        int[][] v = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                v[i][j] = INF;
            }
        }
        v[0][0] = 0;

        for (int i = 0; i < m; i++) {
            String input = reader.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(input.charAt(j)));
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, 0});
        int answer = INF;

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int count = poll[2];
            for (int i = 0; i < 4; i++) {
                int nx = poll[0] + dx[i];
                int ny = poll[1] + dy[i];

                if(nx == m-1 && ny == n-1) {
                    answer = Math.min(answer, count);
                    continue;
                }
                if (inside(nx, ny)) {
                    if (map[nx][ny] == 0 && count < v[nx][ny]) {
                        v[nx][ny] = count;
                        q.add(new int[]{nx, ny, count});
                    } else if (map[nx][ny] == 1 && (count + 1) < v[nx][ny]) {
                        v[nx][ny] = count + 1;
                        q.add(new int[]{nx, ny, count + 1});
                    }
                }

            }
        }
        if(answer == INF) System.out.println(0);
        else
            System.out.println(answer);
    }

    public static boolean inside(int x, int y) {
        return x >= 0 && y >= 0 && x < m && y < n;
    }
}