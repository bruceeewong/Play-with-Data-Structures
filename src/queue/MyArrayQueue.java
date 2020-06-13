package queue;

import array.MyArray;

public class MyArrayQueue<E> implements IQueue<E> {
    private MyArray<E> data;

    public MyArrayQueue(int capacity) { data = new MyArray<>(capacity); }
    public MyArrayQueue() { data = new MyArray<>(); }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        data.addLast(e);
    }

    @Override
    public E dequeue() {
        return data.removeFirst();
    }

    @Override
    public E getFront() {
        return data.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("queue.Queue: ");
        res.append("front [");
        for (int i = 0; i < data.getSize(); i++) {
            res.append(data.get(i));
            if (i != data.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }
}
