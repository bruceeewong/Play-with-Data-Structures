package stack;

import array.MyArray;

public class MyArrayStack<E> implements IStack<E> {
    private MyArray<E> data;

    public MyArrayStack(int capacity) {
        data = new MyArray<>(capacity);
    }

    public MyArrayStack() {
        data = new MyArray<>();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void push(E e) {
        data.addLast(e);
    }

    @Override
    public E pop() {
        return data.removeLast();
    }

    @Override
    public E peek() {
        return data.get(0);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("stack.Stack: ");
        res.append('[');
        for (int i = 0; i < data.getSize(); i ++) {
            res.append(data.get(i));
            if (i != data.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }
}
