# 《玩转数据结构》 笔记

作者： bruski

时间： 2020/03/23

## 简单时间复杂度分析

大O描述的是算法的运行时间与输入数据之间的关系。

- O(1)
- O(n): 线性关系
- O(n^2)
- ...

为什么要用大O，叫做O(n)? 

大O描述的是渐进时间复杂度，即 n 趋近于无穷的情况，所以可以忽略低阶项、常数。

```
T = 2*n + 1   O(n)
T = 2000 * n + 10000   O(n)
T = 1 * n * n + 0  O(n^2)
T = 1 * n * n + 300 * n + 100  O(n^2)
```

## 02 数组

数组主要用于“索引有语义”的情况，如第几个xx。

基于Java的数组，封装Array类，增加 增删改查 功能

类内部自行维护数组与状态

- 实现头部、尾部插入，实现任意位置插入
- 支持指定位置删除、删除头部、删除尾部
- 判断是否存在元素
- 查找元素下标
- 泛型支持
    - 外部实例化, ```Array<T> arr = new Array<>();``
    - 内部实现 ```E[] data = (E[]) new Object[capacity]```
- 动态数组：动态开辟空间
    - 创建更大空间的数组，内部data改变引用
    - 插入元素时，如果数组满了，扩容为原来的2倍
    - 删除元素时，如果元素内存在的数量等于当前容量的一半，就缩容回原来的一半。
    
### 数组的方法时间复杂度分析

#### 添加操作

时间复杂度： O(n)

```
addLast(e) -> O(1)                |
addFirst(e) -> O(n)               | 最坏情况：O(n)
add(index, e) -> O(n/2) = O(n)    |
```

#### 删除操作

时间复杂度： O(n)

```
removeLast(e) -> O(1)                |
removeFirst(e) -> O(n)               | 最坏情况：O(n)
remove(index, e) -> O(n/2) = O(n)    |
```

#### 修改操作

平均时间复杂度： O(n) 

已知索引(最好)： O(1)
未知索引(最坏)： O(n)

```
set(index, e) 
```

#### 查询操作

平均时间复杂度： O(n) 

已知索引(最好)： O(1)
未知索引(最坏)： O(n)

```
get(index) -> O(1)
contains(index) -> O(n)
find(index) -> O(n)
```

#### resize 时间复杂度分析

```
addLast(e) -> O(1)                | 最好情况：O(1) 最坏情况：O(n)  resize: O(n)
addFirst(e) -> O(n)               | O(n)
add(index, e) -> O(n/2) = O(n)    | O(n)
```

##### 均摊复杂度

对于addLast方法，只有超过了数组容量才会触发扩容操作

假设capacity = n, n + 1 次 addLast, 触发resize, 总共进行 2n + 1 次基本操作

用**均摊复杂度（amortized time complexity）**的概念 -> 平均每次 addLast 操作，进行 2次基本操作

则 **addLast 的均摊复杂度为 O(1)**!

##### 复杂度震荡

同时看 addLast 与 resizeLast ，在满的时候，每次操作都会触发 resize, 复杂度突增为 O(n);

因为删除的缩容resize过于着急(Eager)

解决方案: 采用更懒惰的方案Lazy： 当 size == capacity / 4 时，才将capacity减半； 同时要防止capacity缩为 0

## 03 栈与队列

栈: 数组的子集，一种后进先出 Last In First Out (LIFO)的结构，只能从一端添加，从同一端取出，这个端称为 `栈顶`。

### 栈的应用

撤销操作Undo: 原理就是用栈存储输入，撤销时最近的输入出栈。

程序调用的系统栈：用栈记录系统调用的中断位置，从而能完成函数嵌套调用。

### 复杂度分析

ArrayStack<E> 基于动态数组的栈

- void push(E) -> O(1) 均摊
- E pop() ->  O(1) 均摊
- E peek() ->  O(1)
- int getSize() ->  O(1)
- boolean isEmpty() ->  O(1)

### 算法题：括号匹配

需要判断一组符号是否正确匹配且闭合 如

```
() -> true
{(]} -> false
```

思路： 使用栈结构，遍历输入的字符串，逐一取字符，如果是左括号`([{` 入栈，直到遇到右括号 `)]}` 出栈比对：

- 如果遇到左括号而栈里为空，说明不是左括号打头，不构成包裹，返回false；
- 如果括号类型不匹配，返回false；
- 否则继续遍历；

如果遍历结束：

- 栈里还不为空，说明没匹配完，返回false；
- 否则，说明匹配成功，返回true

### 3-5 队列

队列 FIFO 先进先出

接口
```
public interface Queue<E> {
    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();
}
```

#### 数组队列

```
public public class ArrayQueue<E> implements Queue<E> {
    int getSize(); // 时间复杂度: O(1)

    boolean isEmpty(); // 时间复杂度: O(1)

    void enqueue(E e); // 时间复杂度 O(1) 均摊

    E dequeue(); // 时间复杂度： O(n)

    E getFront(); // 时间复杂度 O(1)
}
```

#### 循环队列

基于数组，维护 front 和 tail 指针

front == tail 队列为空
(tail + 1) % size == front 队列满

因为队列满和队列空条件要区别，所以会浪费一个空间

```
public class LoopQueue<E> implements Queue<E> {
    int getSize(); // 时间复杂度: O(1)

    boolean isEmpty(); // 时间复杂度: O(1)

    void enqueue(E e); // 时间复杂度 O(1) 均摊

    E dequeue(); // 时间复杂度： O(1)均摊 // 根据tail指针直接取

    E getFront(); // 时间复杂度 O(1)
}
```

#### 数组队列与循环队列

100000次出队操作, 循环队列 26s 数组队列 0.1s

## 4 链表

动态数据结构，不支持随机访问，不适合索引有语义的情况

```
class Node {
    E e;
    Node next;
}
```

- 从链表头部添加数据
- 在链表中间添加元素: 关键找到要添加节点的前一个节点，顺序很重要

### 第一种实现

只设立尾结点，在插入头的时候，需要单独处理

### 设立虚拟头结点

头结点不存储数据，用于指示位置