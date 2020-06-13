package linkedlist;

// 带虚拟头节点的链表
public class MyLinkedList2<E> implements ILinkedList<E> {
    private class Node {
        public E e;
        public Node next;

        public Node(E e) {
            this.e = e;
            next = null;
        }

        public Node() {
            this(null);
        }
    }

    private Node dummyHead;
    private int size;

    public MyLinkedList2() {
        dummyHead = new Node();
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add failed. index should be in [0, size].");
        }
        Node previousNode = getPrevious(index);
        Node newNode = new Node(e);

        newNode.next = previousNode.next;
        previousNode.next = newNode;
        size++;
    }

    private Node getPrevious(int index) {
        Node cur = dummyHead;
        for (int i = 0; i < index - 1; i++) {
            cur = cur.next;
        }
        return cur;
    }

    private Node find(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (e.equals(cur.e)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    private Node getNode(int index) {
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    @Override
    public void addFirst(E e) {
        add(0, e);
    }

    @Override
    public void addLast(E e) {
        add(size, e);
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Illegal index, ranged [0,size)");
        }
        Node node = getNode(index);
        return node == null ? null : node.e;
    }

    @Override
    public E getFirst() {
        return get(0);
    }

    @Override
    public E getLast() {
        return get(size - 1);
    }

    @Override
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Illegal index, ranged [0,size)");
        }
        Node node = getNode(index);
        node.e = e;
    }

    @Override
    public boolean contains(E e) {
        return find(e) == null;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed. Illegal index. ranged [0,size)");
        }
        Node privousNode = getPrevious(index);
        Node removeNode = privousNode.next;
        privousNode.next = removeNode.next;
        removeNode.next = null;
        size--;
        return removeNode.e;
    }

    @Override
    public E removeFirst() {
        return remove(0);
    }

    @Override
    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Empty";
        }

        StringBuilder res = new StringBuilder();
        Node cur = dummyHead.next;

        while (cur != null) {
            res.append(cur.e).append("->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}
