package game.logic.controller;

import game.core.GameXYID;
import game.net.domain.Message;
import game.net.domain.MessageController;
import io.netty.channel.ChannelHandlerContext;

public class GameController implements MessageController {
	@Override
	public int getXYStart() {
		return GameXYID.TABLE_XY_START;
	}

	@Override
	public int getXYEnd() {
		return GameXYID.TABLE_XY_END;
	}

	@Override
	public void execute(ChannelHandlerContext conetxt, Message message) {
		// TODO Auto-generated method stub
		
	}
}
