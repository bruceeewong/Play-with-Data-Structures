package linkedlist;

// 带虚拟头结点
public class LinkedList1<E> {
    public static void main(String[] args) {
        LinkedList1<Integer> linkedList = new LinkedList1<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        System.out.println(linkedList.isEmpty());

        linkedList.add(4, 666);
        System.out.println(linkedList);

        linkedList.remove(4);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);


        linkedList.removeLast();
        System.out.println(linkedList);
        linkedList.removeLast();
        System.out.println(linkedList);
        linkedList.removeLast();
        System.out.println(linkedList);
        System.out.println(linkedList.isEmpty());
    }

    private class Node {
        E e;
        Node next;

        public Node(E e) {
            this.e = e;
            this.next = null;
        }

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedList1() {
        dummyHead = new Node(null, null);
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 链表插入任意位置
     * @param index int
     * @param e E
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Insert failed. index is out of range.");
        }
        Node cur = dummyHead;
        for (int i = 0; i < index; i ++) {
            cur = cur.next;
        }
        Node target = new Node(e);
        target.next = cur.next;
        cur.next = target;

        size ++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size - 1, e);
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed. index is out of range.");
        }
        Node cur = dummyHead;
        for (int i = 0; i < index; i ++) {
            cur = cur.next;
        }
        Node target = cur.next;
        cur.next = target.next;
        target.next = null;

        size --;
        return target.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed. index is out of range.");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i ++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed. index is out of range.");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i ++) {
            cur = cur.next;
        }
        return cur.e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("linkedlist.LinkedList: ");
        Node cur = dummyHead.next;
        while (cur != null) {
            res.append(cur.e).append("->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}
