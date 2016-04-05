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
			if(d1.getLength() == d2.getLength()){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	//若d2的length大于d1的length 则返回1;相等返回0;小于返回-1
	//若obj1|obj2均不属于基本Data.java类型，抛出NotDataException异常
	@Override
	public int compare(Object obj1, Object obj2) {
		if(obj1 instanceof Data && obj2 instanceof Data){
			Data d1 = (Data) obj1;
			Data d2  = (Data) obj2;
			if(d1.getLength() ==d2.getLength()){
				return 0;
			}else if(d1.getLength()>d2.getLength()){
				return 1;
			}else{
				return -1;
			}
		}else{
			throw new NotDataException("元素类型不为Data.java");
		}
	}

}
