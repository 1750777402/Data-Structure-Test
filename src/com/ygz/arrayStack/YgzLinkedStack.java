package com.ygz.arrayStack;

import com.ygz.linked.YgzLinkedList;

/**
 * 基于链表实现的栈
 */
public class YgzLinkedStack<E> implements  YgzStack<E>{

    //链表
    private YgzLinkedList<E> linkedData;

    /**
     * 无参构造
     */
    public YgzLinkedStack(){
        linkedData = new YgzLinkedList<>();
    }

    @Override
    public int getSize() {
        return linkedData.size();
    }

    @Override
    public boolean isEmpty() {
        return linkedData.isEmpty();
    }

    /**
      * @Author: Ygz
      * @Description:入栈一个元素
      * @Date：2018/6/24 17:31
      * @Param：[e]
      * @return void
      */
    @Override
    public void push(E e) {
        linkedData.addFirst(e);
    }

    /**
      * @Author: Ygz
      * @Description:出栈一个元素
      * @Date：2018/6/24 17:32
      * @Param：[]
      * @return E
      */
    @Override
    public E pop() {
        return linkedData.removeFirst();
    }

    /**
      * @Author: Ygz
      * @Description:查看栈的第一个元素
      * @Date：2018/6/24 17:33
      * @Param：[]
      * @return E
      */
    @Override
    public E peek() {
        return linkedData.getFirst();
    }


}
