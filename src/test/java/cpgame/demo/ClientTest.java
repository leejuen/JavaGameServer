package cpgame.demo;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @project: nettygame
 * @Title: ClientTest.java
 * @Package: cpgame.nettygame
 * @author: chenpeng
 * @email: 46731706@qq.com
 * @date: 2015年8月20日 下午1:45:24
 * @description:
 * @version:
 */
public class ClientTest {
		public void connect(String host, int port) throws Exception {
			EventLoopGroup workerGroup = new NioEventLoopGroup();
			String msg = "Are you ok?";
				try {
					Bootstrap b = new Bootstrap();
					b.group(workerGroup).channel(NioSocketChannel.class);
					b.handler(new ChannelInitializer<Channel>() {
						@Override
						protected void initChannel(Channel ch) throws Exception {
							ChannelPipeline pipeline = ch.pipeline();
							pipeline.addLast("handler", new ClientInboundHandler());
						}
					});
					

						b.option(ChannelOption.SO_KEEPALIVE, true);
						ChannelFuture f = b.connect(host, port).sync();
		while (true) {
			ByteBuf messageData = Unpooled.buffer();
			messageData.writeInt(12000);
			messageData.writeShort(msg.length());
			messageData.writeBytes(msg.getBytes());
			f.channel().writeAndFlush(messageData);
			Thread.sleep(1);
		}
						
						
//						f.channel().closeFuture().sync();
						

				} catch (Exception e) {
					e.printStackTrace();
				}

			

			try {
			} finally {
				workerGroup.shutdownGracefully();
			}

		}
		public static void main(String[] args) throws Exception {
			for(int i=0;i<1;i++){
				
				 new Thread(new Runnable() {
					
					public void run() {
						ClientTest client = new ClientTest();
						try {
							client.connect("127.0.0.1", 8818);
//							client.connect("121.40.112.227 ", 8818);
							
						} catch (Exception e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						} 
						
					
					}
				}).start();
				System.out.println(i);
			}
			
		}
	

}
