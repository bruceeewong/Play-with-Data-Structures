package stack;

public class TestMyArrayStack {
    public static void main(String[] args) {

        MyArrayStack<Integer> stack = new MyArrayStack<>();

        for(int i = 0 ; i < 5 ; i ++){
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }
}
