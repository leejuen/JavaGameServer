package game.net.domain;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 
 * @author: leejun
 * @email: 619344879@qq.com
 * @description: Message队列
 * @version:0.0.1
 */
public class MessageQueue {
	private Queue<Message> requestQueue;
	private boolean running = false;
	public MessageQueue(ConcurrentLinkedQueue<Message> concurrentLinkedQueue) {
		this.requestQueue = concurrentLinkedQueue;
	}

	public Queue<Message> getRequestQueue() {
		return this.requestQueue;
	}

	public void setRequestQueue(Queue<Message> requestQueue) {
		this.requestQueue = requestQueue;
	}

	public void clear() {
		this.requestQueue.clear();
		this.requestQueue = null;
	}

	/**
	 * 由于内部实现是链表。每次获取都会导致性能问题
	 * @return
	 */
	@Deprecated
	public int size() {
		return this.requestQueue != null ? this.requestQueue.size() : 0;
	}
	
	public boolean isEmpty(){
		return this.requestQueue != null ? this.requestQueue.isEmpty() : true;
	}
	
	public boolean add(Message request) {
		return this.requestQueue.add(request);
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public boolean isRunning() {
		return this.running;
	}
}