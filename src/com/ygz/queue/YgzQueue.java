package com.ygz.queue;

public interface YgzQueue<E> {

    //获取队列长度
    int size();

    //队列是否为空
    boolean isEmpty();

    //给队尾添加一个元素
    void enqueue(E e);

    //取出队首元素
    E dequeue();

    //获取队首的元素
    E getFront();

}
