package ren.laughing.datastructure.baseImpl;

import ren.laughing.datastructure.base.Stack;
import ren.laughing.datastructure.exception.StackEmptyException;
/**
 * Stack的链式存储
 * ★此链表为的不含头结点的单链表
 * @author Laughing_Lz
 * @time 2016年4月6日
 */
public class StackLinked implements Stack{
	private SLNode top;//链表首结点引用
	private int size;//栈的大小
	
	public StackLinked() {
		this.size = 0;
//		this.top = new SLNode(null, null);
		top = null;//这个是否也实例化了呢？和上面是否有区别？
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}
	
	@Override
	public void push(Object e) {
		SLNode node = new SLNode(e, top);//相当于insertBefore 在原栈顶前插入新的数据元素
		top = node;
		size++;
	}

	@Override
	public Object pop() throws StackEmptyException {
		if (size<=0){
			throw new StackEmptyException("错误，堆栈为空。");
		}
		Object obj = top.getData();
		top = top.getNext();
		size--;
		return obj;
	}

	@Override
	public Object peek() throws StackEmptyException {
		if (size<=0){
			throw new StackEmptyException("错误，堆栈为空。");
		}
		Object obj = top.getData();
		return obj;
	}

}
