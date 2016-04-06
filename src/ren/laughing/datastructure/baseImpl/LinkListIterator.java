package ren.laughing.datastructure.baseImpl;

import ren.laughing.datastructure.base.Iterator;
import ren.laughing.datastructure.base.LinkedList;
import ren.laughing.datastructure.base.Node;
import ren.laughing.datastructure.exception.OutOfBoundaryException;
/**
 * 对于链接表的迭代器的实现
 * @author Laughing_Lz
 * @time 2016年4月6日
 */
public class LinkListIterator implements Iterator{
	private LinkedList linkedList;//链接表
	private Node current;//当前结点
	
	public LinkListIterator(LinkedList linkedList) {
		this.linkedList = linkedList;
		if(linkedList.isEmpty()){//若当前链表为空
			current = null;//当前结点置空
		}else{
			current = linkedList.first();//否则从第一个数据元素开始
		}
	}

	@Override
	public void first() {
		if(linkedList.isEmpty()){
			current = null;
		}else{
			current = linkedList.first();
		}
	}

	@Override
	public void next() {
		if(isDone()){
			throw new OutOfBoundaryException("错误：已经没有未遍历的元素了");
		}else if(current == linkedList.last()){
			current = null;//已经到达最后一个数据元素
		}else{
			current= linkedList.getNext(current);
		}
	}
	//检查迭代器中是否还有剩余的元素
	@Override
	public boolean isDone() {
		if(current == null){
			return true;
		}else{
			return false;
		}
	}
	//返回当前元素
	@Override
	public Object currentItem() throws OutOfBoundaryException {
		if(isDone()){
			throw new OutOfBoundaryException("错误：已经没有未遍历的元素了");
		}
		return current.getData();
	}

}
