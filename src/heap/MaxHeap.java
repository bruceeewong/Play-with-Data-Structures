package heap;


import array.Array;

// 最大堆
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    // 返回堆中元素个数
    public int size() {
        return data.getSize();
    }

    // 返回一个布尔值，表示堆中是否为空
    public boolean isEmpty() {
        return data.isEmpty();
    }

    // 返回index对应的父节点位置
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return (index - 1) / 2;
    }

    // 返回index对应的左子节点位置
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    // 返回index对应的右子节点位置
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    // 向堆中添加元素
    public void add(E e) {
        data.addLast(e);
        int index = data.getSize() - 1; // 新插入的元素的下标
        siftUp(index);
    }

    private int compareToParent(int index) {
        E currentElement = data.get(index);
        E parentElement = data.get(parent(index));
        return currentElement.compareTo(parentElement);
    }

    // 元素上浮的操作，即不停与其父节点比较，若当前节点大于父节点，则交换位置
    private void siftUp(int index) {
        while (index > 0 && compareToParent(index) > 0) {
            data.swap(index, parent(index));
            index = parent(index);
        }
    }

    public E findMax() {
        if (data.getSize() == 0) {
            throw new IllegalArgumentException("Can not findMax when heap is empty");
        }
        return data.get(0);
    }

    // 取出最大元素，
    public E extractMax() {
        E ret = findMax();

        // 为避免融合两个堆，采用将最末尾的节点放到根处，然后执行下沉操作维护堆的性质
        data.swap(0, data.getSize() - 1);
        data.removeLast(); // 删掉最末尾元素最大值
        siftDown(0);

        return ret;
    }

    private int getMaxChildIndex(int index) {
        if (leftChild(index) >= data.getSize()) {
            throw new IllegalArgumentException("Index is illegal: Can not get the child index of a leaf node!");
        }

        // 若存在右子节点，比较左右节点大小
        if (rightChild(index) < data.getSize()) {
            E leftChildElement = data.get(leftChild(index));
            E rightChildElement = data.get(rightChild(index));

            if (rightChildElement.compareTo(leftChildElement) > 0) {
                return rightChild(index);
            }
        }

        return leftChild(index);
    }

    // 下沉操作
    private void siftDown(int index) {
        // 左孩子索引小于数组大小时，保证不越界
        while (leftChild(index) < data.getSize()) {
            int maxChildIndex = getMaxChildIndex(index);
            E currentElement = data.get(index);
            E maxChildElement = data.get(maxChildIndex);

            // 如果当前节点比最大子节点相等或大于，及退出完成下沉操作
            if (currentElement.compareTo(maxChildElement) >= 0) {
                break;
            }

            data.swap(index, maxChildIndex);
            index = maxChildIndex;
        }
    }
}
