package boj7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int m, n;
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
        m = Integer.parseInt(stringTokenizer.nextToken());
        n = Integer.parseInt(stringTokenizer.nextToken());
        int[][] tomato = new int[n][m];
        Queue<Pair> q = new LinkedList<>();
        boolean isAllRipe = true;

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < m; j++) {
                tomato[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (tomato[i][j] == 0) isAllRipe = false;
                if (tomato[i][j] == 1) q.add(new Pair(i, j));
            }
        }

        if (isAllRipe) {
            System.out.println("0");
            return;
        }

        int count = 0;
        while (!q.isEmpty()) {
            int queueSize = q.size();
            ++count;
            for (int i = 0; i < queueSize; i++) {
                Pair poll = q.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = poll.x + dx[j];
                    int ny = poll.y + dy[j];
                    if (isInside(nx, ny, n, m) && tomato[nx][ny] == 0) {
                        tomato[nx][ny] = 1;
                        q.add(new Pair(nx, ny));
                    }
                }
            }

        }
        boolean checkMap = true;
        for (int i = 0; i < n && checkMap; i++) {
            for (int j = 0; j < m; j++) {
                if (tomato[i][j] == 0) {
                    checkMap = false;
                    break;
                }
            }
        }

        if (checkMap) {
            System.out.println(count-1);
        } else {
            System.out.println("-1");
        }

    }

    public static boolean isInside(int x, int y, int n, int m) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}


