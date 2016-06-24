package game.logic.service;

import game.logic.entity.Result;
import game.logic.model.Game_users;

/**
 * 用户服务接口，包含了用户相关的服务
 * 
 * @author YHF
 *
 */
public interface UserService {
	/**
	 * 重置密码
	 * 
	 * @param userid
	 *            用户名
	 * @param oldpasswd
	 *            旧密码
	 * @param newpasswd
	 *            新密码
	 * @return
	 */
	Result changePassword(String userid, String oldpasswd, String newpasswd);
	
	/**
	 * 根据用户id查询用户相关信息
	 * @param numid
	 * @return
	 */
	Game_users queryUserBy(Integer numid);
	
	/**
	 * 用户充值金币
	 * @param numid 用户id
	 * @param gold 充值的金币数量
	 * @return
	 */
	Result addGold(Integer numid,Long gold);
	
	/**
	 * 消费用户金币
	 * @param numid 用户id
	 * @param gold 充值的金币数量
	 * @return
	 */
	Result reduceGold(Integer numid,Long gold);
}
