package game.logic.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import game.logic.entity.Code_MSG;
import game.logic.entity.LoginBean;
import game.logic.entity.LoginResult;
import game.logic.entity.Result;
import game.logic.service.LoginService;
import game.logic.service.UserService;
import game.net.domain.Message;
import game.net.domain.MessageController;
import game.net.domain.MessageData;
import game.net.domain.MessageSender;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

public class LoginController implements MessageController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private LoginService loginService;
	@Override
	public void execute(ChannelHandlerContext conetxt, Message message) {
		// TODO Auto-generated method stub
		String requestString = new String(message.getData());
		logger.debug("登陆成功 request " + requestString);
		LoginBean login=JSON.parseObject(requestString, LoginBean.class);
		
		LoginResult res=loginService.login(0, login.getUserid(), login.getPasswd());
		if(res.getCode()==Code_MSG.LOGIN_SUCCESS.getMsg_id()){
			MessageSender.bind(res.getUser().getNumid(), conetxt);//绑定分发器
		}
		//返回结果
		String msg=JSON.toJSONString(res,new SimplePropertyPreFilter(LoginResult.class, "code","msg"));
		ByteBuf messageData = Unpooled.buffer();
		messageData.writeInt(10001);
		byte[] b=msg.getBytes();
		messageData.writeInt(b.length);
		messageData.writeBytes(b);
		conetxt.channel().writeAndFlush(messageData);
	}

	@Override
	public int getXYStart() {
		// TODO Auto-generated method stub
		return 10000;
	}

	@Override
	public int getXYEnd() {
		// TODO Auto-generated method stub
		return 10999;
	}

}
