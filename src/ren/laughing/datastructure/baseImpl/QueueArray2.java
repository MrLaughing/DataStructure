package ren.laughing.datastructure.baseImpl;

import ren.laughing.datastructure.base.Queue;
import ren.laughing.datastructure.exception.QueueEmptyException;
/**
 * 队列Queue的顺序存储,此处使用循环队列，逆时针，方法二
 * ★循环队列：难点在于如何判断队空和队满
 * 方法一：★少使用一个存储空间，即：当队尾指针的下一指针指向队首指针时，就停止入队。
 * 判决：队空：rear=front,队满：(rear+1)%capacity = float
 * 方法二：★增设一个标志size，以size？=MAX区别队满队空
 * 判决：队空：size = 0,队满：size = capacity
 * @author Laughing_Lz
 * @time 2016年4月7日
 */
public class QueueArray2 implements Queue{
	private static final int CAP=8;//队列默认容量大小
	private Object[] elements;
	private int capacity;//数组的实际大小 elements.length
	private int size;//队列容量（空/满判断标志）
	private int front;
	private int rear;
	
	public QueueArray2() {
		this(CAP);
	}
	
	public QueueArray2(int cap) {
		this.elements = new Object[capacity];
		this.capacity = cap;//此处cap = capacity
		this.size = 0;//队列初始为空
		this.front = 0;
		this.rear = 0;
	}
	//获取队列的容量
	@Override
	public int getSize() {
		if(size == capacity){
			return capacity;
		}else{
			return (rear-front+capacity)%capacity;
		}
	}
	//判断是否队空
	@Override
	public boolean isEmpty() {
		if(rear == front&&size !=capacity){
			return true;
		}else{
			return false;
		}
	}
	//入队：相当于在insertAfter队尾
	@Override
	public void enqueue(Object e) {
		if(size  == capacity){//判断是否队满
			expandSpace();//扩充队列的容量
		}
		elements[rear] = e;
		rear = (rear+1)%capacity;//rear有可能从capacity-1移动到0
		size++;
	}
	//出队：相当于在remove队首
	@Override
	public Object dequeue() throws QueueEmptyException {
		if(rear == front&&size != capacity){//判断是否队空 (此时size为0)
			throw new QueueEmptyException("错误：队列已空");
		}
		Object obj = elements[front];
		elements[front] = null;//置空
		front = (front+1)%capacity;//front有可能从capacity-1移动到0
		size--;
		return obj;
	}
	//获取队首数据元素
	@Override
	public Object peek() throws QueueEmptyException {
		if(rear == front&&size != capacity){//判断是否队空 (此时size为0)
			throw new QueueEmptyException("错误：队列已空");
		}
		return elements[front];
	}
	/**
	 * ★扩充数组长度
	 */
	private void expandSpace() {
		Object[] a = new Object[elements.length * 2];
		int i = front;
		int j = 0;
		do{//将从 front 开始到 rear 前一个存储单元的元素复制到新数组
			a[j++] = elements[i];
			i = (i+1)%capacity;
		}while(i!=rear);//★
		elements = a;
		capacity = elements.length;
		front  = 0;
		rear = j;//重新设置新的队首队尾指针
	}
}

