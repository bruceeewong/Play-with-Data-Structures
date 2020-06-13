package linkedlist;

public class TestLinkedList {
    public static void main(String[] args) {
        MyLinkedList1<Integer> myLinkedList1 = new MyLinkedList1<>();
        testLinkedList(myLinkedList1);

        System.out.println("==========================");

        MyLinkedList2<Integer> myLinkedList2 = new MyLinkedList2<>();
        testLinkedList(myLinkedList2);
    }

    private static void testLinkedList(ILinkedList<Integer> linkedList) {
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.add(4, 666);
        print(linkedList);

        linkedList.remove(4);
        print(linkedList);

        linkedList.removeFirst();
        print(linkedList);

        linkedList.removeLast();
        print(linkedList);
    }

    private static void print(ILinkedList<Integer> linkedList) {
        System.out.println(linkedList);
        System.out.println("size: " + linkedList.getSize());

    }
}
