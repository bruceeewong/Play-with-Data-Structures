package queue;

public class MyLoopQueue<E> implements IQueue<E> {
    private E[] data;
    private int size;
    private int front, tail;

    public MyLoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        size = front = tail = 0;
    }

    public MyLoopQueue() {
        this(10);
    }


    @Override
    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    public boolean isFull() {
        return getNextIndex(tail) == front;
    }

    private int getNextIndex(int index) {
        return (index + 1) % data.length;
    }

    @Override
    public void enqueue(E e) {
        if (isFull()) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = getNextIndex(tail);
        size ++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("dequeue failed. queue is empty");
        }
        E ret = data[front];
        data[front] = null;
        front = getNextIndex(front);
        size --;

        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }

        return ret;
    }

    private void resize(int capacity) {
        E[] newData = (E[])new Object[capacity + 1];
        for (int i = 0; i < size; i ++) {
            newData[i] = data[(front + i) % data.length];
        }
        data = newData;
        // 重置头尾指针
        front = 0;
        tail = size;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("dequeue failed. queue is empty");
        }
        return data[front];
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        res.append("front [");

        for(int i = front ; i != tail ; i = getNextIndex(i)){
            res.append(data[i]);
            if((i + 1) % data.length != tail)
                res.append(", ");
        }

        res.append("] tail");
        return res.toString();
    }
}
