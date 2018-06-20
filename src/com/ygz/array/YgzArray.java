package com.ygz.array;

public class YgzArray<E> {

    private E[] arrData;//实际的数组

    private int size;//数组的实时长度

    /**
      * @Author: Ygz
      * @Description: 无参构造  默认10个空间的数组
      * @Date：2018/6/20 10:51
      */
    public YgzArray(){
        arrData = (E[]) new Object[10];
        size = 0;
    }

    /**
      * @Author: Ygz
      * @Description: 传入初始长度的构造参数
      * @Date：2018/6/20 10:52
      * @Param：[capacity]-初始数组容量
      */
    public YgzArray(int capacity){
        arrData = (E[]) new Object[capacity];
        size = 0;
    }

    /**
      * @Author: Ygz
      * @Description:获取数组中的内容数量
      * @Date：2018/6/20 10:58
      * @return int
      */
    public int size(){
        return size;
    }
    /**
      * @Author: Ygz
      * @Description:获取数组的容量
      * @Date：2018/6/20 10:59
      * @Param：[]
      * @return int
      */
    public int capacity(){
        return arrData.length;
    }

    /**
      * @Author: Ygz
      * @Description:查看数组的内容是不是为空 为空返回true 否则返回false
      * @Date：2018/6/20 11:02
      * @return boolean
      */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
      * @Author: Ygz
      * @Description:根据下标获取数组元素
      * @Date：2018/6/20 11:03
      * @Param：[index]-数组下标
      * @return E
      */
    public E get(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("数组下标错误，get失败");
        }
        return arrData[index];
    }

    /**
      * @Author: Ygz
      * @Description:修改数组指定下标的元素
      * @Date：2018/6/20 11:06
      * @Param：[index, e]
      * @return void
      */
    public void set(int index, E e){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("数组下标错误，set失败");
        }
        arrData[index] = e;
    }

    /**
      * @Author: Ygz
      * @Description:将数组扩容到newCapacity大小,其实是创建一个newCapacity大小的数组，
      *                 并把原数组的内容拷贝过去，然后给arrData的引用指向newData
      * @Date：2018/6/20 11:18
      * @Param：[newCapacity]
      * @return void
      */
    public void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];
        for(int i = 0; i < size; i++)
            newData[i] = arrData[i];
        arrData = newData;
    }

    /**
      * @Author: Ygz
      * @Description:向数组末尾添加一个元素，其实就是给size下标的位置添加一个元素
      * @Date：2018/6/20 11:12
      * @Param：[e]
      */
    public void add(E e){
        this.addByIndex(size, e);
    }

    /**
      * @Author: Ygz
      * @Description:向数组中指定下标的位置插入一个元素
      * @Date：2018/6/20 11:14
      * @Param：[index, e]
      * @return void
      */
    public void addByIndex(int index, E e){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("数组下标错误，add失败");
        }
        if(arrData.length == size){ //如果数组已经满了   那应该先扩容
//            this.resize((int)Math.ceil(arrData.length * 1.5));//扩容时扩大1.5倍 并向上取整
            this.resize(arrData.length * 2);//扩容时扩大2倍 并向上取整
        }
        for(int i = size; i > index; i--)
            arrData[i] = arrData[i-1];//把index下标后面的数组，全部往后挪一个位置，并把index位置赋值e
        arrData[index] = e;
        size++;
    }

    /**
      * @Author: Ygz
      * @Description:从数组中删除指定下标的元素,并返回被删除的元素
      * @Date：2018/6/20 12:03
      * @Param：[index]
      * @return E
      */
    public E remove(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("数组下标错误，remove失败");
        }
        E oldE = arrData[index];    //记录要删除的元素  留待return
        for(int i = index; i < size; i++){
            arrData[i] = arrData[i+1];
        }
        size--;
        //当数组中的内容只有数组容量的1/4时 并且 数组容量/2 不等于0
        if(size == arrData.length / 4 && arrData.length / 2 != 0)
            resize(arrData.length / 2);     //数组容量就可以减少一半  均摊复杂度
        return oldE;
    }

    /**
      * @Author: Ygz
      * @Description:查询数组中是否存在元素e，存在返回元素下标，不存在返回-1
      * @Date：2018/6/20 16:57
      * @Param：[e]
      * @return int
      */
    public int find(E e){
        for(int i = 0; i < arrData.length; i++)
            if(arrData[i].equals(e)) return i;
        return -1;
    }

    /**
      * @Author: Ygz
      * @Description:删除数组中的某个元素
      * @Date：2018/6/20 17:00
      * @Param：[e]
      * @return void
      */
    public void removeElement(E e){
        int index = this.find(e);//查找这个元素是不是存在
        if(index != -1) //如果元素存在
            this.remove(index); //根据下标删除元素
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d →", size, arrData.length));
        res.append('[');
        for(int i = 0 ; i < size ; i ++){
            res.append(arrData[i]);
            if(i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }
}
