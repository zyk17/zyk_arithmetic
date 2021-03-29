package com.zhangsan.no_dp_skill;

import com.zhangsan.util.ArrayUtil;

/**
 * 通关怪兽
 * int[] d  d[i]   代表怪兽的能力
 * int[] p  p[i]   代表收买怪兽需要的钱
 * 刚开始能力为0, 收买怪兽可获得怪兽的能力, 能力比怪兽大可以收买它获得它的能力, 也可以通关.
 * 返回通过所有怪兽最少需要花多少钱
 *
 * @author zhangsan
 * @date 2021/3/29 20:23
 */
public class Code04_MoneyProbleam {

    /** 暴力尝试: 收买当前怪兽,不收买当前怪兽.能力不够必须收买当前怪兽,算出最大值
     *  该动态规划的话,需要根据我的能力达到的最大值是我的数据上限.
     * */
    public static long func1(int[] d, int[] p) {
        return process(d, p, 0, 0);
    }

    public static long process(int[] d, int[] p, int i, int ability) {
        // base case, 通过了所有怪兽
        if( i == d.length) {
            return 0;
        }
        // 如果现在我的能力比怪兽小, 我必须收买怪兽d
        if(ability < d[i]) {
            return p[i] + process(d, p, i+1, ability+d[i]);
        }else {
            // 我的能力比怪兽大, 我可以收买怪兽,也可以不收买,返回后序需要的最少的+收还是不收买
            // 收买
            long r1 = p[i] + process(d, p, i+1, ability+d[i]);
            // 不收买
            long r2 = process(d, p, i+1, ability);
            return Math.min(r1, r2);
        }
    }

    /** func1 的动态规划版 */
    public static long func3(int[] d, int[] p) {
        int maxAbility = 0;
        for (int i : d) {
            maxAbility += i;
        }
        int N = d.length;
        long[][] dp = new long[N+1][maxAbility+1];
//        for (int i = 0; i <= maxAbility; i++) {
//            dp[0][i] = 0
//        }

        for (int i = d.length-1; i >= 0; i--) {
            for (int hp = 0; hp <= maxAbility; hp++) {
                if( hp + d[i] > maxAbility ) {
                    continue;
                }
                if( hp < d[i] ) {
                    dp[i][hp] = p[i] + dp[i+1][hp+d[i]];
                }else {
                    long r1 = p[i] + dp[i+1][hp+d[i]];
                    // 不收买
                    long r2 = dp[i+1][hp];
                    dp[i][hp] = Math.min(r1, r2);
                }
            }
        }
        return dp[0][0];
    }

    /** 暴力尝试: 我花最少的钱能否通过, 中途不能通过的话,就返回-1.否则返回最后的能力
     * 改动态规划的话, 花的钱既是数据的上限
     * */
    public static long func2(int[] d, int[] p) {
        long maxMoney = 0;
        for (int i : p) {
            maxMoney += i;
        }
        for (int i = 0; i < maxMoney; i++) {
            // 我从最少的钱开始试, 如果能拿到一个正确解就返回这个钱.
            if( process2(d, p, d.length-1, i) != -1 ) {
                return i;
            }
        }
        // 否则我只能遇到每个怪兽都花钱
        return maxMoney;
    }

    /** func2 的动态规划版 */
    public static long func4(int[] d, int[] p) {
        int sum = 0;
        for (int num : p) {
            sum += num;
        }
        // dp[i][j]含义：
        // 能经过0～i的怪兽，且花钱为j（花钱的严格等于j）时的武力值最大是多少？
        // 如果dp[i][j]==-1，表示经过0～i的怪兽，花钱为j是无法通过的，或者之前的钱怎么组合也得不到正好为j的钱数
        int[][] dp = new int[d.length][sum + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = -1;
            }
        }
        // 经过0～i的怪兽，花钱数一定为p[0]，达到武力值d[0]的地步。其他第0行的状态一律是无效的
        dp[0][p[0]] = d[0];
        for (int i = 1; i < d.length; i++) {
            for (int j = 0; j <= sum; j++) {
                // 可能性一，为当前怪兽花钱
                // 存在条件：
                // j - p[i]要不越界，并且在钱数为j - p[i]时，要能通过0～i-1的怪兽，并且钱数组合是有效的。
                if (j >= p[i] && dp[i - 1][j - p[i]] != -1) {
                    dp[i][j] = dp[i - 1][j - p[i]] + d[i];
                }
                // 可能性二，不为当前怪兽花钱
                // 存在条件：
                // 0~i-1怪兽在花钱为j的情况下，能保证通过当前i位置的怪兽
                if (dp[i - 1][j] >= d[i]) {
                    // 两种可能性中，选武力值最大的
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
            }
        }
        int ans = 0;
        // dp表最后一行上，dp[N-1][j]代表：
        // 能经过0～N-1的怪兽，且花钱为j（花钱的严格等于j）时的武力值最大是多少？
        // 那么最后一行上，最左侧的不为-1的列数(j)，就是答案
        for (int j = 0; j <= sum; j++) {
            if (dp[d.length - 1][j] != -1) {
                ans = j;
                break;
            }
        }
        return ans;
    }

    /** 返回正好花money, 获得的能力. */
    public static long process2(int[] d, int[] p, int i, int money) {
        // base case,如果我这个钱打完怪兽正好是0, 就符合初始能力为0,是个正确的解,否则就不是一个正确的解
        if(i == -1) {
            return money==0? 0: -1;
        }
        // 这个怪兽我不花钱, 他放上边会阻止掉很多尝试
        long preMaxAbility = process2(d, p, i-1, money);
        long p1 = -1;
        if(preMaxAbility != -1 && preMaxAbility >= d[i]) {
            p1 = preMaxAbility;
        }
        // 这个怪兽我要花钱
        long preMaxAbility2 = process2(d, p, i-1, money-p[i]);
        long p2 = -1;
        if(preMaxAbility2 != -1) {
            p2 = preMaxAbility2 + d[i];
        }
        return Math.max(p1, p2);
    }

    // for test
    public static int[] generateArray(int maxLen, int maxValue) {
        int[] ans = new int[maxLen];
        for (int i = 0; i < maxLen; i++) {
            ans[i] = (int) (Math.random() * maxValue)+1;
        }
        return ans;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        int[] newArray = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArray[i] = arr[i];
        }
        return newArray;
    }

    // for test
    public static void main(String[] args) {

//        int[] d = {3, 5, 6, 7, 9, 5, 21, 5};
//        int[] p = {2, 4, 6, 8, 10, 2, 9, 1};
        /*int[] d = {3, 2, 4};
        int[] p = {1, 2, 3};

        long r1 = func1(d, p);
        long r2 = func2(d, p);
        long r3 = func3(d, p);
        long r4 = func4(d, p);

        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);
        System.out.println(r4);*/

        int dMax = 10;
        int pMax = 10;
        int maxLen = 20;
        int times = 200;
        for (int i = 0; i < times; i++) {
            maxLen = (int) (Math.random()*20)+1;
            int[] d = generateArray(maxLen, dMax);
            int[] p = generateArray(maxLen, pMax);

            long ans1 = func1(d, p);
            long ans2 = func2(d, p);
            long ans3 = func3(d, p);
            long ans4 = func4(d, p);

            if(ans1 != ans2 || ans2 != ans3 || ans3 != ans4) {
                System.out.println("OOPS");
                ArrayUtil.printArr(d);
                ArrayUtil.printArr(p);
                System.out.println(ans1 + "\t" + ans2 + "\t" + ans3 + "\t" + ans4);
                break;
            }
        }
    }

}
