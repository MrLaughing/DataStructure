package ren.laughing.datastructure.base;


import ren.laughing.datastructure.exception.InvalidNodeException;
import ren.laughing.datastructure.exception.OutOfBoundaryException;
/**
 * 链接表ADT
 * 单链表和双链表用顺序存储结构并不友好
 * 链表方便插入，不方便查找
 * SLNode和DLNode都实现了Node结点
 * 因此采用Node结点作为参数、降低算法复杂度
 * 链接表可以看作是一组结点序列以及基于结点进行操作的线性结构的抽象
 * @author Laughing_Lz
 * @time 2016年4月6日
 */
public interface LinkedList {
	//查询链接表当前的规模
	public int getSize();
	//判断列表是否为空
	public boolean isEmpty();
	//返回第一个结点
	public Node first() throws OutOfBoundaryException;
	//返回最后一结点
	public Node last() throws OutOfBoundaryException;
	//返回 p 之后的结点
	public Node getNext(Node p) throws InvalidNodeException, OutOfBoundaryException;
	//返回 p 之前的结点
	public Node getPre(Node p) throws InvalidNodeException, OutOfBoundaryException;
	//将 e 作为第一个元素插入链接表,并返回 e 所在结点
	public Node insertFirst(Object e);
	//将 e 作为最后一个元素插入列表,并返回 e 所在结点
	public Node insertLast(Object e);
	//将 e 插入至 p 之后的位置,并返回 e 所在结点
	public Node insertAfter(Node p, Object e) throws InvalidNodeException;
	//将 e 插入至 p 之前的位置,并返回 e 所在结点
	public Node insertBefore(Node p, Object e) throws InvalidNodeException;
	//删除给定位置处的元素，并返回之
	public Object remove(Node p) throws InvalidNodeException;
	//删除首元素，并返回之
	public Object removeFirst() throws OutOfBoundaryException;
	//删除末元素，并返回之
	public Object removeLast() throws OutOfBoundaryException;
	//将处于给定位置的元素替换为新元素，并返回被替换的元素
	public Object replace(Node p, Object e) throws InvalidNodeException;
	//元素迭代器
	public Iterator elements();
	}