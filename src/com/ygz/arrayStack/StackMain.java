package com.ygz.arrayStack;

import java.util.Random;

public class StackMain {

    // 测试使用stack运行opCount个push和pop操作所需要的时间，单位：秒
    private static double testStack(YgzStack<Integer> stack, int opCount){

        long startTime = System.nanoTime();

        Random random = new Random();
        for(int i = 0 ; i < opCount ; i ++)
            stack.push(random.nextInt(Integer.MAX_VALUE));
        for(int i = 0 ; i < opCount ; i ++)
            stack.pop();

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        int opCount = 100000;

        YgzArrayStack<Integer> arrayStack = new YgzArrayStack<>();
        double time1 = testStack(arrayStack, opCount);
        System.out.println("ArrayStack, time: " + time1 + " s");

        YgzLinkedStack<Integer> linkedListStack = new YgzLinkedStack<>();
        double time2 = testStack(linkedListStack, opCount);
        System.out.println("LinkedListStack, time: " + time2 + " s");
    }

}
