-- 3. 养护记录表（做了什么）
CREATE TABLE `care_record` (
    `id` bigint PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `user_id` bigint NOT NULL COMMENT '用户ID（冗余字段，提升查询效率）',
    `garden_plant_id` bigint NOT NULL COMMENT '关联garden_plant.id',
    `care_type` tinyint NOT NULL COMMENT '养护动作类型：1浇水 2施肥 3修剪 4换盆 5喷药 6松土 7擦拭叶片 8其他',
    `care_time` datetime COMMENT '操作时间',
    `note` varchar(255) DEFAULT '' COMMENT '备注（如：浇了500ml水）',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `deleted` tinyint DEFAULT 0 COMMENT '软删除：0未删除 1已删除',

    INDEX idx_user_id (`user_id`),
    INDEX idx_garden_plant_id (`garden_plant_id`),
    INDEX idx_care_time (`care_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='养护记录表';