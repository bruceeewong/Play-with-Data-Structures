package heap;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int n = 1000000;

        MaxHeap<Integer> maxHeap = new MaxHeap<>();

        Random random = new Random();
        // 插入100w个随机数
        for (int i = 0; i < n; i ++) {
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }

        // 取出堆的顶部元素(最大值)
        int[] arr = new int[n];
        for (int i = 0; i < n; i ++) {
            arr[i] = maxHeap.extractMax();
        }

        // 构成的数组应该是降序排列的
        for (int i = 1; i < n; i ++) {
            if (arr[i - 1] < arr[i]) {
                throw new IllegalArgumentException("Error");
            }
        }

        System.out.println("Test MaxHeap completed.");
    }
}
