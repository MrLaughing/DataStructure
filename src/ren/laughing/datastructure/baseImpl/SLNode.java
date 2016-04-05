package ren.laughing.datastructure.baseImpl;

import ren.laughing.datastructure.base.Node;
/**
 * 单链表结点的定义
 * @author Laughing_Lz
 * @time 2016年4月5日
 */
public class SLNode implements Node{
	private Object element;//数据元素data
	private SLNode next;//指向下一结点的指针
	
	public SLNode() {
		this(null,null);
	}

	public SLNode(Object element, SLNode next) {
		this.element = element;
		this.next = next;
	}

	public SLNode getNext() {
		return next;
	}

	public void setNext(SLNode next) {
		this.next = next;
	}
	
	@Override
	public Object getData() {
		return this.element;
	}

	@Override
	public void setData(Object obj) {
		this.element=obj;
	}

}
