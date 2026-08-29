package com.zhiyu.plantsislandssystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("garden_plant")
public class GardenPlant {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;
    private Long plantId;
    private String nickname;
    private String coverImage;
    private LocalDate plantDate;
    private Integer status;
    private String location;
    private String note;
    private Integer userWaterCycleDays;
    private Integer userFertilizeCycleDays;
    private Integer userPestCycleDays;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @TableLogic
    private Integer deleted;
}
