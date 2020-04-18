package recursive;

public class Sum {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        System.out.println(sum(nums));
    }

    public static int sum(int[] arr) {
        return getSum(arr, 0);
    }

    private static int getSum(int[] arr, int start) {
        if (start == arr.length) {
            return 0;
        }
        return arr[start] + getSum(arr, start + 1);
    }
}