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
@TableName("plant")
public class Plant {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;
    private String alias;
    private String scientificName;
    private String family;
    private String genus;
    private String category;
    private String image;
    private Integer difficulty;
    private String light;
    private String water;
    private String temperature;
    private String soil;
    private String fertilizer;
    private String flowerpot;
    private String ventilation;
    private String pest;
    private String propagation;
    private Integer toxic;
    private String description;
    private String detail;
    private Integer waterCycleDays;
    private Integer fertilizerCycleDays;
    private Integer pestCycleDays;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @TableLogic
    private Integer deleted;
}
