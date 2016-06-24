package game.net.domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.net.utils.ExceptionUtils;

public abstract class MessageData {
	private static final Logger logger = LoggerFactory.getLogger(MessageData.class);
	public void initData(Message message){
		DataInputStream input = new DataInputStream(new ByteArrayInputStream(message.getData()));
		try {
			read(input);
		} catch (IOException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
	
	public abstract void read(DataInputStream input) throws IOException;
	
	public byte[] getBytes() {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream output = new DataOutputStream(byteStream);
		try {
			write(output);
			output.flush();
			return byteStream.toByteArray();
		} catch (IOException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}
	
	public abstract void write(DataOutputStream output) throws IOException;
}
