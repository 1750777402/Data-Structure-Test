package com.ygz.queue;

public class QueueMain {

    public static void main(String[] args){

//        YgzLoopArrayQueue<Integer> queue = new YgzLoopArrayQueue<>();
//        for(int i = 0 ; i < 10 ; i ++){
//            queue.enqueue(i);
//            System.out.println(queue);
//
//            if(i % 3 == 2){
//                queue.dequeue();
//                System.out.println(queue);
//            }
//        }

        YgzLinkedQueue<Integer> queue1 = new YgzLinkedQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue1.enqueue(i);
            System.out.println(queue1);

            if(i % 3 == 2){
                queue1.dequeue();
                System.out.println(queue1);
            }
        }
    }

}
