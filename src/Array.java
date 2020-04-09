public class Array<E> {
    private E[] data;
    private int size;

    // 构造函数，传入数组的容量capacity构造Array
    public Array(int capacity) {
        data = (E[]) new Object[capacity]; // java 8 泛型的写法
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
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Require index >=0 and index <= size");
        }
        if (size == data.length) {
            // 动态扩容, 选择当前值的两倍
            resize(2 * data.length);
        }

        // 将要插入的位置后面的元素往后挪
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = e;
        size++;
    }

    public void addLast(E e) {
        add(size, e);
    }

    public void addFirst(E e) {
        add(0, e);
    }

    // 获取index索引位置的元素
    public E get(int index) {
        // 禁止访问没使用的空间
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is illegal");
        }
        return data[index];
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    // 赋值函数
    public void set(int index, E e) {
        // 禁止赋值没赋值的部分
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Index is illegal");
        }
        data[index] = e;
    }

    // 查找数组中是否有元素 e
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return true;
    }


    // 查找数组中元素 e, 找到了返回元素下标，否则返回 -1
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    // 删除元素，并返回删除的元素
    // 并动态缩容
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal");
        }

        E ret = data[index];
        // 将要删除的位置后面的元素往前挪一个
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null; // 解除引用

        // 动态缩容
        // 采用懒惰策略： 当 size == capacity / 4 时，才将capacity减半
        // 防止缩容为0
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return ret;
    }

    // 删除数组的第一个元素
    public E removeFirst() {
        return remove(0);
    }

    // 删除数组的最后一个元素
    public E removeLast() {
        return remove(size - 1);
    }

    // 从数组中删除元素e, 如果有同样的值，只删除第一个
    public void removeElement(E e) {
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

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity]; // 扩容
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}