package game.logic.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import game.logic.dao.Game_usersMapper;
import game.logic.entity.Code_MSG;
import game.logic.entity.Result;
import game.logic.model.Game_users;
import game.logic.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger  = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private Game_usersMapper game_usersMapper;
	@Override
	public Result changePassword(String userid, String oldpasswd, String newpasswd) {
		logger.info(userid+"请求修改密码");
		
		Game_users user=new Game_users();
		user.setUserid(userid);
		user=game_usersMapper.selectByUser(user);
		if(user==null){
			return new Result(Code_MSG.USER_DOES_NOT_EXIST);
		}
		if(!user.getPasswd().equals(oldpasswd)){
			return new Result(Code_MSG.OLDPASSWD_ERROR);
		}
		
		if("".equals(newpasswd)||newpasswd==null){
			return new Result(Code_MSG.NEWPASSWD_ERROR);
		}
		user.setPasswd(newpasswd);
		game_usersMapper.updateByPrimaryKeySelective(user);
		return new Result(Code_MSG.CHANGEPASSWD_SUCCESS);
	}
	@Override
	public Game_users queryUserBy(Integer numid) {
		
		return game_usersMapper.selectByPrimaryKey(numid);
	}
	@Transactional
	@Override
	public Result addGold(Integer numid, Long gold) {
		
			Game_users user=game_usersMapper.selectByPrimaryKey(numid);
			if(user==null){
				return new Result(Code_MSG.USER_DOES_NOT_EXIST);
			}
			user.setGold(gold);
			if(game_usersMapper.addGold(user)==1){
				return new Result(Code_MSG.ADDNGOLD_SUCCESS);
			}
			
			return new Result(Code_MSG.ADDGOLD_FAIL);
	}
	@Transactional
	@Override
	public Result reduceGold(Integer numid, Long gold) {
		Game_users user=game_usersMapper.selectByPrimaryKey(numid);
		if(user==null){
			return new Result(Code_MSG.USER_DOES_NOT_EXIST);
		}
		if(user.getGold()<gold){
			return new Result(Code_MSG.GOLD_NOTENOUGE);	
		}
		user.setGold(gold);
		if(game_usersMapper.reduceGold(user)==1){
			return new Result(Code_MSG.REDUCEGOLD_SUCCESS);
		}
		
	 return new Result(Code_MSG.REDUCEGOLD_FAIL);
	}

}
