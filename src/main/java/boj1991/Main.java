package boj1991;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        char[][] tree = new char[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int a = stringTokenizer.nextToken().charAt(0) - 'A';

            char l = stringTokenizer.nextToken().charAt(0);
            tree[a][0] = l;
            char r = stringTokenizer.nextToken().charAt(0);
            tree[a][1] = r;

        }

        preOrder(tree, 'A');
        System.out.println();
        inOrder(tree, 'A');
        System.out.println();
        postOrder(tree,'A');

    }

    public static void preOrder(char[][] tree, char value) {
        if (value == '.') return;

        System.out.print(value);
        preOrder(tree, tree[value - 'A'][0]);
        preOrder(tree, tree[value - 'A'][1]);

    }

    public static void inOrder(char[][] tree, char value) {
        if (value == '.') return;

        inOrder(tree, tree[value - 'A'][0]);
        System.out.print(value);
        inOrder(tree, tree[value - 'A'][1]);
    }

    public static void postOrder(char[][] tree, char value) {
        if (value == '.') return;

        postOrder(tree, tree[value - 'A'][0]);
        postOrder(tree, tree[value - 'A'][1]);
        System.out.print(value);
    }
}
