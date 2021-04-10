package com.zhangsan.no_bfprt;

import com.zhangsan.util.ArrayUtil;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 无序数组中寻找到第k小的数组
 *
 * @author zhangsan
 * @date 2021/3/16 15:02
 */
public class Code01_FindMinKth {

    public static int minKth(int[] arr, int k) {
        Arrays.sort(arr);
        return arr[k - 1];
    }

    /*public static int minKth2(int[] arr, int k) {
        return process(arr, 0, arr.length-1, k-1);
    }

    private static int process(int[] arr, int L, int R, int k) {
        if(L == R) {
            return arr[L];
        }
        int pivot = arr[L + (int) (Math.random()*(R-L+1))]; // 随机选一个中心点
        int[] range = partition(arr, L, R, pivot);          // 荷兰国旗排序数组并获得中心点这个数左右区间
        if(k >= range[0] && k <= range[1]) {                // 命中返回这个区间
            return arr[k];
        }else if(k < range[0]) {
            return process(arr, L, range[0]-1, k);
        }else {
            return process(arr,range[0]+1, R, k);
        }
    }*/

    public static int minKth2(int[] arr, int k) {
        return process(arr, 0, arr.length - 1, k - 1);
    }

    private static int process(int[] arr, int L, int R, int K) {
        if (L == R) {
            return arr[L];
        }
        int pivot = arr[L + (int) (Math.random() * (R - L + 1))];
        int[] range = partition(arr, L, R, pivot);
        if (K >= range[0] && K <= range[1]) {
            return arr[K];
        } else if (K < range[0]) {
            // 左边找
            return process(arr, L, range[0] - 1, K);
        } else {
            // 右边找
            return process(arr, range[1] + 1, R, K);
        }
    }

    // 递归改迭代
    public static int minKth3(int[] arr, int k) {
        int L = 0;
        int R = arr.length - 1;
        k--;
        int pivot = 0;
        int[] range = null;
        while (L < R) {
            pivot = arr[L + (int) (Math.random() * (R - L + 1))];
            range = partition(arr, L, R, pivot);
            if (k >= range[0] && k <= range[1]) {
                return pivot;
            } else if (k < range[0]) {
                R = range[0] - 1;
            } else {
                L = range[1] + 1;
            }
        }
        return arr[L];
    }

    public static int minKth4(int[] arr, int k) {
        return bfprt(arr, 0, arr.length - 1, k - 1);
    }

    /** 精挑细选选一个数, 将数组分成< p =p= >p 的三份, 再看K位置的数在哪儿一份上
     * 如果在中间直接找到, 如果在左边, 只会向左找, 或在右边向右找,不会全部找完
     * */
    private static int bfprt(int[] arr, int L, int R, int K) {
        if (L == R) {
            return arr[L];
        }
        int pivot = medianOfMedians(arr, L, R);

        int[] range = partition(arr, L, R, pivot);
        if (K >= range[0] && K <= range[1]) {
            return arr[K];
        } else if (K < range[0]) {
            // 左边找
            return bfprt(arr, L, range[0] - 1, K);
        } else {
            // 右边找
            return bfprt(arr, range[1] + 1, R, K);
        }
    }

    /**
     * 以5各大小为区间分成n/5份, 排好序获取中位数, 在从n/5份上获取一个中位数,及精挑细选的中位数
     */
    private static int medianOfMedians(int[] arr, int L, int R) {
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] medians = new int[size / 5 + offset];
        for (int i = 0; i < medians.length; i++) {
            // l~r排序选中间
            int teamFirst = i * 5;
            medians[i] = getMedian(arr, teamFirst, Math.min(teamFirst+4, R));
        }
        return bfprt(medians, 0, medians.length-1, medians.length/2);
    }

    /**
     * 在arr[L~R] 排序返回中位数
     * @param arr 数组
     * @param L 左边界
     * @param R 有边界
     * @return 中位数
     */
    private static int getMedian(int[] arr, int L, int R) {
        // 大小为5, 或者比5小, 使用插入排序
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length && arr[j] < arr[j-1]; j++) {
                arr[j] = arr[j] ^ arr[j-1];
                arr[j-1] = arr[j] ^ arr[j-1];
                arr[j] = arr[j] ^ arr[j-1];
            }
        }
        return arr[(L+R) / 2];
    }

    private static int[] partition(int[] arr, int l, int r, int pivot) {
        int curIndex = l, leftBorder = l - 1, rightBorder = r + 1;

        // 1 3 2 4 4 8 5 6
        while (curIndex < rightBorder) {
            if (arr[curIndex] < pivot) {
                if (curIndex != ++leftBorder) {
                    swap(arr, curIndex, leftBorder);
                }
                curIndex++;
            } else if (arr[curIndex] == pivot) {
                curIndex++;
            } else {
                if (curIndex != --rightBorder) {
                    swap(arr, curIndex, rightBorder);
                }
            }
        }
        return new int[]{leftBorder + 1, rightBorder - 1};
    }

    private static void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];
    }


    // for test
    public static void main(String[] args) {

        /*int[] arr = ArrayUtil.generateRandomArray(1000, 1000);
//        int[] arr = {1,23,13,165,4321,3,1231,31,53};
        int[] arr1 = ArrayUtil.copyArr(arr);
        int[] arr2 = ArrayUtil.copyArr(arr);
        int[] arr3 = ArrayUtil.copyArr(arr);
        int k = (int) (Math.random() * 100 + 1);

        int r1 = minKth(arr, k);
        int r2 = minKth2(arr1, k);
        int r3 = minKth3(arr2, k);
        int r4 = minKth3(arr3, k);
        System.out.println(r1 + "\t" + r2 + "\t" + r3 + "\t" + r4);*/

        // 以10倍, 为区间测速
        for (int i = 10; i < Integer.MAX_VALUE; i*=10) {
            int[] arr = ArrayUtil.generateRandomArray(i, 1000, true, true);
            int[] arr1 = ArrayUtil.copyArr(arr);
            int[] arr2 = ArrayUtil.copyArr(arr);
            int[] arr3 = ArrayUtil.copyArr(arr);
//            int[] arr4 = ArrayUtil.copyArr(arr);
            int k = (int) (Math.random() * i) + 1;

            long s1 = System.currentTimeMillis();
            int r1 = minKth(arr, k);
            long s2 = System.currentTimeMillis();
            int r2 = minKth2(arr1, k);
            long s3 = System.currentTimeMillis();
            int r3 = minKth3(arr2, k);
            long s4 = System.currentTimeMillis();
            int r4 = minKth3(arr3, k);
            long s5 = System.currentTimeMillis();

            System.out.println("==============数组大小: "+ i +"====================");
            System.out.println("数组大小: "+ i +"排序解: " + (s2-s1) + "ms.");
            System.out.println("数组大小: "+ i +"递归快排思想解: " + (s3-s2) + "ms.");
            System.out.println("数组大小: "+ i +"迭代快排思想解: " + (s4-s3) + "ms.");
            System.out.println("数组大小: "+ i +"bfprt解: " + (s5-s4) + "ms.");
            System.out.println("==================================");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(r1 != r2 || r2 != r3 || r3 != r4) {
                System.out.println("OOPS!");
//                ArrayUtil.printArr(arr4);
                System.out.println(r1 + "\t" + r2 + "\t" + r3 + "\t" + r4);
                break;
            }

        }

    }

}
