-- 2. 成长记录表（照片 + 文字记录）
CREATE TABLE `growth_record` (
    `id` bigint PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `user_id` bigint NOT NULL COMMENT '用户ID（冗余字段，提升查询效率）',
    `garden_plant_id` bigint NOT NULL COMMENT '关联garden_plant.id',
    `image` varchar(255) NOT NULL COMMENT '照片URL',
    `title` varchar(64) DEFAULT '' COMMENT '标题（如：第一片新叶）',
    `note` varchar(500) DEFAULT '' COMMENT '描述（如：今天发现长新芽了！）',
    `record_time` datetime COMMENT '记录时间（用户拍摄/记录的时间）',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `deleted` tinyint DEFAULT 0 COMMENT '软删除：0未删除 1已删除',

    INDEX idx_user_id (`user_id`),
    INDEX idx_garden_plant_id (`garden_plant_id`),
    INDEX idx_record_time (`record_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='成长记录表';