package game.logic.entity;

import java.io.Serializable;

public class Result implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 299058276961000427L;
	
	/**
	 * 状态码
	 */
	private int code;
	/**
	 * 返回内容
	 */
	private String msg;
	
	public Result(){
		
	}
	
	public Result(Code_MSG code){
		this.code=code.getMsg_id();
		this.msg=code.getMsg();
	}
	/**
	 * @return code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code 要设置的 code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg 要设置的 msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
