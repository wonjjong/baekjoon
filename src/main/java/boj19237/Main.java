package  boj19237;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 해당문제 풀면서 느낀점
 * 1.
 */
public class Main {
    static int n, m, k, count = 0, answer = -1;
    static int smell[][], sharkNum[][];
    static Shark[] sharkArr;
    static List<Shark> sharkList = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        k = Integer.parseInt(stringTokenizer.nextToken());

        smell = new int[n][n];
        sharkNum = new int[n][n];
        sharkArr = new Shark[m];

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                int n = Integer.parseInt(stringTokenizer.nextToken());
                if (n != 0) {
                    sharkArr[n - 1] = new Shark(n, i, j);
                    smell[i][j] = k;
                    sharkNum[i][j] = n;
                }
            }
        }

        stringTokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < m; i++) {
            sharkArr[i].dir = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        }

        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(reader.readLine());
            sharkArr[i].prior[0][0] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            sharkArr[i].prior[0][1] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            sharkArr[i].prior[0][2] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            sharkArr[i].prior[0][3] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            stringTokenizer = new StringTokenizer(reader.readLine());
            sharkArr[i].prior[1][0] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            sharkArr[i].prior[1][1] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            sharkArr[i].prior[1][2] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            sharkArr[i].prior[1][3] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            stringTokenizer = new StringTokenizer(reader.readLine());
            sharkArr[i].prior[2][0] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            sharkArr[i].prior[2][1] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            sharkArr[i].prior[2][2] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            sharkArr[i].prior[2][3] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            stringTokenizer = new StringTokenizer(reader.readLine());
            sharkArr[i].prior[3][0] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            sharkArr[i].prior[3][1] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            sharkArr[i].prior[3][2] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            sharkArr[i].prior[3][3] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        }

        for(Shark s : sharkArr) {
            sharkList.add(s);
        }

        while (count < 1000) {
            int[][] check = new int[n][n];

            for (int i = 0; i < sharkList.size(); i++) {
                Shark shark = sharkList.get(i);
                int dir = shark.dir;
                boolean flag = true;
                boolean remove = false;

                for (int j = 0; j < 4; j++) {
                    int priorDir = shark.prior[dir][j];
                    int nx = shark.x + dx[priorDir];
                    int ny = shark.y + dy[priorDir];
                    if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                        if (smell[nx][ny] == 0) {
                            if (check[nx][ny] == 0) {
                                check[nx][ny] = shark.number;
                                shark.x = nx;
                                shark.y = ny;
                                shark.dir = priorDir;
                                sharkNum[nx][ny] = shark.number;
                                flag = false;
                            } else {
                                sharkList.remove(shark);
                                remove = true;
                                i--;
                            }
                            break;
                        }
                    }
                }

                if(flag && !remove) {
                    for (int j = 0; j < 4; j++) {
                        int priorDir = shark.prior[dir][j];
                        int nx = shark.x + dx[priorDir];
                        int ny = shark.y + dy[priorDir];
                        if (nx >= 0 && ny >= 0 && nx < n && ny < n){
                            if(sharkNum[nx][ny] == shark.number) {
                                shark.x = nx;
                                shark.y = ny;
                                shark.dir = priorDir;
                                sharkNum[nx][ny] = shark.number;
                                smell[nx][ny] = k + 1;
                                break;
                            }
                        }
                    }
                }
            }

            for (Shark shark : sharkList) {
                smell[shark.x][shark.y] = k+1;
            }

            for (int j = 0; j < n; j++) {
                for (int l = 0; l < n; l++) {
                    if(smell[j][l] > 0) {
                        --smell[j][l];
                    }
                    if(smell[j][l] == 0 ) {
                        sharkNum[j][l] = 0;
                    }
                }
            }

            ++count;
            if(sharkList.size() == 1 && sharkList.get(0).number == 1) {
                answer = count;
                break;
            }
        }

        System.out.println(answer);

    }
    static class Shark {
        int number;
        int dir;
        int[][] prior = new int[4][4];
        int x, y;
        public Shark(int number, int x, int y) {
            this.number = number;
            this.x = x;
            this.y = y;
        }
    }
}
