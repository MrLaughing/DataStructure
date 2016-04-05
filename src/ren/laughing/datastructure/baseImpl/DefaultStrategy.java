package ren.laughing.datastructure.baseImpl;

import ren.laughing.datastructure.base.Strategy;
import ren.laughing.datastructure.exception.NotDataException;
import ren.laughing.datastructure.model.Data;
/**
 * 默认比较策略应针对数据元素类型进行比较
 * 此处均假设数据元素默认类型为Data.java
 * @author Laughing
 * @time 2016年4月5日
 */
public class DefaultStrategy implements Strategy {

	@Override
	public boolean equal(Object obj1, Object obj2) {
		if(obj1 instanceof Data && obj2 instanceof Data){
			Data d1 = (Data) obj1;
			Data d2  = (Data) obj2;
			if(d1.getContent().equals(d2.getContent())){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	//返回：d1.getContent().compareTo(d2.getContent());
	//若obj1|obj2均不属于基本Data.java类型，抛出NotDataException异常
	@Override
	public int compare(Object obj1, Object obj2) {
		if(obj1 instanceof Data && obj2 instanceof Data){
			Data d1 = (Data) obj1;
			Data d2  = (Data) obj2;
			return d1.getContent().compareTo(d2.getContent());
		}else{
			throw new NotDataException("元素类型不为Data.java");
		}
	}

}
