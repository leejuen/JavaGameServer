package game.net.domain;

import io.netty.channel.ChannelHandlerContext;

public abstract interface MessageController {
	public abstract int getXYStart();
	public abstract int getXYEnd();
	public abstract void execute(ChannelHandlerContext conetxt,Message message);
}
