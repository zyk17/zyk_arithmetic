package com.zhangsan.no_2_sort;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * @author zhangsan
 * @date 2021/2/5 19:57
 */
public class C006_01_Comparator {

    static public void main(String[] args) {
        Student a = new Student(2L, "A", 18);
        Student b = new Student(1L, "B", 19);
        Student c = new Student(3L, "C", 17);
        Student d = new Student(2L, "D", 20);
        Student e = new Student(3L, "E", 20);

        {
            Student[] students = {a, b, c};
            System.out.println("第一条打印：\n排序前");
            System.out.println(Arrays.toString(students));
            Arrays.sort(students, (o1, o2) -> {
                /*if(o1.id < o2.id){
                    return -1;
                }else if(o1.id.equals(o2.id) ) {
                    return 0;
                }else {
                    return 1;
                }*/
                return (int) (o1.id - o2.id);
            });
            System.out.println("排序后：\n" + Arrays.toString(students));
        }

        System.out.println("第二条打印：");
        TreeMap<Student, String> treeMap = new TreeMap<Student, String>(
                (o1, o2) -> o1.id.equals(o2.id) ? System.identityHashCode(o1) - System.identityHashCode(o2) : (int) (o1.id - o2.id));
        treeMap.put(a, "我是a");
        treeMap.put(b, "我是b");
        treeMap.put(b, "我是b");
        treeMap.put(c, "我是c");
        treeMap.put(d, "我是d");
        treeMap.put(e, "我是e");
        for (Student student : treeMap.keySet()) {
            System.out.println(student);
        }
    }

    public static class Student /*implements Comparable<Student>*/ {
        public Student(Long id, String name, Integer age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        private Long id;
        private String name;
        private Integer age;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        /*@Override
        public int compareTo(Student o) {
            if(id < o.id){
                return -1;
            }else if(id.equals(o.id) ) {
                return 0;
            }else {
                return 1;
            }
        }*/

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}
