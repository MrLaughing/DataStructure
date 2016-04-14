package ren.laughing.datastructure.baseImpl;

import ren.laughing.datastructure.base.Queue;
import ren.laughing.datastructure.exception.QueueEmptyException;
/**
 * 队列Queue的顺序存储,此处使用循环队列，逆时针，方法一
 * ★循环队列：难点在于如何判断队空和队满
 * 方法一：★少使用一个存储空间，即：当队尾指针的下一指针指向队首指针时，就停止入队。
 * 判决：队空：rear=front,队满：(rear+1)%capacity = float
 * 方法二：★增设一个标志size，以size？=MAX区别队满队空
 * 判决：队空：size = 0,队满：size = capacity
 * @author Laughing_Lz
 * @time 2016年4月7日
 */
public class QueueArray implements Queue{
	private static final int CAP=7;//队列默认容量大小
	private Object[] elements;
	private int capacity;//数组的实际大小 elements.length
	private int front;
	private int rear;
	
	public QueueArray() {
		this(CAP);
	}
	public QueueArray(int cap) {
		this.capacity = cap+1;//这里数组实际大小capacity比队列容量cap大1
		this.elements = new Object[capacity];
		this.front = 0;
		this.rear = 0;
	}
	//获取队列的容量
	@Override
	public int getSize() {
		return (rear-front+capacity)%capacity;//返回队列的实际容量，小于等于capacity-1
	}
	//判断是否队空
	@Override
	public boolean isEmpty() {
		if(rear == front){
			return true;
		}else{
			return false;
		}
	}
	//入队：相当于在insertAfter队尾
	@Override
	public void enqueue(Object e) {
//		if(getSize()==capacity-1){//同下，判断队列容量是否已满
		if((rear+1)%capacity == front){//判断是否队满
			expandSpace();//扩充队列的容量
		}
		elements[rear] = e;
		rear = (rear+1)%capacity;//rear有可能从capacity-1移动到0
	}
	//出队：相当于在remove队首
	@Override
	public Object dequeue() throws QueueEmptyException {
		if(rear == front){//判断是否队空
			throw new QueueEmptyException("错误：队列已空");
		}
		Object obj = elements[front];
		elements[front] = null;//置空
		front = (front+1)%capacity;//front有可能从capacity-1移动到0
		return obj;
	}
	//获取队首数据元素
	@Override
	public Object peek() throws QueueEmptyException {
		if(rear == front){//判断是否队空
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
		while(i!=rear){//将从 front 开始到 rear 前一个存储单元的元素复制到新数组
			a[j++] = elements[i];
			i = (i+1)%capacity;
		}
		elements = a;
		capacity = elements.length;
		front  = 0;
		rear = j;//重新设置新的队首队尾指针
	}
}
