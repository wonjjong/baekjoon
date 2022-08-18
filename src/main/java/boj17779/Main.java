package boj17779;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, x, y, d1 = 1, d2 = 1;
    static int map[][], temp[][];
    static int answer = Integer.MAX_VALUE;

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


        for (int d1 = 1; d1 <= n; d1++) {
            for (int d2 = 1; d2 <= n; d2++) {
                for (int x = 0; x + d1 + d2 < n; x++) {
                    for (int y = 0; y + d2 < n; y++) {
                        if(y-d1 < 0) continue;
                        temp = new int[n][n];
                        boundary(x,y,d1,d2);
                        numbering(x,y,d1,d2);
                        answer = Math.min(answer,cal());
                    }
                }
            }
        }
        System.out.println(answer);
    }

    static void boundary(int x, int y, int d1, int d2) {
        int tx = x;
        int ty = y;

//        (x, y), (x+1, y-1), ..., (x+d1, y-d1)
        while (tx <= x + d1) {
            temp[tx][ty] = 5;
            ++tx;
            --ty;
        }

        tx = x;
        ty = y;
//        (x, y), (x+1, y+1), ..., (x+d2, y+d2)
        while (tx <= x + d2) {
            temp[tx][ty] = 5;
            ++tx;
            ++ty;
        }

        tx = x + d1;
        ty = y - d1;
//        (x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
        while (tx <= x + d1 + d2) {
            temp[tx][ty] = 5;
            ++tx;
            ++ty;
        }

        tx = x + d2;
        ty = y + d2;
//        (x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)
        while (tx <= x + d1 + d2) {
            temp[tx][ty] = 5;
            ++tx;
            --ty;
        }

    }

    static int cal() {
        int[] arr = new int[5];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[temp[i][j] - 1] += map[i][j];
            }
        }
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        return max - min;
    }

    static void numbering(int x, int y, int d1, int d2) {
//        1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
        for (int i = 0; i < x + d1; i++) {
            for (int j = 0; j <= y; j++) {
                if (temp[i][j] != 5)
                    temp[i][j] = 1;
            }
        }

//        2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
        for (int i = 0; i <= x + d2; i++) {
            for (int j = y + 1; j <= n - 1; j++) {
                if (temp[i][j] != 5)
                    temp[i][j] = 2;
            }
        }

//        3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
        for (int i = x + d1; i <= n - 1; i++) {
            for (int j = 0; j < y - d1 + d2; j++) {
                if (temp[i][j] != 5)
                    temp[i][j] = 3;
            }
        }

//        4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
        for (int i = x + d2 + 1; i <= n - 1; i++) {
            for (int j = y - d1 + d2; j <= n - 1; j++) {
                if (temp[i][j] != 5) temp[i][j] = 4;
            }
        }

//        5번 선거구
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (temp[i][j] == 5) {
                    int tj = j + 1;
                    while (tj < n && temp[i][tj] != 5) {
                        ++tj;
                    }
                    if (tj < n) {
                        for (int k = j; k < tj; k++) {
                            temp[i][k] = 5;
                        }
                    }
                    break;
                }
            }
        }
    }
}