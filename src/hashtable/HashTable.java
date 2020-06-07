package hashtable;

import map.Map;

import java.util.TreeMap;

// Bug: 底层是 TreeMap, K 要求是可比较的, 若传入不可比较的类型回报错
// 解决方案:学习Java8的实现,冲突小时采用链表,冲突大时,检查键是否可比较,如果是,则转为红黑树
public class HashTable<K, V> implements Map<K, V> {
    private final int[] capacity = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};

    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    private int capacityIndex = 0;

    private TreeMap<K, V>[] hashtable;
    private int M;
    private int size;

    public HashTable() {
        this.M = capacity[capacityIndex];
        size = 0;
        hashtable = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }

    // 哈希函数, 重要
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;  // 转换为索引
    }

    public void add(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, value);
        } else {
            map.put(key, value);
            size++;

            // 如果每个树平均存放数(size / M) 多于 上部阈值 upperTol, 执行扩容操作
            if (size >= upperTol * M && capacityIndex + 1 < capacity.length) {
                capacityIndex += 1;
                resize(capacity[capacityIndex]);
            }
        }
    }

    public V remove(K key) {
        TreeMap<K, V> map = hashtable[hash(key)];
        V ret = null;
        if (map.containsKey(key)) {
            ret = map.remove(key);
            size--;

            // 如果每个树平均存放数(size / M) 少于 下部阈值 lowerTol, 执行缩容操作
            if (size < lowerTol * M && capacityIndex - 1 >= 0) {
                capacityIndex -= 1;
                resize(capacity[capacityIndex]);
            }
        }
        return ret;
    }


    private void resize(int capacity) {
        TreeMap<K, V>[] newHashTable = new TreeMap[capacity];
        for (int i = 0; i < capacity; i++) {
            newHashTable[i] = new TreeMap<>();
        }

        int oldM = M;
        this.M = capacity;  // 注意内部 hash依赖此 M 的值

        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashtable[i];

            for (K key : map.keySet()) {
                // 重新计算HashCode, 放入对应位置的TreeMap中
                newHashTable[hash(key)].put(key, map.get(key));
            }
        }

        this.hashtable = newHashTable;
    }

    public void set(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException(key + " does not exist!");
        }
        map.put(key, value);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(K key) {
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key) {
        return hashtable[hash(key)].get(key);
    }
}
