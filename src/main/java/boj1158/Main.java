package boj1158;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        List<Integer> arr = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            arr.add(i+1);
        }

        int index =0;
        k = k-1;
        System.out.print("<");
        while(!arr.isEmpty()) {
            index = (index+k) % arr.size();
            if(arr.size() == 1) {
                System.out.println(arr.get(index)+">");
            } else
            System.out.print(arr.get(index)+", ");
            arr.remove(index);
        }

    }

}
