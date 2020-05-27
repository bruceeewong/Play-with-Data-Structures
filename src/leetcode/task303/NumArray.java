package leetcode.task303;

import Tree.SegmentTree;

/**
 * 303 区域与检索 - 不可变
 * <p>
 * https://leetcode-cn.com/problems/range-sum-query-immutable/
 */
public class NumArray {
    private SegmentTree<Integer> segmentTree;

    public NumArray(int[] nums) {
        if (nums.length > 0) {
            // int[] 不能直接转换成 Integer[]
            Integer[] data = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                data[i] = nums[i];
            }

            segmentTree = new SegmentTree<>(data, ((a, b) -> a + b));
        }
    }

    public int sumRange(int i, int j) {
        if (segmentTree == null) {
            throw new IllegalArgumentException("Segment Tree is null");
        }
        return segmentTree.query(i, j);
    }
}
