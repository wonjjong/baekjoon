import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int a[];
    static boolean robot[];
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(stringTokenizer.nextToken());
        k = Integer.parseInt(stringTokenizer.nextToken());
        a = new int[n * 2 + 1];
        robot = new boolean[n + 1];
        stringTokenizer = new StringTokenizer(reader.readLine());

        for (int i = 1; i < a.length; i++) {
            a[i] = Integer.parseInt(stringTokenizer.nextToken());

        }

        while (true) {
            ++answer;
            step01();
            step02();
            step03();
            int zeroCnt = step04();
            if (zeroCnt >= k) {
                break;
            }

        }
        System.out.println(answer);
    }

    static void step01() {
        int tmp = a[2 * n];
        for (int i = 2 * n; i >= 1; i--) {
            if (i == 1) {
                a[1] = tmp;
            } else {
                a[i] = a[i - 1];
            }
        }

        for (int i = n; i >= 2; i--) {
            robot[i] = robot[i - 1];
        }
        robot[1] = false;
        robot[n] = false;

    }

    static void step02() {
        for (int i = n - 1; i >= 1; i--) {
            if (robot[i]) {
                if (!robot[i + 1] && a[i + 1] >= 1) {
                    --a[i + 1];
                    robot[i + 1] = true;
                    robot[i] = false;
                }
            }
        }

    }

    static void step03() {
        if (a[1] > 0) {
            robot[1] = true;
            --a[1];
        }
    }

    static int step04() {
        int zeroCnt = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] == 0) ++zeroCnt;
        }

        return zeroCnt;
    }

}
