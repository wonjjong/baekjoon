package boj16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] map, distance;
    static int n;
    static int sharkX, sharkY, sharkSize = 2, eatCount = 0;
    static int dx[] = {0, 0, -1, 1};
    static int dy[] = {-1, 1, 0, 0};
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        map = new int[n][n];
        int answer = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (map[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0;
                }
            }
        }

        while (true) {
            PriorityQueue<Fish> pq = new PriorityQueue<>();
            distance = new int[n][n];
            calDistance(sharkX, sharkY);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] != 0 && map[i][j] < sharkSize && distance[i][j] != INF) {
                        pq.add(new Fish(i,j,distance[i][j]));
                    }
                }
            }
            if (pq.isEmpty()) break;
            else {
                Fish poll = pq.poll();
                answer += poll.distance;
                sharkX = poll.x;
                sharkY = poll.y;
                eatCount++;
                map[poll.x][poll.y] = 0;
                if (eatCount == sharkSize) {
                    sharkSize++;
                    eatCount = 0;
                }
            }
        }
        System.out.println(answer);
    }

    private static void calDistance(int sharkX, int sharkY) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = INF;
            }
        }

        Queue<int[]> q = new LinkedList<>();
        distance[sharkX][sharkY] = 0;
        q.add(new int[]{sharkX, sharkY});

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = poll[0] + dx[dir];
                int ny = poll[1] + dy[dir];
                if (inside(nx, ny) && map[nx][ny] <= sharkSize) {
                    if (distance[poll[0]][poll[1]] + 1 < distance[nx][ny]) {
                        q.add(new int[]{nx, ny});
                        distance[nx][ny] = distance[poll[0]][poll[1]] + 1;
                    }
                }
            }
        }
    }

    static boolean inside(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }

    static class Fish implements Comparable<Fish> {
        int x, y, distance;

        public Fish(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(Fish o) {
            if (this.distance == o.distance) {
                if (this.x == o.x) {
                    return Integer.compare(this.y, o.y);
                } else {
                    return Integer.compare(this.x, o.x);
                }
            } else
                return Integer.compare(this.distance, o.distance);
        }
    }
}