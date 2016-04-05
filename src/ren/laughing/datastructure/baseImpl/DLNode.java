package ren.laughing.datastructure.baseImpl;

import ren.laughing.datastructure.base.Node;
/**
 * 双向链表的结点定义
 * @author Laughing_Lz
 * @time 2016年4月5日
 */
public class DLNode implements Node {
	private Object element;
	private DLNode pre;
	private DLNode next;

	public DLNode() {
		this(null,null,null);
	}
	public DLNode(Object element, DLNode pre, DLNode next) {
		this.element = element;
		this.pre = pre;
		this.next = next;
	}
	public DLNode getPre() {
		return pre;
	}
	public void setPre(DLNode pre) {
		this.pre = pre;
	}
	public DLNode getNext() {
		return next;
	}
	public void setNext(DLNode next) {
		this.next = next;
	}
	@Override
	public Object getData() {
		return this.element;
	}
	@Override
	public void setData(Object obj) {
		this.element = obj;
	}
	
	
}
