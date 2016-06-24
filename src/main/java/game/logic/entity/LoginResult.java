package game.logic.entity;

import game.logic.model.Game_users;

public class LoginResult extends Result{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1814386186051007993L;
	public Game_users user;
	public LoginResult(Code_MSG userDoesNotExist) {
		super(userDoesNotExist);
	}
	/**
	 * @return user
	 */
	public Game_users getUser() {
		return user;
	}
	/**
	 * @param user 要设置的 user
	 */
	public void setUser(Game_users user) {
		this.user = user;
	}
}
