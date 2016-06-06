package game.net.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.net.dispatcher.HandlerDispatcher;
import game.net.domain.Message;
import game.net.domain.MessageSender;
import game.net.utils.ExceptionUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 
 * @author: leejun
 * @email: 619344879@qq.com
 * @description: Handler代理
 * @version:0.0.1
 */
public class ServerAdapter extends SimpleChannelInboundHandler<Object> {
	private static final Logger logger = LoggerFactory.getLogger(ServerAdapter.class);
	private HandlerDispatcher handlerDispatcher;

	public void setHandlerDispatcher(HandlerDispatcher handlerDispatcher) {
		this.handlerDispatcher = handlerDispatcher;
	}

	public ServerAdapter(HandlerDispatcher handlerDispatcher) {
		this.handlerDispatcher = handlerDispatcher;
	}

	public ServerAdapter() {
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		//重写channelRead0每次收到信息加入到队列中去
		this.handlerDispatcher.addMessage(ctx,new Message((ByteBuf)msg));		
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	
	//出现异常
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ServerAdapter.logger.error(ExceptionUtils.getStackTrace(cause));
		this.handlerDispatcher.removeMessageQueue(ctx);
		MessageSender.unbind(ctx);
		ctx.close();
	}
	
	//断开链接
	@Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		this.handlerDispatcher.removeMessageQueue(ctx);
		MessageSender.unbind(ctx);
        super.channelInactive(ctx);
    }
}
