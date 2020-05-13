package leetcode.task347;

import heap.PriorityQueue;

import java.util.LinkedList;
import java.util.TreeMap;

/**
 * 347. 前 K 个高频元素
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *  
 * 提示：
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * 你可以按任意顺序返回答案。
 */
public class Solution {

    // 定义可比较的类型
    private class Freq implements Comparable<Freq> {
        int e, freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq another) {
            // 频次越低，优先级越高
            if (freq < another.freq)
                return 1;
            if (freq > another.freq)
                return -1;
            return 0;
        }
    }

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
        PriorityQueue<Freq> pq = new PriorityQueue<>();
        // 构造优先队列
        for (int key : map.keySet()) {
            Freq freq = new Freq(key, map.get(key));
            // 先入队 k 个元素
            if (pq.getSize() < k) {
                pq.enqueue(freq);
            } else {
                // k 之后的元素会跟队首元素比较，若频次小于队首元素，则替换
                Freq top = pq.getFront();
                if (freq.compareTo(top) < 0) {
                    pq.dequeue(); // 队首出队
                    pq.enqueue(freq);  // 入队新的频次较小的元素
                }
            }
        }

        // 返回数组
        LinkedList<Integer> list = new LinkedList<>();
        while (!pq.isEmpty()) {
            list.add(pq.dequeue().e);
        }

        // 转换成int[]返回
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i ++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
