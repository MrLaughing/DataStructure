package ren.laughing.datastructure.baseImpl;

import ren.laughing.datastructure.base.Strategy;
import ren.laughing.datastructure.model.Data;
/**
 * 默认比较策略应针对数据元素类型进行比较
 * 此处数据元素默认类型为Data.java
 * @author Laughing
 * @time 2016年4月5日
 */
public class DefaultStrategy implements Strategy<Data> {

	@Override
	public boolean equal(Data obj1, Data obj2) {
		if(obj1.getContent().equals(obj2.getContent())){
			return true;
		}else{
			return false;
		}
	}
	//返回：d1.getContent().compareTo(d2.getContent());
	@Override
	public int compare(Data obj1, Data obj2) {
		return obj1.getContent().compareTo(obj2.getContent());
	}

}
