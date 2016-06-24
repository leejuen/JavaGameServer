package game.net.dispatcher;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.net.domain.Message;
import game.net.domain.MessageController;
import game.net.domain.MessageQueue;
import game.net.utils.ExceptionUtils;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author: leejun
 * @email: 619344879@qq.com
 * @description: message分发器
 * @version:
 */
public class HandlerDispatcher implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(HandlerDispatcher.class);
	private final Map<ChannelHandlerContext, MessageQueue> sessionMsgQ;
	private Executor messageExecutor;
	private List<MessageController> controllerArray;
	private boolean running;
	private long sleepTimeMS;
	private int sleepTimeNS;
	public HandlerDispatcher() {
		this.sessionMsgQ = new ConcurrentHashMap<ChannelHandlerContext, MessageQueue>();

		this.running = true;
		this.sleepTimeMS = 200L;
		this.sleepTimeNS = 0;
	}

	public void setControllerArray(List<MessageController> controllerArray) {
		this.controllerArray = controllerArray;
	}

	public void setMessageExecutor(Executor messageExecutor) {
		this.messageExecutor = messageExecutor;
	}

	public void setSleepTimeMS(long sleepTimeMS) {
		this.sleepTimeMS = sleepTimeMS;
	}
	
	public void setSleepTimeNS(int sleepTimeNS) {
		this.sleepTimeNS = sleepTimeNS;
	}
	
	public void addMessageQueue(ChannelHandlerContext context, MessageQueue messageQueue) {
		this.sessionMsgQ.put(context, messageQueue);
	}

	public MessageQueue getMessageQueue(ChannelHandlerContext context) {
		return (MessageQueue) this.sessionMsgQ.get(context);
	}
	
	public void removeMessageQueue(ChannelHandlerContext context) {
		MessageQueue queue = (MessageQueue) this.sessionMsgQ.remove(context);
		if (queue != null)
			queue.clear();
	}
	
	public void addMessage(ChannelHandlerContext conetxt,Message message) {
		try {
			// 通过Hash算法将其放入相应的队列中
			MessageQueue messageQueue = this.getMessageQueue(conetxt);
			if (messageQueue == null) {
				messageQueue = new MessageQueue(new ConcurrentLinkedQueue<Message>());
				messageQueue.add(message);
				this.addMessageQueue(conetxt,messageQueue);
			} else {
				messageQueue.add(message);
			}
		} catch (Exception e) {
			HandlerDispatcher.logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

	public void stop() {
		this.running = false;
	}
	
	public void run() {
		while (this.running) {
			try {
				//处理一个时间片中每个玩家的所有请求，一个玩家一个线程
				for (Map.Entry<ChannelHandlerContext, MessageQueue> entry : sessionMsgQ.entrySet()) {
					MessageQueue messageQueue = entry.getValue();
					ChannelHandlerContext context = entry.getKey();
					if ((messageQueue != null) && (!messageQueue.isEmpty()) && (!messageQueue.isRunning())) {
						MessageWorker messageWorker = new MessageWorker(context,messageQueue);
						this.messageExecutor.execute(messageWorker);
					}
				}
			} catch (Exception e) {
				HandlerDispatcher.logger.error(ExceptionUtils.getStackTrace(e));
			}
			try {
				Thread.sleep(this.sleepTimeMS,this.sleepTimeNS);
			} catch (InterruptedException e) {
				HandlerDispatcher.logger.error(ExceptionUtils.getStackTrace(e));
			}
		}
	}

	private final class MessageWorker implements Runnable {
		private final MessageQueue messageQueue;
		private final ChannelHandlerContext context;
		private Message request;

		private MessageWorker(ChannelHandlerContext context,MessageQueue messageQueue) {
			messageQueue.setRunning(true);
			this.context = context;
			this.messageQueue = messageQueue;
		}

		public void run() {
			try {
				while(!messageQueue.isEmpty()){
					this.request = ((Message) messageQueue.getRequestQueue().poll());
					handMessageQueue();
				}
			} catch (Exception e) {
				HandlerDispatcher.logger.error(ExceptionUtils.getStackTrace(e));
			} finally {
				this.messageQueue.setRunning(false);
				this.request = null;
			}
		}

		private void handMessageQueue() {
			// TODO 此处执行事件的分发
			int messageId = this.request.getId();
			MessageController handler = null;
			for(MessageController controller : HandlerDispatcher.this.controllerArray) {
				if(controller.getXYStart() <= messageId && messageId <= controller.getXYEnd()) {
					handler = controller;
					break;
				}
			}			
			if (handler != null) {
				handler.execute(this.context,this.request);
			} else {
				HandlerDispatcher.logger.warn("指令 [{}]找不到", messageId);
			}
		}
	}
}
