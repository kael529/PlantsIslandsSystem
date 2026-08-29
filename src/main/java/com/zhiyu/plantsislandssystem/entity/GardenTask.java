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
@TableName("garden_task")
public class GardenTask {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;
    private Long gardenPlantId;
    private Integer taskType;
    private Integer source;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Integer priority;
    private Integer isDone;
    private LocalDateTime doneTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @TableLogic
    private Integer deleted;
}
