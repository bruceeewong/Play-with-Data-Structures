package leetcode.task347;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 347. 前 K 个高频元素
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 * <p>
 * 使用Java的PriorityQueue，内部是最小堆, 使用匿名比较器
 */
public class Solution3 {
    public int[] topKFrequent(int[] nums, int k) {
        // 1. 统计频次
        // 使用TreeMap，键为元素，值为频次
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1); // 自增频次
            } else {
                map.put(num, 1); // 第一次见, 加入map中
            }
        }

        // 2. 求出频率前K高的元素
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer num1, Integer num2) {
                return map.get(num1) - map.get(num2);  // 内部类，可以使用成员变量定义队列的比较
            }
        });

        // 构造优先队列
        for (int key : map.keySet()) {
            // 先入队 k 个元素
            if (pq.size() < k) {
                pq.add(key);
            } else {
                // k 之后的元素会跟队首元素比较，若频次小于队首元素，则替换
                // 此处比较的是优先级的大小
                if (map.get(key) > map.get(pq.peek())) {
                    pq.remove(); // 队首出队
                    pq.add(key);  // 入队新的频次较小的元素
                }
            }
        }

        // 返回数组
        LinkedList<Integer> list = new LinkedList<>();
        while (!pq.isEmpty()) {
            list.add(pq.remove());
        }

        // 转换成int[]返回
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
