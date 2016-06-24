package game.net.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.MessageLite;

import game.net.utils.ExceptionUtils;
import io.netty.channel.ChannelHandlerContext;

/**
 * 
 * @author: leejun
 * @email: 619344879@qq.com
 * @description: 协议发送器,可以广播，可以根据玩家id广播
 * @version:0.0.1
 */
public class MessageSender {
	private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);
	private static Map<Integer,ChannelHandlerContext> bindMap = new ConcurrentHashMap<Integer,ChannelHandlerContext>(); 
	private static Map<ChannelHandlerContext,Integer> reverseBindMap = new ConcurrentHashMap<ChannelHandlerContext,Integer>();

	private MessageSender() {
		
	}
	
	public static Integer getNumIdByContext(ChannelHandlerContext context) {
		return reverseBindMap.get(context);
	}
	
	/**
	 * 将数字id与连接绑定
	 * @param numId
	 * @param context
	 */
	public static void bind(int numId,ChannelHandlerContext context) {
		bindMap.put(numId, context);
		reverseBindMap.put(context,numId);
	}

	/**
	 * 根据数字id解除绑定
	 * @param numId
	 */
	public static void unbind(int numId) {
		ChannelHandlerContext context = (ChannelHandlerContext)bindMap.remove(numId);
		if(context != null){
			reverseBindMap.remove(context);
		}
	}
	
	/**
	 * 根据连接解除绑定
	 * @param context
	 */
	public static void unbind(ChannelHandlerContext context) {
		Integer numId = (Integer)reverseBindMap.remove(context);
		if(numId != null) {
			bindMap.remove(numId);
		}
	}
	
	/**
	 * 将消息发送给指定的连接
	 * @param context
	 * @param id
	 * @param msg
	 */
	public static void sendMessage(ChannelHandlerContext context,short id,MessageLite msg) {
		try {
			context.writeAndFlush(new Message(id,msg.toByteArray()).toByteArray());
		} catch (Exception e) {
			MessageSender.logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
	
	/**
	 * 将消息发送给指定numid的玩家
	 * @param numId
	 * @param id
	 * @param msg
	 */
	public static void sendMessage(int numId,Message msg) {
		try {
			bindMap.get(numId).writeAndFlush(msg.toByteArray());
		} catch (Exception e) {
			MessageSender.logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
	
	/**
	 * 将消息发送给指定numid的玩家
	 * @param numId
	 * @param id
	 * @param msg
	 */
	public static void sendMessage(int numId,short id,MessageLite msg) {
		try {
			bindMap.get(numId).writeAndFlush(new Message(id,msg.toByteArray()).toByteArray());
		} catch (Exception e) {
			MessageSender.logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
	
	/**
	 * 将消息发送给指定的一些玩家
	 * @param numIds
	 * @param id
	 * @param msg
	 */
	public static void sendMessageByIds(int[] numIds,short id,MessageLite msg){
		try {
			byte[] message= new Message(id,msg.toByteArray()).toByteArray();
			for(int numId : numIds){
				bindMap.get(numId).writeAndFlush(message);
			}
		} catch (Exception e) {
			MessageSender.logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
	
	/**
	 * 将消息发送给所有玩家
	 * @param id
	 * @param msg
	 */
	public static void sendMessageAll(short id,MessageLite msg){
		try {
			byte[] message= new Message(id,msg.toByteArray()).toByteArray();
			for(ChannelHandlerContext context : bindMap.values()) {
				context.writeAndFlush(message);
			}
		} catch (Exception e) {
			MessageSender.logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
}
