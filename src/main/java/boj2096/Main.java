package boj2096;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int[][] arr = new int[n][3];
        int[][] min = new int[n][3];
        int[][] max = new int[n][3];
        for(int i=0;i<n;i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            arr[i][0] = Integer.parseInt(stringTokenizer.nextToken());
            arr[i][1] = Integer.parseInt(stringTokenizer.nextToken());
            arr[i][2] = Integer.parseInt(stringTokenizer.nextToken());
        }
        min[0][0] = max[0][0] = arr[0][0];
        min[0][1] = max[0][1] = arr[0][1];
        min[0][2] = max[0][2] = arr[0][2];

        for(int i=1;i<n;i++) {
            max[i][0] = Math.max(arr[i][0] + max[i-1][0] , arr[i][0] + max[i-1][1]);
            max[i][1] = Math.max(arr[i][1] + max[i-1][2] , Math.max(arr[i][1] + max[i-1][0] , arr[i][1] + max[i-1][1]));
            max[i][2] = Math.max(arr[i][2] + max[i-1][1] , arr[i][2] + max[i-1][2]);

            min[i][0] = Math.min(arr[i][0] + min[i-1][0] , arr[i][0] + min[i-1][1]);
            min[i][1] = Math.min(arr[i][1] + min[i-1][2] , Math.min(arr[i][1] + min[i-1][0] , arr[i][1] + min[i-1][1]));
            min[i][2] = Math.min(arr[i][2] + min[i-1][1] , arr[i][2] + min[i-1][2]);
        }
        int resultMin = Math.min(min[n-1][0], Math.min(min[n-1][1],min[n-1][2]));
        int resultMax = Math.max(max[n-1][0], Math.max(max[n-1][1],max[n-1][2]));

        System.out.println(resultMax + " " + resultMin);
    }
}
