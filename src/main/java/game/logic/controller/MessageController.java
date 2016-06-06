package game.logic.controller;

import game.net.domain.Message;
import io.netty.channel.ChannelHandlerContext;

public abstract interface MessageController {
	public abstract void execute(ChannelHandlerContext conetxt,Message message);
}
