package com.zhangsan.no_dp_skill;

/**
 *
 * 有两种袋子: 容量为6和8的, 给定一个要购买的苹果数量,最少用多少个袋子刚好能装完
 *
 * @author zhangsan
 * @date 2021/3/28 21:02
 */
public class Code01_AppleMinBags {

    // 暴力尝试
    public static int minBags(int apple) {
        if( apple < 0 ) {
            return -1;
        }
        int bag6 = -1;
        int bag8 = apple / 8;
        int rest = apple - bag8*8;
        while ( bag8>=0 && rest < 24 ) {
            int restUseBag6 = rest % 6 == 0? rest/6 : -1;
            if(restUseBag6 != -1) {
                bag6 = restUseBag6;
                break;
            }
            rest = apple - (--bag8) * 8;
        }
        return bag6 == -1? -1: bag6+bag8;
    }

    /** 通过对数器找规律. */
    public static int minAppleBase6(int apple) {
        if(apple % 2 == 1) { return -1; }
        if(apple < 18) {
            return apple == 6 || apple == 8  ? 1 : (apple == 12 || apple == 14 ||apple == 16 )? 2 : -1;
        }else {
            return apple % 8 == 0? apple/8 : apple/8+1;
        }
    }

    public static void main(String[] args) {
//        int apple = 28;
//        int r1 = minBags(apple);
//        System.out.println(r1);

        long s1 = System.nanoTime();
        for (int i = 1; i <= 200; i++) {
            System.out.println(i + " : " + minBags(i));
        }
        long s2 = System.nanoTime();

        for (int i = 1; i <= 200; i++) {
            System.out.println(i + " : " + minAppleBase6(i));
        }
        long s3 = System.nanoTime();
        System.out.println("第一种: " + (s2-s1));
        System.out.println("第二种: " + (s3-s2));

    }

}
