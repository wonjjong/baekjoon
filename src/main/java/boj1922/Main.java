package boj1922;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        PriorityQueue<Info> pq = new PriorityQueue<>();

        int answer = 0;
        for (int i = 0; i < m; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());
            pq.add(new Info(a, b, c));
        }
        while (!pq.isEmpty()) {
            Info poll = pq.poll();
            if (find(poll.a, poll.b)) {
                union(poll.a, poll.b);
                answer += poll.c;
            }

        }
        System.out.println(answer);
    }

    static private void union(int a, int b) {
        a = getParent(a);
        b = getParent(b);

        if (a < b) parent[b] = a;
        else
            parent[a] = b;
    }

    static private boolean find(int a, int b) {
        a = getParent(a);
        b = getParent(b);
        if (a == b) return false;
        return true;
    }

    static private int getParent(int x) {
        if (parent[x] == x) return x;
        return parent[x] = getParent(parent[x]);
    }

    private static class Info implements Comparable<Info> {
        int a, b, c;

        public Info(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Info o) {
            return Integer.compare(c, o.c);
        }
    }
}
