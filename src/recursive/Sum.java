package recursive;

public class Sum {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println(sum(nums));
    }

    public static int sum(int[] arr) {
        return sum(arr, 0);
    }

    private static int sum(int[] arr, int start) {
        if (start == arr.length) {
            return 0; // 最小问题
        }
        return arr[start] + sum(arr, start + 1); // 转换更小问题的逻辑
    }
}