package leetcode.task303;

/**
 * 303 区域与检索 - 不可变
 * <p>
 * https://leetcode-cn.com/problems/range-sum-query-immutable/
 * <p>
 * 不使用线段树的方法
 */
public class NumArray2 {
    private int[] sum;  // sum[i] 存储的是前 i 个元素的和

    public NumArray2(int[] nums) {
        sum = new int[nums.length + 1]; // 因为 sum[0] 代表 0, sum[1] 才是第一个元素的值
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];  // 累加
        }
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];  // j + 1代表[0, j]的和， i代表前[0...i)的和, 相减即[i,j]的和
    }
}
