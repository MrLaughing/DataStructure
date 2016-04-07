package ren.laughing.datastructure.Instance;

import ren.laughing.datastructure.base.Stack;
import ren.laughing.datastructure.baseImpl.StackLinked;

/**
 * 关于栈的一些实例
 * @author Laughing_Lz
 * @time 2016年4月7日
 */
public class StackInstance {
	/**
	 * 十进制转换为八进制
	 * 使用Stack倒序输出i%8的余数，即为八进制数
	 * @param i 入参,十进制数
	 */
	public void baseConversion(int i){
		Stack s = new StackLinked();
		for(;i>0;i=i/8){
			s.push(i%8);
		}
		while(!s.isEmpty()){
			System.out.print(s.pop());
		}
	}
	
	public static void main(String[] args) {
		StackInstance si =new StackInstance();
		si.baseConversion(800);//1440
	}
}
