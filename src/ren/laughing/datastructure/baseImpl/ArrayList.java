package ren.laughing.datastructure.baseImpl;

import ren.laughing.datastructure.base.List;
import ren.laughing.datastructure.base.Strategy;
import ren.laughing.datastructure.exception.OutOfBoundaryException;
/**
 * 线性表的实现
 * @author Laughing
 * @time 2016年4月5日
 */
public class ArrayList implements List{
	private final int LEN = 8; //默认数组大小8
	private Strategy strategy; //数据元素的比较策略
	private int size; //线性表中数据元素的个数
	private Object[] elements; //数据元素数组
	//构造器
	public ArrayList() {
		this(new DefaultStrategy());
	}
	public ArrayList(Strategy strategy) {
		this.strategy = strategy;
		size = 0;
		elements = new Object[LEN];//默认数组大小
	}


	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		if(size == 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean contains(Object e) {
		for(int i=0;i<size;i++){
			if(strategy.equal(e, elements[i])){
				return true;
			}
		}
		return false;
	}

	@Override
	public int indexOf(Object e) {
		for(int i=0;i<size;i++){
			if(strategy.equal(e, elements[i])){
				return i;
			}
		}
		return -1;
	}

	@Override
	public void insert(int i, Object e) throws OutOfBoundaryException {
		if (i < 0 || i > size) {
			throw new OutOfBoundaryException("错误，指定的插入序号越界。");
		}
		//若数据元素个数大于数据元素数组长度
		if (size >= elements.length) {
			expandSpace();// 扩充数组长度、动态扩展数组空间
		}
		for (int j = size; j > i; j--) {
			elements[j] = elements[j - 1];
		}
		elements[i] = e;
		size++;

	}
	//将数据元素 e 插入到元素 obj之前
	@Override
	public boolean insertBefore(Object obj, Object e) {
		int index = this.indexOf(obj);
		if(index < 0){
			return false;
		}else{
			this.insert(index, e);
			return true;
		}
	}
	//将数据元素 e 插入到元素 obj之后
	@Override
	public boolean insertAfter(Object obj, Object e) {
		int index  = this.indexOf(obj);
		if(index < 0){
			return false;
		}else{
			this.insert(index+1, e);
			return true;
		}
	}
	//删除线性表中序号为 i 的元素,并返回之
	@Override
	public Object remove(int i) throws OutOfBoundaryException {
		if(i>=size||i<0){
			throw new OutOfBoundaryException("指定的删除序号越界");
		}
		Object obj = elements[i];
		for(int j=i;j<size;j++){
			elements[j]=elements[j+1];
		}
		elements[size--]=null;//释放数组最后一个位置的元素
		return obj;
	}
	//删除线性表中第一个与 e 相同的元素
	@Override
	public boolean remove(Object e) {
		int index = indexOf(e);
		if(index<0){
			return false;
		}else{
			this.remove(index);
			return true;
		}
	}
	//替换线性表中序号为 i 的数据元素为 e，返回原数据元素
	@Override
	public Object replace(int i, Object e) throws OutOfBoundaryException {
		if(i<0||i>=size){
			throw new OutOfBoundaryException("要替换元素的序号越界");
		}
		Object obj =  elements[i];
		elements[i] = e;
		return obj;
	}
	//返回线性表中序号为 i 的数据元素
	@Override
	public Object get(int i) throws OutOfBoundaryException {
		if(i<0||i>=size){
			throw new OutOfBoundaryException("要获取元素的序号越界");
		}
		Object obj = elements[i];
		return obj;
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
