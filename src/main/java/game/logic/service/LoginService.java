package game.logic.service;

import game.logic.entity.LoginResult;
import game.logic.entity.Result;

/**
 * 游戏登陆服务接口
 * @author YHF
 *
 */
public interface LoginService {
		/**
		 * 一般登陆服务,通过账号和密码登陆
		 * @param areaid 平台id
		 * @param userid 用户名
		 * @param passwd 密码	
		 * @return
		 */
		LoginResult login(Integer areaid,String userid ,String passwd);
		
		/**
		 * 注册用户的方法
		 * @param areaid 区域id
		 * @param userid 用户名
		 * @param passwd 密码
		 * @param nickname 昵称
		 * @param sex 性别
		 * @param head 头像
		 * @param mobile 手机号
		 * @param realname 真实姓名
		 * @param channelid 渠道号
		 * @param usertype 用户类型
		 * @return
		 */
		Result register(Integer areaid,String userid ,String passwd,String nickname,int sex,int head,String mobile,String realname,int channelid,int usertype);
}
