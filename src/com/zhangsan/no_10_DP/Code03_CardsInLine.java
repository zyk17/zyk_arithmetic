package com.zhangsan.no_10_DP;

/**
 * 给定一个数组arr 代表一堆卡片。
 * 两个聪明绝顶的人，只能从最左边或最右边选择一张牌。请问获胜者的分数
 *
 * 例子：
 * 50 100 20 10
 * 先手：10 100
 * 后手：50 20
 * 获胜者分数：先手获胜，分数110
 * @author zhangsan
 * @date 2021/2/21 21:36
 */
public class Code03_CardsInLine {

    /** 根据自然智慧（贪心），暴力解出答案。 */
    public static int win1(int[] arr) {
        if( arr==null || arr.length == 0 ) { return 0; }
        int first = first1(arr, 0, arr.length - 1);
        int second = after1(arr, 0, arr.length - 1);
//        System.out.println(first + "\t" + second);
        return Math.max(first, second);
    }

    /** 先手姿态选择最大的 */
    public static int first1(int[] arr, int l, int r) {
        if( l == r ) {
            return arr[l];
        }
        // 先手答案，和后手答案相加最大的选择
        return Math.max(arr[l] + after1(arr, l+1, r), arr[r] + after1(arr, l, r - 1));
    }

    /** 后手姿态选择对手留下的最小的，因为对手肯定选择大的 */
    public static int after1(int[] arr, int l, int r) {
        // 就剩一张牌了，那就被对手拿走了
        if( l == r ) {
            return 0;
        }
        // 获得 假如对手拿l后的最优解，和加入对手拿了r后的最有答案 中的最小值。因为对手会阻碍
        return Math.min( first1(arr, l+1, r), first1(arr, l, r-1) );
    }




    /** 在暴力解出答案之上，加入傻缓存。 */
    public static int win2dp(int[] arr) {
        if( arr==null || arr.length == 0 ) { return 0; }
        int N = arr.length;
        int[][] gCache = new int[N][N];
        int[][] fCache = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fCache[i][j] = -1;
                gCache[i][j] = -1;
            }
        }

        int first = f(arr, 0, arr.length - 1, fCache, gCache);
        int second = g(arr, 0, arr.length - 1, fCache, gCache);
        /*System.out.println("f方法的缓存");
        for (int[] ints : fCache) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("g方法的缓存");
        for (int[] ints : gCache) {
            System.out.println(Arrays.toString(ints));
        }*/

//        System.out.println(first + "\t" + second);
        return Math.max(first, second);
    }

    /** 加入傻缓存，先手姿态 */
    public static int f(int[] arr, int l, int r, int[][] fCache, int[][] gCache) {
        if( fCache[l][r] != -1 ) {
            return fCache[l][r];
        }
        /*if( l == r ) {
            return arr[l];
        }
        // 先手答案，和后手答案相加最大的选择
        int p1 = arr[l] + g(arr, l + 1, r, fCache, gCache);
        int p2 = arr[r] + g(arr, l, r - 1, fCache, gCache);
        gCache[l+1][r] = p1;
        gCache[l][r-1] = p2;*/

        int ans = 0;
        if( l == r ) {
            ans = arr[l];
        } else {
            // 先手答案，和后手答案相加最大的选择
            int p1 = arr[l] + g(arr, l + 1, r, fCache, gCache);
            int p2 = arr[r] + g(arr, l, r - 1, fCache, gCache);
            ans = Math.max(p1, p2);
        }
        fCache[l][r] = ans;
        return ans;
    }

    /** 加入傻缓存，后手姿态 */
    public static int g(int[] arr, int l, int r, int[][] fCache, int[][] gCache) {
        if( gCache[l][r] != -1 ) {
            return gCache[l][r];
        }
        // 就剩一张牌了，那就被对手拿走了
        /*if( l == r ) {
            return 0;
        }
        // 获得 假如对手拿l后的最优解，和加入对手拿了r后的最有答案 中的最小值。因为对手会阻碍
        int p1 = f(arr, l+1, r, fCache, gCache);
        int p2 = f(arr, l, r-1, fCache, gCache);
        fCache[l+1][r] = p1;
        fCache[l][r-1] = p2;*/
        int ans = 0;
        // 获得 假如对手拿l后的最优解，和加入对手拿了r后的最有答案 中的最小值。因为对手会阻碍
        if( l != r) {
            int p1 = f(arr, l + 1, r, fCache, gCache);
            int p2 = f(arr, l, r - 1, fCache, gCache);
            ans = Math.min(p1, p2);
        }
        gCache[l][r] = ans;
        return ans;
    }


    /** 根据暴力解+缓存的方式，推出加入缓存的方法，直接赋值缓存，解答案 */
    public static int win3dp(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] fCache = new int[N][N];
        int[][] gCache = new int[N][N];

        for (int i = 0; i < N; i++) {
            fCache[i][i] = arr[i];
//            gCache[i][i] = 0;
        }

        for (int c = 1; c < N; c++) {
            int row = 0;
            int col = c;
            for (; col < N; col++,row++) {
                fCache[row][col] = Math.max(arr[row] +gCache[row + 1][col], arr[col] + gCache[row][col - 1]);
                gCache[row][col] = Math.min(fCache[row + 1][col], fCache[row][col - 1]);
            }

        }

        /*System.out.println("fCache：");
        for (int[] ints : fCache) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("gCache：");
        for (int[] ints : gCache) {
            System.out.println(Arrays.toString(ints));
        }*/

        return Math.max(fCache[0][N-1], gCache[0][N-1]);
    }


    public static void main(String[] args) {
        int[] arr = { 50, 100, 20, 10, 30, 60, 20, 10, 50, 100, 20, 10, 30, 60, 20, 10, 50, 100, /*20, 10, 30, 60, 20, 10*/ };
        // 先手：10 60 10 100
        // 后手：20 30 20 50
        long s1 = System.nanoTime();
        int r1 = win1(arr);
        long s2 = System.nanoTime();
        int r2 = win2dp(arr);
        long s3 = System.nanoTime();
        int r3 = win3dp(arr);
        long s4 = System.nanoTime();


        System.out.println("暴力解答案：" + r1 + "， 耗时：" + (s2-s1));
        System.out.println("加入傻缓存答案：" + r2 + "， 耗时：" + (s3-s2));
        System.out.println("推断加入缓存的方式，直接操作数组答案：" + r3 + "， 耗时：" + (s4-s3));
    }

}
