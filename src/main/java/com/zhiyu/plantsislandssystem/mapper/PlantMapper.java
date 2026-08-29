package com.zhiyu.plantsislandssystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhiyu.plantsislandssystem.entity.Plant;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlantMapper extends BaseMapper<Plant> {
}
