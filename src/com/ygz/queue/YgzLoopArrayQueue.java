package com.ygz.queue;

/**
  * @Author: Ygz
  * @Description: 基于数组的循环队列
  * @Date：2018/6/21 20:54
  */
public class YgzLoopArrayQueue<E> implements  YgzQueue<E>{

    //循环队列的数组
    private E[] queueArr;

    //front:队首下标   tail:队尾下标+1   size：队列内容数量
    private int front,tail,size;

    /**
      * @Author: Ygz
      * @Description:无参构造
      * @Date：2018/6/21 21:25
      * @Param：[]
      * @return
      */
    public YgzLoopArrayQueue(){
        this(10);
    }

    /**
      * @Author: Ygz
      * @Description:传入指定容量的构造
      * @Date：2018/6/21 21:25
      * @Param：[capacity]
      * @return
      */
    public YgzLoopArrayQueue(int capacity){
        queueArr = (E[])new Object[capacity + 1];//循环队列会有意的空出一个为null的元素位置
    }

    /**
      * @Author: Ygz
      * @Description:获取队列的长度
      * @Date：2018/6/21 20:51
      * @Param：[]
      * @return int
      */
    @Override
    public int size() {
        return size;
    }

    /**
      * @Author: Ygz
      * @Description:队列是否为空
      * @Date：2018/6/21 20:51
      * @Param：[]
      * @return boolean
      */
    @Override
    public boolean isEmpty() {
        return front == tail;//当队首和队尾位置一样的时候  说明队列为空  否则不为空
    }

    /**
      * @Author: Ygz
      * @Description:获取队列的容量
      * @Date：2018/6/21 21:41
      * @Param：[]
      * @return int
      */
    public int getCapacity(){
        //要时刻记住  size是队列内容数量  queueArr.length是队列真正的长度  getCapacity()才是队列的容量
        return queueArr.length - 1;
    }

    /**
      * @Author: Ygz
      * @Description:给队尾插入一个元素
      * @Date：2018/6/21 20:51
      * @Param：[e]
      * @return void
      */
    @Override
    public void enqueue(E e) {
        if((tail + 1) % queueArr.length == front)   //如果 队尾+1对队列容量取余等于队首   说明队列满了
            this.resize(this.getCapacity() * 2);// 扩容2倍
        queueArr[tail] = e;
        tail = (tail + 1) % queueArr.length;//循环队列   队首位置+1但是其实是循环的，所以是(tail + 1) % queueArr.length
        size++;
    }

    /**
      * @Author: Ygz
      * @Description:队列的扩容方法
      * @Date：2018/6/21 21:39
      * @Param：[]
      * @return void
      */
    private void resize(int newCapacity) {
        E[] newQueue = (E[])new Object[newCapacity + 1];//新建一个数组
        for(int i = 0; i < size; i++)
            //循环把原来数组的每个元素从队首开始依次放入新的队列
            newQueue[i] = queueArr[(front + i) % queueArr.length];
        front = 0;//队首下标变成0
        tail = size;//队尾下标是size
        queueArr = newQueue;//新的数组复制给queueArr
    }

    /**
      * @Author: Ygz
      * @Description:取出队首的元素
      * @Date：2018/6/21 20:52
      * @Param：[]
      * @return E
      */
    @Override
    public E dequeue() {
        if(this.isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        E res = queueArr[front];//把要取出的记录下来留待return
        queueArr[front] = null;//把取出的变成null
        front = (front + 1) % queueArr.length;//front往后挪一位 因为是循环队列  所以是(front + 1) % queueArr.length
        size--;//队列内容数量-1
        //和之前的动态数组一样  如果队列的内容数量等于了队列容量的1/4  并且队列的容量不小于2
        if(size == getCapacity() / 4 && getCapacity() / 2 != 0)
            this.resize(getCapacity() / 2); //那就给队列容量缩小一半
        return res;
    }

    /**
      * @Author: Ygz
      * @Description:获取队首的元素
      * @Date：2018/6/21 20:53
      * @Param：[]
      * @return E
      */
    @Override
    public E getFront() {
        if(this.isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return queueArr[front];
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("YgzQueue: size = %d , capacity = %d\n", size, getCapacity()));
        res.append("队首在这边 [");
        for(int i = front ; i != tail ; i = (i + 1) % queueArr.length){
            res.append(queueArr[i]);
            if((i + 1) % queueArr.length != tail)
                res.append(", ");
        }
        res.append("] 队尾在这边");
        return res.toString();
    }
}
