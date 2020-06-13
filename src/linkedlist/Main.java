package linkedlist;

public class Main {
    public static void main(String[] args) {
        MyLinkedList1<Integer> myLinkedList1 = new MyLinkedList1<>();
        testLinkedList(myLinkedList1);
    }

    private static void testLinkedList(ILinkedList<Integer> linkedList) {
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
}
