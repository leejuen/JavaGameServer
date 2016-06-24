package game.net.domain;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import io.netty.buffer.ByteBuf;

/**
 * @author: leejun
 * @email: 619344879@qq.com
 * @description: 简单地描述了协议类
 * @version:
 */
public class Message {
	private int id;
	private int appid;
	private byte[] data;
	public Message(ByteBuf msg) {
		this.id = msg.readUnsignedShort();
		this.appid = msg.readUnsignedShort();
		int length = msg.readUnsignedShort();
		this.data = msg.readBytes(length).array();
	}
	
	public Message(int id,byte[] data){
		this(id,1000,data);
	}
	
	public Message(int id,int appid,byte[] data){
		this.id = id;
		this.appid = appid;
		this.data = data;
	}
	public int getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public int getAppid() {
		return appid;
	}
	
	public void setAppid(short appid) {
		this.appid = appid;
	}
	
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
	/**
	 * 如果data的数据大于65535则会跑出参数异常
	 * @return
	 */
	public byte[] toByteArray() throws Exception{
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream output = new DataOutputStream(byteStream);
		output.writeShort(this.id);
		output.writeShort(this.appid);
		output.writeShort(this.data.length);
		for(int i=0;i<this.data.length;i++) {
			output.writeByte(this.data[i]);
		}
		output.flush();
		return byteStream.toByteArray();
	}
}
