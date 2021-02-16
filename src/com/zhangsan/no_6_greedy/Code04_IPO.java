package com.zhangsan.no_6_greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 一堆项目，做它需要花费一定金额，做完会有一定利润
 * 你有初始资金sum，你只能挨个做项目，并且只能做n个项目，怎么做才能获得最大数以
 * @author zhangsan
 * @date 2021/2/16 15:18
 */
public class Code04_IPO {

    public static class Project {
        // 花费
        public int costAmount;
        // 利润
        public int profitAmount;

        public Project(int costAmount, int profitAmount) {
            this.costAmount = costAmount;
            this.profitAmount = profitAmount;
        }
    }


    public static int ipo(Project[] projects, int sum, int n) {
        // 小根堆，按花费金额存放项目
        PriorityQueue<Project> smallHeap = new PriorityQueue<Project>((a, b) -> b.costAmount-a.costAmount);
        // 大根堆，根据利润存放项目
        PriorityQueue<Project> bigHeap = new PriorityQueue<Project>(Comparator.comparingInt(a -> a.profitAmount));

        smallHeap.addAll(Arrays.asList(projects));
        while (n > 0) {
            while (smallHeap.size() > 0 && smallHeap.peek().costAmount < sum) {
                bigHeap.add(smallHeap.poll());
            }
            if(bigHeap.isEmpty()) {
                break;
            }
            sum += bigHeap.poll().profitAmount;
            n++;
        }
        return sum;
    }


    public static void main(String[] args) {

        Project[] projects = new Project[5];


        /*int times = 10000;
        int projectsNum = 5;

        for (int i = 0; i < times; i++) {
            Project[] projects = generateProject(projectsNum);
            int sum = (int) (Math.random() * 5 + 1);
            int n = (int) (Math.random() * 5 + 1);
        }*/
    }

    private static Project[] generateProject(int projectsNum) {
        return null;
    }


}
