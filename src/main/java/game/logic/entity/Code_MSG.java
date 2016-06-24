package game.logic.entity;
/**
 * 错误代码枚举类
 * @author YHF
 *
 */
public enum Code_MSG {
	//用户登陆状态码
	USER_DOES_NOT_EXIST("用户不存在",10000)
    ,PASSWORD_ERROR("密码错误",10001)
    ,LOGIN_SUCCESS("登陆成功",10002)
    
    
    //用户注册状态码
    ,USER_EXIST("用户已经存在",10003)
    ,PASSWORD_NOTSET("密码未设置",10004)
    ,NICKNAME_NOTSET("昵称未设置",10005)
    ,REGISTER_SUCCESS("注册成功",10006)
    
    
    //用户修改密码状态码
    ,OLDPASSWD_ERROR("旧密码错误",10007)
    ,NEWPASSWD_ERROR("新密码不符合规范",10008)
    ,CHANGEPASSWD_SUCCESS("修改密码成功",10009)
    
    //用户金币状态码
    ,ADDGOLD_FAIL("金币充值失败",100010)
    ,ADDNGOLD_SUCCESS("金币充值成功",100011)
    ,REDUCEGOLD_FAIL("金币消费成功",100012)
    ,REDUCEGOLD_SUCCESS("金币消费成功",100013)
    ,GOLD_NOTENOUGE("金币不足",100014)
    ;
    private String msg;
    private int msg_id;
    private Code_MSG(String msg,int msg_id)
    {
        this.msg=msg;
        this.msg_id=msg_id;
    }
    public String getMsg()
    {
        return this.msg;
    }
    public int getMsg_id() {
      return this.msg_id;
  }
}
