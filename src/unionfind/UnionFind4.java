package unionfind;

// 第四版，基于rank来执行合并
public class UnionFind4 implements UF {
    private int[] parent;
    private int[] rank; // rank[i] 表示以i为根的树高度

    public UnionFind4(int size) {
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

        // 指向自己的是根节点
        while (p != parent[p]) {
            p = parent[p];  // 不停向上找
        }
        return p;
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
