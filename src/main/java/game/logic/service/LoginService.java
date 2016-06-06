package game.logic.service;

/**
 * 游戏登陆服务接口
 * @author YHF
 *
 */
public interface LoginService {
		/**
		 * 登陆服务
		 * @param username 用户名
		 * @param password 密码
		 * @return
		 */
		boolean login(String username,String password);
}
