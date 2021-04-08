package com.zhangsan.no_other;

/**
 *
 * 蓄水池算法:
 * 给定n个球, 和容量位10的袋子
 * 每次装一个球, 要求入袋的几率都是等概率的.
 *
 * @author zhangsan
 * @date 2021/4/7 20:30
 */
public class ReservoirSampling {


    // 1 ~ i 等概率返回一个值
    public static int random(int i) {
        return (int) (Math.random()*i) + 1;
    }

    // for test
    public static void main(String[] args) {
        System.out.println("准备测试:");
        System.out.println("====================================");
        int times = 100000;
        int nums = 1729;    // 用户数
        int[] count = new int[nums];
        for (int i = 0; i < times; i++) {
            int[] bag = new int[]{1, 2, 3, 4, 5, 6, 7 ,8 ,9 ,10};   // 10个桶
            int bagI = 0;
            // 1729个球随机入袋
            for (int num = 11; num < nums; num++) {
                // 这渴求右 10/num 的概率被选中入袋
                if( random(num) < 11) {
                    // 要剔除掉袋子中的一个球, 也是等概率1/10的几率随机剔除
                    bagI = (int) (Math.random()*10);
                    bag[bagI] = num;
                }
            }

            // 做统计
            for (int num : bag) {
                count[num]++;
            }
        }
        System.out.println("====================================");
        System.out.println("测试结束: 测试" + times + "次, 球的个数" + nums + ", 袋子容量: 10.");
        for (int i = 0; i < count.length; i++) {
            System.out.println(i + "号球: 入袋: " + count[i] + "次.");
        }
    }

}
