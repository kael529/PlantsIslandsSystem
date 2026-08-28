-- 4. 待办事件表（提醒要做什么）
CREATE TABLE `garden_task` (
    `id` bigint PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `user_id` bigint NOT NULL COMMENT '用户ID（冗余字段，提升查询效率）',
    `garden_plant_id` bigint NOT NULL COMMENT '关联garden_plant.id',
    `task_type` tinyint NOT NULL COMMENT '任务类型：1浇水 2施肥 3修剪 4换盆 5喷药 6其他',
    `source` tinyint DEFAULT 1 COMMENT '来源：1系统生成 2用户手动添加',
    `title` varchar(128) NOT NULL COMMENT '任务标题（如：该给绿萝浇水了）',
    `description` varchar(255) DEFAULT '' COMMENT '任务描述',
    `due_date` date NOT NULL COMMENT '截止日期（哪天之前要做）截止日期=上次养护时间+植物的养护周期',
    `priority` tinyint DEFAULT 1 COMMENT '优先级：1低 2中 3高',
    `is_done` tinyint DEFAULT 0 COMMENT '是否完成：0未完成 1已完成',
    `done_time` datetime COMMENT '完成时间（仅当is_done从0→1时写入，记录用户点击“完成”的时刻）',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录修改时间（标题/截止日期/优先级等变更时更新）',
    `deleted` tinyint DEFAULT 0 COMMENT '软删除：0未删除 1已删除',

    INDEX idx_user_id (`user_id`),
    INDEX idx_garden_plant_id (`garden_plant_id`),
    INDEX idx_due_date (`due_date`),
    INDEX idx_is_done (`is_done`),

    -- 防止重复生成待办：同一盆花 + 同一任务类型 + 未完成，只能有一条
    UNIQUE KEY `uk_plant_task` (`garden_plant_id`, `task_type`, `is_done`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='待办事件表';