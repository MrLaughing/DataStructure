package ren.laughing.datastructure.base;

import ren.laughing.datastructure.exception.StackEmptyException;

/**
 * Stack 栈：后进先出
 * 只能在栈顶top进行插入（入栈）、删除（出栈）操作
 * @author Laughing_Lz
 * @time 2016年4月6日
 */
public interface Stack {
	//返回堆栈的大小
	public int getSize();
	//判断堆栈是否为空
	public boolean isEmpty();
	//数据元素 e入栈
	public void push(Object e);
	//栈顶元素出栈
	public Object pop() throws StackEmptyException;
	//取栈顶元素
	public Object peek() throws StackEmptyException;
	}