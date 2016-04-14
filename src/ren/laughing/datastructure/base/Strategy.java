package ren.laughing.datastructure.base;
/**
 * 数据元素的比较策略
 * @author Laughing
 * @time 2016年4月5日
 */
public interface Strategy<T> {
	//判断两个数据是否相等
	public boolean equal(T obj1,T obj2);
	//判断两个数据大小，返回-1|0|1
	public int compare(T obj1,T obj2);
}
