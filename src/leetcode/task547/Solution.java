package leetcode.task547;

import java.util.TreeSet;

/**
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
 * <p>
 * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/friend-circles
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {
    public static void main(String[] args) {
//        int[][] M = {{1,1,0}, {1,1,0}, {0,0,1}};
        int[][] M = {{1,1,0}, {1,1,1}, {0,1,1}};
        Solution s = new Solution();
        System.out.println(s.findCircleNum(M));
    }

    private class UnionFind {
        private int[] parent;

        public UnionFind(int size) {
            parent = new int[size];

            for (int i = 0; i < size; i ++) {
                parent[i] = i;
            }
        }

        private int find(int p) {
            if (p < 0 || p >= parent.length) {
                throw new IllegalArgumentException("param out of range");
            }
            while (p != parent[p]) {
                p = parent[p];
            }
            return p;
        }

        public void unionElements(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);

            if (pRoot == qRoot) {
                return;
            }

            // 将所有
            parent[pRoot] = qRoot;
        }

        public int getUnionSize() {
            TreeSet<Integer> set = new TreeSet<>();
            for (int i = 0; i < parent.length; i ++) {
                int root = find(parent[i]);
                set.add(root);
            }
            return set.size();
        }
    }


    public int findCircleNum(int[][] M) {
        int size = M.length;
        UnionFind uf = new UnionFind(size);

        for (int i = 0; i < size - 1; i ++) {
            for (int j = i + 1; j < size; j ++) {
                int isFriend = M[i][j];
                if (isFriend == 1) {
                    uf.unionElements(i, j);
                }
            }
        }
        return uf.getUnionSize();
    }
}