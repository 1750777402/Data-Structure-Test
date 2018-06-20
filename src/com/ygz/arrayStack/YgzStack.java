package com.ygz.arrayStack;

/**
 * 自定义数组栈接口
 */
public interface YgzStack<E> {

    //获取栈的内容数量
    int getSize();

    //看栈是不是空的
    boolean isEmpty();

    //进栈
    void push(E e);

    //出栈
    E pop();

    //查看栈顶元素
    E peek();

}
