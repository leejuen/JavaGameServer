package game.net.netty;

import game.net.dispatcher.HandlerDispatcher;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * 
 * @author: leejun
 * @email: 619344879@qq.com
 * @description: Netty初始化配置 
 * @version:0.0.1
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {
	private static final int MAX_LENGTH= 0xFFFF;
	private int readTimeout;

	private HandlerDispatcher handlerDispatcher;
	public void init() {
		new Thread(this.handlerDispatcher).start();
	}

	public void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		/**
		 * 关于LengthFieldBasedFrameDecoder的说明
		 * http://blog.163.com/linfenliang@126/blog/static/127857195201210821145721/
		 */
		pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(MAX_LENGTH, 4, 2, 0, 0));
		//ch.pipeline().addLast("frameEncoder", new LengthFieldPrepender(4));
		pipeline.addLast("timeout", new ReadTimeoutHandler(this.readTimeout));
		pipeline.addLast("handler", new ServerAdapter(this.handlerDispatcher));	//设置handler分发器
	}
	
	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	public void setHandlerDispatcher(HandlerDispatcher handlerDispatcher) {
		this.handlerDispatcher = handlerDispatcher;
	}
}