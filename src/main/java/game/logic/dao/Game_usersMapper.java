package game.logic.dao;

import game.logic.model.Game_users;

public interface Game_usersMapper {
	/**
	 * 根据用户id删除用户信息
	 * @param numid 用户id，主键
	 * @return
	 */
    int deleteByPrimaryKey(Integer numid);
    /**
     * 在数据库中插入用户信息，需要对象中的值不存在空值
     * @param user 用户对象
     * @return
     */
    int insert(Game_users user);
    /**
     * 在数据库中插入用户信息，允许对象中的值是空
     * @param user 用户对象
     * @return
     */
    int insertSelective(Game_users user);
    /**
	 * 根据用户id查询用户信息
	 * @param numid 用户id，主键
	 * @return
	 */
    Game_users selectByPrimaryKey(Integer numid);
    /**
     * 更新用户信息，只更新需要更新的对象
     * @param user 用户对象，其中numid不能为空
     * @return
     */
    int updateByPrimaryKeySelective(Game_users user);
    /**
     * 更新用户信息，更新所有对象
     * @param user 用户对象，其中numid不能为空
     * @return
     */
    int updateByPrimaryKey(Game_users user);
    /**
     * 根据设置的用户对象的值查询对应的用户信息
     * @param user 用户对象
     * @return
     */
    Game_users selectByUser(Game_users user);
    /**
     * 增加金币
     * @param user 设置Game_user对象中的numid和gold字段
     * @return 更改的记录数 1为正常
     */
    int addGold(Game_users user);
    /**
     * 减少金币
     * @param user 设置Game_user对象中的numid和gold字段
     * @return 更改的记录数  1为正常
     */
    int reduceGold(Game_users user);
}