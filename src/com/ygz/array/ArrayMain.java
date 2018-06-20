package com.ygz.array;

/**
  * @Author: Ygz
  * @Description:动态数组测试类
  * @Date：2018/6/20 17:31
  */
public class ArrayMain {

    public static void main(String[] args) {
        YgzArray<Integer> arr = new YgzArray();
        for (int i = 0; i < 10; i++) {
            arr.add(i);
        }
        System.out.println(arr);
        arr.add(11);
        System.out.println(arr);
        arr.addByIndex(0,-1);
        System.out.println(arr);
        arr.set(0,-2);
        System.out.println(arr);
        arr.remove(0);
        System.out.println(arr);
        arr.removeElement(11);
        System.out.println(arr);
        arr.removeElement(1);
        arr.removeElement(2);
        arr.removeElement(3);
        arr.removeElement(4);
        arr.removeElement(5);
        arr.removeElement(6);
        arr.removeElement(7);
        arr.removeElement(0);
        System.out.println(arr);
    }

}
