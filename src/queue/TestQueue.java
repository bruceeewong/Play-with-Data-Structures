package queue;

public class TestQueue {
    public static void main(String[] args) {
        MyArrayQueue<Integer> arrayQueue = new MyArrayQueue<>();
        testQueue(arrayQueue);

        MyLoopQueue<Integer> loopQueue = new MyLoopQueue<>();
        testQueue(loopQueue);

    }

    private static void testQueue(IQueue<Integer> queue) {
        for (int i = 0; i < 10; i ++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }

        System.out.println("======================");
    }
}
