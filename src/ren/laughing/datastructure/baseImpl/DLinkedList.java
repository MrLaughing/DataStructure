package ren.laughing.datastructure.baseImpl;

import ren.laughing.datastructure.base.Iterator;
import ren.laughing.datastructure.base.LinkedList;
import ren.laughing.datastructure.base.Node;
import ren.laughing.datastructure.exception.InvalidNodeException;
import ren.laughing.datastructure.exception.OutOfBoundaryException;
/**
 * 链接表的实现：双向链表
 * @author Laughing_Lz
 * @time 2016年4月6日
 */
public class DLinkedList implements LinkedList{
	private DLNode head;//头结点
	private DLNode tail;//尾结点
	private int size;//规模
	
	public DLinkedList() {
		size=0;
		head = new DLNode();
		tail = new DLNode();
		head.setNext(tail);//初始化双链表时，首尾结点互指
		tail.setPre(head);
	}
	//辅助方法：检验结点p是否合法，如合法转换为DLNode
	public DLNode checkPosition(Node p) throws InvalidNodeException{
		if(p == null){
			throw new InvalidNodeException("错误：p为空");
		}else if(p==head){
			throw new InvalidNodeException("错误：p指向头结点，非法");
		}else if(p == tail){
			throw new InvalidNodeException("错误：p指向尾结点，非法");
		}else{
			DLNode node = (DLNode) p;
			return node;
		}
	}
	
	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		if(this.size==0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Node first() throws OutOfBoundaryException {
		if(size==0){
			throw new OutOfBoundaryException("链接表为空");
		}else{
			return head.getNext();//返回DLNode(已实现Node)
		}
	}

	@Override
	public Node last() throws OutOfBoundaryException {
		if(size==0){
			throw new OutOfBoundaryException("链接表为空");
		}else{
			return tail.getPre();//返回DLNode(已实现Node)
		}
	}

	@Override
	public Node getNext(Node p) throws InvalidNodeException, OutOfBoundaryException {
		DLNode node = this.checkPosition(p);
		node  = node.getNext();
		if(node == tail){
			throw new OutOfBoundaryException("错误：已经是链表尾端");
		}
		return node;
	}

	@Override
	public Node getPre(Node p) throws InvalidNodeException, OutOfBoundaryException {
		DLNode node =this.checkPosition(p);
		node = node.getPre();
		if(node == head){
			throw new OutOfBoundaryException("错误：已经是链表首端");
		}
		return node;
	}
	//将 e 作为第一个元素插入链接表,并将含有该元素的结点返回
	@Override
	public Node insertFirst(Object e) {
		DLNode p = new DLNode(e, head, head.getNext());
		head.getNext().setPre(p);
		head.setNext(p);
		size++;
		return p;
	}
	//将 e 作为最后一个元素插入链接表,并将含有该元素的结点返回
	@Override
	public Node insertLast(Object e) {
		DLNode p = new DLNode(e, tail.getPre(), tail);
		tail.getPre().setNext(p);
		tail.setPre(p);
		size++;
		return p;
	}

	@Override
	public Node insertAfter(Node p, Object e) throws InvalidNodeException {
		DLNode node = checkPosition(p);
		DLNode newNode = new DLNode(e, node, node.getNext());
		node.getNext().setPre(newNode);
		node.setNext(newNode);
		size++;
		return newNode;
	}

	@Override
	public Node insertBefore(Node p, Object e) throws InvalidNodeException {
		DLNode node = checkPosition(p);
		DLNode newNode = new DLNode(e, node.getPre(), node);
		node.getPre().setNext(newNode);
		node.setPre(newNode);
		size++;
		return newNode;
	}

	@Override
	public Object remove(Node p) throws InvalidNodeException {
		DLNode node = checkPosition(p);
		node.getPre().setNext(node.getNext());
		node.getNext().setPre(node.getPre());
		size--;
		return node.getData();
	}

	@Override
	public Object removeFirst() throws OutOfBoundaryException {
		return remove(head.getNext());
	}

	@Override
	public Object removeLast() throws OutOfBoundaryException {
		return remove(tail.getPre());
	}

	@Override
	public Object replace(Node p, Object e) throws InvalidNodeException {
		DLNode node = checkPosition(p);
		Object obj = node.getData();
		node.setData(e);
		return obj;
	}
	//元素迭代器
	@Override
	public Iterator elements() {
		Iterator it = new LinkedListIterator(this);
		return it;
	}

}
