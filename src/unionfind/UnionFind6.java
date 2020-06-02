package unionfind;

// 第四版，基于rank来执行合并
public class UnionFind6 implements UF {
    private int[] parent;
    private int[] rank; // rank[i] 表示以i为根的树高度

    public UnionFind6(int size) {
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p is out of bound.");
        }

        // 宏观语义写法
        // 如果当前节点不是根节点
        if (p != parent[p]) {
            parent[p] = find(parent[p]); // 将当前节点直接指向根节点
        }
        return parent[p]; // 返回根节点

        // 微观语义
        // 如果当前节点不是根节点
//        if (p == parent[p]) {
//            return p;
//        }
//        parent[p] = find(parent[p]); // 将当前节点直接指向根节点
//        return parent[p]; // 返回根节点
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        // 比较该元素的子树高度
        // 小的接到大的上, 各自的高度都不变
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[qRoot] < rank[pRoot]) {
            parent[qRoot] = pRoot;
        } else {
            // rank[qRoot] == rank[pRoot]
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }
}
