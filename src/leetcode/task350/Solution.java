package leetcode.task350;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * 350. 两个数组的交集 II
 *
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 *
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 */
public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int num: nums1) {
            if (!map.containsKey(num)) {
                map.put(num, 1); // 记录频次
            } else {
                map.put(num, map.get(num) + 1);  // 更新
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int num: nums2) {
            if (map.containsKey(num)) {
                list.add(num);
                map.put(num, map.get(num) - 1);
                if (map.get(num) == 0) {
                    map.remove(num);
                }
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i ++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
