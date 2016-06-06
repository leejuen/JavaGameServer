package game.logic.service.impl;

import org.springframework.stereotype.Service;

import game.logic.service.LoginService;

/**
 * 登陆服务的实现类
 * @author YHF
 *
 */
@Service
public class LoginServiceImpl implements LoginService{

	@Override
	public boolean login(String username, String password) {

		//如果登陆成功则将用户的信息保存起来，大致为生成一个AbstractGamer对象，并存入AbstractGameContext中，或者游戏大厅的数据集合中
		return false;
	}

}
