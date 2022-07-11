package boj12100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map, copy;
    static int answer = 0;
    static ArrayList<Integer> comb;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        comb = new ArrayList<>();
        mkAllComb(0, 5);
        System.out.println(answer);
    }

    static void mkAllComb(int mvCnt, int totCnt) {
        if (mvCnt == totCnt) {
            copy = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    copy[i][j] = map[i][j];
                }
            }

            for (Integer i : comb) {
                merge(i);
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    answer = Math.max(answer, copy[i][j]);
                }
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            comb.add(i);
            mkAllComb(mvCnt + 1, totCnt);
            comb.remove(mvCnt);
        }

    }

    static void merge(int dir) {
        if (dir == 0) {
            //left
            for (int row = 0; row < n; row++) {
                int idx = 0;
                Queue<Integer> q = new LinkedList<>();
                for (int col = 0; col < n; col++) {
                    if (copy[row][col] != 0) {
                        q.add(copy[row][col]);
                        copy[row][col] = 0;
                    }
                }
                while (!q.isEmpty()) {
                    int v1 = q.poll();
                    if (!q.isEmpty()) {
                        int v2 = q.peek();
                        if (v1 == v2) {
                            q.poll();
                            copy[row][idx++] = v2 * 2;
                        } else {
                            copy[row][idx++] = v1;
                        }
                    } else {
                        copy[row][idx++] = v1;
                    }
                }
            }
        } else if (dir == 1) {
            //down
            for (int col = 0; col < n; col++) {
                int idx = n - 1;
                Queue<Integer> q = new LinkedList<>();
                for (int row = n - 1; row >= 0; row--) {
                    if (copy[row][col] != 0) {
                        q.add(copy[row][col]);
                        copy[row][col] = 0;
                    }
                }
                while (!q.isEmpty()) {
                    int v1 = q.poll();
                    if (!q.isEmpty()) {
                        int v2 = q.peek();
                        if (v1 == v2) {
                            q.poll();
                            copy[idx--][col] = v2 * 2;
                        } else {
                            copy[idx--][col] = v1;
                        }
                    } else {
                        copy[idx--][col] = v1;
                    }
                }
            }
        } else if (dir == 2) {
            //right
            for (int row = 0; row < n; row++) {
                int idx = n - 1;
                Queue<Integer> q = new LinkedList<>();
                for (int col = n - 1; col >= 0; col--) {
                    if (copy[row][col] != 0) {
                        q.add(copy[row][col]);
                        copy[row][col] = 0;
                    }
                }
                while (!q.isEmpty()) {
                    int v1 = q.poll();
                    if (!q.isEmpty()) {
                        int v2 = q.peek();
                        if (v1 == v2) {
                            q.poll();
                            copy[row][idx--] = v2 * 2;
                        } else {
                            copy[row][idx--] = v1;
                        }
                    } else {
                        copy[row][idx--] = v1;
                    }
                }
            }
        } else if (dir == 3) {
            //up
            for (int col = 0; col < n; col++) {
                int idx = 0;
                Queue<Integer> q = new LinkedList<>();
                for (int row = 0; row < n; row++) {
                    if (copy[row][col] != 0) {
                        q.add(copy[row][col]);
                        copy[row][col] = 0;
                    }
                }

                while (!q.isEmpty()) {
                    int v1 = q.poll();
                    if (!q.isEmpty()) {
                        int v2 = q.peek();
                        if (v1 == v2) {
                            q.poll();
                            copy[idx++][col] = v2 * 2;
                        } else {
                            copy[idx++][col] = v1;
                        }
                    } else {
                        copy[idx++][col] = v1;
                    }
                }
            }
        }
    }
}