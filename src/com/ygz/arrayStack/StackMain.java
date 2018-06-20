package com.ygz.arrayStack;

public class StackMain {

    public static void main(String[] args) {

        YgzArrayStack<Integer> stack = new YgzArrayStack<>();

        for(int i = 0 ; i < 5 ; i ++){
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
    }

}
