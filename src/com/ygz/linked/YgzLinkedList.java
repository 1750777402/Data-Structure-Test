package com.ygz.linked;

/**
 * 单向链表
 */
public class YgzLinkedList<E> {

    //虚拟的头节点，此Node中的E为null，next指向链表中真正的第一个节点
    private Node virtualHead;

    //链表的内容数量
    private int size;

    /**
     * 链表的节点
     */
    private  class Node{

        //链表的某个节点的实际值
        public E e;

        //链表某个节点的下一个节点
        public Node next;

        /**
          * @Author: Ygz
          * @Description:无参构造
          * @Date：2018/6/24 13:09
          */
        public Node(){
            this(null,null);
        }

        /**
          * @Author: Ygz
          * @Description:用E做参数的构造
          * @Date：2018/6/24 13:10
          * @Param：[e]
          */
        public Node(E e){
            this(e,null);
        }

        /**
          * @Author: Ygz
          * @Description:以E和next做参数的构造
          * @Date：2018/6/24 13:12
          * @Param：[e, next]
          */
        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    /*
     * 无参构造
     */
    public YgzLinkedList(){
        virtualHead = new Node();//初始的时候创建一个空节点给virtualHead虚拟头节点
        size = 0;//因为是虚拟节点  所以size=0
    }

    /**
      * @Author: Ygz
      * @Description:获取链表的有效节点数量
      * @Date：2018/6/24 13:22
      * @Param：[]
      * @return int
      */
    public int size(){
        return size;
    }

    /**
      * @Author: Ygz
      * @Description:链表是不是空的
      * @Date：2018/6/24 13:23
      * @Param：[]
      * @return boolean
      */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
      * @Author: Ygz
      * @Description:根据下标获取链表的某个元素
      * @Date：2018/6/24 13:54
      * @Param：[index]
      * @return E
      */
    public E get(int index){
        if(index < 0 || index > this.size)
            throw new IllegalArgumentException("get失败，下标错误。");
        Node res = virtualHead.next;//首先是根据虚拟节点拿到链表的第一个节点
        for (int i = 0; i < index; i++)     //然后从0开始  循环到index前一个停止
            res = res.next;         //index的前一个的next就是index位置的节点引用
        return res.e;       //返回index节点的E
    }

    /**
      * @Author: Ygz
      * @Description:获取链表的第一个元素
      * @Date：2018/6/24 14:00
      * @Param：[]
      * @return E
      */
    public E getFirst(){
        return this.get(0);
    }

    /**
      * @Author: Ygz
      * @Description:获取最后一个元素
      * @Date：2018/6/24 14:21
      * @Param：[]
      * @return E
      */
    public E getLast(){
        return this.get(size - 1);
    }

    /**
      * @Author: Ygz
      * @Description:在指定下标的位置添加一个元素
      * @Date：2018/6/24 14:21
      * @Param：[index, e]
      * @return void
      */
    public void add(int index, E e){
        if(index < 0 || index > this.size)
            throw new IllegalArgumentException("add失败，下标错误。");
        Node prior = virtualHead;      //先拿到虚拟节点
        for (int i = 0; i < index; i++)     //然后从0开始  循环到index的前一个停止  就是index位置的前一个
            prior = prior.next;
        //循环结束后   prior就是index下标位置的前一个节点
        //新加的node的E是传进来的e  新的Node的next是前一个节点的next 然后前一个节点的next是新的node
        prior.next = new Node(e,prior.next);
        size ++;
    }

    /**
      * @Author: Ygz
      * @Description:在链表的头添加一个元素
      * @Date：2018/6/24 14:37
      * @Param：[e]
      * @return
      */
    public void addFirst(E e){
        this.add(0,e);
    }

    /**
      * @Author: Ygz
      * @Description:在链表为末尾添加一个元素
      * @Date：2018/6/24 14:41
      * @Param：[e]
      * @return void
      */
    public void addLast(E e){
        this.add(size, e);
    }

    /**
      * @Author: Ygz
      * @Description:修改指定位置的元素
      * @Date：2018/6/24 14:46
      * @Param：[index, e]
      * @return void
      */
    public void set(int index,E e){
        if(index < 0 || index > this.size)
            throw new IllegalArgumentException("set失败，下标错误。");
        Node indexNode = virtualHead;//从虚拟节点开始  也就是从头开始
        for (int i = 0; i <= index; i++)
            indexNode = indexNode.next;
        //循环结束后  indexNode就是index位置的节点
        indexNode.e = e;//把index的e修改为 传进来的e
    }

    /**
      * @Author: Ygz
      * @Description:在链表的第一个位置添加一个元素
      * @Date：2018/6/24 15:09
      * @Param：[e]
      * @return void
      */
    public void setFirst(E e){
        this.add(0,e);
    }

    /**
      * @Author: Ygz
      * @Description:在链表的最后面添加一个元素
      * @Date：2018/6/24 15:10
      * @Param：[e]
      * @return void
      */
    public void setLast(E e){
        this.set(size, e);
    }

    /**
      * @Author: Ygz
      * @Description:删除链表指定位置的元素
      * @Date：2018/6/24 15:12
      * @Param：[index]
      * @return E
      */
    public E remove(int index){
        if(index < 0 || index > this.size)
            throw new IllegalArgumentException("remove失败，下标错误。");
        Node prior = virtualHead;           //先拿到虚拟节点
        for (int i = 0; i < index; i++)
            prior = prior.next;
        //循环结束后   prior就是index的前一个节点
        //前一个节点的下一个就是index的节点  把index节点的next给index的上一个的节点的next
        Node indexNode = prior.next;
        prior.next = indexNode.next;
        indexNode.next = null;//然后把index的下一个节点引用变成null
        size--;
        return indexNode.e;

        /*prior.next = prior.next.next;
        prior.next = null;//然后把index的下一个变成null
        size--;
        return prior.next.e;*/
    }


    /**
      * @Author: Ygz
      * @Description:删除第一个元素
      * @Date：2018/6/24 15:26
      * @Param：[]
      * @return E
      */
    public E removeFirst(){
        return this.remove(0);
    }

    /**
      * @Author: Ygz
      * @Description:删除链表的最后一个元素
      * @Date：2018/6/24 15:27
      * @Param：[]
      * @return E
      */
    public E removeLast(){
        return this.remove(size - 1);
    }

    /**
      * @Author: Ygz
      * @Description:查看链表中是否存在传入的元素E
      * @Date：2018/6/24 15:28
      * @Param：[e]
      * @return boolean
      */
    public boolean contains(E e){
        Node cur = virtualHead.next;    //从第一个元素开始
        while(cur != null){
            if(cur.e.equals(e))     //如果元素相同
                return true;    //返回true
            //不相同就看下一个  直到有相同的就会return true; 否则会递归到最后一个cur就成了null 递归结束返回false
            cur = cur.next;
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        Node cur = virtualHead.next;
        while(cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("结束");
        return res.toString();
    }

}

