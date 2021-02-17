package com.zhangsan.no_8_graph;

/**
 * 图的生成器。
 * 将其他表示图的方式，转化成自定义的图的结构
 * @author zhangsan
 * @date 2021/2/17 19:02
 */
public class GraphGenerator {

    /**
     * 二维矩阵
     * n*3
     * [weight, from节点的值, to节点的值]
     */
    public static Graph create(Integer[][] matrix) {
        Graph graph = new Graph();
        for (Integer[] integers : matrix) {
            int weight = integers[0];
            int fromValue = integers[1];
            int toValue = integers[2];
            graph.connect(weight, fromValue, toValue);
        }
        return graph;
    }

    /**
     * 一维数组代表图
     *
     *  1 3 5 1 3 2
     *  0 1 2 3 4 5
     *  0->1    1->3    2->5    3->1     4->3     5->2
     */
    public static Graph create(Integer[] arr) {
        Graph graph = new Graph();
        for (int value = 0; value < arr.length; value++) {
            int toValue = arr[value];
            graph.connect(0, value, toValue);
        }
        return graph;
    }

}
