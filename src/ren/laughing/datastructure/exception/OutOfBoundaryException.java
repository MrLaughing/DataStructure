package ren.laughing.datastructure.exception;
/**
 * 序号越界时抛出该异常
 * @author Laughing
 * @time 2016年4月5日
 */
public class OutOfBoundaryException extends RuntimeException{
	public OutOfBoundaryException(String err) {
		super(err);
	}
}
