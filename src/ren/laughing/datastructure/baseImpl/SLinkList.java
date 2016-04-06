package ren.laughing.datastructure.baseImpl;

import ren.laughing.datastructure.base.List;
import ren.laughing.datastructure.base.Strategy;
import ren.laughing.datastructure.exception.OutOfBoundaryException;
/**
 * 线性表的实现：链式存储结构：单链表
 * @author Laughing_Lz
 * @time 2016年4月5日
 */
public class SLinkList implements List{
	private Strategy strategy;//数据元素比较策略
	private SLNode head;//单链表首结点引用
	private int size;//数据元素个数
	
	
	public SLinkList() {
		this(new DefaultStrategy());
	}

	public SLinkList(Strategy strategy) {
		this.strategy = strategy;
		head = new SLNode();
		size = 0;
	}
	//辅助方法：获取数据元素为e的前驱结点
	public SLNode getPreNode(Object obj){
		int index = indexOf(obj);
		if(index >=0){
			SLNode p = getPreNode(index);
			return p;
		}else{
			return null;
		}
	}
	//辅助方法：获取序号为0<=i<size的前驱结点
	public SLNode getPreNode(int i){
		if(i>=1){
			SLNode p = getNode(i-1);
			return p;
		}else{
			return head;
		}
	}
	//辅助方法：获取序号为 0<=i<size 的元素所在结点
	public SLNode getNode(int i){
		SLNode p = head.getNext();//head不存储任何实质对象,仅作a0的前驱结点
		for(int j= 0;j<i;j++){
			p=p.getNext();
		}
		return p;
	}
	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		if(this.size == 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean contains(Object e) {
		SLNode p = head.getNext();//定义一个指针P，起始点为head结点
		while(p!=null){
			if(strategy.equal(p.getData(), e)){
				return true;
			}else{
				p=p.getNext();
			}
		}
		return false;
	}
	
	@Override
	public int indexOf(Object e) {
		SLNode p = head.getNext();//定义一个指针P，起始点为a0结点
		int index = 0;
		while(p!=null){
			if(strategy.equal(p.getData(), e)){
				return index;
			}else{
				index++;
				p=p.getNext();
			}
		}
		return -1;
	}

	@Override
	public void insert(int i, Object e) throws OutOfBoundaryException {
		if(i<0||i>=size){
			throw new OutOfBoundaryException("指定插入位置的序号越界");
		}
		SLNode p = getPreNode(i);//得到前驱结点
		SLNode q = new SLNode(e, p.getNext());//定义新结点并插入
		p.setNext(q);//与原始结点相连
		size++;
	}
	//将数据元素 e 插入到元素 obj 之前
	@Override
	public boolean insertBefore(Object obj, Object e) {
//		int index = indexOf(obj);//获取obj位置
//		if(index>=0&&index<size){
//			insert(index, e);
//			return true;
//		}else{
//			return false;
//		}
		SLNode p = getPreNode(obj);
		if(p!=null){
			SLNode q = new SLNode(e, p.getNext());
			p.setNext(q);
			size++;
			return true;
		}else{
			return false;
		}
	}
	//将数据元素 e 插入到元素 obj 之后
	@Override
	public boolean insertAfter(Object obj, Object e) {
		SLNode p = head.getNext();
		while(p!=null){
			if(strategy.equal(p.getData(), obj)){
				SLNode q = new SLNode(e, p.getNext());
				p.setNext(q);
				size++;
				return true;
			}else{
				p = p.getNext();
			}
		}
		return false;
	}

	@Override
	public Object remove(int i) throws OutOfBoundaryException {
		if (i<0||i>=size){
			throw new OutOfBoundaryException("错误，指定的删除序号越界。");
		}
		SLNode p = getPreNode(i);//首先找到该位置结点和前驱结点
		SLNode q = getNode(i);
		p.setNext(q.getNext());//将前驱结点和后继结点相连
		size--;
		return q.getData();//返回删除的结点数据元素
	}

	@Override
	public boolean remove(Object e) {
//		int index = indexOf(e);
//		if(index >=0&&index<size){
//			remove(index);
//			return true;
//		}else{
//			return false;
//		}
		SLNode p = getPreNode(e);
		if(p!=null){
			p.setNext(p.getNext().getNext());
			size--;
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Object replace(int i, Object e) throws OutOfBoundaryException {
		if (i<0||i>=size){
			throw new OutOfBoundaryException("错误，指定替代位置的序号越界。");
		}
		SLNode p = getNode(i);
		Object obj = p.getData();
		p.setData(e);//只需替换该结点数据元素
		return obj;
	}

	@Override
	public Object get(int i) throws OutOfBoundaryException {
		if(i<0||i>size){
			throw new OutOfBoundaryException("指定序号越界");
		}
		SLNode p = getNode(i);
		return p.getData();
	}

}
