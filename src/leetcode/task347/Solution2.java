package leetcode.task347;

import java.util.Comparator;
import java.util.PriorityQueue;

import java.util.LinkedList;
import java.util.TreeMap;

/**
 * 347. 前 K 个高频元素
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 * <p>
 * 使用Java的PriorityQueue，内部是最小堆
 */
public class Solution2 {

    // 定义可比较的类型
    private class Freq {
        int e, freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }
    }

    // 实现比较器
    private class FreqComparator implements Comparator<Freq> {
        @Override
        public int compare(Freq a, Freq b) {
            return a.freq - b.freq;
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
        PriorityQueue<Freq> pq = new PriorityQueue<>(new FreqComparator());  // 接受比较器
        // 构造优先队列
        for (int key : map.keySet()) {
            Freq newElement = new Freq(key, map.get(key));
            // 先入队 k 个元素
            if (pq.size() < k) {
                pq.add(newElement);
            } else {
                // k 之后的元素会跟队首元素比较，若频次小于队首元素，则替换
                Freq top = pq.peek();
                // 此处比较的是优先级的大小
                if (newElement.freq > top.freq) {
                    pq.remove(); // 队首出队
                    pq.add(newElement);  // 入队新的频次较小的元素
                }
            }
        }

        // 返回数组
        LinkedList<Integer> list = new LinkedList<>();
        while (!pq.isEmpty()) {
            list.add(pq.remove().e);
        }

        // 转换成int[]返回
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
