package ren.laughing.datastructure.exception;
/**
 * 队列为空时出队或取队首元素抛出此异常
 * @author Laughing_Lz
 * @time 2016年4月7日
 */
public class QueueEmptyException extends RuntimeException{

	public QueueEmptyException(String message) {
		super(message);
	}
	
}
