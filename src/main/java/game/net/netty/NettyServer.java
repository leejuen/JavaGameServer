package game.net.netty;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 
 * @author leejun
 *
 */
public class NettyServer {
	private Logger logger = LoggerFactory.getLogger(getClass());
	/** 用于分配处理业务线程的线程组个数 */
	protected static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors() * 2; 
	/** 业务出现线程大小 */
	protected static final int BIZTHREADSIZE = 4;

	private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
	private static final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);

	private ServerInitializer initializer;
	private final int port;

	public NettyServer(int port) {
		this.port = port;
	}

	public void setInitializer(ServerInitializer initializer) {
		this.initializer = initializer;
	}

	public void run() throws Exception {

		try {
			//设置使用PooledByteBufAllocator避免内存溢出
			//见http://blog.csdn.net/jiangguilong2000/article/details/39501541
			ServerBootstrap b = new ServerBootstrap();
			((ServerBootstrap) b.group(bossGroup, workerGroup)
								.channel(NioServerSocketChannel.class))
								.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
								.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
								.childHandler(this.initializer);
			Channel ch = null;
			this.logger.info("server started at port " + this.port);
			ch = b.bind(new InetSocketAddress(this.port)).sync().channel();		
			ch.closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
