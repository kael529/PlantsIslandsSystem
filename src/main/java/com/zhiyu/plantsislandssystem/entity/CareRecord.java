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
@TableName("care_record")
public class CareRecord {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;
    private Long gardenPlantId;
    private Integer careType;
    private LocalDateTime careTime;
    private String note;
    private LocalDateTime createTime;

    @TableLogic
    private Integer deleted;
}
