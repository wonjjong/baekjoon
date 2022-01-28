package boj10825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        StringBuilder sb = new StringBuilder();
        Student[] students = new Student[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            String name = stringTokenizer.nextToken();
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());
            students[i] = new Student(name, a, b, c);
        }

        Arrays.sort(students);
        for (Student student : students) {
            sb.append(student.name).append("\n");
        }

        System.out.println(sb.toString());
    }

    static class Student implements Comparable<Student> {
        String name;
        int korean, english, math;

        public Student(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }


        //        국어 점수가 감소하는 순서로
//        국어 점수가 같으면 영어 점수가 증가하는 순서로
//        국어 점수와 영어 점수가 같으면 수학 점수가 감소하는 순서로
//        모든 점수가 같으면 이름이 사전 순으로 증가하는 순서로 (단, 아스키 코드에서 대문자는 소문자보다 작으므로 사전순으로 앞에 온다.
        @Override
        public int compareTo(Student o) {
            if (this.korean == o.korean) {
                if (this.english == o.english) {
                    if (this.math == o.math) {
                        return this.name.compareTo(o.name);
                    }
                    return Integer.compare(o.math, this.math);
                }
                return Integer.compare(this.english, o.english);
            }
            return Integer.compare(o.korean, this.korean);
        }
    }
}
