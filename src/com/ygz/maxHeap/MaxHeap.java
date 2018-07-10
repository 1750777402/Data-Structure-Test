package com.ygz.maxHeap;

import com.ygz.array.YgzArray;

/**
 * 一个最大堆的数组实现   基础数据结构是完全二叉树  这个二叉树的树顶元素一定是最大的值  也就是数组的第一个值就是最大的值
 * @param <E>
 */
public class MaxHeap<E extends Comparable<E>> {

    private YgzArray<E> data;

    public MaxHeap(int capacity){
        data = new YgzArray<>(capacity);
    }

    public MaxHeap(){
        data = new YgzArray<>();
    }

    //传入一个数组  并把这个数组变成最大堆    (这个操作要比把一个数组的元素一个一个往最大堆里面添加效率高)
    public MaxHeap(E[] arr){
        data = new YgzArray<>(arr);
        for(int i = parent(arr.length - 1) ; i >= 0 ; i --)
            siftDown(i);
    }

    // 返回堆中的元素个数
    public int size(){
        return data.size();
    }

    // 返回一个布尔值, 表示堆中是否为空
    public boolean isEmpty(){
        return data.isEmpty();
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index){
        if(index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index - 1) / 2;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return index * 2 + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return index * 2 + 2;
    }

    // 向堆中添加元素
    public void add(E e){
        data.add(e);
        siftUp(data.size() - 1);
    }

    //上浮操作  把大的值往树的上面放
    private void siftUp(int k){
        while(k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0 ){
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    // 看堆中的最大元素
    public E findMax(){
        if(data.size() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return data.get(0);
    }

    // 取出堆中最大元素
    public E extractMax(){
        E ret = findMax();
        data.swap(0, data.size() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    //下沉操作   把小的值往树的下面放
    private void siftDown(int k){
        while(leftChild(k) < data.size()){
            int j = leftChild(k); // 在此轮循环中,data[k]和data[j]交换位置
            if( j + 1 < data.size() &&
                    data.get(j + 1).compareTo(data.get(j)) > 0 )
                j ++;
            // data[j] 是 leftChild 和 rightChild 中的最大值
            if(data.get(k).compareTo(data.get(j)) >= 0 )
                break;
            data.swap(k, j);
            k = j;
        }
    }

    // 取出堆中的最大元素，并且替换成元素e
    public E replaceMax(E e){
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

}
