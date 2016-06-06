package game.logic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import game.logic.dao.SYS_USERMapper;
import game.logic.service.LoginService;
import game.net.domain.Message;
import io.netty.channel.ChannelHandlerContext;

public class LoginController implements MessageController{
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private LoginService loginService;
	
	@Override
	public void execute(ChannelHandlerContext conetxt,Message message) {
		// TODO Auto-generated method stub
		String requestString = new String(message.getData());
		logger.debug("登陆成功 request "+requestString);
		System.out.println(loginService.login("", ""));
		//logger.debug("登陆成功 request "+((SYS_USERMapper)NettyServerStart.factory.getBean("SYS_USERMapper")).selectByPrimaryKey("1").getName());
		//message.getContext().close();
		
	}
}
