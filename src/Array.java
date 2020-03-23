public class Array {
    private int[] data;
    private int size;

    // 构造函数，传入数组的容量capacity构造Array
    public Array(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    // 无参数的构造类型，默认数组的容量capacity = 10
    public Array() {
        this(10);
    }

    // 获取数组元素个数
    public int getSize() {
        return size;
    }

    // 获取数组容量
    public int getCapacity() {
        return data.length;
    }

    // 判断是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 在 index 位置出 插入一个元素 e
    public void add(int index, int e) {
        if (size == data.length) {
            throw new IllegalArgumentException("Add failed. Array is full.");
        }
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Require index >=0 and index <= size");
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = e;
        size++;
    }

    public void addLast(int e) {
        add(size, e);
    }

    public void addFirst(int e) {
        add(0, e);
    }

    // 获取index索引位置的元素
    public int get(int index) {
        // 禁止访问没使用的空间
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is illegal");
        }
        return data[index];
    }

    // 赋值函数
    public void set(int index, int e) {
        // 禁止赋值没赋值的部分
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Index is illegal");
        }
        data[index] = e;
    }

    // 查找数组中是否有元素 e
    public boolean contain(int e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return true;
            }
        }
        return true;
    }


    // 查找数组中元素 e, 找到了返回元素下标，否则返回 -1
    public int find(int e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    // 删除元素，并返回删除的元素
    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal");
        }

        int ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        return ret;
    }

    // 删除数组的第一个元素
    public int removeFirst() {
        return remove(0);
    }

    // 删除数组的最后一个元素
    public int removeLast() {
        return remove(size - 1);
    }

    // 从数组中删除元素e, 如果有同样的值，只删除第一个
    public void removeElement(int e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    // 重写 打印方法
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }
}