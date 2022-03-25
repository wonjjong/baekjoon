package boj11404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());

        int[][] map = new int[n+1][n+1];
        for(int i=1;i<=n;i++) {
            for(int j=1; j<=n;j++) {
                if(i != j ) map[i][j] = INF;
            }
        }
        for(int i=0;i<m;i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());
            if(map[a][b] > c) map[a][b]=c;
        }

        for(int k=1;k<=n;k++) {
            for(int i=1;i<=n;i++) {
                for(int j=1; j<=n;j++) {
                    if(map[i][k] != INF && map[k][j] != INF) {
                        map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                    }
                }
            }
        }

        StringBuffer sb = new StringBuffer();

        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                if(i == j || map[i][j] == INF)
                    sb.append("0 ");
                else
                    sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
