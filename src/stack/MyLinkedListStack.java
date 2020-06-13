package stack;

import linkedlist.MyLinkedList2;

public class MyLinkedListStack<E>  implements IStack<E> {
    private MyLinkedList2<E> linkedList;

    public MyLinkedListStack() {
        linkedList = new MyLinkedList2<>();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return linkedList.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("stack.Stack: top ");
        res.append(linkedList);
        return res.toString();
    }
}
