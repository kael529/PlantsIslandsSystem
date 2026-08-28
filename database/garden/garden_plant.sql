-- 1. 花园植物表（用户养了什么）
CREATE TABLE `garden_plant` (
    `id` bigint PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `plant_id` bigint NOT NULL COMMENT '植物ID（关联plant表）',
    `nickname` varchar(64) DEFAULT '' COMMENT '用户自定义昵称（如：小绿）',
    `cover_image` varchar(255) DEFAULT '' COMMENT '封面图URL',
    `plant_date` date COMMENT '入手/种植日期',
    `status` tinyint DEFAULT 1 COMMENT '状态：1生长中 2已开花 3已结果 4已枯萎 5已送人',
    `location` varchar(64) DEFAULT '' COMMENT '摆放位置（阳台/客厅/卧室）',
    `note` varchar(255) DEFAULT '' COMMENT '备注',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` tinyint DEFAULT 0 COMMENT '软删除：0未删除 1已删除',

    INDEX idx_user_id (`user_id`),
    INDEX idx_plant_id (`plant_id`),
    INDEX idx_status (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='花园植物表';

ALTER TABLE `garden_plant`
ADD COLUMN `user_water_cycle_days` int DEFAULT NULL COMMENT '用户自定义浇水周期',
ADD COLUMN `user_fertilize_cycle_days` int DEFAULT NULL COMMENT '用户自定义施肥周期',
ADD COLUMN `user_pest_cycle_days` int DEFAULT NULL COMMENT '用户自定义病虫害防治周期';