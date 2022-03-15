package boj11725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        ArrayList<Integer>[] list = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }

        boolean[] v = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        int[] answer = new int[n];
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int b = Integer.parseInt(stringTokenizer.nextToken()) - 1;

            list[a].add(b);
            list[b].add(a);

        }

        q.add(0);
        v[0] = true;

        while (!q.isEmpty()) {
            Integer poll = q.poll();
            ArrayList<Integer> integers = list[poll];
            for (int i = 0; i < integers.size(); i++) {
                int num = integers.get(i);
                if (!v[num]) {
                    v[num] = true;
                    q.add(num);
                    answer[num] = poll;
                }
            }
        }

        StringBuilder sb= new StringBuilder();
        for (int i = 1; i < n; i++) {
            sb.append(answer[i]+1).append("\n");
        }
        System.out.println(sb);
    }
}
