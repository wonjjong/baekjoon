package boj7569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] dir = {{0,0,1},{0,0,-1},{1,0,0},{-1,0,0},{0,-1,0},{0,1,0}};
    static int m,n,h;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
        m = Integer.parseInt(stringTokenizer.nextToken());
        n = Integer.parseInt(stringTokenizer.nextToken());
        h =  Integer.parseInt(stringTokenizer.nextToken());

        int[][][] map = new int[n][m][h];
        boolean[][][] v = new boolean[n][m][h];
        Queue<Node> q = new LinkedList<>();

        for(int i=0; i<h;i++) {
            for(int j=0;j<n;j++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for(int k=0;k<m;k++) {
                    map[j][k][i] = Integer.parseInt(stringTokenizer.nextToken());
                    if(map[j][k][i] == 1)  {
                        q.add(new Node(j,k,i));
                        v[j][k][i] = true;
                    }
                }
            }
        }

        int answer = 0;
        while(!q.isEmpty()) {
            int queueSize = q.size();
            for(int i=0;i<queueSize;i++) {
                Node poll = q.poll();
                for(int d=0; d< 6; d++) {
                    int nx = poll.x + dir[d][0];
                    int ny = poll.y + dir[d][1];
                    int nz = poll.z + dir[d][2];
                    if(inside(nx,ny,nz) && !v[nx][ny][nz] && map[nx][ny][nz] == 0) {
                        q.add(new Node(nx,ny,nz));
                        v[nx][ny][nz] = true;
                        map[nx][ny][nz] = 1;
                    }
                }
            }
            ++answer;
        }

        //0이 있으면 -1
        for(int i=0; i<h;i++) {
            for(int j=0;j<n;j++) {
                for(int k=0;k<m;k++) {
                    if(map[j][k][i] == 0)  {
                        System.out.println("-1");
                        return ;
                    }
                }
            }
        }
        //아니면 answer-1
        System.out.println(answer-1);

    }

    static boolean inside(int x,int y, int z) {
        return x>=0 && y>=0 && z>=0 & x < n && y < m && z < h;
    }
    static class Node {
        int x,y,z;

        public Node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
