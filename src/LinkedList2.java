// 不带虚拟头结点的链表实现
public class LinkedList2<E> {
    public static void main(String[] args) {
        LinkedList2<Integer> linkedList = new LinkedList2<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.add(4, 666);
        System.out.println(linkedList);

        linkedList.remove(4);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);

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

    private Node head;
    private int size;

    public LinkedList2() {
        head = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int index, E e) {
        // 插入范围 [0, size]
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        Node target = new Node(e);

        if (index == 0) {
            target.next = head;
            head = target;
        } else {
            Node cur = head;
            for (int i = 1; i < index; i ++) {
                cur = cur.next;
            }
            target.next = cur.next;
            cur.next = target;
        }
        size ++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size - 1, e);
    }

    public E remove(int index) {
        // 插入范围 [0, size-1]
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Illegal index.");
        }
        Node target;
        if (index == 0) {
            target = head;
            head = target.next;
            target.next = null;
        } else {
            Node cur = head;
            for (int i = 1; i < index; i ++) {
                cur = cur.next;
            }
            target = cur.next;
            cur.next = target.next;
            target.next = null;
        }
        size --;
        return target.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

//        Node cur = dummyHead.next;
//        while(cur != null) {
//            res.append(cur + "->");
//            cur = cur.next;
//        }
        for (Node cur = head; cur != null; cur = cur.next) {
            res.append(cur.e).append("->");
        }
        res.append("NULL");

        return res.toString();
    }
}
