package ren.laughing.datastructure.exception;

/**
 * 调用图不支持的操作时抛出的异常
 * 
 * @author Laughing_Lz
 * @time 2016年4月16日
 */
public class UnsupportedOperation extends RuntimeException {
	public UnsupportedOperation(String err) {
		super(err);
	}
}