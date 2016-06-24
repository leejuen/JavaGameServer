package game.logic.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import game.logic.model.Game_users;
import game.logic.service.LoginService;
import game.net.domain.Message;
import game.net.domain.MessageController;
import io.netty.channel.ChannelHandlerContext;

public class LobbyController implements MessageController{
	private static final Logger logger = LoggerFactory.getLogger(LobbyController.class);
	
	private static Map<Integer,Game_users> map=new HashMap<Integer,Game_users>();
	
	
	@Override
	public void execute(ChannelHandlerContext conetxt,Message message) {
		
	}

	@Override
	public int getXYStart() {
		// TODO Auto-generated method stub
		return 9000;
	}

	@Override
	public int getXYEnd() {
		// TODO Auto-generated method stub
		return 9999;
	}
}
