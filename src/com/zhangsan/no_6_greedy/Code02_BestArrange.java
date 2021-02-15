package com.zhangsan.no_6_greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 一堆会议，安排一天最多能安排多少场
 * @author zhangsan
 * @date 2021/2/15 16:54
 */
public class Code02_BestArrange {

    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public Program() {}

        @Override
        public String toString() {
            return "Program{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static class ProgramComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    public static int bestArrange(Program[] programs) {
        if( programs == null || programs.length == 0 ) {
            return 0;
        }
        Arrays.sort(programs, new ProgramComparator());
        int ans = 0;
        Program preProgram = programs[0];
        for (int i = 1; i < programs.length; i++) {
            if(programs[i].start >= preProgram.end) {
                ans++;
                preProgram = programs[i];
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int maxSize = 1000;
        int maxStart = 1000;
        int maxInterval = 10;
        Program[] programs = generateProgram(maxSize, maxStart, maxInterval);
        System.out.println(Arrays.toString(programs));
        System.out.println(bestArrange(programs));
    }


    private static Program[] generateProgram(int maxSize, int maxStart, int maxInterval) {
        maxSize = (int) (Math.random()*maxSize+1);
        Program[] programs = new Program[maxSize];
        for (int i = 0; i < programs.length; i++) {
            int start = (int) (Math.random()*maxStart+1), end = (int) (maxInterval*Math.random()+1) + start;
            programs[i] = new Program(start, end);
        }
        return programs;
    }

}
