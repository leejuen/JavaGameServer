package game.net.domain;
import io.netty.buffer.ByteBuf;

/**
 * @author: leejun
 * @email: 619344879@qq.com
 * @description: 简单地描述了协议类
 * @version:
 */
public class Message {
	private int id;
	private byte[] data;
	public Message(ByteBuf msg) {
		this.id = msg.readInt();
		int length = msg.readUnsignedShort();
		this.data = msg.readBytes(length).array();
	}
	public Message(int id,byte[] data) throws Exception{
		this.id = id;
		if(data.length > 0xFFFF) {
			throw new Exception("the data len = "+data.length+" is too long");
		}
		this.data = data;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	public byte[] toByteArray(){
		final byte[] result = new byte[6+this.data.length];
		int tmpId = this.id;
		int tmpLen = this.data.length;
		//封装id
		for(int i=0;i<4;i++) {
			result[i] = new Integer(tmpId & 0xff).byteValue();
			tmpId = tmpId >> 8; // 向右移8位 
		}
		//封装长度
		for(int i=4;i<6;i++) {
			result[i] = new Integer(tmpLen & 0xff).byteValue();
			tmpLen = tmpLen >> 8; // 向右移8位 
		}
		//封装msg
		for(int i=6;i<result.length;i++) {
			result[i] = this.data[i-6];
		}
		return result;
	}
}
