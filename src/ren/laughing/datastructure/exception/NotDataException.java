package ren.laughing.datastructure.exception;
/**
 * 数据类型不为默认Data.java类型异常
 * 暂定，随实际情况修改
 * @author Laughing
 * @time 2016年4月5日
 */
public class NotDataException extends RuntimeException{
	public NotDataException(String err) {
		super(err);
	}
}
