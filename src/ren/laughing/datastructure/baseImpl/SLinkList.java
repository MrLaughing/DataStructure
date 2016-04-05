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
	
	//辅助方法：获取序号为i的前驱结点
	public SLNode getPreNode(int i){
		
		return null;
	}
	//辅助方法：获取序号为 0<=i<size 的元素所在结点
	public SLNode getNode(int i){
		SLNode p = head.getNext();
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
		SLNode p = head.getNext();//定义一个指针P，起始点为head结点
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
		
	}

	@Override
	public boolean insertBefore(Object obj, Object e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertAfter(Object obj, Object e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object remove(int i) throws OutOfBoundaryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object replace(int i, Object e) throws OutOfBoundaryException {
		
		return null;
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
