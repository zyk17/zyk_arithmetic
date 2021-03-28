package com.zhangsan.no_dp_skill;

/**
 * 牛羊吃草
 * 牛先吃,羊后吃,他们都只能吃4 的 n次方份草*=
 * 谁先让对方无草可吃谁赢,牛羊都是聪明绝顶的
 * 请问n份草谁能获胜
 *
 * @author zhangsan
 * @date 2021/3/28 21:44
 */
public class Code02_EatGrass {

    public static String whoWin(int n) {
        if( n < 5 ) {
            return n == 0|| n== 2 ? "后手" : "先手";
        }
        int want = 1;
        while (want <= n) {
            if( whoWin(n - want).equals("后手") ) {
                return "先手";
            }
            want *= 4;
        }
        return "后手";
    }

    public static String whoWinBase(int n) {
        n %= 5;
        return n == 0|| n== 2 ? "后手" : "先手";
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 50; i++) {
            System.out.println(whoWin(i));
            System.out.println(whoWinBase(i));
        }
        /*for (int i = 0; i <= 100; i++) {
            String r1 = whoWin(i);
            String r2 = whoWinBase(i);
            if(!r1.equals(r2)) {
                System.out.println("OOPS");
                System.out.println(r1);
                System.out.println(r2);
                break;
            }
        }*/
    }

}
