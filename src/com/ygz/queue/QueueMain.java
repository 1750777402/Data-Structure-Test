package com.ygz.queue;

public class QueueMain {

    public static void main(String[] args){

        YgzLoopQueue<Integer> queue = new YgzLoopQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

}
