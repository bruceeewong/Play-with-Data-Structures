package Tree;

public class Main {
    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};

        SegmentTree<Integer> segTree = new SegmentTree<>(nums, ((a, b) -> a + b));
//        System.out.println(segTree);

        int result = segTree.query(0, 2);// [0,2]所有元素的和
        System.out.println(result);

        result = segTree.query(3, 5);// [3,5]所有元素的和
        System.out.println(result);

        result = segTree.query(1, 4);// [1,4]所有元素的和
        System.out.println(result);
    }
}
