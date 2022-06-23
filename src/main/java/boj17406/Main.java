package boj17406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr, copyArr, rotateInfo;
    static int n, m, k, answer = Integer.MAX_VALUE;
    static int[] order;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        k = Integer.parseInt(stringTokenizer.nextToken());
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        rotateInfo = new int[k][3];
        for (int i = 0; i < k; i++) {
            stringTokenizer = new StringTokenizer(reader.readLine());
            rotateInfo[i][0] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            rotateInfo[i][1] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            rotateInfo[i][2] = Integer.parseInt(stringTokenizer.nextToken());
        }

        for (int i = 0; i < k; i++) {
            v = new boolean[k];
            order = new int[k];
            order[0] = i;
            v[i] = true;
            makeAllComb(1);
            v[i] = false;
        }

        System.out.println(answer);
    }

    public static void makeAllComb(int count) {
        if (count == k) {
            copyArr = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    copyArr[i][j] = arr[i][j];
                }
            }
            for (int i = 0; i < k; i++) {
                int idx = order[i];
                rotate(rotateInfo[idx][0] - rotateInfo[idx][2], rotateInfo[idx][1] - rotateInfo[idx][2], rotateInfo[idx][2] * 2);
            }
            answer = Math.min(answer, getMinValue());
            return;
        }

        for (int i = 0; i < k; i++) {
            if (!v[i]) {
                v[i] = true;
                order[count] = i;
                makeAllComb(count + 1);
                v[i] = false;
            }
        }
    }

    public static int getMinValue() {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < m; j++) {
                sum += copyArr[i][j];
            }
            min = Math.min(min, sum);
        }
        return min;
    }


    public static void rotate(int x, int y, int size) {
        if (size <= 0) return;

        int temp = copyArr[x][y];
        up(x, y, size);
        left(x, y, size);
        down(x, y, size);
        right(x, y, size);
        copyArr[x][y + 1] = temp;
        rotate(x + 1, y + 1, size - 2);
    }

    public static void up(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            copyArr[i][y] = copyArr[i + 1][y];
        }
    }

    public static void left(int x, int y, int size) {
        for (int i = y; i < y + size; i++) {
            copyArr[x + size][i] = copyArr[x + size][i + 1];
        }
    }

    public static void down(int x, int y, int size) {
        for (int i = x + size; i > x; i--) {
            copyArr[i][y + size] = copyArr[i - 1][y + size];
        }
    }

    public static void right(int x, int y, int size) {
        for (int i = y + size; i > y; i--) {
            copyArr[x][i] = copyArr[x][i - 1];
        }
    }
}