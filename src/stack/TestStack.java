package stack;

public class TestStack {
    public static void main(String[] args) {
        MyArrayStack<Integer> arrayStack = new MyArrayStack<>();
        testStack(arrayStack);

        System.out.println("==================");

        LinkedListStack<Integer> linkedListStack1 = new LinkedListStack<>();
        testStack(linkedListStack1);

        System.out.println("==================");

        MyLinkedListStack<Integer> linkedListStack2 = new MyLinkedListStack<>();
        testStack(linkedListStack2);
    }

    private static void testStack(IStack<Integer> stack) {
        for(int i = 0 ; i < 5 ; i ++){
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }
}
