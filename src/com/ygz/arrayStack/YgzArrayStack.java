package com.ygz.arrayStack;

import com.ygz.array.YgzArray;

/**
 * 自定义栈实现类 基于数组
 */
public class YgzArrayStack<E> implements YgzStack<E> {

    private YgzArray<E> arrStack;

    /**
      * @Author: Ygz
      * @Description:无参构造
      * @Date：2018/6/20 22:22
      * @Param：[]
      * @return
      */
    public YgzArrayStack(){
        arrStack = new YgzArray<>();
    }

    /**
     * @Author: Ygz
     * @Description:带参数构造
     * @Date：2018/6/20 22:22
     * @Param：[]
     * @return
     */
    public YgzArrayStack(int capacity){
        arrStack = new YgzArray<>(capacity);
    }

    /**
      * @Author: Ygz
      * @Description:获取栈容量
      * @Date：2018/6/20 22:21
      * @Param：[]
      * @return int
      */
    private int getCapacity(){
        return arrStack.getCapacity();
    }

    /**
      * @Author: Ygz
      * @Description:获取栈内容数量
      * @Date：2018/6/20 22:11
      * @return int
      */
    @Override
    public int getSize() {
        return arrStack.size();
    }

    /**
      * @Author: Ygz
      * @Description:判断栈是不是空的
      * @Date：2018/6/20 22:12
      * @return boolean
      */
    @Override
    public boolean isEmpty() {
        return arrStack.isEmpty();
    }

    /**
      * @Author: Ygz
      * @Description:入栈一个元素
      * @Date：2018/6/20 22:12
      * @Param：[e]
      */
    @Override
    public void push(E e) {
        arrStack.add(e);
    }

    /**
      * @Author: Ygz
      * @Description:出栈
      * @Date：2018/6/20 22:13
      * @return E
      */
    @Override
    public E pop() {
        return arrStack.removeLast();
    }

    /**
      * @Author: Ygz
      * @Description:查看栈顶元素
      * @Date：2018/6/20 22:15
      * @return E
      */
    @Override
    public E peek() {
        return arrStack.getLast();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("YgzArrayStack: ");
        res.append('[');
        for(int i = 0 ; i < arrStack.size() ; i ++){
            res.append(arrStack.get(i));
            if(i != arrStack.size() - 1)
                res.append(", ");
        }
        res.append("] 栈顶在这边");
        return res.toString();
    }
}
