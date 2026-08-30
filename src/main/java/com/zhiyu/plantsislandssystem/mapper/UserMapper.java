package com.zhiyu.plantsislandssystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhiyu.plantsislandssystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    //根据手机号查用户
    User selectUserByPhone(@Param("phone") String phone);

    //根据账号查用户
    User selectUserByNo(@Param("no") String no);

    //统计手机号出现的次数
    int countUserByPhone(@Param("phone") String phone);
}
