package game.logic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.InvalidProtocolBufferException;

import game.logic.entity.PersonOuterClass;
import game.net.domain.Message;
import game.net.domain.MessageController;
import io.netty.channel.ChannelHandlerContext;

public class ProtobufTestController implements MessageController{
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Override
	public void execute(ChannelHandlerContext conetxt,Message message) {
		// TODO Auto-generated method stub
		PersonOuterClass.Person person;
		try {
			person = PersonOuterClass.Person.parseFrom(message.getData());
			logger.debug(person.getName()+"连接上了服务器");
			person.toByteArray();
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public int getXYStart() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getXYEnd() {
		// TODO Auto-generated method stub
		return 1;
	}
}
