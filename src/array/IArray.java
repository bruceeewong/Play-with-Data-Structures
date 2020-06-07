package array;

public interface IArray<E> {
    int getSize();
    int getCapacity();
    boolean isEmpty();

    void add(int index, E e);
    void addLast(E e);
    void addFirst(E e);

    E get(int index);
    E getFirst();
    E getLast();
    int find(E e);
    boolean contains(E e);

    void set(int index, E e);

    E remove(int index);
    E removeFirst();
    E removeLast();
    void removeElement(E e);
}
