package com.ygz.queue;

/**
 * 基于链表实现的队列
 */
public class YgzLinkedQueue<E> implements YgzQueue<E>{

    /**
     * 节点对象
     */
    private class Node{

        //链表的某个节点的实际值
        public E e;

        //链表某个节点的下一个节点
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null, null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    //链表的头节点和尾节点
    private Node head, tail;

    private int size;

    /**
     * 无参构造
     */
    public YgzLinkedQueue(){
        head = null;
        tail = null;
        size = 0;
    }

    /**
      * @Author: Ygz
      * @Description:获取队列的内容数量
      * @Date：2018/6/24 19:15
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
      * @Date：2018/6/24 19:15
      * @Param：[]
      * @return boolean
      */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
      * @Author: Ygz
      * @Description:队列尾新增一个元素，即入队
      * @Date：2018/6/24 19:15
      * @Param：[e]
      * @return void
      */
    @Override
    public void enqueue(E e) {
        //下面描述一下  我这个基于链表队列，链表是有head和tail的，
        // 但是在链表头新增和删除元素时间复杂度都是O(1)，在链表尾添加元素也是O(1)，但是在链表尾删除元素的时候
        // 由于是单向链表  链表尾不知道它自己的前一个元素节点  所以删除链表尾的元素时间复杂度会变成O(n)
        // 这样的话  这里做一个变换
        // 我们从链表头删除元素(出队)  链表尾新增元素(入队)
        // 这样根据队列先进先出的特性  这个基于链表的队列不管是出队还是入队
        // 时间复杂度都是O(1)  操作如下：
        if(tail == null){           //如果队尾为null  说明队列为空
            tail = new Node(e);     //new Node给队尾
            head = tail;            //队首和队尾一样
        }else{
            tail.next = new Node(e);//给队尾的下一个赋值new Node 相当于给队尾的位置添加一个元素
            tail = tail.next;       //更新tail队尾的值
        }
        size ++;
    }

    /**
      * @Author: Ygz
      * @Description:队列首出去一个元素，即出队
      * @Date：2018/6/24 19:36
      * @Param：[]
      * @return E
      */
    @Override
    public E dequeue() {
        if(this.isEmpty())
            throw new IllegalArgumentException("队列为空，dequeue失败。");
        Node res = head;        //首先拿到队首的元素  准备出队留作return
        head = head.next;       //然后更新head为head的下一个(next)
        res.next = null;        //要出队的节点的下一个(next)变为null
        if(head == null)        //这里要注意  经过上一步  head == null 说明队列之前只有1个元素了
            tail = null;        //所以要更新tail也变成null
        size--;
        return res.e;
    }

    /**
      * @Author: Ygz
      * @Description:查看队列的队首元素
      * @Date：2018/6/24 19:57
      * @Param：[]
      * @return E
      */
    @Override
    public E getFront() {
        if(this.isEmpty())
            throw new IllegalArgumentException("队列为空，getFront失败。");
        return head.e;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("YgzLinkedQueue: head  ");
        Node cur = head;
        while(cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append(" tail");
        return res.toString();
    }
}
