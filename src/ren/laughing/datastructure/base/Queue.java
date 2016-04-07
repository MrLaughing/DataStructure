package ren.laughing.datastructure.base;

import ren.laughing.datastructure.exception.QueueEmptyException;

/**
 * 队列Queue的ADT:先进先出
 * 在队列中把插入数据元素的一端称为 队尾(rear), 删除数据元素
 * 的一端称为 队首(front)。向队尾插入元素称为 进队或入队，
 * 新元素入队后成为新的队尾元素；从队列中删除元素称为 离队或出队，
 * 元素出队后，其后续元素成为新的队首元素。
 * @author Laughing_Lz
 * @time 2016年4月7日
 */
public interface Queue {
	//返回队列的大小
	public int getSize();
	//判断队列是否为空
	public boolean isEmpty();
	//数据元素 e 入队
	public void enqueue(Object e);
	//队首元素出队
	public Object dequeue() throws QueueEmptyException;
	//取队首元素
	public Object peek() throws QueueEmptyException;
	}