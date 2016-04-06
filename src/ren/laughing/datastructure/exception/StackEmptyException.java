package ren.laughing.datastructure.exception;
/**
 * 堆栈为空时出栈或取栈顶元素抛出此异常
 * @author Laughing_Lz
 * @time 2016年4月6日
 */
public class StackEmptyException extends RuntimeException {

	public StackEmptyException(String message) {
		super(message);
	}
	
}
