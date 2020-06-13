package linkedlist;

public interface ILinkedList<E> {
    int getSize();
    boolean isEmpty();
    void add(int index, E e);
    void addFirst(E e);
    void addLast(E e);
    E get(int index);
    E getFirst();
    E getLast();
    void set(int index, E e);
    boolean contains(E e);
    E remove(int index);
    E removeFirst();
    E removeLast();
}
