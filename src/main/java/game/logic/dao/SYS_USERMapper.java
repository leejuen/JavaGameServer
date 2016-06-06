package game.logic.dao;

import game.logic.model.SYS_USER;

public interface SYS_USERMapper {
    int deleteByPrimaryKey(String userId);

    int insert(SYS_USER record);

    int insertSelective(SYS_USER record);

    SYS_USER selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(SYS_USER record);

    int updateByPrimaryKey(SYS_USER record);
}