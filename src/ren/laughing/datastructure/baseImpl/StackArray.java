package ren.laughing.datastructure.baseImpl;

import ren.laughing.datastructure.base.Stack;
import ren.laughing.datastructure.exception.StackEmptyException;
/**
 * 栈的顺序存储结构
 * 一般来说在构造堆栈时不应设定堆栈的最大容量。
 * 一种合理的做法为先为堆栈分配一个基本容量，然后在实际的使用过程中，
 * 当堆栈的空间不够时再倍增存储空间，这个过程所需的时间均摊到每个数
 * 据元素时间为Θ(1)，不会影响操作实现的时间复杂度。
 * @author Laughing_Lz
 * @time 2016年4月6日
 */
public class StackArray implements Stack {
	private final int LEN = 8;//默认数组的存储大小
	private Object[] elements;//数据元素数组
	private int top;//栈顶指针
	
	public StackArray() {
		this.elements = new Object[LEN];
		this.top = -1;//top为-1时表示空栈
	}

	@Override
	public int getSize() {
		return top+1;
	}

	@Override
	public boolean isEmpty() {
		if(top < 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void push(Object e) {
		if(getSize()>=elements.length){
			expandSpace();
		}
		//++top:因为入栈操作相当于insertAfter，
		//只能在顶点后插入，所以首先将top加1，再放入数据元素
		elements[++top] = e;
	}

	@Override
	public Object pop() throws StackEmptyException {
		if(top<0){
			throw new StackEmptyException("错误：栈为空，不可出栈操作");
		}else{
			Object obj = elements[top];
			elements[top--] = null;//先取出原栈顶数据元素，再置空、top减一
			return obj;
		}
	}
	//取栈顶元素
	@Override
	public Object peek() throws StackEmptyException {
		if(getSize()<=0){
			throw new StackEmptyException("错误：栈顶为空");
		}else{
			return elements[top];
		}
	}
	/**
	 * 扩充数组长度
	 */
	private void expandSpace() {
		Object[] a = new Object[elements.length * 2];
		for (int i = 0; i < elements.length; i++)
			a[i] = elements[i];
		elements = a;
	}
}
