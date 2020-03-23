public class Main {
    public static void main(String[] args) {
        Array<Integer> arr = new Array(); // 默认有10个元素

        for (int i = 0; i < 11; i ++) {
            arr.addLast(i);
        }

        // 扩容
        arr.add(1, 100);
        System.out.println(arr);

        // 缩容
        arr.removeFirst();
        arr.removeFirst();
        System.out.println(arr);

    }
}
