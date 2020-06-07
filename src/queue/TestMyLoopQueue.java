package queue;

public class TestMyLoopQueue {
    public static void main(String[] args){
        MyLoopQueue<Integer> queue = new MyLoopQueue<>();
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
