package boj11652;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        HashMap<Long, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            long key = Long.parseLong(reader.readLine());
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        int max = map.values().stream().max(Comparator.naturalOrder()).get();

        long answer = Long.MAX_VALUE;
        for (Long aLong : map.keySet()) {
            if (max == map.get(aLong))
                answer = Math.min(answer, aLong);
        }
        System.out.println(answer);
    }
}
