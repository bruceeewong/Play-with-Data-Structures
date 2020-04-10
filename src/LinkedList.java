public class LinkedList<E> {

    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 链表头添加数据
     * @param e {E}
     */
    public void addFirst(E e) {
//        Node node = new Node(e);
//        node.next = head;
//        head = node;
         // 或者用下面一句话
        head = new Node(e, head);

        size ++;
    }

    /**
     * 插入任意位置
     * @param e {E}
     * @param index {int}
     */
    public void add(E e, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        if (index == 0) {
            addFirst(e);
            return;
        }
        Node prev = head;
        // 移动 prev 指针到指定位置的前面一个
        for (int i = 0; i < index - 1; i ++) {
            prev = prev.next;
        }
        prev.next = new Node(e, prev.next);
        size ++;
    }
}
