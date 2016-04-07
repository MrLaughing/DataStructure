package ren.laughing.datastructure.baseImpl;

import ren.laughing.datastructure.base.Queue;
import ren.laughing.datastructure.exception.QueueEmptyException;
/**
 * 队列的链式存储
 * 单链表、含有队首和队尾指针
 * @author Laughing_Lz
 * @time 2016年4月7日
 */
public class QueueLinked implements Queue {
	private SLNode front;//队首指针，指向链表空的头结点
	private SLNode rear;//队尾指针，指向链表的队尾元素
	private int size;//队的容量
	
	public QueueLinked() {
		front = new SLNode();
		rear = front;//队列初始为空，队首指针和队尾指针均指向空的头结点
		size = 0;
	}
	
	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	//入队：从队尾入队,相当于insertAfter队尾结点rear
	@Override
	public void enqueue(Object e) {
		SLNode node =new SLNode(e, null);//写出rear.getNext()应该也可以？
		rear.setNext(node);
		rear = node;
		size++;
	}
	//出队：从队首出队，相当于removeAfter队首头结点front
	@Override
	public Object dequeue() throws QueueEmptyException {
		if(size <= 0){
			throw new QueueEmptyException("错误：队列为空");
		}
		SLNode node = front.getNext();//要删除的队首数据元素
		front.setNext(node.getNext());
		size--;
		if(size <= 0){//★再次判断size，若已全部出队，则将rear指针也和front指针一样指向空的头结点
			rear = front;
		}
		return node.getData();
	}
	
	@Override
	public Object peek() throws QueueEmptyException {
		if(size <= 0){
			throw new QueueEmptyException("错误：队列为空");
		}
		SLNode node = front.getNext();
		return node.getData();
	}

}
