package com.zhangsan.no_4_linked;

/**
 * 数组实现栈和队列结构
 * 该类为固定数组，没有做动态扩容
 * @author zhangsan
 * @date 2021/2/3 13:22
 */
public class Code06_ArrayListStackAndQueue {

    public Code06_ArrayListStackAndQueue(int maxSize) {
        this.maxSize = maxSize;
        elements = new String[maxSize];
    }

    final int maxSize;
    int size;
    int addIndex;
    int lpopIndex;
    String[] elements;

    public void addElement(String val) {
        if(size >= maxSize) {
            throw new ArrayIndexOutOfBoundsException("下标越界");
        }
        if(addIndex == 5){
            addIndex = 0;
        }
        elements[addIndex++] = val;
        size++;
    }

    public String rpop() {
        if(size > maxSize || addIndex < 1) {
            throw new ArrayIndexOutOfBoundsException("下标越界");
        }
        String r = elements[--addIndex];
        elements[addIndex] = null;
        size--;
        return r;
    }

    public String lpop() {
        if(size > maxSize) {
            throw new ArrayIndexOutOfBoundsException("下标越界");
        }
        if(lpopIndex == maxSize) {
            lpopIndex = 0;
        }
        String r = elements[lpopIndex];
        elements[lpopIndex++] = null;
        size--;
        return r;
    }

    public void print() {
        System.out.print("[");
        for (String element : elements) {
            System.out.print(element + ", ");
        }
        System.out.print("]\n");
    }

}

class TestCode06_ArrayListStackAndQueue {
    public static void main(String[] args) {
        Code06_ArrayListStackAndQueue code = new Code06_ArrayListStackAndQueue(5);
        // 栈数据结构
        code.addElement("a");
        code.addElement("b");
        code.addElement("c");
        code.addElement("d");
        code.addElement("e");
        code.print();
        System.out.println(code.rpop());
        System.out.println(code.rpop());
        System.out.println(code.rpop());
        code.print();
        code.addElement("b");
        code.addElement("c");
        code.addElement("d");
        code.print();
        System.out.println(code.rpop());
        code.addElement("e");
        // 队列数据结构
        /*code.addElement("a");
        code.addElement("b");
        code.addElement("c");
        code.addElement("d");
        code.addElement("e");
        code.print();
        System.out.println(code.lpop());
        System.out.println(code.lpop());
        System.out.println(code.lpop());
        code.print();
        code.addElement("b");
        code.addElement("c");
        code.addElement("d");
        code.print();
        System.out.println(code.lpop());
        System.out.println(code.lpop());
        System.out.println(code.lpop());
        code.addElement("e");
        code.print();
        code.addElement("f");
        code.addElement("g");
        code.print();*/
    }
}
