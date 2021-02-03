package com.zhangsan.no_4_linked;

/**
 * 栈结构，push(), pop(), getMin(),获取最小值方法
 * 要求时间复杂度都为O(1)
 * @author zhangsan
 * @date 2021/2/3 14:30
 */
public class Code07_StackGetMin {

    public Code07_StackGetMin(int maxSize) {
        this.maxSize = maxSize;
        elements = new int[maxSize];
        minStack = new int[maxSize];
    }

    int[] elements;
    int[] minStack;
    final int maxSize;
    int size;
    int addIndex;
    int lpopIndex;

    public void push(int val) {
        if(size >= maxSize) {
            throw new ArrayIndexOutOfBoundsException("下标越界");
        }
        if(addIndex == maxSize){
            addIndex = 0;
        }

        elements[addIndex] = val;
        if(addIndex == 0) {
            minStack[addIndex] = val;
        } else {
            minStack[addIndex] = Math.min(val, getMin());
        }
        addIndex++;
        size++;
    }
    public int pop() {
        if(size > maxSize || addIndex < 1) {
            throw new ArrayIndexOutOfBoundsException("下标越界");
        }
        int r = elements[--addIndex];
        elements[addIndex] = 0;
        size--;
        return r;
    }

    public int getMin() {
        return minStack[addIndex-1];
    }

}

class TestCode07_StackGetMin {
    public static void main(String[] args) {
        Code07_StackGetMin code = new Code07_StackGetMin(5);
        code.push(2);
        code.push(3);
        code.push(4);
        code.push(1);
        code.push(5);
        System.out.println(code.getMin());

        code.pop();
        System.out.println(code.getMin());
        code.pop();
        System.out.println(code.getMin());
        code.pop();
        System.out.println(code.getMin());
        code.pop();
        System.out.println(code.getMin());

    }
}
