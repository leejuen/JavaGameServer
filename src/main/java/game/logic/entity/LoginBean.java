package game.logic.entity;

public class LoginBean {
	/**
	 * 用户名
	 */
	private String userid;
	/**
	 * 密码
	 */
	private String passwd;
	/**
	 * @return userid
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * @param userid 要设置的 userid
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * @return passwd
	 */
	public String getPasswd() {
		return passwd;
	}
	/**
	 * @param passwd 要设置的 passwd
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}
