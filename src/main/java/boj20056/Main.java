import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Solution {
    static int n, m, k, answer = 0;
    static ArrayList<FireBall> map[][];
    static int dx[] = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int dy[] = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        k = Integer.parseInt(stringTokenizer.nextToken());
        map = new ArrayList[n][n];
        ArrayList<FireBall> fireBalls = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(reader.readLine());
            int r = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int c = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int s = Integer.parseInt(stringTokenizer.nextToken());
            int d = Integer.parseInt(stringTokenizer.nextToken());
            fireBalls.add(new FireBall(r, c, m, s, d));
        }

        while (k-- > 0) {
            ArrayList<FireBall> tmpFireBalls = new ArrayList<>();
            for (FireBall fireBall : fireBalls) {
                fireBall.move();
                if (map[fireBall.r][fireBall.c] == null) {
                    map[fireBall.r][fireBall.c] = new ArrayList<>();
                }
                map[fireBall.r][fireBall.c].add(fireBall);
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] != null && map[i][j].size() != 0) {
                        if (map[i][j].size() == 1) {
                            tmpFireBalls.add(map[i][j].get(0));
                            map[i][j].clear();
                            continue;
                        }
                        if (map[i][j].size() >= 2) {
                            int sumM = 0;
                            int sumS = 0;
                            int size = map[i][j].size();
                            boolean odd = true;
                            boolean even = true;
                            for (int l = 0; l < size; l++) {
                                FireBall fireBall = map[i][j].get(l);
                                if (fireBall.d % 2 == 0) odd = false;
                                else if (fireBall.d % 2 != 0) even = false;
                                sumM += fireBall.m;
                                sumS += fireBall.s;
                            }
                            int m = sumM / 5;
                            int s = sumS / size;
                            if (m > 0) {
                                if (odd || even) {
                                    tmpFireBalls.add(new FireBall(i, j, m, s, 0));
                                    tmpFireBalls.add(new FireBall(i, j, m, s, 2));
                                    tmpFireBalls.add(new FireBall(i, j, m, s, 4));
                                    tmpFireBalls.add(new FireBall(i, j, m, s, 6));
                                } else {
                                    tmpFireBalls.add(new FireBall(i, j, m, s, 1));
                                    tmpFireBalls.add(new FireBall(i, j, m, s, 3));
                                    tmpFireBalls.add(new FireBall(i, j, m, s, 5));
                                    tmpFireBalls.add(new FireBall(i, j, m, s, 7));
                                }
                            }
                            map[i][j].clear();
                        }
                    }
                }
            }
            fireBalls = tmpFireBalls;
        }

        for (FireBall fireBall : fireBalls) {
            answer += fireBall.m;
        }

        System.out.println(answer);
    }

    static class FireBall {
        int r, c, m, s, d;

        public FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }

        void move() {
            int mvCnt = s % n;
            for (int i = 0; i < mvCnt; i++) {
                this.r += dx[d];
                this.c += dy[d];
                if (this.r >= n) this.r = 0;
                else if (this.r < 0) this.r = n - 1;
                if (this.c >= n) this.c = 0;
                else if (this.c < 0) this.c = n - 1;
            }
        }
    }

}
