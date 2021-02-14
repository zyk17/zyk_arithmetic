package com.zhangsan.no_5_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个公司，都有仅没有下级列表。一个人的上级只有一个，或没有
 *
 * 一个聚会，上级和下级只能来一个， 求这个聚会邀请那些人可以获得最大的happy值
 *
 * @author zhangsan
 * @date 2021/2/14 14:22
 */
public class Code15_MaxHappy {

    public static class Employee {
        public int happy;
        public List<Employee> next;

        public Employee(int happy, List<Employee> next) {
            this.happy = happy;
            this.next = next;
        }
        public Employee(int happy) { this.happy = happy; next = new ArrayList<>(); }
    }

    public static class Info {
        public int no;      // 他来
        public int yes;     // 他不来

        public Info(int no, int yes) {
            this.no = no;
            this.yes = yes;
        }
    }

    public static int maxHappy(Employee head) {
        Info allInfo = process(head);
        return Math.max(allInfo.no, allInfo.yes);
    }

    private static Info process(Employee head) {
        if(head == null) {
            return new Info(0, 0);
        }
        int no = 0;
        int yes = head.happy;

        Info nextInfo = null;
        for (Employee employee : head.next) {
            nextInfo = process(employee);
            no += Math.max(nextInfo.yes, nextInfo.no);
            yes += nextInfo.no;
        }

        return new Info(no, yes);
    }

}

class TestCode15 {
    public static void main(String[] args) {
        Code15_MaxHappy.Employee a = new Code15_MaxHappy.Employee(10);
        Code15_MaxHappy.Employee b = new Code15_MaxHappy.Employee(5);
        Code15_MaxHappy.Employee c = new Code15_MaxHappy.Employee(5);
        Code15_MaxHappy.Employee d = new Code15_MaxHappy.Employee(5);
        Code15_MaxHappy.Employee e = new Code15_MaxHappy.Employee(7);
        Code15_MaxHappy.Employee f = new Code15_MaxHappy.Employee(8);
        Code15_MaxHappy.Employee g = new Code15_MaxHappy.Employee(6);
        Code15_MaxHappy.Employee h = new Code15_MaxHappy.Employee(3);
        Code15_MaxHappy.Employee i = new Code15_MaxHappy.Employee(2);
        Code15_MaxHappy.Employee j = new Code15_MaxHappy.Employee(5);
        Code15_MaxHappy.Employee k = new Code15_MaxHappy.Employee(1);

        a.next.add(b); a.next.add(c); a.next.add(d);
        b.next.add(e); b.next.add(f);
        c.next.add(g); c.next.add(h);
        f.next.add(i); f.next.add(j); f.next.add(k);

        System.out.println(Code15_MaxHappy.maxHappy(a));
    }
}
