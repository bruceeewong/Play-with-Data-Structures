package linkedlist;

/**
 * 只是用尾指针实现
 */
public class MyLinkedList1<E> implements ILinkedList<E> {
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

    private Node head;
    private int size;

    public MyLinkedList1() {
        head = null;
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
            throw new IllegalArgumentException("illegal index");
        }

        Node newNode = new Node(e);
        if (index == 0) {
            // 插入头节点,将创建的节点指向当前头节点,然后再改变头节点的指向指到新增节点
            newNode.next = head;
            head = newNode;
        } else {
            // 插入普通位置,先找到前一个节点
            Node cur = findPreviousNode(index);
            newNode.next = cur.next;
            cur.next = newNode;
        }
        size++;
    }

    private Node findPreviousNode(int index) {
        if (index <= 0 || index >= size) {
            throw new IllegalArgumentException("Illegal index");
        }
        Node cur = head;
        for (int i = 0; i < index - 1; i++) {
            cur = cur.next;
        }
        return cur;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Illegal index");
        }
        Node cur = head;
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
        return (getNode(index)).e;
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
        Node cur = getNode(index);
        cur.e = e;
    }

    public Node find(E e) {
        Node cur = head;
        while (cur != null) {
            if (e.equals(cur.e)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    @Override
    public boolean contains(E e) {
        return find(e) != null;
    }

    @Override
    public E remove(int index) {
        if (isEmpty()) {
            throw new IllegalArgumentException("remove failed. linkedlist is empty.");
        }
        Node removeNode;
        if (index == 0) {
            // 删除头节点
            removeNode = head;
            head = removeNode.next;
        } else {
            // 找到前一个节点
            Node previousNode = findPreviousNode(index);
            removeNode = previousNode.next;

            previousNode.next = removeNode.next;
        }
        removeNode.next = null; // 断开与链表的连接
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
        if (isEmpty()) { return "Empty"; }

        StringBuilder res = new StringBuilder();
        Node cur = head;
        while (cur != null) {
            res.append(cur.e).append("->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}
