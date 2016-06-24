package game.logic.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import game.logic.dao.Game_usersMapper;
import game.logic.entity.Code_MSG;
import game.logic.entity.LoginResult;
import game.logic.entity.Result;
import game.logic.model.Game_users;
import game.logic.service.LoginService;

/**
 * 登陆服务的实现类
 * @author YHF
 *
 */
@Service
public class LoginServiceImpl implements LoginService{
	private static final Logger logger  = Logger.getLogger(LoginServiceImpl.class);
	@Autowired
	private Game_usersMapper game_usersMapper;

	/**
	 * 一般登陆服务,通过账号和密码登陆
	 * @param areaid 平台id
	 * @param userid 用户名
	 * @param passwd 密码	
	 */
	private void userLogin(int areaid,String userid,String passwd)
	{
		
	}
	
	/**
	 * 三方登陆方法,通过token登陆,如果是第一次登陆则会导入三方数据
	 * @param areaid 平台id
	 * @param token 三方凭证
	 */
	private void thirdLogin(int areaid,String token)
	{
		
	}
	
	/**
	 * 游客登陆方法,通过devid登陆,一个devid绑定一个账号
	 * @param areaid 平台id
	 * @param devid 设备号
	 */
	private void visitorLogin(int areaid,String devid)
	{
		
	}

	@Override
	public LoginResult login(Integer areaid, String userid, String passwd) {
		logger.info(userid+"请求普通登陆");
		LoginResult res;
		Game_users user=new Game_users();
		user.setAreaid(areaid);
		user.setUserid(userid);
		user=game_usersMapper.selectByUser(user);
		if(user==null){
			return new LoginResult(Code_MSG.USER_DOES_NOT_EXIST);
		}
		if(passwd.equals(user.getPasswd())){
			res=new LoginResult(Code_MSG.LOGIN_SUCCESS);	
		}else{
			res=new LoginResult(Code_MSG.PASSWORD_ERROR);
		}
		res.setUser(user);
		return res;
	}

	@Override
	public Result register(Integer areaid, String userid, String passwd, String nickname, int sex, int head,
			String mobile, String realname, int channelid, int usertype) {
		logger.info(userid+"请求注册用户");
		
		Game_users user=new Game_users();
		user.setUserid(userid);
		
		if(game_usersMapper.selectByUser(user)!=null){
			return new Result(Code_MSG.USER_EXIST);
		}
		if("".equals(passwd)||passwd==null){
			return new Result(Code_MSG.PASSWORD_NOTSET);
		}
		user.setPasswd(passwd);
		if("".equals(nickname)||nickname==null){
			return new Result(Code_MSG.NICKNAME_NOTSET);
		}
		user.setNickname(nickname);
		user.setSex(sex);
		user.setHead(head);
		user.setMobile(mobile);
		user.setRealname(realname);
		user.setChannelid(channelid);
		user.setUsertype(usertype);
		user.setAreaid(areaid);
		//设置金额
		user.setGold(0l);
		user.setDiamond(0l);
		game_usersMapper.insertSelective(user);
		return new Result(Code_MSG.REGISTER_SUCCESS);
	}
	
}
