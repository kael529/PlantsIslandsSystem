package com.zhiyu.plantsislandssystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("growth_record")
public class GrowthRecord {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long gardenPlantId;
    private String image;
    private String title;
    private String note;
    private LocalDateTime recordTime;
    private LocalDateTime createTime;

    @TableLogic
    private Integer deleted;
}
